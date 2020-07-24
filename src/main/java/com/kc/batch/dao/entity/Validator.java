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
	


}
