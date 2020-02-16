package com.scrapcollection.service;

import java.util.List;

import com.scrapcollection.domain.Authority;

public interface AuthorityService {
	
	public int saveAuthority(Authority Authority);

	public boolean updateAuthority(Authority Authority);

	public Authority findAuthorityById(int id);

	public List<Authority> findAllAuthority();

	public boolean deleteAuthority(int id);
	
	public Authority login(String uname, String pword);

}
