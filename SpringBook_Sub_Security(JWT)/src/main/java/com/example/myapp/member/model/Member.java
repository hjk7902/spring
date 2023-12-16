package com.example.myapp.member.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class Member {
	private String userid;
	private String name;
	private String password;
	private String password2;
	private String phone;
	private String email;
	private String role;
}
