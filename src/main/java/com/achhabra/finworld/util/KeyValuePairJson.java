package com.achhabra.finworld.util;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class KeyValuePairJson {
	
	public KeyValuePairJson(String id, String value) {
		super();
		this.id = id;
		this.value = value;
	}

	public KeyValuePairJson(int id, String value) {
		super();
		this.id = String.valueOf(id);
		this.value = value;
	}

	private String id;
	
	private String value;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	

}
