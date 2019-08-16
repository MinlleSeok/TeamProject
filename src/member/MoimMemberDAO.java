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
	
	
	public int UpdataLevel(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String sql="";
		try {
			con=getConn();
		} catch (Exception e) {
			
		} finally {
			if(pstmt!=null){try {pstmt.close();} catch (Exception err) {err.printStackTrace();}}
			if(con!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}
			if(rs!=null){try {con.close();} catch (Exception err) {err.printStackTrace();}}
		}
		
	}
	
	public int insertMoimMember(int Moim_Num , int Usernum, int Level){
		Connection con = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs=null;
		String sql="";
		int check=0;
		try {
			con = getConn();
			
//			sql="select * from member where userNum=?";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, Usernum);
//			rs = pstmt.executeQuery();
//			
//			sql="select * from moim where Moim_Num=?";
//			pstmt2 = con.prepareStatement(sql);
//			pstmt2.setInt(1, Moim_Num);
//			rs = pstmt2.executeQuery(); 
//			
//			sql="insert into moimuser(Moim_Num,UserNum,Level) values(?,?,1)";
//			pstmt3 = con.prepareStatement(sql);			
//			pstmt3.setInt(1, Moim_Num);
//			pstmt3.setInt(2, Usernum);
//			pstmt3.setInt(3, Level);
//			check=pstmt3.executeUpdate();
			System.out.println(1);
			sql="insert into moimuser(Moim_Num,UserNum,Level) values((select Moim_Num from moim where Moim_Num=?),"
					+ "(select userNum from member where userNum=?),1)";
			pstmt1= con.prepareStatement(sql);
			pstmt1.setInt(1, Moim_Num);
			pstmt1.setInt(2, Usernum);
			check=pstmt1.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("insertMember 오류");
		} finally {
			if(pstmt1!=null){try {pstmt1.close();} catch (Exception err) {err.printStackTrace();}}
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
