package com.tourism.service;

public class Validation {
	public static boolean checkName(String name) {
		if (name.matches("[^0-9]"))
			return true;
		return false;
	}

	public static boolean checkPhone_Number(String phone_number) {
		if (phone_number.matches("0\\d{9}") && phone_number.length() == 10)
			return true;
		return false;
	}

	public static boolean checkEmail(String email) {
		if (email.matches("\\w+@\\w+[.]\\w+"))
			return true;
		return false;
	}
}
