package com.sreepapers.app.web.dao;

import java.util.List;

import com.sreepapers.app.web.model.User;

public interface UserDAO {
	
	public void addUser(User user);
	public void updateUser(User user);
	public List<User> listUsers();
	public User getUserById(int id);
	public void removeUser(int id);
}
