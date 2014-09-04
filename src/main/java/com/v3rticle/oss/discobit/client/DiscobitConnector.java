package com.v3rticle.oss.discobit.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.v3rticle.oss.discobit.client.bootstrap.DiscobitSettings;
import com.v3rticle.oss.discobit.client.dto.ApplicationDTO;
import com.v3rticle.oss.discobit.client.dto.ConfigPropertyDTO;
import com.v3rticle.oss.discobit.client.dto.ConfigurationDTO;

/**
 * REST connector to discoBit repository API
 * See http://discobit.com/api for more information
 * 
 * @author jens@v3rticle.com
 *
 */
public class DiscobitConnector {

	Logger log = Logger.getLogger(DiscobitConnector.class.getName());
	
	private DiscobitSettings settings = new DiscobitSettings();
	
	private boolean authenticated;
	
	public DiscobitConnector(){
		authenticate();
	}
	
	/**
	 * do form authentication against rest interface, will get proper session cookie set in return
	 */
	private void authenticate(){
		URI serverAddress = null;
		try {
			serverAddress = new URI( settings.getServerURL() + "/rest/j_spring_security_check");
		} catch (URISyntaxException e) {
			log.log(Level.SEVERE, "[discobit] connector failed to authenticate:" + e.getMessage());
		}

	    URI loginUri = null;
		try {
			loginUri = new URIBuilder(serverAddress).setParameter("j_username", settings.getRepositoryUsername()).setParameter("j_password", settings.getRepositoryPassword()).build();
		} catch (URISyntaxException e) {
			log.log(Level.SEVERE, "[discobit] connector failed to authenticate:" + e.getMessage());
		}

	    RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.BEST_MATCH).build();
	    CookieStore cookieStore = new BasicCookieStore();
	    HttpClientContext context = HttpClientContext.create();
	    context.setCookieStore(cookieStore);

