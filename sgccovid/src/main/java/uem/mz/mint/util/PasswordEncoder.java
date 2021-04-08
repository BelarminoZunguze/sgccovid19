package uem.mz.mint.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

	public static void main(String[] args) {

		int i = 0;
		while (i < 10) {
			String password = "123456";
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(password);
			System.out.println(hashedPassword);
			match("0000", "$2a$10$HjOTBkTKJjD00IUGvlRd8.jjvbf9buXpRV0me0BxbiHhqGu.aCtC6");
			i++;
		}
	}

	public static String encode(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}

	public static void match(String password, String hashedPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if (passwordEncoder.matches(password, hashedPassword))
			System.out.println("mesmas");
		else
			System.err.println("diferentes");
	}

}
