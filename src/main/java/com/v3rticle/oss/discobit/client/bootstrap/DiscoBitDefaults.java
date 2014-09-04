package com.v3rticle.oss.discobit.client.bootstrap;

import java.util.HashMap;

/**
 * discoBit client defaults.
 * @author jens@v3rticle.com
 *
 */
public class DiscoBitDefaults extends HashMap<String, Object>{

	private static final long serialVersionUID = 1L;

	public DiscoBitDefaults(){
		put(DiscobitOptions.DISCOBIT_LOCAL_CACHE_ENABLED, true);
		put(DiscobitOptions.DISCOBIT_EAGER_LOADING, true);
	}
}
