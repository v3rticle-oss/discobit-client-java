package com.v3rticle.oss.discobit.client.dto;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * DTO for configurations.
 * @author jens@v3rticle.com
 *
 */public class ConfigurationDTO {

	Map<String, ConfigPropertyDTO> properties = new ConcurrentHashMap<String, ConfigPropertyDTO>();
	
	private String name;
	private String description;
	private String uuid;
	public Map<String, ConfigPropertyDTO> getProperties() {
		return properties;
	}
	public void setProperties(Map<String, ConfigPropertyDTO> properties) {
		this.properties = properties;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	
}
