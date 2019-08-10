package com.bungae.db;

import java.sql.*;
import java.util.ArrayList;

import javax.naming.*;
import javax.sql.DataSource;

public class BungaeDAO {
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
		
		public boolean insertBungae(BungaeDTO bean) {
			boolean check = false;
			
			try {
				con = getConn();
				sql = "insert into bungae(mmNum, subject, content, max, bdate) values(?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, bean.getMmNum());
				pstmt.setString(2, bean.getSubject());
				pstmt.setString(3, bean.getContent());
				pstmt.setInt(4, bean.getMax());
				pstmt.setDate(5, bean.getBdate());
				int success = pstmt.executeUpdate();
				if(success > 0) {
					check = true;
				}
				
				close();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return check;
		}

		public ArrayList<BungaeDTO> selectBungae(int mmNum) {
			ArrayList<BungaeDTO> list = new ArrayList<BungaeDTO>();
			BungaeDTO bdto = null;
			
			try {
				con = getConn();
				sql = "select * from bungae where mmNum="+mmNum+" order by num desc limit 5";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					bdto = new BungaeDTO();
					bdto.setBdate(rs.getDate("bdate"));
					bdto.setContent(rs.getString("content"));
					bdto.setMax(rs.getInt("max"));
					bdto.setMmNum(rs.getInt("mmNum"));
					bdto.setNum(rs.getInt("num"));
					bdto.setSubject(rs.getString("subject"));
					list.add(bdto);
				}
				
				close();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return list;
		}

		
	} //DAO 클래스 종료
