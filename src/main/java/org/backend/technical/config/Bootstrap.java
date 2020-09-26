package org.backend.technical.config;

import org.backend.technical.enums.UserType;
import org.backend.technical.model.User;
import org.backend.technical.service.UserManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {
	
	
	private static final Logger LOG = LoggerFactory.getLogger(Bootstrap.class);

	@Autowired
	private UserManagementService userManagementService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		LOG.info("******************** Start User Configuration ********************");
		
		String permissionLocatorAtms = "LOCATOR_ATMS";
		String permissionLocatorSearchAtms = "LOCATOR_SEARCH-ATMS";
		
		String permissionUserList = "USER_LIST";
		
		
		
		User user = new User();
		user.setFirstName("Piyush");
		user.setLastName("Chaudhari");
		user.setEmail("user@gmail.com");
		user.setPasswordHash(passwordEncoder.encode("000000"));
		user.addPermission(new SimpleGrantedAuthority(permissionLocatorAtms));
		user.addPermission(new SimpleGrantedAuthority(permissionLocatorSearchAtms));
		
		user.setUserType(UserType.USER);
		
		User adminUser = new User();
		adminUser.setFirstName("Shrikant");
		adminUser.setLastName("Khernar");
		adminUser.setEmail("admin@gmail.com");
		adminUser.setPasswordHash(passwordEncoder.encode("111111"));
		adminUser.addPermission(new SimpleGrantedAuthority(permissionLocatorAtms));
		adminUser.addPermission(new SimpleGrantedAuthority(permissionLocatorSearchAtms));
		adminUser.addPermission(new SimpleGrantedAuthority(permissionUserList));
		
		
		adminUser.setUserType(UserType.ADMIN);
		
		userManagementService.addUser(user);
		userManagementService.addUser(adminUser);
		
		LOG.info("******************** END User Configuration ********************");
	}

}
