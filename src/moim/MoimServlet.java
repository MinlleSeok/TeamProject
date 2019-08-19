package moim;

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

import moim.Action;
import moim.ActionForward;


public class MoimServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doProcess(request,response);System.out.println("�ΰ�");}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doProcess(request,response);System.out.println("������Ʈ");}
	
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
	if(command.equals("/moimContent.mo")){
		//페이지 이동방식 선택(true=리다이렉트, false=디스패치(경로 노툴안함))
		forward=new ActionForward();
		forward.setRedirect(false);		
		forward.setPath("./moim/moimContent.jsp");  //이동할 페이지 경로 주소값 저장(회원가입 입력 페이지)
		
		
// moimContent.mo(모임정보 페이지 이동)////////////////////////////////////////////////////////////////////	
	}else if(command.equals("/moimInfo.mo")){
		forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./moim/moimContent.jsp?pageCall=moimInfo.jsp");		

	}else if(command.equals("/moimContent.me")){
		forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/index.jsp");		

	}else if(command.equals("/moimBoard.mo")){
		forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./moim/moimContent.jsp?pageCall=moimBoard.jsp");		

	}
	
	
	// ������� ó�� (������)////////////////////////////////////////////////////////////////////
	if(forward!=null){
		if(forward.isRedirect()){
			response.sendRedirect(forward.getPath());
		}else{//����ġ ������� ������ ��� ���� ���� ������
			RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
			dispatcher.forward(request, response);}
		
	}// ������� if ����		
} // doProcess() ����	
} // ���� ����