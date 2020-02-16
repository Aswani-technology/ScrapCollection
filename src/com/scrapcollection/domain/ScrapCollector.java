package com.scrapcollection.domain;

import java.time.LocalDate;

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
@AllArgsConstructor
@EqualsAndHashCode

public class ScrapCollector {

	private int id;
	private String first_name;
	private String last_name;
	private String gender;
	 
	String email;
	String password;
	private String dob;
	private String address;
	private long contact;
	private String city;
	
	private Authority authority_id;
	private int availability_status;

}
