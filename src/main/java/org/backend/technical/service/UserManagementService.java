package org.backend.technical.service;

import java.util.List;

import org.backend.technical.model.User;

public interface UserManagementService {

	public void addUser(User user);
	
	public List<User> getUserList();
	
	public User findByEmail(String email);
	
	public User getLoggedInUser();
}
