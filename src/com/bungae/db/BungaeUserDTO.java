package com.bungae.db;

public class BungaeUserDTO {

	private int num;
	private int mmNum;
	private int bgNum;
	private String userName;
	private String userPhoto;
	
	public int getMmNum() {
		return mmNum;
	}
	public void setMmNum(int mmNum) {
		this.mmNum = mmNum;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getBgNum() {
		return bgNum;
	}
	public void setBgNum(int bgNum) {
		this.bgNum = bgNum;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhoto() {
		return userPhoto;
	}
	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}
	
	
}
