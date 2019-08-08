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
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/mmpjt");
		con = ds.getConnection();
		return con;
	}// getConn() ����
	
} //DAO Ŭ���� ����
