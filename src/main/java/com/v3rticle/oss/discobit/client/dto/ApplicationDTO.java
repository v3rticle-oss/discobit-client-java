package com.v3rticle.oss.discobit.client.dto;

import java.util.Date;
import java.util.HashMap;

/**
 * DTO for application spaces.
 * @author jens@v3rticle.com
 *
 */
public class ApplicationDTO {

	private long id;
	private String name;
	private String email;
	private String owner;
	private Date created;
	private Date modified;
	private String url;
	private HashMap<String, String> configurations = new HashMap<String, String>();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	public HashMap<String, String> getConfigurations() {
		return configurations;
	}
	public void setConfigurations(HashMap<String, String> configurations) {
		this.configurations = configurations;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
