package com.scrapcollection.data;

import java.util.List;

import com.scrapcollection.domain.Authority;

public interface AuthorityDao {
	
	public int saveAuthority(Authority authority);
	public boolean updateAuthority(Authority authority);
	public Authority findAuthorityById(int id);
	public List<Authority> findAllAuthority();
	public boolean deleteAuthority(int id);
	public Authority Login(String username, String password);
	
	public Authority findAuthorityByDistrict(String district);

}
