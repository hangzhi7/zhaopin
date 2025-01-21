package com.kai.recruit.model;

public class ManagerModel {
	private int managerId;
	private String mobile;
	private String password;
	private String managerName;
	private String email;

	public int getManagerId() {
		System.out.println(this.managerId);
		System.out.println(this.mobile);
		return this.managerId;
	}

	public void setManagerId(int ManagerId) {
		this.managerId=managerId;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile=mobile;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password=password;
	}

	public String getManagerName() {
		return this.managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName=managerName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email=email;
	}
}