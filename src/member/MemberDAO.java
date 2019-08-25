package member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.sql.DataSource;


public class MemberDAO {
	
//*********** getConn()생성 : 커넥션풀로 부터 커넥션 객체con을 만들기 위한 메소드. 	
	private Connection getConn() throws Exception{
	
		Connection con = null;
		Context init = new InitialContext();

		// 커넥션 풀 얻기(context.xml파일의 <Resource> 태그의 name정보로 가져옴)
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/mmpjt");
		con = ds.getConnection();
		return con;
	}// getConn() 종료
	
	// 권한 부여 및 강퇴 기능
	// 0-> 권한 x (추방)
	// 1-> 접근 권한 생김 / 2-> 강퇴 권한 생김(level 3 은 강퇴 불가) / 3 -> 마스터(level2 지정 가능)
	// level 2와 level 3는 회원 정보 열람 가능
	// Memberlevel은 member 함수에 level 항목을 추가하여 수정을 통해 가능하도록 설정.
	// 정보 열람 시 모든 정보
	
	// moim은 쿼리가 자동으로 여러가지 개설 됨  member 항목에 가입된 모임이 적혀잇어야함.
	// member 테이블에 각각의 모임마다 level이 지정됨
	
	 
	
	
	// Level 함수 사용 위한 if 문
	/*
	  
	  if(MemberLevel.equals("3")){
	  	// 멤버 레벨 체크박스 모두 
	  
	  
	  
	  
	  
	 
	 */
	
	public int getMemberCount1(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		//DB로부터 검색한 전체글개수를 저장할 변수 
		int count1 = 0;
		
		try {
			//커넥션풀로부터 커넥션 얻기
			con = getConn();//DB접속
			//전체 글개수 검색 SQL문 
			sql = "select count(*) from moimuser where enter=0";
			//SQL문을 실행할 실행 객체 얻기
			pstmt = con.prepareStatement(sql);
			//SQL문을 실행하여 검색한 글의 갯수 정보를  ResultSet에 저장하여 
			//ResultSet을 반환
			rs = pstmt.executeQuery();
			//ResultSet객체 내부에 검색한 글의 갯수 정보가 존재하면?
			if(rs.next()){
				//검색한 글의 갯수 정보를 count변수에 저장
				count1 = rs.getInt(1);						
			}		
		} catch (Exception e) {
			System.out.println("getMemberCount1()메소드 내부 오류 : " + e);
		} finally {
			//자원해제
			if(rs != null){try{rs.close();}catch(Exception err){err.printStackTrace();}}
			if(pstmt != null){try{pstmt.close();}catch(Exception err){err.printStackTrace();}}
			if(con != null){try{con.close();}catch(Exception err){err.printStackTrace();}}							
		}	
		return count1;//검색한 글의 갯수 정보 반환
	}
	
	
	//Member list로 변경하기
	// select 문에서는 자기 가입된 모임의 level 1 이상만 select 되도록 표시.
	public int getMemberCount(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		//DB로부터 검색한 전체글개수를 저장할 변수 
		int count = 0;
		
		try {
			//커넥션풀로부터 커넥션 얻기
			con = getConn();//DB접속
			//전체 글개수 검색 SQL문 
			sql = "select count(*) from moimuser where enter=1";
			//SQL문을 실행할 실행 객체 얻기
			pstmt = con.prepareStatement(sql);
			//SQL문을 실행하여 검색한 글의 갯수 정보를  ResultSet에 저장하여 
			//ResultSet을 반환
			rs = pstmt.executeQuery();
			//ResultSet객체 내부에 검색한 글의 갯수 정보가 존재하면?
			if(rs.next()){
				//검색한 글의 갯수 정보를 count변수에 저장
				count = rs.getInt(1);						
			}		
		} catch (Exception e) {
			System.out.println("getMemberCount()메소드 내부 오류 : " + e);
		} finally {
			//자원해제
			if(rs != null){try{rs.close();}catch(Exception err){err.printStackTrace();}}
			if(pstmt != null){try{pstmt.close();}catch(Exception err){err.printStackTrace();}}
			if(con != null){try{con.close();}catch(Exception err){err.printStackTrace();}}							
		}	
		return count;//검색한 글의 갯수 정보 반환
	}
	
	
	
