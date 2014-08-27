package com.v3rticle.discobit.api.configuration;

import java.util.Collection;
import java.util.Map;

/**
 * A collection of configuration properties. Assigned to an application 
 * @author Jens Saade
 *
 */
public interface Configuration {

	/**
	 * (database id)
	 * @return
	 */
	public long getId();
	
	/**
	 * @return the properties
	 */
	public Map<String, ConfigProperty> getPropertyMap();
	
	/**
	 * @return ConfigProperty Collection
	 */
	public Collection<ConfigProperty> getProperties();

	/**
	 * @param properties the properties to set
	 */
	public void setProperties(Collection<ConfigProperty> properties);

	/**
	 * @return
	 */
	public ConfigurationUUID getUuid();

	/**
	 * @param uuid
	 */
	void setUuid(ConfigurationUUID uuid);
	
	
}
