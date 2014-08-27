package com.v3rticle.oss.discobit.client.bootstrap;

/**
 * Option list for discoBit client.
 * @see also {@link DiscobitSettings}
 * @author jens@v3rticle.com
 */
public class DiscobitOptions {

	String[] optionList = new String[]{DiscobitOptions.DISCOBIT_CONFIG_UUID, DiscobitOptions.DISCOBIT_SERVER_URL, DiscobitOptions.DISCOBIT_LOCAL_CACHE_ENABLED};
	
	/**
	 * 
	 */
	public static final String DISCOBIT_SERVER_URL = "discobit.repository.url"; 
	
	
	/**
	 * 
	 */
	public static final String DISCOBIT_CONFIG_UUID = "discobit.cfg.uuid"; 
	
	/**
	 * 
	 */
	public static final String DISCOBIT_LOCAL_CACHE_ENABLED ="discobit.local.cache.enabled";
	
	/**
	 * 
	 */
	public static final String DISCOBIT_EAGER_LOADING ="discobit.bootstrap.eager.loading";
	
}
