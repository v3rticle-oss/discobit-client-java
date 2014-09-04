package com.v3rticle.oss.discobit.client.dto;


/**
 * DTO for config properties.
 * @author jens@v3rticle.com
 *
 */
public class ConfigPropertyDTO {

	private String key;
	private String value;
	private String comment;
	private boolean locked = false;
	private boolean overrideAllowed = true;
	private boolean encrypted = false;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	public boolean isOverrideAllowed() {
		return overrideAllowed;
	}
	public void setOverrideAllowed(boolean overrideAllowed) {
		this.overrideAllowed = overrideAllowed;
	}
	public boolean isEncrypted() {
		return encrypted;
	}
	public void setEncrypted(boolean encrypted) {
		this.encrypted = encrypted;
	}
	
}
