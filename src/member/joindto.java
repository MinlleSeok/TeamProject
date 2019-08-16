package member;

import java.sql.Timestamp;

public class joindto {
	private int userNum;
	private String userId;
	private String userPwd;
	private String userEmail;
	private String userNickname;
	private String userGender;
	private String userDistrict1;
	private String userDistrict2;
	private int userBirth;
	private Timestamp joinDate;
	private String userIp;
	private String userPhoto;
	private String userName;
	private int userLevel;
	
	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getUserDistrict1() {
		return userDistrict1;
	}
	public void setUserDistrict1(String userDistrict1) {
		this.userDistrict1 = userDistrict1;
	}
	public String getUserDistrict2() {
		return userDistrict2;
	}
	public void setUserDistrict2(String userDistrict2) {
		this.userDistrict2 = userDistrict2;
	}
	public int getUserBirth() {
		return userBirth;
	}
	public void setUserBirth(int userBirth) {
		this.userBirth = userBirth;
	}
	public Timestamp getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Timestamp joinDate) {
		this.joinDate = joinDate;
	}
	public String getUserIp() {
		return userIp;
	}
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	public String getUserPhoto() {
		return userPhoto;
	}
	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}
	public String getUserText() {
		return userText;
	}
	public void setUserText(String userText) {
		this.userText = userText;
	}
	public int getLevel() {
		return Level;
	}
	public void setLevel(int level) {
		Level = level;
	}
	private String userText;
	private int Level;
}
