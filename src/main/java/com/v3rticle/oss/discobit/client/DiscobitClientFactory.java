package com.v3rticle.oss.discobit.client;

/**
 * Factory for discoBit clients. Will return a singleton instance.
 * @author jens@v3rticle.com
 *
 */
public class DiscobitClientFactory {

	private static DiscobitClient instance;
	
	/**
	 * gets the client instance
	 * @return
	 */
	public static DiscobitClient getClient(){
		if (instance == null)
			instance = new DiscobitClient();
		
		return instance;
	}
}
