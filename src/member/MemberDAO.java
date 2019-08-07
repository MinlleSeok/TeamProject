package member;

import java.sql.*;
import java.util.ArrayList;

import javax.naming.*;
import javax.sql.DataSource;

public class MemberDAO {
	
//*********** getConn()생성 : 커넥션풀로 부터 커넥션 객체con을 만들기 위한 메소드. 	
	private Connection getConn() throws Exception{
	
		Connection con = null;
		Context init = new InitialContext();

		// 커넥션 풀 얻기(context.xml파일의 <Resource> 태그의 name정보로 가져옴)
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/vanco");
		con = ds.getConnection();
		return con;
	}// getConn() 종료
	

//*********** snsLoginIdChk() 생성 : 회원 로그인 메소드
	// 리턴값 : true(아이디없음), false(아이디있음)
public boolean snsLoginIdChk(String userId) {
		
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	boolean idChk=false;
	
	try {
		con = getConn();
		sql="select * from vancomember where userId=?";
		pstmt=con.prepareStatement(sql);			
		
		pstmt.setString(1,userId);
		rs=pstmt.executeQuery();	
		
		if(rs.next()){//id값이 있으면(true) => idChk=false
			idChk=true;
		}			
	} catch (Exception e) {
		System.out.println("snsLoginIdChk에러"+e);
	}finally{
		if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
		if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
		if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}	
	}		
	return  idChk;
} //snsLoginIdChk() 종료
	
//*********** snsUserLogin() 생성 : SNS회원 로그인 메소드
	// 리턴값 : 로그인성공(1), 비번틀림(0), ID없음(-1)
	public int snsUserLogin(String userId) {
	int logResult=-1;
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	
	try {
		con = getConn();
		sql="select * from vancomember where userId=?";
		pstmt=con.prepareStatement(sql);			
		
		pstmt.setString(1,userId);
		rs=pstmt.executeQuery();	
		
		if(rs.next()){
			
			logResult=1;
		}			
	} catch (Exception e) {
		System.out.println("snsUserLogin()에러"+e);
	}finally{
		if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
		if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
		if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}	
	}		
	return  logResult;
	} //snsUserLogin() 종료


	
//*********** snsUserJoin() 생성 : 회원 가입 메소드	
	public int snsUserJoin(MemberDTO mdto){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		int logResult=-1;
		
		try {
			con=getConn();
			sql="insert into vancoMember(userId, userPwd, userNickname,joinDate,userPhoto) values(?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			
			// ?값 셋팅
			pstmt.setString(1,mdto.getUserId());
			pstmt.setString(2,mdto.getUserPwd());
			pstmt.setString(3,mdto.getUserNickname());
			pstmt.setTimestamp(4,mdto.getJoinDate());
			pstmt.setString(5,mdto.getUserPhoto());
			
			System.out.println(mdto.getUserId());
			
			/*if(mdto.getUserGender().equals("남")){
			pstmt.setString(6,"/vanco/imageProfile/defaultMan.jpg");
			userPhoto="/vanco/imageProfile/defaultMan.jpg";
			}else{
			pstmt.setString(6,"/vanco/imageProfile/defaultWoman.jpg");
			userPhoto="/vanco/imageProfile/defaultWoman.jpg";
			}*/
			
			// 쿼리 실행
			pstmt.executeUpdate();	
			pstmt.close();
			
			//회원가입 성공 후 바로 로그인 시키기			
			sql = "select userPwd from vancoMember where userId=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,mdto.getUserId());
			rs=pstmt.executeQuery();			
			
			if(rs.next()){//id값이 있으면(true) => 비번 비교하는 로그인 검사 실행
				if(mdto.getUserPwd().equals(rs.getString("userPwd"))){
					logResult=1;
				}else{
					logResult=0;
				}
			}else{
				logResult=-1;
			}					
		} catch (Exception e) {
			System.out.println("snsUserJoin()오류"+e);
			e.printStackTrace();
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}
			if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}	
		}
		return logResult;
	} // snsUserJoin() 종료

	
	//*********** getUserInfo() 생성 :(사용자 id로) myPage에서 모든 user정보 가져옴., 그외 session정보로만 부족한 개인 정보 필요할때 당겨씀
	// 로그인시에는 id값만 세션에 저장함, 사진/닉네임/나이 등정보는 여기서 끌고가기.

public MemberDTO getUserInfo(String userId){
	
	Connection con = null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;
	String sql = "";
	MemberDTO mdto=new MemberDTO();
				
	try {
		con = getConn();
		sql="select * from vancomember where userId=?";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1,userId);
		rs=pstmt.executeQuery();
		
		while(rs.next()){
			mdto=new MemberDTO();
			
			mdto.setUserNum(rs.getInt("userNum"));
			mdto.setUserId(rs.getString("userId"));
			mdto.setUserNickname(rs.getString("userNickname"));
			mdto.setUserPwd(rs.getString("userPwd"));
			mdto.setUserEmail(rs.getString("userEmail"));
			mdto.setUserGender(rs.getString("userGender"));	
			mdto.setUserDistrict1(rs.getString("userDistrict1"));
			mdto.setUserDistrict2(rs.getString("userDistrict2"));
			mdto.setUserBirth(rs.getInt("userBirth"));						
			mdto.setJoinDate(rs.getTimestamp("joinDate"));
			mdto.setUserIp(rs.getString("userIp"));
			mdto.setUserPhoto(rs.getString("userPhoto"));	
		}			
	} catch (Exception e) {
		System.out.println("getUserInfo()오류"+e);
	}finally{
		if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
		if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
		if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}
	}		
	return mdto;
} //getUserInfo() 종료
	
	
} //DAO 클래스 종료
