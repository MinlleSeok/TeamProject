package member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javafx.scene.control.Alert;

public class MoimMemberjoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession();
		joindto dto = new joindto();
		//response.setCharacterEncoding("UTF-8");
		int Moim_Num = Integer.parseInt(request.getParameter("Moim_Num"));
		int Usernum = Integer.parseInt(request.getParameter("UserNum"));
		int Level=0;
		int UserCount=0;
		int enter= Integer.parseInt(request.getParameter("enter"));
		MoimMemberDAO dao = new MoimMemberDAO();
		
		
		
		int check = dao.insertMoimMember(Moim_Num, Usernum, Level,UserCount,enter);
		if(enter==0){
			if(check==1){
				forward.setPath("MoimMemberjoin.jsp?Moim_Num="+Moim_Num+"&UserNum="+Usernum);
				forward.setRedirect(false);
			}
		}else if(enter==1){
			
		}else if(enter==2){
			out.print("<script>");
			out.print("alert('강퇴당한 유저는 재가입이 불가합니다.');");
			out.print("location.href='./MoimMemberjoin.jsp';");
			out.print("</script>");
		}
			
	
		
		
		
		
		
		return forward;
	}

}
