package member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class moimmemberlevelAction implements Action {

	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession();
		
		MoimMemberDAO dao = new MoimMemberDAO();
		
		joindto joindto = new joindto();

		System.out.println("1212");
		int NUM = Integer.parseInt(request.getParameter("NUM"));
		int Level= Integer.parseInt(request.getParameter("Level"));
		
		int check = dao.memberlevelup(Level, NUM);
		
		
		if(check==1){
			forward.setPath("Memberinfo.jsp");
			forward.setRedirect(true);
		}
		

	return forward;
	}
		
}
