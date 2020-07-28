package com.kc.batch.dao.entity;

import java.util.ArrayList;
import java.util.List;

public class Validator {
	
	private List<Hotel> validator = new ArrayList<Hotel>();

	public List<Hotel> getValidator() {
		return validator;
	}

	public void setValidator(List<Hotel> validator) {
		this.validator = validator;
	}
	
	public void addAll(List<Hotel> validator) {
		this.validator.addAll(validator);
	}
	
	private long startTime ;
	private long endTime;
	
	public void setStartTime(long st) {
		startTime = st;
	}
	
	public long getStartTime() {
		return startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long st) {
		endTime = st;
	}
	
}
