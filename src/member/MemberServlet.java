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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doProcess(request,response);System.out.println("�ΰ�");}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doProcess(request,response);System.out.println("������Ʈ");}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("���� �۵�");
		
		// ��û�� �����ּҰ� ���(���ؽ�Ʈ �н������ּ�-���ؽ�Ʈ���� = ���� ���)
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		int contextPathLength= contextPath.length();
		String command = RequestURI.substring(contextPathLength);// ��ü�ּҿ��� ��û�ּҸ� �߶� ���
		System.out.println("1:"+RequestURI);
		System.out.println("2:"+contextPath);
		System.out.println("3:"+contextPathLength);
		System.out.println("command��������:"+command);
		
		//doProcess �������� ����
		ActionForward forward = null;
		Action action=null;		
		
		// ��û�ּ�(command)�� ���� �۾� ó�� �����ϱ�
		
// ȸ������ ���� ����(userJoin.me)////////////////////////////////////////////////////////////////////
	if(command.equals("/userJoin.me")){
		//������ �̵���� ����(true=�����̷�Ʈ, false=����ġ(��� ��������))
		forward=new ActionForward();
		forward.setRedirect(false);		
		forward.setPath("./member/userJoin.jsp");  //�̵��� ������ ��� �ּҰ� ����(ȸ������ �Է� ������)
		
		
// ���������� ����(mm.me)////////////////////////////////////////////////////////////////////	
	}else if(command.equals("/mm.me")){
		//������ �̵���� ����(true=�����̷�Ʈ, false=����ġ(��� �������))
		forward=new ActionForward();
		forward.setRedirect(false);
		
		//�̵��� ������ ��� �ּҰ� ����
		forward.setPath("/index.jsp");	

		
// īī���� ȸ������ ���̵� ���� ���� Ȯ�� �� id���� �� �α��� ó��. idChk		
	}else if(command.equals("/snsIdChk.me")){
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html, charset=utf-8");
						
		System.out.println("sns���̵�üũ ȣ�� ��.");	
		
		// ���۵� id�� DB�� ID���� ���� Ȯ�� �� ajax����� �̿��Ͽ� ������� ���� �Ѵ�.
		String userId = request.getParameter("userId");	
		System.out.println("�������� �Ѿ�� Id�� :"+userId);
		
		
		action=new snsIdChkAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	//īī���� ȸ������ �׼� 	
	}else if(command.equals("/snsUserJoinAction.me")){
		System.out.println("sns�α��� ȣ���");
		
		String userId = request.getParameter("userId");			
		System.out.println("snsUserJoin ȣ�� id :"+userId);
		
		action=new snsUserJoinAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			System.out.println("snsUserJoinAction����"+e);
		}	
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
