package member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class deletemoimmemberAction1 implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("3333");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession();
		
		MoimMemberDAO dao = new MoimMemberDAO();
		joindto joindto = new joindto();
		int NUM1 = Integer.parseInt(request.getParameter("NUM1"));
		int usercount = Integer.parseInt(request.getParameter("usercount"));
		int check = dao.deletemoimmember1(usercount,NUM1);
		System.out.println(usercount);
		System.out.println("check :" + check);
//		check = dao.usercount(usercount);
//		System.out.println("check :" + check);
		if(usercount==0||usercount==1||usercount==2){
			//out.println("<script language='javascript'>alert('3회이후 경고 누적 시 강퇴 시킵니다.');");				
		
			out.print("<script>");
			out.print("alert('3회이후 경고 누적 시 강퇴 시킵니다.');");
			out.print("location.href='./Memberinfo.jsp';");
			out.print("</script>");
			
		}else if(check==1){					
			forward.setPath("Memberinfo.jsp");
			forward.setRedirect(true);
		}
		
		
		
		return forward;
	}

}
