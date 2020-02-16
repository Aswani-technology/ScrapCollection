package com.scrapcollection.domain;

 
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor

@EqualsAndHashCode
public class ScrapCollectionRequest {

	private int id;

	private User user;
	private String date;

	private int status;

	private List<ScrapRequestList> scrapRequestList;

	public ScrapCollectionRequest(int id, User user, String date, int status, List<ScrapRequestList> scrapRequestList) {
		super();
		this.id = id;
		this.user = user;
		this.date = date;
		this.status = status;
		this.scrapRequestList = scrapRequestList;
	}

	public ScrapCollectionRequest(int id, User user, List<ScrapRequestList> scrapRequestList) {
		super();
		this.id = id;
		this.user = user;
		this.scrapRequestList = scrapRequestList;
	}

	 

}
