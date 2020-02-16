package com.scrapcollection.service_impl;

import java.util.List;

import com.scrapcollection.data.UserDao;
import com.scrapcollection.data_impl.UserDaoImpl;
import com.scrapcollection.domain.User;
import com.scrapcollection.service.UserService;

public class UserServiceImpl implements UserService{

	UserDao dao=new UserDaoImpl();

	@Override
	public int saveUser(User User) {
		// TODO Auto-generated method stub
		return dao.saveUser(User);
	}

	@Override
	public boolean updateUser(User User) {
		// TODO Auto-generated method stub
		return dao.updateUser(User);
	}

	@Override
	public User findUserById(int id) {
		// TODO Auto-generated method stub
		return dao.findUserById(id);
	}

	@Override
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		return dao.findAllUser();
	}

	@Override
	public boolean deleteUser(int id) {
		// TODO Auto-generated method stub
		return dao.deleteUser(id);
	}

	@Override
	public User login(String email, String password) {
		// TODO Auto-generated method stub
		return dao.login(email, password);
	}
}
