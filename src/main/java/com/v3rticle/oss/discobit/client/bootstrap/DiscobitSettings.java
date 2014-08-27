package com.v3rticle.oss.discobit.client.bootstrap;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

/**
 * Settings for discoBit client. Retrieved from system properties and environment.
 * System properties have higher priority and will be looked up first.
 * 
 * @author jens@v3rticle.com
 *
 */
public class DiscobitSettings {
	
	Logger log = Logger.getLogger(DiscobitSettings.class.getName());

	private URL serverURL;
	private String apiVersion = "v1";
	private String defaultConfigurationUUID;
	
	public DiscobitSettings(){
		String dUrlEnv = System.getenv(DiscobitOptions.DISCOBIT_SERVER_URL);
		String dUrlSys = System.getProperty(DiscobitOptions.DISCOBIT_SERVER_URL);
		
		try {
			if (dUrlSys != null){
				log.info("[discobit] configuring server from system: " + dUrlSys);
					serverURL = new URL(dUrlSys);
			} else if (dUrlEnv != null){
				log.info("[discobit] configuring server from env: " + dUrlEnv);
				serverURL = new URL(dUrlEnv);
			} else {
				serverURL = new URL("http://discobit.v3rticle.com");	
			}
		} catch (MalformedURLException e) {
			log.info("[discobit] error configuring connector: "+ e.getMessage());
		}
		
		defaultConfigurationUUID = System.getProperty(DiscobitOptions.DISCOBIT_CONFIG_UUID);
		if (defaultConfigurationUUID == null){
			defaultConfigurationUUID = System.getenv(DiscobitOptions.DISCOBIT_CONFIG_UUID);
		}

	}

	public URL getServerURL() {
		return serverURL;
	}

	public void setServerURL(URL serverURL) {
		this.serverURL = serverURL;
	}

	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	public String getDefaultConfigurationUUID() {
		return defaultConfigurationUUID;
	}

	public void setDefaultConfigurationUUID(String defaultConfigurationUUID) {
		this.defaultConfigurationUUID = defaultConfigurationUUID;
	}

}
