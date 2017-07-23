package com.sreepapers.app.web.services;

import java.util.List;

import com.sreepapers.app.web.model.User;

public interface UserService {

	public void addUser(User user);
	public void updateUser(User user);
	public List<User> listUsers();
	public User getUserById(int id);
	public void removeUser(int id);
}
