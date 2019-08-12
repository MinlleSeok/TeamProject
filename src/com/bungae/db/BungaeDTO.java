package com.bungae.db;

import java.sql.Date;

public class BungaeDTO {
	private int num;
	private int mmNum;
	private String subject;
	private String content;
	private Date bdate;
	private int max;
	private int pin;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getMmNum() {
		return mmNum;
	}
	public void setMmNum(int mmNum) {
		this.mmNum = mmNum;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public Date getBdate() {
		return bdate;
	}
	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	
	
	
}
