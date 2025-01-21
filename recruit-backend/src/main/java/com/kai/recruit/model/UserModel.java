package com.kai.recruit.model;

public class UserModel{
	private int userId;
	private String user;
	private String avatar;
	private String userName;
	private String mobile;
	private String password;
	private String nickname;
	private String email;
	private int gender;	//true: male; false: female
	private int birthYear;
	private String graduation;
	private String eduDegree;
	private String major;
	private int graduateYear;
	private int dirDesire;	//??
	private String province;
	private String city;

	public int getUserId() {return this.userId;}

	public void setUserId(int userId) {this.userId=userId;}

	public String getUser() {return this.user;}

	public  void setUser(String user) {this.user = user;}

	public String getName() {return this.userName;}

	public void setName(String userName) {this.userName=userName;}

	public String getAvatar() {return this.avatar;}

	public void setAvatar(String avatar) {this.avatar=avatar;}

	public String getMobile() {return this.mobile;}

	public void setMobile(String mobile) {this.mobile=mobile;}

	public String getPassword() {return this.password;}

	public void setPassword(String password) {this.password=password;}

	public String getNickname() {return this.nickname;}

	public void setNickname(String nickname) {this.nickname=nickname;}

	public String getEmail() {return this.email;}

	public void setEmail(String email) {this.email=email;}

	public int getGender() {return this.gender;}

	public void setGender(int gender) {this.gender=gender;}

	public int getBirthYear() {return this.birthYear;}

	public void setBirthYear(int birthYear) {this.birthYear=birthYear;}

	public String getGraduation() {return this.graduation;}

	public void setGraduation(String graduation) {this.graduation=graduation;}

	public String getEduDegree() {return this.eduDegree;}

	public void setEduDegree(String eduDegree) {this.eduDegree=eduDegree;}

	public String getMajor() {return this.major;}

	public void setMajor(String major) {this.major=major;}

	public int getGraduateYear() {return this.graduateYear;}

	public void setGraduateYear(int graduateYear) {this.graduateYear=graduateYear;}

	public int getDirDesire() {return this.dirDesire;}

	public void setDirDesire(int dirDesire) {this.dirDesire=dirDesire;}

	public String getProvince() {return this.province;}

	public void setProvince(String province) {this.province=province;}

	public String getCity() {return this.city;}

	public void setCity(String city) {this.city=city;}
}