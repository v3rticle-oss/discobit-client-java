package com.v3rticle.oss.discobit.client;

import java.net.URL;

import com.v3rticle.oss.discobit.client.bootstrap.DiscobitSettings;

/**
 * Factory for discoBit clients. Will return a singleton instance. Settings will be auto discovered.
 * If settings are provided a configured prototype will be returned.
 * @author jens@v3rticle.com
 *
 */
public class DiscobitClientFactory {

	private static DiscobitClient instance;
	
	/**
	 * gets the client instance (singleton)
	 * @return
	 */
	public static DiscobitClient getClient(){
		if (instance == null)
			instance = new DiscobitClient();
		return instance;
	}
	
	
	/**
	 * gets the client with manual configuration (prototype)
	 * @param serverURL
	 * @param repositoryUsername
	 * @param repositoryPassword
	 * @since 0.7
	 */
	public static DiscobitClient getClient(URL serverURL, String repositoryUsername, 
			String repositoryPassword) {
		DiscobitSettings settings = new DiscobitSettings(serverURL, repositoryUsername, repositoryPassword);
		DiscobitConnector connector = new DiscobitConnector(settings);
		return new DiscobitClient(connector);
	}
}
