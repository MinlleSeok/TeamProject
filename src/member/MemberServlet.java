package member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class MemberServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doProcess(request,response);System.out.println("두겟");}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doProcess(request,response);System.out.println("두포스트");}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("서블릿 작동");
		
		// 요청한 가상주소값 얻기(컨텍스트 패스포함주소-컨텍스트길이 = 순수 경로)
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		int contextPathLength= contextPath.length();
		String command = RequestURI.substring(contextPathLength);// 전체주소에서 요청주소만 잘라낸 결과
		System.out.println("1:"+RequestURI);
		System.out.println("2:"+contextPath);
		System.out.println("3:"+contextPathLength);
		System.out.println("command최종형태:"+command);
		
		//doProcess 전역변수 선언
		ActionForward forward = null;
		Action action=null;		
		
		// 요청주소(command)에 따라 작업 처리 시작하기
		
// 회원가입 메인 연결(userJoin.me)////////////////////////////////////////////////////////////////////
	if(command.equals("/userJoin.me")){
		//페이지 이동방식 선택(true=리다이렉트, false=디스패치(경로 노툴안함))
		forward=new ActionForward();
		forward.setRedirect(false);		
		forward.setPath("./member/userJoin.jsp");  //이동할 페이지 경로 주소값 저장(회원가입 입력 페이지)
		
		
// 메인페이지 연결(mm.me)////////////////////////////////////////////////////////////////////	
	}else if(command.equals("/mm.me")){
		//페이지 이동방식 선택(true=리다이렉트, false=디스패치(경로 노출안함))
		forward=new ActionForward();
		forward.setRedirect(false);
		
		//이동할 페이지 경로 주소값 저장
		forward.setPath("/index.jsp");	

		
// 카카오톡 회원가입 아이디 존재 여부 확인 및 id존재 시 로그인 처리. idChk		
	}else if(command.equals("/snsIdChk.me")){
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html, charset=utf-8");
						
		System.out.println("sns아이디체크 호출 됨.");	
		
		// 전송된 id로 DB에 ID존재 여부 확인 후 ajax기술을 이용하여 결과값을 리턴 한다.
		String userId = request.getParameter("userId");	
		System.out.println("서블릿으로 넘어온 Id값 :"+userId);
		
		
		action=new snsIdChkAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	//카카오톡 회원가입 액션 	
	}else if(command.equals("/snsUserJoinAction.me")){
		System.out.println("sns로그인 호출됨");
		
		String userId = request.getParameter("userId");			
		System.out.println("snsUserJoin 호출 id :"+userId);
		
		action=new snsUserJoinAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			System.out.println("snsUserJoinAction에러"+e);
		}	
	}
	
	
	
	
	
	
	
	
	// 공통사항 처리 (포워딩)////////////////////////////////////////////////////////////////////
	if(forward!=null){
		if(forward.isRedirect()){
			response.sendRedirect(forward.getPath());
		}else{//디스패치 방식으로 페이지 경로 노출 없이 포워딩
			RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
			dispatcher.forward(request, response);}
		
	}// 공통사항 if 종료		
} // doProcess() 종료	
} // 서블릿 종료
