package member;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

public class KakaoLoginCheck extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandle(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandle(req,resp);
	}

	protected void doHandle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String userId = req.getParameter("idtoken");
		String userPwd = req.getParameter("idtoken") + "@k";
		String userNickname = req.getParameter("nicknametoken");
		String userProfile_image = req.getParameter("profile_imagetoken");
		String userEmail = req.getParameter("emailtoken");

		String subId = userId.substring(0, 7);
		  // Use or store profile information
		  // ...
		
		  System.out.println("유저 아이디 : " + userId);
		  System.out.println("유저 비밀번호 : " + userPwd);
		  System.out.println("유저 닉네임 : " + userNickname);
		  System.out.println("유저 프로필 사진 URL : " + userProfile_image);
		  System.out.println("유저 이메일 : " + userEmail);

		  
		  MemberDAO dao = new MemberDAO();
		  if(dao.idCheck(userId) == 0) {
			  MemberDTO memberBean = new MemberDTO();
			  memberBean.setUserEmail(userEmail);
			  memberBean.setUserNickname(userNickname);
			  memberBean.setUserId(userId);
			  memberBean.setUserPwd(userPwd);
//			  memberBean.setReg_date(new Timestamp(System.currentTimeMillis()));
			  memberBean.setJoinDate(new Timestamp(System.currentTimeMillis()));
			  memberBean.setUserPhoto(userProfile_image);

			  dao.insertKakaoMember(memberBean);
			  
			/*
		  	  HttpSession session = req.getSession();
			  session.setAttribute("id", subId);
			  
			  RequestDispatcher dis = this.getServletContext().getRequestDispatcher("/index.jsp");
			  dis.forward(req, resp);
			*/
			  
		  } else {
			  HttpSession session = req.getSession();
			  session.setAttribute("id", subId);
			  
			  RequestDispatcher dis = this.getServletContext().getRequestDispatcher("/index.jsp");
			  dis.forward(req, resp);
		  }
	}
}