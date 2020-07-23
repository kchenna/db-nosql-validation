package com.kc.batch.dao.entity;

import java.util.List;

//import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;

@Document
public class Hotel {

	private String address;
	private String alias;
	private String checkin;
	private String checkout;
	private String city;
	private String country;
	private String description;
	private String directions;
	private String email;
	private String fax;
	private boolean free_breakfast;
	
	public Geo getGeo() {
		return geo;
	}
	public void setGeo(Geo geo) {
		this.geo = geo;
	}
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	private boolean free_internet;
	private boolean free_parking;
	private Geo geo;
	
	@Id
	@Field (value="ID")
	@GeneratedValue (strategy = GenerationStrategy.UNIQUE)
	private String id;
	
	 private String name;
	private boolean pets_ok;
	private String phone;
	private List<String> public_likes;
	public List<Review> reviews;
	private String state;
	private String title;
	private String tollfree;
	private String type;
	private String url;
	private boolean vacancy;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getCheckin() {
		return checkin;
	}
	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}
	public String getCheckout() {
		return checkout;
	}
	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDirections() {
		return directions;
	}
	public void setDirections(String directions) {
		this.directions = directions;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public boolean isFree_breakfast() {
		return free_breakfast;
	}
	public void setFree_breakfast(boolean free_breakfast) {
		this.free_breakfast = free_breakfast;
	}
	public boolean isFree_internet() {
		return free_internet;
	}
	public void setFree_internet(boolean free_internet) {
		this.free_internet = free_internet;
	}
	public boolean isFree_parking() {
		return free_parking;
	}
	public void setFree_parking(boolean free_parking) {
		this.free_parking = free_parking;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isPets_ok() {
		return pets_ok;
	}
	public void setPets_ok(boolean pets_ok) {
		this.pets_ok = pets_ok;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<String> getPublic_likes() {
		return public_likes;
	}
	public void setPublic_likes(List<String> public_likes) {
		this.public_likes = public_likes;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTollfree() {
		return tollfree;
	}
	public void setTollfree(String tollfree) {
		this.tollfree = tollfree;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isVacancy() {
		return vacancy;
	}
	public void setVacancy(boolean vacancy) {
		this.vacancy = vacancy;
	}
		
	
	
}
