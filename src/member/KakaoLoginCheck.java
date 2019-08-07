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

@WebServlet("/KakaoLoginCheck")
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
		String userId = req.getParameter("idtoken");
		
		String subId = userId.substring(0, 7);
		  // Use or store profile information
		  // ...
		  System.out.println(userId);
		  MemberDAO dao = new MemberDAO();
		  if(dao.idCheck(subId) == 0) {
			  MemberBean memberBean = new MemberBean();
			  memberBean.setEmail("asd@asd.net");
			  memberBean.setName("@@");
			  memberBean.setId(subId);
			  memberBean.setPasswd(subId+"*");
			  memberBean.setReg_date(new Timestamp(System.currentTimeMillis()));
			  dao.insertMember(memberBean);
			  HttpSession session = req.getSession();
			  session.setAttribute("id", subId);
			  
			  RequestDispatcher dis = this.getServletContext().getRequestDispatcher("/index.jsp");
			  dis.forward(req, resp);
		  } else {
			  HttpSession session = req.getSession();
			  session.setAttribute("id", subId);
			  
			  RequestDispatcher dis = this.getServletContext().getRequestDispatcher("/index.jsp");
			  dis.forward(req, resp);
		  }
	}
}
