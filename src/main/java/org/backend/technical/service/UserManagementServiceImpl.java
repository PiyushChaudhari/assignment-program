package org.backend.technical.service;

import java.util.ArrayList;
import java.util.List;

import org.backend.technical.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserManagementServiceImpl implements UserManagementService {

	private List<User> userList = new ArrayList<>();

	@Override
	public void addUser(User user) {
		this.userList.add(user);
	}

	@Override
	public List<User> getUserList() {
		return userList;
	}

	@Override
	public User findByEmail(String email) {
		return userList.stream().filter( user -> email.equals(user.getEmail())).findFirst().orElse(null);
	}

	@Override
	public User getLoggedInUser() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	
}
