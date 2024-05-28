package com.alikaddoura.model;

public class User {
	
	private int id;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	
	public User(int id, String firstName, String lastName, String userName, String password) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName= userName;
		this.password = password;
	
	}
	
	
		
	

	public static void main(String[] args) {
		User me = new User(1, "ali", "kaddoura","alikaddoura", "1100");
		
		System.out.println(me.firstName);
			
		

	}

}
