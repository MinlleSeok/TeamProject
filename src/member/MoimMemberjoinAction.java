package member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MoimMemberjoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;char=utf-8");
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession();
		
		//response.setCharacterEncoding("UTF-8");
		int Moim_Num = Integer.parseInt(request.getParameter("Moim_Num"));
		int Usernum = Integer.parseInt(request.getParameter("UserNum"));
		int Level=0;
		
		MoimMemberDAO dao = new MoimMemberDAO();
		
		int check = dao.insertMoimMember(Moim_Num, Usernum, Level);
			if(check==1){
				forward.setPath("MoimMemberjoin.jsp?Moim_Num="+Moim_Num+"&UserNum="+Usernum);
				forward.setRedirect(false);
			}
		
		
		
		
		
		return forward;
	}

}