	    CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).setDefaultCookieStore(cookieStore).build();
	    HttpPost httpGet = new HttpPost(loginUri);
	    try {
			CloseableHttpResponse loginResponse = httpClient.execute(httpGet,context);
			log.info("[discobit] authentication response: " + loginResponse.getStatusLine());
		} catch (IOException e) {
			log.log(Level.SEVERE, "[discobit] connector failed to authenticate:" + e.getMessage());
		}

	    log.info("[discobit] authentication cookie: " + context.getCookieStore().getCookies());
		
		Unirest.setHttpClient(httpClient);
		this.authenticated = true;

	}
	
	/**
	 * check if a given configuration exists in repository
	 * @param cUUID
	 * @return
	 */
	public boolean checkConfiguration(String cUUID){
		// check cookie auth
		if (!authenticated){
			log.log(Level.SEVERE, "[discobit] connector not authenticated, returning");
			return false;
		}
		
		boolean configExists = false;
		try {
			String op = settings.getServerURL() + "/rest/" + settings.getApiVersion() + "/configuration/" + cUUID;
			
			GetRequest getReq = Unirest.get(op);
			if (getReq.asString().getBody() != null){
				try {
					new JSONObject(getReq.asString().getBody());
					HttpResponse<JsonNode> jsonResponse = getReq.asJson();
					configExists = jsonResponse.getBody().getObject().getString("uuid") != null;
				} catch (Exception e){
					log.log(Level.WARNING, "[discobit] connector failed to fetch configuration: " + cUUID + ", reason: " + e.getMessage());
				}
				
			} else {
				log.log(Level.WARNING, "[discobit] connector failed to fetch configuration: " + cUUID);
			}
		} catch (UnirestException e) {
			log.log(Level.SEVERE, "[discobit] connector failed to fetch configuration: " + e.getMessage());
		}
		
		return configExists;
	}
	
	
	/**
	 * creates a new configuration space for an application in the repository
	 * @param aDTO
	 * @return
	 */
	protected int createApplicationSpace(ApplicationDTO aDTO){
		// check cookie auth
		if (!authenticated){
			log.log(Level.SEVERE, "[discobit] connector not authenticated, returning");
			return -1;
		}
		
		int applicationSpaceId = -1;
		HttpResponse<JsonNode> response = null;
		try {
			String op = settings.getServerURL() + "/rest/" + settings.getApiVersion() + "/application";
			Object ob = JSONObject.wrap(aDTO);
			JsonNode node = new JsonNode(ob.toString());
			
			response = Unirest.post(op)
			.header("accept", "application/json")
			.header("Content-Type", "application/json")
			.body(node).asJson();
			
			applicationSpaceId = response.getBody().getObject().getInt("id");
		} catch (UnirestException e) {
			log.log(Level.SEVERE, "[discobit] connector failed to create application space: " + e.getMessage() + "\nResponse: " + response);
		}
		
		return applicationSpaceId;
	}
	
	
	/**
	 * creates a new configuration in the repository
	 * @param cDTO
	 * @return
	 */
	protected UUID createConfiguration(ConfigurationDTO cDTO, ApplicationDTO aDTO){
		// check cookie auth
		if (!authenticated){
			log.log(Level.SEVERE, "[discobit] connector not authenticated, returning");
			return null;
		}
		
		String configUUID = null;
		HttpResponse<JsonNode> response = null;
		try {
			String op = settings.getServerURL() + "/rest/" + settings.getApiVersion() + "/configuration/" + cDTO.getName();
			Object ob = JSONObject.wrap(aDTO);
			JsonNode node = new JsonNode(ob.toString());
			
			response = Unirest.post(op)
			.header("accept", "application/json")
			.header("Content-Type", "application/json")
			.body(node).asJson();
			configUUID = response.getBody().getObject().getString("uuid");
		} catch (UnirestException e) {
			log.log(Level.SEVERE, "[discobit] connector failed to fetch configuration: " + e.getMessage() + "\nResponse: " + response);
		}
		
		return UUID.fromString(configUUID);
	}
	
	/**
	 * creates a new key-value pair in repository
	 * @param cpDTO
	 * @param cDTO
	 * @return
	 */
	protected boolean createConfigProperty(ConfigPropertyDTO cpDTO, ConfigurationDTO cDTO){
		// check cookie auth
		if (!authenticated){
			log.log(Level.SEVERE, "[discobit] connector not authenticated, returning");
			return false;
		}
		
		boolean success = false;
		HttpResponse<JsonNode> response = null;
		try {
			String op = settings.getServerURL() + "/rest/" + settings.getApiVersion() + "/configuration/" + cDTO.getUuid() + "/" + cpDTO.getKey() + "/";
			
			Object ob = JSONObject.wrap(cpDTO);
			JsonNode node = new JsonNode(ob.toString());
			
			response = Unirest.post(op)
			.header("accept", "application/json")
			.header("Content-Type", "application/json")
			.body(node).asJson();
			
			success = true;
		} catch (UnirestException e) {
			log.log(Level.SEVERE, "[discobit] connector failed to create new config property: " + e.getMessage() + "\nResponse: " + response);
		}
		
		return success;
	}
	
	/**
	 * read a key-value pair from repository
	 * @param cUUID
	 * @param configParamKey
	 * @return
	 */
	protected String getConfigParamValue(String cUUID, String configParamKey){
		// check cookie auth
		if (!authenticated){
			log.log(Level.SEVERE, "[discobit] connector not authenticated, returning");
			return null;
		}
		
		String responseValue = null;
		try {
			String op = settings.getServerURL() + "/rest/" + settings.getApiVersion() + "/configuration/" + cUUID + "/" + configParamKey;
			
			GetRequest getReq = Unirest.get(op);
			if (getReq.asString().getBody() != null){
				try {
					new JSONObject(getReq.asString().getBody());
					HttpResponse<JsonNode> jsonResponse = getReq.asJson();
					responseValue = jsonResponse.getBody().getObject().getString("value");
				} catch (Exception e){
					log.log(Level.WARNING, "[discobit] connector failed to fetch configuration value: " + cUUID + "/" + configParamKey + ", reason: " + e.getMessage());
				}
				
			} else {
				log.log(Level.WARNING, "[discobit] connector failed to fetch configuration value: " + cUUID + "/" + configParamKey);
			}
		} catch (UnirestException e) {
			log.log(Level.SEVERE, "[discobit] connector failed to fetch configuration value: " + e.getMessage());
		}
		
		return responseValue;
	}
	
	protected Properties getConfiguration(String cUUID){
		// check cookie auth
		if (!authenticated){
			log.log(Level.SEVERE, "[discobit] connector not authenticated, returning");
			return null;
		}
		
		Properties confProps = new Properties();
		
		try {
			String op = settings.getServerURL() + "/rest/" + settings.getApiVersion() + "/configuration/" + cUUID;
			
			GetRequest getReq = Unirest.get(op);
			if (getReq.asString().getBody() != null){
				try {
					HttpResponse<JsonNode> jsonResponse = getReq.asJson();
					Iterator it = jsonResponse.getBody().getObject().getJSONObject("properties").keys();
					JSONObject a;
					while(it.hasNext()){
						String key = (String) it.next();
						a = jsonResponse.getBody().getObject().getJSONObject("properties").getJSONObject(key);
						confProps.put(key, a.getString("value"));
					}
				} catch (Exception e){
					log.log(Level.WARNING, "[discobit] connector failed to fetch configuration value: " + cUUID  + ", reason: " + e.getMessage());
				}
				
			} else {
				log.log(Level.WARNING, "[discobit] connector failed to fetch configuration value: " + cUUID);
			}
		} catch (UnirestException e) {
			log.log(Level.SEVERE, "[discobit] connector failed to fetch configuration value: " + e.getMessage());
		}
		
		return confProps;
	}
	
}
