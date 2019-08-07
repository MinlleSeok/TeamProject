package member;

import java.sql.*;
import java.util.ArrayList;

import javax.naming.*;
import javax.sql.DataSource;

public class MemberDAO {
	
//*********** getConn()���� : Ŀ�ؼ�Ǯ�� ���� Ŀ�ؼ� ��ücon�� ����� ���� �޼ҵ�. 	
	private Connection getConn() throws Exception{
	
		Connection con = null;
		Context init = new InitialContext();

		// Ŀ�ؼ� Ǯ ���(context.xml������ <Resource> �±��� name������ ������)
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/vanco");
		con = ds.getConnection();
		return con;
	}// getConn() ����
	

//*********** snsLoginIdChk() ���� : ȸ�� �α��� �޼ҵ�
	// ���ϰ� : true(���̵����), false(���̵�����)
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
		
		if(rs.next()){//id���� ������(true) => idChk=false
			idChk=true;
		}			
	} catch (Exception e) {
		System.out.println("snsLoginIdChk����"+e);
	}finally{
		if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
		if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
		if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}	
	}		
	return  idChk;
} //snsLoginIdChk() ����
	
//*********** snsUserLogin() ���� : SNSȸ�� �α��� �޼ҵ�
	// ���ϰ� : �α��μ���(1), ���Ʋ��(0), ID����(-1)
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
		System.out.println("snsUserLogin()����"+e);
	}finally{
		if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
		if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
		if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}	
	}		
	return  logResult;
	} //snsUserLogin() ����


	
//*********** snsUserJoin() ���� : ȸ�� ���� �޼ҵ�	
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
			
			// ?�� ����
			pstmt.setString(1,mdto.getUserId());
			pstmt.setString(2,mdto.getUserPwd());
			pstmt.setString(3,mdto.getUserNickname());
			pstmt.setTimestamp(4,mdto.getJoinDate());
			pstmt.setString(5,mdto.getUserPhoto());
			
			System.out.println(mdto.getUserId());
			
			/*if(mdto.getUserGender().equals("��")){
			pstmt.setString(6,"/vanco/imageProfile/defaultMan.jpg");
			userPhoto="/vanco/imageProfile/defaultMan.jpg";
			}else{
			pstmt.setString(6,"/vanco/imageProfile/defaultWoman.jpg");
			userPhoto="/vanco/imageProfile/defaultWoman.jpg";
			}*/
			
			// ���� ����
			pstmt.executeUpdate();	
			pstmt.close();
			
			//ȸ������ ���� �� �ٷ� �α��� ��Ű��			
			sql = "select userPwd from vancoMember where userId=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,mdto.getUserId());
			rs=pstmt.executeQuery();			
			
			if(rs.next()){//id���� ������(true) => ��� ���ϴ� �α��� �˻� ����
				if(mdto.getUserPwd().equals(rs.getString("userPwd"))){
					logResult=1;
				}else{
					logResult=0;
				}
			}else{
				logResult=-1;
			}					
		} catch (Exception e) {
			System.out.println("snsUserJoin()����"+e);
			e.printStackTrace();
		}finally{
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}
			if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}	
		}
		return logResult;
	} // snsUserJoin() ����

	
	//*********** getUserInfo() ���� :(����� id��) myPage���� ��� user���� ������., �׿� session�����θ� ������ ���� ���� �ʿ��Ҷ� ��ܾ�
	// �α��νÿ��� id���� ���ǿ� ������, ����/�г���/���� �������� ���⼭ ������.

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
		System.out.println("getUserInfo()����"+e);
	}finally{
		if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
		if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
		if(rs!=null){try {rs.close();} catch (Exception err) {err.printStackTrace();}}
	}		
	return mdto;
} //getUserInfo() ����
	
	
} //DAO Ŭ���� ����
