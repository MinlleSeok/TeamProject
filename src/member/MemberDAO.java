package member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
//회원 가입, 회원수정, 삭제, 검색등.. DB연결 작업을 위한  DAO역할의 클래스 
public class MemberDAO {

	//커넥션풀로부터 커넥션객체 얻는 메소드(DB접속 객체 얻기)
	private Connection  getConnection() throws Exception{	
		Connection con = null;
		Context init = new InitialContext();
		//커넥션풀(DataSource객체) 얻기
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/jspbeginner");
		//커넥션풀을 이용하여 커넥션(Connection객체)얻기
		con = ds.getConnection();		
		return con;
	}
	//로그인 처리시 이용되는 메소드 
	//사용자가 입력받은 아이디,비밀번호와 DB에 저장되어 있는 값과 비교하여 로그인처리
	public int userCheck(String id, String passwd){
		//판별값 저장할 변수 
		int check = -1; // 1 -> 아이디,비밀번호 맞음
						// 0 -> 아이디 맞음, 비밀번호 틀림
						// -1 -> 아이디 틀림
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		
		try {
			//커넥션풀로 부터 커넥션객체 얻기 (DB연결)
			con = getConnection();
			//매개변수로 전달받은 로그인시 입력한 아이디에 해당하는 레코드 검색
			sql = "select * from  member where id=?";
			//SELECT를 실행할 PreparedStatement객체 얻기
			pstmt = con.prepareStatement(sql);
			//?셋팅
			pstmt.setString(1, id);
			//PreparedStatement객체를 통해 전체 SELECT문장을 실행하여 검색!
			//검색한 결과를 ResultSet임시 저장공간역할의 객체에 저장하여
			//ResultSet객체 자체를 반환
			rs = pstmt.executeQuery();
			
			if(rs.next()){//로그인시 입력한 아이디가 DB에 존재 하고 
				
				//로그인시 입력한 비밀호가  DB에 존재하면?
				if(passwd.equals(rs.getString("passwd"))){
					
					check = 1; //아이디 , 비밀번호 맞음
			
				}else{//아이디 OK,비밀번호 틀림
					check = 0; 
				}
				
			}else{//로그인시 입력한 아이디가 DB에 존재 하지 않을때
				
				check = -1;
			}
				
		} catch (Exception e) {
			System.out.println("userCheck메소드 내부에서 오류 : " + e);
		} finally {
			//자원해제
			if(rs != null){try{rs.close();}catch(Exception err){err.printStackTrace();}}
			if(pstmt != null){try{pstmt.close();}catch(Exception err){err.printStackTrace();}}
			if(con != null){try{con.close();}catch(Exception err){err.printStackTrace();}}				
		}
		
		return check;
		
	}
	
	
	/*insertMember()메소드 추가*/
	//MemberBean객체(가입할 회원정보를 저장하고 있는 객체)를 메소드의 매개변수로 전달받아..
	//DB에 회원을 추가 시키는 메소드
	public void insertMember(MemberBean memberBean){
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql="";
		
		try {
			//커넥션풀로 부터 커넥션객체 얻기 (DB연결)
			con = getConnection();
			//insert SQL문장 만들기
			sql = "insert into member(id,passwd,name,reg_date,email,address,tel,mtel)"
				+ "values(?,?,?,?,?,?,?,?)";
			//insert문장을 실행할 PreparedStatement객체 얻기
			pstmt = con.prepareStatement(sql);
			//?셋팅
			pstmt.setString(1, memberBean.getId());
			pstmt.setString(2, memberBean.getPasswd());
			pstmt.setString(3, memberBean.getName());
			pstmt.setTimestamp(4, memberBean.getReg_date());
			pstmt.setString(5, memberBean.getEmail());
			pstmt.setString(6, memberBean.getAddress());
			pstmt.setString(7, memberBean.getTel());
			pstmt.setString(8, memberBean.getMtel());
			//insert문장 전체 실행
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("insertMember메소드 내부의 코드 오류 : " + e);
		} finally {
			//자원해제
			if(pstmt != null){try{pstmt.close();}catch(Exception err){err.printStackTrace();}}
			if(con != null){try{con.close();}catch(Exception err){err.printStackTrace();}}
		}
	}//insertMember()끝 
	
	//회원가입을 위해.. 사용자가 입력한 id값을 매개변수로 전달받아..
	//DB에 사용자가 입력한 id값이 존재하는지 SELECT검색하여..
	//만약 사용자가 입력한 id에 해당하는 회원레코드가 검색되면?
	//check변수값을 1로 저장하여 <-----아이디 중복을 나타내고,
	//만약에 사용자가 입력한 id에 해당하는 회원레코드가 검색되지 않으면?
	//check변수값을0으로 저장하여 <----- 아이디 중복 아님을 나타낸다.
	//결과적으로 아이디 중복이냐 중복이 아니냐는 check변수의값으로 저장되어 있으므로
	//메소드의 반환값은 check변수값을 리턴 한다.
	public int idCheck(String id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int check = 0;
		String sql = "";
		
		try {
			//커넥션풀로 부터 커넥션객체 얻기 (DB연결)
			con = getConnection();
			//SELECT SQL문장 만들기
			sql = "select * from member where id=?";					
			//SELECT문장을 실행할 PreparedStatement객체 얻기
			pstmt = con.prepareStatement(sql);
			//?셋팅
			pstmt.setString(1, id);		
			//SELECT문장 전체 실행
			rs = pstmt.executeQuery();
			
			if(rs.next()){//입력한 아이디에 해당하는 데이터가검색이 된다면(아이디 중복 ,아이디 존재)
				check = 1;
			}else{//아이디 중복 아님 
				check = 0;
			}		
		} catch (Exception e) {
			System.out.println("idCheck메소드 내부의 코드 오류 : " + e);
		} finally {
			//자원해제
			if(rs != null){try{rs.close();}catch(Exception err){err.printStackTrace();}}
			if(pstmt != null){try{pstmt.close();}catch(Exception err){err.printStackTrace();}}
			if(con != null){try{con.close();}catch(Exception err){err.printStackTrace();}}	
		}
		return check; // 1또는 0을 리턴		
	}
		
}//MemberDAO끝












