package com.kc.batch.dao.entity;

import java.util.List;

public class Inventory {
	
	private List<Item> items;
	private String name;
	
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
