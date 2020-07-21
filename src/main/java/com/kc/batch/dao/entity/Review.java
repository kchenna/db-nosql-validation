package com.kc.batch.dao.entity;

import java.util.HashMap;

import org.springframework.data.couchbase.core.mapping.Document;


@Document
public class Review {
	private String author;
	private String content;
	private String date;
	private HashMap<String,Integer> ratings;
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public HashMap<String, Integer> getRatings() {
		return ratings;
	}
	public void setRatings(HashMap<String, Integer> ratings) {
		this.ratings = ratings;
	}
	
	
}