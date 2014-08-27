package com.v3rticle.discobit.api.configuration;

import java.net.URL;

/**
 * The application space containing configurations.
 * @author jens@v3rticle.com
 *
 */
public interface Application {
	public long getId();
	public String getName();
	public void setName(String name);
	public URL getUrl();
	public void setUrl(URL url);
}
