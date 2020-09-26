package org.backend.technical.service;

import org.backend.technical.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("secureUserDetailsService")
public class SecureUserServiceImpl implements UserDetailsService{

	@Autowired
	private UserManagementService userManagementService; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userManagementService.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Username not found");
		}
		return user;
	}

}
