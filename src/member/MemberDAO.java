package member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.sql.DataSource;

import board.BoardDto;

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
	
	// Level 함수 사용 위한 if 문
	/*
	  
	  if(MemberLevel.equals("3")){
	  	// 멤버 레벨 체크박스 모두 
	  
	  
	  
	  
	  
	 
	 */
	
	
	
//	public void MemberLevel(MemberDTO memberdto){
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		String sql="";
//		
//		try {
//			con = getConn();
//			sql="update member set userLevel=? where userId";
//			pstmt = con.prepareStatement(sql);
	

//			pstmt.setInt(1, memberdto.getUserLevel());	
//			pstmt.setString (2, memberdto.getuserId());
//			
//			
//			
//			pstmt.executeUpdate();
//			
//		} catch (Exception e) {
//			System.out.println("MemberLevel 오류");
//		} finally {
//			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
//			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
//			
//		}
//	}
	
	//Member list로 변경하기
	public List<MemberDTO> getBoardList(int startRow int pageSize){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberDTO> dto = new ArrayList<MemberDTO>();
		String sql = "";
		
		try {
			con = getConn();
			sql = "select * from board order by re_ref desc, re_seq asc limit ?,?";
			//select구문을 실행할 PreparedStatement실행객체 얻기
			pstmt = con.prepareStatement(sql);
			//?문자에 대응 되는 값 셋팅
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, pageSize);
			//select구문 실행하여 검색한 결과를 ResultSet으로 반환
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				//BoardDto객체를 생성하여 DB로부터검색한 글정보를 ReusltSet에서 얻어서 저장
				BoardDto boardDto = new BoardDto();
				boardDto.setContent(rs.getString("content"));
				boardDto.setDate(rs.getTimestamp("date"));
				boardDto.setIp(rs.getString("ip"));
				boardDto.setName(rs.getString("name"));
				boardDto.setNum(rs.getInt("num"));
				boardDto.setPasswd(rs.getString("passwd"));
				boardDto.setRe_lev(rs.getInt("re_lev"));
				boardDto.setRe_ref(rs.getInt("re_ref"));
				boardDto.setRe_seq(rs.getInt("re_seq"));
				boardDto.setReadcount(rs.getInt("readcount"));
				boardDto.setSubject(rs.getString("subject"));
				boardDto.setFile(rs.getString("file"));
				
				//BoardDto객체 -> ArrayList에 추가
				boardList.add(boardDto);
			}//while반복문 끝
				
		} catch (Exception e) {
			System.out.println("getBoardList메소드 내부에서 오류 : " + e);
		} finally {
			//자원해제
			if(rs != null){try{rs.close();}catch(Exception err){err.printStackTrace();}}
			if(pstmt != null){try{pstmt.close();}catch(Exception err){err.printStackTrace();}}
			if(con != null){try{con.close();}catch(Exception err){err.printStackTrace();}}												
		}
		return boardList; //ArrayList 반환
	

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
			
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("Memberinfo 오류");
		} finally {
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
			
		}
	}
	
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