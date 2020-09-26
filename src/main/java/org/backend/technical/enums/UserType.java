package org.backend.technical.enums;

public enum UserType {

	ADMIN("Admin"), USER("User");

	private String type;

	UserType(String code) {
		this.type = code;
	}

	public String getCode() {
		return type;
	}

	public static UserType fromTode(String userType) {
		for (UserType uType : UserType.values()) {
			if (uType.getCode().equals(userType)) {
				return uType;
			}
		}
		throw new UnsupportedOperationException("The code " + userType + " is not supported!");
	}
}
