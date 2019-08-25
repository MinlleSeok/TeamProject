package member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class maxmemberinsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession();
		MoimMemberDAO dao = new MoimMemberDAO();
		joindto joindto = new joindto();
		int point = Integer.parseInt(request.getParameter("point"));		
		int Moim_Num = Integer.parseInt(request.getParameter("Moim_Num"));
		int maxmember = Integer.parseInt(request.getParameter("maxmember"));
		String ten = request.getParameter("ten");
		int addmaxmember= Integer.parseInt(request.getParameter("addmaxmember"));
		int check = dao.maxmemberinsert(point, maxmember, Moim_Num, addmaxmember);
		
		if(check==1){
			forward.setPath("maxmemberinsert.jsp");
			forward.setRedirect(true);
		}
		
		
		
		return forward;
	}

}
