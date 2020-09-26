package org.backend.technical.model;

import java.util.Collection;
import java.util.HashSet;

import org.backend.technical.enums.UserType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails {

	private static final long serialVersionUID = 3656113704247149060L;

	private String firstName;
	private String lastName;
	private String email;
	private String passwordHash;
	private UserType userType;
	private Collection<GrantedAuthority> permission = new HashSet<>();

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public void addPermission(GrantedAuthority authority) {
		this.permission.add(authority);
	}
	
	public Collection<GrantedAuthority> getPermission() {
		return permission;
	}

	public void setPermission(Collection<GrantedAuthority> permission) {
		this.permission = permission;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.permission;
	}

	@Override
	public String getPassword() {
		return this.getPasswordHash();
	}

	@Override
	public String getUsername() {
		return this.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public boolean equals(Object object) {
		if (object instanceof User) {
			return getUsername().equals(((User) object).getUsername());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return getUsername().hashCode();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", email=");
		builder.append(email);
		builder.append(", passwordHash=");
		builder.append(passwordHash);
		builder.append(", userType=");
		builder.append(userType);
		builder.append("]");
		return builder.toString();
	}
}
