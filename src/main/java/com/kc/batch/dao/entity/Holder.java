package com.kc.batch.dao.entity;

public class Holder {
	
	private Hotel source;
	private Hotel dest;
	
	public Hotel getSource() {
		return source;
	}
	public void setSource(Hotel source) {
		this.source = source;
	}
	public Hotel getDest() {
		return dest;
	}
	public void setDest(Hotel dest) {
		this.dest = dest;
	}
	
	public boolean compare() {
		return source.equals(dest);
	}

}
