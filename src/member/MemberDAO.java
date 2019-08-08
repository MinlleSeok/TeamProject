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
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/mmpjt");
			con = ds.getConnection();
			return con;
		}// getConn() 종료
		
	} //DAO 클래스 종료
