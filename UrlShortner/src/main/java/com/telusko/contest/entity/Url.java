package com.telusko.contest.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import java.sql.Timestamp;

@Entity
@Table(name = "urlshortner")
public class Url {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String short_url;
	private String long_url;

	private int access_count;

	@Generated
	private java.sql.Timestamp last_access_time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShort_url() {
		return short_url;
	}

	public void setShort_url(String short_url) {
		this.short_url = short_url;
	}

	public String getLong_url() {
		return long_url;
	}

	public void setLong_url(String long_url) {
		this.long_url = long_url;
	}

	public int getAccess_count() {
		return access_count;
	}

	public void setAccess_count(int access_count) {
		this.access_count = access_count;
	}

	public Timestamp getLast_access_time() {
		return last_access_time;
	}

	public void setLast_access_time(Timestamp last_access_time) {
		this.last_access_time = last_access_time;
	}

	@Override
	public String toString() {
		return "Url [id=" + id + ", shorturl=" + short_url + ", longurl=" + long_url + "]";
	}
	
	public Url(int id, String short_url, String long_url) {
		super();
		this.id = id;
		this.short_url = short_url;
		this.long_url = long_url;
	}

	public Url(String short_url, String long_url) {
		super();
		this.short_url = short_url;
		this.long_url = long_url;
	}
	
	public Url(String short_url, String long_url, int access_count, int id) {
		super();
		this.short_url = short_url;
		this.long_url = long_url;
		this.access_count = access_count;
		this.id = id;
	}
	public Url() {
		super();
	}
}
