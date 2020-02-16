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
public class Authority {

	private int id;
	private String name;
	private String username;
	private String password;
	private String email;
	private String city;
	private String district;
	private Long contact;
	
}
