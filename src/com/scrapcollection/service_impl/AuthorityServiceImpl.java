package com.scrapcollection.service_impl;

import java.util.List;
import com.scrapcollection.data.AuthorityDao;
import com.scrapcollection.data_impl.AuthorityDaoImpl;
import com.scrapcollection.domain.Authority;
import com.scrapcollection.service.AuthorityService;

public class AuthorityServiceImpl  implements AuthorityService{

	AuthorityDao dao=new AuthorityDaoImpl();

	@Override
	public int saveAuthority(Authority Authority) {
		// TODO Auto-generated method stub
		return dao.saveAuthority(Authority);
	}

	@Override
	public boolean updateAuthority(Authority Authority) {
		// TODO Auto-generated method stub
		return dao.updateAuthority(Authority);
	}

	@Override
	public Authority findAuthorityById(int id) {
		// TODO Auto-generated method stub
		return dao.findAuthorityById(id);
	}

	@Override
	public List<Authority> findAllAuthority() {
		// TODO Auto-generated method stub
		return dao.findAllAuthority();
	}

	@Override
	public boolean deleteAuthority(int id) {
		// TODO Auto-generated method stub
		return dao.deleteAuthority(id);
	}

	@Override
	public Authority login(String uname, String pword) {
		// TODO Auto-generated method stub
		return dao.Login(uname, pword);
	}
}
