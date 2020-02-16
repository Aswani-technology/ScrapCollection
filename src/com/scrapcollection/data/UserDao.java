package com.scrapcollection.data;

import java.util.List;

import com.scrapcollection.domain.User;

public interface UserDao {
	
	public int saveUser(User User);
	public boolean updateUser(User scarpCollector);
	public User findUserById(int id);
	public List<User> findAllUser();
	public boolean deleteUser(int id);
	public User login(String email, String password);


}
