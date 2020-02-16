package com.scrapcollection.service;

import java.util.List;

import com.scrapcollection.domain.User;

public interface UserService {

	
	public int saveUser(User user);

	public boolean updateUser(User user);

	public User findUserById(int id);

	public List<User> findAllUser();

	public boolean deleteUser(int id);
	
	public User login(String email, String password);

}
