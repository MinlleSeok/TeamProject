package member;

import java.sql.Timestamp;

//DTO 클래스의 목적
	//1.회원가입 정보를 각 변수에 저장하여 DB에 insert 용도의 클래스
	//2.DB로 부터 회원정보를 검색한 후 검색한 데이터를 저장할 용도의 클래스

public class MemberDTO {
	
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

	
	

}
