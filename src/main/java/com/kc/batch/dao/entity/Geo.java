package com.kc.batch.dao.entity;

import org.springframework.data.couchbase.core.mapping.Document;

@Document
public class Geo {
	
	private String accuracy;
	private String lat;
	private String lon;
	public String getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	
	

}
