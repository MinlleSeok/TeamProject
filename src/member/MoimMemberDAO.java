package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MoimMemberDAO {

	private Connection getConn() throws Exception{
		
		Connection con = null;
		Context init = new InitialContext();

		// 커넥션 풀 얻기(context.xml파일의 <Resource> 태그의 name정보로 가져옴)
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/mmpjt");
		
		con = ds.getConnection();
		return con;
	}
	
	public int insertMoimMember(int Moim_Num , int Usernum){
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs=null;
		String sql="";
		int check=0;
		try {
			con = getConn();
			
			sql="select * from member where userNum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Usernum);
			rs = pstmt.executeQuery();
			
			
			sql="select * from moim where Moim_Num=?";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setInt(1, Moim_Num);
			rs = pstmt2.executeQuery(); 
			
		
			sql="insert into moimuser(Moim_Num,UserNum) values(?,?)";
			pstmt3 = con.prepareStatement(sql);			
			pstmt3.setInt(1, Moim_Num);
			pstmt3.setInt(2, Usernum);
			check=pstmt3.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("insertMember 오류");
		} finally {
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(pstmt2!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(pstmt3!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}
			if(rs!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}
			
		}
		return check;
	}
	
	public void MoimMemberinfo(MoimMemberBean MoimMemberBean){
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql="";
		
		try {
			con = getConn();
			sql="select * from moimuser";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, MoimMemberBean.getNUM());
			pstmt.setInt(2, MoimMemberBean.getMoim_Num());
			pstmt.setInt(3, MoimMemberBean.getUserNum());
			pstmt.setInt(4, MoimMemberBean.getLevel());
			
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("MoimMemberinfo 오류");
		} finally {
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}			
			
		}
	}
}