	//게시판 DB에 저장되어 있는 회원목록 검색 해서 반환 하는 메소드
	public List<joindto> getMemberList(int startRow1, int memberSize1){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<joindto> memberList1 = new ArrayList<joindto>();
		String sql = "";
		
		try {
			//DB접속
			con = getConn();
			//SELECT 구문 만들기
			sql = "select * from Member natural join moimuser where enter=1"
					+ " order by Level desc, joinDate desc limit ?,?";
			//select구문을 실행할 PreparedStatement실행객체 얻기
			pstmt = con.prepareStatement(sql);
			//?문자에 대응 되는 값 셋팅
			pstmt.setInt(1, startRow1);
			pstmt.setInt(2, memberSize1);
			//select구문 실행하여 검색한 결과를 ResultSet으로 반환
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				//BoardDto객체를 생성하여 DB로부터검색한 글정보를 ReusltSet에서 얻어서 저장
				joindto joindto = new joindto();
				joindto.setNUM(rs.getInt("NUM"));
				joindto.setNum(rs.getInt("Num"));
				joindto.setMoim_Num(rs.getInt("Moim_Num"));
				joindto.setUserId(rs.getString("UserId"));
				joindto.setUserEmail(rs.getString("UserEmail"));
				joindto.setUserNickname(rs.getString("UserNickname"));
				joindto.setUserGender(rs.getString("UserGender"));
				joindto.setUserDistrict1(rs.getString("UserDistrict1"));
				joindto.setUserDistrict2(rs.getString("UserDistrict2"));
				joindto.setUserBirth(rs.getInt("UserBirth"));
				joindto.setJoinDate(rs.getTimestamp("JoinDate"));
				joindto.setUserIp(rs.getString("UserIp"));
				joindto.setUserPhoto(rs.getString("UserPhoto"));
				joindto.setUserName(rs.getString("UserName"));
				joindto.setUserText(rs.getString("UserText"));
				joindto.setLevel(rs.getInt("Level"));
				joindto.setUsercount(rs.getInt("usercount"));
				//BoardDto객체 -> ArrayList에 추가
				memberList1.add(joindto);
			}//while반복문 끝
				
		} catch (Exception e) {
			System.out.println("getMemberList메소드 내부에서 오류 : " + e);
		} finally {
			//자원해제
			if(rs != null){try{rs.close();}catch(Exception err){err.printStackTrace();}}
			if(pstmt != null){try{pstmt.close();}catch(Exception err){err.printStackTrace();}}
			if(con != null){try{con.close();}catch(Exception err){err.printStackTrace();}}												
		}
		return memberList1; //ArrayList 반환
		
	}
	
	public List<joindto> getMemberList1(int startRow, int memberSize){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<joindto> memberList = new ArrayList<joindto>();
		String sql = "";
		
		try {
			//DB접속
			con = getConn();
			//SELECT 구문 만들기
			sql = "select * from Member natural join moimuser where enter=0"
					+ " order by Level desc, joinDate desc limit ?,?";
			//select구문을 실행할 PreparedStatement실행객체 얻기
			pstmt = con.prepareStatement(sql);
			//?문자에 대응 되는 값 셋팅
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, memberSize);
			//select구문 실행하여 검색한 결과를 ResultSet으로 반환
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				//BoardDto객체를 생성하여 DB로부터검색한 글정보를 ReusltSet에서 얻어서 저장
				joindto joindto = new joindto();
				joindto.setNUM(rs.getInt("NUM"));
				joindto.setNum(rs.getInt("Num"));
				joindto.setMoim_Num(rs.getInt("Moim_Num"));
				joindto.setUserId(rs.getString("UserId"));
				joindto.setUserEmail(rs.getString("UserEmail"));
				joindto.setUserNickname(rs.getString("UserNickname"));
				joindto.setUserGender(rs.getString("UserGender"));
				joindto.setUserDistrict1(rs.getString("UserDistrict1"));
				joindto.setUserDistrict2(rs.getString("UserDistrict2"));
				joindto.setUserBirth(rs.getInt("UserBirth"));
				joindto.setJoinDate(rs.getTimestamp("JoinDate"));
				joindto.setUserIp(rs.getString("UserIp"));
				joindto.setUserPhoto(rs.getString("UserPhoto"));
				joindto.setUserName(rs.getString("UserName"));
				joindto.setUserText(rs.getString("UserText"));
				joindto.setLevel(rs.getInt("Level"));
				joindto.setUsercount(rs.getInt("usercount"));
				//BoardDto객체 -> ArrayList에 추가
				memberList.add(joindto);
			}//while반복문 끝
				
		} catch (Exception e) {
			System.out.println("getMemberList1메소드 내부에서 오류 : " + e);
		} finally {
			//자원해제
			if(rs != null){try{rs.close();}catch(Exception err){err.printStackTrace();}}
			if(pstmt != null){try{pstmt.close();}catch(Exception err){err.printStackTrace();}}
			if(con != null){try{con.close();}catch(Exception err){err.printStackTrace();}}												
		}
		return memberList; //ArrayList 반환
		
	}
	
	//검색.
	/*public ArrayList getList(String search, String searchText){		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="";
		ArrayList list = new ArrayList();
		try {
			//DB접속
			con = getConn();
			if(searchText ==null || searchText.isEmpty()){
			sql = "select * from member";
			}else{
				sql="select* from member where " +search + "like '%" + searchText + "%'";			
			}
			
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				MemberDTO MemberDTO = new MemberDTO();
				MemberDTO.setUserId(rs.getString("UserId"));
				MemberDTO.setUserEmail(rs.getString("UserEmail"));
				MemberDTO.setUserNickname(rs.getString("UserNickname"));
				MemberDTO.setUserGender(rs.getString("UserGender"));
				MemberDTO.setUserDistrict1(rs.getString("UserDistrict1"));
				MemberDTO.setUserDistrict2(rs.getString("UserId"));
				MemberDTO.setUserBirth(rs.getInt("UserBirth"));
				MemberDTO.setJoinDate(rs.getTimestamp("JoinDate"));
				MemberDTO.setUserIp(rs.getString("UserIp"));
				MemberDTO.setUserPhoto(rs.getString("UserPhoto"));
				MemberDTO.setUserName(rs.getString("UserName"));
				MemberDTO.setUserLevel(rs.getInt("UserLevel"));
				MemberDTO.setUserText(rs.getString("UserText"));
			
				list.add(MemberDTO);
			
			}
		} catch (Exception e) {
			System.out.println("getlist메소드 오류 : " + e);
		} finally {
			//자원해제
			if(rs != null){try{rs.close();}catch(Exception err){err.printStackTrace();}}
			if(pstmt != null){try{pstmt.close();}catch(Exception err){err.printStackTrace();}}
			if(con != null){try{con.close();}catch(Exception err){err.printStackTrace();}}												
		}
		return list;
	}
	*/
	
	
		// select 문에서는 자기 가입된 모임의 level 1 이상만 select 되도록 표시.
	
	public void Memberinfo(MemberDTO memberdto){
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql="";
		
		try {
			con = getConn();
			sql="select * from member";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, memberdto.getUserId());
			pstmt.setString(2, memberdto.getUserPwd());
			pstmt.setString(3, memberdto.getUserEmail());
			pstmt.setString(4, memberdto.getUserNickname());
			pstmt.setString(5, memberdto.getUserGender());
			pstmt.setString(6, memberdto.getUserDistrict1());
			pstmt.setString(7, memberdto.getUserDistrict2());
			pstmt.setInt(8, memberdto.getUserBirth());
			pstmt.setTimestamp(9, memberdto.getJoinDate());
			pstmt.setString(10, memberdto.getUserIp());
			pstmt.setString(11, memberdto.getUserPhoto());
			pstmt.setString(12, memberdto.getUserName());
			pstmt.setInt(13, memberdto.getUserLevel());
			pstmt.setString(14, memberdto.getUserText());
			
			
			
			pstmt.executeQuery();
			
		} catch (Exception e) {
			System.out.println("Memberinfo 오류");
		} finally {
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
			
		}
	}
	//////////////////////////////////////////////////////////////////////////////////
	public void insertNaverMember(MemberDTO memberdto){
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql="";
		
		try {
			con = getConn();
			sql="insert into member(userId,userPwd,userEmail,userNickname) values(?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, memberdto.getUserId());
			pstmt.setString(2, memberdto.getUserPwd());
			pstmt.setString(3, memberdto.getUserEmail());
			pstmt.setString(4, memberdto.getUserNickname());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("insertMember 오류");
		} finally {
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
			
		}
	}
	

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
		sql="select * from member where userId=?";
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



	
	
} //DAO 클래스 종료