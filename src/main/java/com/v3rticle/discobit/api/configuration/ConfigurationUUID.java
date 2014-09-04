package com.v3rticle.discobit.api.configuration;

import java.util.UUID;

/**
 * A unique identifier for configuration
 * @author jens@v3rticle.com
 *
 */
public class ConfigurationUUID {

	private UUID uuid;
	
	public ConfigurationUUID(){
		if (uuid == null)
			uuid = UUID.randomUUID();
	}
	
	public ConfigurationUUID(UUID uuid){
			this.uuid = uuid;
	}

	/**
	 * @return the uuid
	 */
	public UUID getUuid() {
		return uuid;
	}

	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	
	public static ConfigurationUUID create(){
		ConfigurationUUID uuid = new ConfigurationUUID();
		uuid.setUuid(UUID.randomUUID());
		return uuid;
	}
}
