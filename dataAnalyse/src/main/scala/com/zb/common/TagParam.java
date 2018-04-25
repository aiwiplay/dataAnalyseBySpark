package com.zb.common;

import java.io.Serializable;

public class TagParam implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String name = "";
	private String url = "";
	private String tagname = "";
	
	/**
	 * @param name
	 * @param url
	 * @param tagname
	 */
	public TagParam(String name, String url, String tagname)
	{
		this.name = name;
		this.url = url;
		this.tagname = tagname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTagname() {
		return tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}
}
