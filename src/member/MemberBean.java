package member;

import java.sql.Timestamp;

//1.회원가입할 정보를 각변수에 저장하여 DB에  insert할 용도의 클래스
//2.DB로부터 회원정보를 검색한후 검색한 데이터를 저장할 용도의 클래스 
public class MemberBean {//DTO
	//변수 
	 private String id;
	 private String passwd;
	 private String name;
	 private Timestamp reg_date;
	 private int age;
	 private String gender;   
	 private String email;   
	 private String address;  
	 private String tel;      
	 private String mtel;    
	 
	//getter,setter 메소드 
	//alt + shift + s   r
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMtel() {
		return mtel;
	}
	public void setMtel(String mtel) {
		this.mtel = mtel;
	}
	

}





