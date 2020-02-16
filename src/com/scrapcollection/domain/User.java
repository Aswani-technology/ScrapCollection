package com.scrapcollection.domain;

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

public class User {
	
	private int id;
	private String first_name;
	private String last_name;
	private String gender;
	private String email;
	private String password;
	private String address;
	private Long contact;
	private String place;
	private  String house_no;
	private int ward_no;
	private String panchayath;
	private String district;
	 

}
