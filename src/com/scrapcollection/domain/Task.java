package com.scrapcollection.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@NoArgsConstructor
@Getter
@Setter
@ToString


@EqualsAndHashCode

public class Task {


	private int id;
	private User user;
	private ScrapCollectionRequest request;
	private ScrapCollector scrapCollector;

	private String date;
	private String task_completed_date;
	private int status;
	
	
	public Task(int id, ScrapCollectionRequest request) {
		
		this.id=id;
		this.request = request;
	}
	
	
	public Task(int id,User user,String date,int status){
		this.id=id;
		this.user= user;
		this.date = date;
		this.status = status;
	}

	public Task(int id, ScrapCollectionRequest request, ScrapCollector scrapCollector, String date,
			String task_completed_date, int status) {
		super();
		this.id = id;
		this.request = request;
		this.scrapCollector = scrapCollector;
		this.date = date;
		this.task_completed_date = task_completed_date;
		this.status = status;
		
	}


	
}
