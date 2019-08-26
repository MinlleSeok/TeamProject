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
		MoimMemberDAO dao = new MoimMemberDAO();
		
		int addmaxmember= Integer.parseInt(request.getParameter("addmaxmember"));
		int addpoint = Integer.parseInt(request.getParameter("addpoint"));
		int check = dao.maxmemberinsert(addmaxmember,addpoint);
		
		if(check==1){
			forward.setPath("maxmemberinsert.jsp");
			forward.setRedirect(true);
		}
		/*PrintWriter out = response.getWriter();
		HttpSession session=request.getSession();
		
		
		joindto joindto = new joindto();
		
		
		int point = Integer.parseInt(request.getParameter("point"));		
		int Moim_Num = Integer.parseInt(request.getParameter("Moim_Num"));
		int maxmember = Integer.parseInt(request.getParameter("maxmember"));
		String ten = request.getParameter("ten");
		*/
		
		
		return forward;
	}

}
