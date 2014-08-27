package com.v3rticle.discobit.api.configuration;


/**
 * Single configuration property.
 * @author jens@v3rticle.com
 *
 */
public interface ConfigProperty {
	/**
	 * @return the key
	 */
	public String getKey();

	/**
	 * @param key the key to set
	 */
	public void setKey(String key);

	/**
	 * @return the value
	 */
	public String getValue();

	/**
	 * @param value the value to set
	 */
	public void setValue(String value);

	/**
	 * @return the locked
	 */
	public boolean isLocked();

	/**
	 * @param locked the locked to set
	 */
	public void setLocked(boolean locked);

	/**
	 * @return the overrideAllowed
	 */
	public boolean isOverrideAllowed();

	/**
	 * @param overrideAllowed the overrideAllowed to set
	 */
	public void setOverrideAllowed(boolean overrideAllowed);

	/**
	 * @return the encrypted
	 */
	public boolean isEncrypted();

	/**
	 * @param encrypted the encrypted to set
	 */
	public void setEncrypted(boolean encrypted);
}
