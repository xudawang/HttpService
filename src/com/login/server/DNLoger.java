package com.login.server;

public class DNLoger {
	private String name = "";
	private String password = "";
	private String sex = "";
	private String career = "";
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	
	public void clear() {
		this.name = "";
		this.password = "";
		this.sex = "";
		this.career = "";
	}

}
