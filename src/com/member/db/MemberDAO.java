package com.member.db;

import java.sql.*;
import java.util.ArrayList;

import javax.naming.*;
import javax.sql.DataSource;

public class MemberDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	
	
	//*********** getConn()생성 : 커넥션풀로 부터 커넥션 객체con을 만들기 위한 메소드. 	
		private Connection getConn() throws Exception{
		
			Context init = new InitialContext();

			// 커넥션 풀 얻기(context.xml파일의 <Resource> 태그의 name정보로 가져옴)
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/mmpjt");
			con = ds.getConnection();
			return con;
		}// getConn() 종료

		public void close() {
			try {
			if(rs != null) {	
					rs.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(con != null){
				con.close();
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public MemberDTO selectMember(String id) {
			MemberDTO mdto = new MemberDTO();
			
			try {
				con = getConn();
				
				sql = "select * from member where userId=?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					if(rs.getDate("joinDate")!=null)
					mdto.setJoinDate(rs.getDate("joinDate"));
					if(rs.getString("userId")!=null)
					mdto.setUserId(rs.getString("userId"));
					if(rs.getInt("userBirth")>0)
					mdto.setUserBirth(rs.getInt("userBirth"));
					if(rs.getString("userDistrict1")!=null)
					mdto.setUserDistrict1(rs.getString("userDistrict1"));
					if(rs.getString("userDistrict2")!=null)
					mdto.setUserDistrict2(rs.getString("userDistrict2"));
					if(rs.getString("userEmail")!=null)
					mdto.setUserEmail(rs.getString("userEmail"));
					if(rs.getString("userGender")!=null)
					mdto.setUserGender(rs.getString("userGender"));
					if(rs.getString("userIp")!=null)
					mdto.setUserIp(rs.getString("userIp"));
					if(rs.getString("userNickname")!=null)
					mdto.setUserNickname(rs.getString("userNickname"));
					if(rs.getInt("userNum")>0)
					mdto.setUserNum(rs.getInt("userNum"));
					if(rs.getString("userPhoto")!=null)
					mdto.setUserPhoto(rs.getString("userPhoto"));
					if(rs.getString("userPwd")!=null)
					mdto.setUserPwd(rs.getString("userPwd"));
				}
				
				close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return mdto;
		}
		
	} //DAO 클래스 종료
