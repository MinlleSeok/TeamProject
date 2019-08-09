package com.bungae.controller;

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

import com.action.Action;
import com.action.ActionForward;


public class BungaeFrontController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doProcess(request,response);System.out.println("GET");}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doProcess(request,response);System.out.println("POST");}
	
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
		
		// 번개 개설 연결(insert.bg)////////////////////////////////////////////////////////////////////
		if(command.equals("/index.bg")){
			//페이지 이동방식 선택(true=리다이렉트, false=디스패치(경로 노툴안함))
			forward=new ActionForward();
			forward.setRedirect(false);		
			forward.setPath("./index.jsp");  //이동할 페이지 경로 주소값 저장(회원가입 입력 페이지)
		}
		
		// 번개 개설 연결(insert.bg)////////////////////////////////////////////////////////////////////
				if(command.equals("/insert.bg")){
					//페이지 이동방식 선택(true=리다이렉트, false=디스패치(경로 노툴안함))
					action = new BungaeInsertAction();
					try {
						forward = action.execute(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
	
		// 공통사항 처리 (포워딩)////////////////////////////////////////////////////////////////////
		if(forward!=null){
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath());
			}else{	//디스패치 방식으로 페이지 경로 노출 없이 포워딩
				RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);}
		
		}// 공통사항 if 종료		
	} // doProcess() 종료	
} // 서블릿 종료