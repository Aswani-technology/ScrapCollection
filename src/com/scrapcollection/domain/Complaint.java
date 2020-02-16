package com.scrapcollection.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor


public class Complaint {
	private int id;
	private ScrapCollectionRequest request;
	private int request_id;
	private String description;

	private String date;

	public Complaint(int id, ScrapCollectionRequest request, String description, String date) {
		super();
		this.id = id;
		this.request = request;
		this.description = description;
		this.date = date;
	}

	public Complaint(int id, int request_id, String description, String date) {
		super();
		this.id = id;
		this.request_id = request_id;
		this.description = description;
		this.date = date;
	}
	
	
}
