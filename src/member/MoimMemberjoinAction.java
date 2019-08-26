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
		MoimMemberDAO mdao = new MoimMemberDAO();
		//response.setCharacterEncoding("UTF-8");
		int Moim_Num = Integer.parseInt(request.getParameter("Moim_Num"));//입력받는 값
		int Usernum = Integer.parseInt(request.getParameter("UserNum"));//입력받는 값
		int Moim_Num1 = mdao.selectMoim_Num();// 기존 DB에 저장된 값
		int Usernum1 = mdao.selectUsernum();// 기존 DB에 저장된 값
		int Level=0;
		int UserCount=0;
		int enter= mdao.selectenter();
		MoimMemberDAO dao = new MoimMemberDAO();
		
		
		
		int check = dao.insertMoimMember(Moim_Num, Usernum, Level,UserCount,enter);
		if(Moim_Num==Moim_Num1&&Usernum==Usernum1){
			if(enter==0){
				out.print("<script>");
				out.print("alert('이미 가입신청된 아이디 입니다.');");
				out.print("location.href='./MoimMemberjoin.jsp';");
				out.print("</script>");
			}else if(enter==1){
				out.print("<script>");
				out.print("alert('이미 가입된 아이디 입니다.');");
				out.print("location.href='./MoimMemberjoin.jsp';");
				out.print("</script>");
			}else if(enter==2){
				out.print("<script>");
				out.print("alert('강퇴당한 유저는 재가입이 불가능 합니다.');");
				out.print("location.href='./MoimMemberjoin.jsp';");
				out.print("</script>");
			}
		}else if(check==1){
			forward.setPath("MoimMemberjoin.jsp?Moim_Num="+Moim_Num+"&UserNum="+Usernum);
			forward.setRedirect(false);
		}
		
	
		
		
		
		
		
		return forward;
	}

}
