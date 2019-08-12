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

		public void insertKakaoMember(MemberDTO memberBean) {
			try {
				//커넥션풀로부터 커넥션 얻기(DB접속)
				Connection con = getConn();
				
				PreparedStatement pstmt;

				//INSERT문장 준비
				String sql = "insert into member(userNum,userId,userPwd,userEmail,"
						   + "userNickname,userGender,userDistrict1,userDistrict2,userBirth,joinDate,userIp,userPhoto)"
						   + "values(?,?,?,?,?,?,?,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				//?문자에 대응되는 예약할 정보 값 셋팅
				pstmt.setInt(1, memberBean.getUserNum());
				pstmt.setString(2, memberBean.getUserId());
				pstmt.setString(3, memberBean.getUserPwd());
				pstmt.setString(4, memberBean.getUserEmail());
				pstmt.setString(5, memberBean.getUserNickname());
				pstmt.setString(6, memberBean.getUserGender());
				pstmt.setString(7, memberBean.getUserDistrict1());
				pstmt.setString(8, memberBean.getUserDistrict2());
				pstmt.setInt(9, memberBean.getUserBirth());
				pstmt.setTimestamp(10, memberBean.getJoinDate());
				pstmt.setString(11, memberBean.getUserIp());
				pstmt.setString(12, memberBean.getUserPhoto());
		
				//예약 정보를 CarOrder테이블에  INSERT
				pstmt.executeUpdate();
				//자원해제
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public int idCheck(String userId) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int check = 1;
			String sql = "";

			try {
				// 커넥션풀로부터 커넥션객체 얻기 (DB연결)
				con = getConn();

				// SELECT SQL문장 만들기
				sql = "select * from member where userId=?";

				// SELECT문장흘 실행할 PreparedStatement객체 얻기
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, userId);

				// SELECT문장 전체 실행
				rs = pstmt.executeQuery();

				if (rs.next()) { // 입력한 아이디에 해당하는 데이터가 검색이 된다면(아이디 중복, 아이디 존재)
					check = 1;
				} else { // 아이디 중복 아님
					check = 0;
				}

			} catch (Exception e) {
				System.out.println("idCheck메소드 내부 오류 : " + e);
			} finally {
				// 자원해제
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (Exception err) {
						err.printStackTrace();
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception err) {
						err.printStackTrace();
					}
				}
				if (rs != null) {
					try {
						rs.close();
					} catch (Exception err) {
						err.printStackTrace();
					}
				}
			}

			return check;
		}
		
	} //DAO 클래스 종료
