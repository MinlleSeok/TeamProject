package member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class snsIdChkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		HttpSession session= request.getSession();
		request.setCharacterEncoding("utf-8");	
		
		
		String userId = request.getParameter("userId");
		MemberDAO memberDAO = new MemberDAO();
		boolean result = memberDAO.snsLoginIdChk(userId);
	
		System.out.println(result);
		
		String idResult;
		if(result==false){
			idResult="noId";
					
			MemberDTO memberdto = new MemberDTO();
			System.out.print(request.getParameter("userEmail"));
			System.out.print(request.getParameter("userNickname"));
			System.out.print(request.getParameter("userPwd"));
			System.out.print(userId);
			memberdto.setUserId(userId);
			memberdto.setUserEmail(request.getParameter("userEmail"));
			memberdto.setUserNickname(request.getParameter("userNickname"));
			memberdto.setUserPwd(request.getParameter("userPwd"));
			memberDAO.insertNaverMember(memberdto);
			//dto에서 4개의 값 저장
			/*String userId1 =memberdto.getUserId();
			String userPwd =memberdto.getUserPwd();
			String userNickname = memberdto.getUserNickname();
			String userEmail = memberdto.getUserEmail();
			*/
			System.out.print("가입완료");
		}else{
			
			idResult="yesId";
			//userId = (String)session.getAttribute("userId");
			
			session.setAttribute("userId", userId);
			//index.jsp로 이동.
			
		}
		
		out.print(idResult);
		
		
		forward.setRedirect(false);
	
		forward.setPath("mm.me");
		
		return null;
	}

}
