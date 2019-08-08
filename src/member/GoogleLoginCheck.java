package member;


import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;



@SuppressWarnings("serial")
public class GoogleLoginCheck extends HttpServlet {
	
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();
	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	private static final String CLIENT_ID = "668617944084-5is0mb0j9q7p9pp2rugimnttsfi6mdld.apps.googleusercontent.com";  
	  
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}



	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(HTTP_TRANSPORT, JSON_FACTORY)
			    // Specify the CLIENT_ID of the app that accesses the backend:
			    .setAudience(Collections.singletonList(CLIENT_ID))
			    // Or, if multiple clients access the backend:
			    //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
			    .build();

			// (Receive idTokenString by HTTPS POST)
			String idTokenString = req.getParameter("idtoken");
			GoogleIdToken idToken = null;
			try {
				idToken = verifier.verify(idTokenString);
			} catch (GeneralSecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (idToken != null) {
			  Payload payload = idToken.getPayload();

			  // Print user identifier
			  String userId = payload.getSubject();
			  System.out.println("User ID: " + userId);

			  // Get profile information from payload
			  String email = payload.getEmail();
			  boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
			  String name = (String) payload.get("name");
			  String pictureUrl = (String) payload.get("picture");
			  String locale = (String) payload.get("locale");
			  String familyName = (String) payload.get("family_name");
			  String givenName = (String) payload.get("given_name");
			  
			  String subId = userId.substring(0, 7);
			  // Use or store profile information
			  // ...
			  System.out.println(userId);
			  /*
			  MemberDAO dao = new MemberDAO();
			  if(dao.idCheck(subId) == 0) {
				  MemberBean memberBean = new MemberBean();
				  memberBean.setEmail(email);
				  memberBean.setName(name);
				  memberBean.setId(subId);
				  memberBean.setPasswd(subId+"*");
				  memberBean.setReg_date(new Timestamp(System.currentTimeMillis()));
				  dao.insertMember(memberBean);
				  HttpSession session = req.getSession();
				  session.setAttribute("id", subId);
				  
				  RequestDispatcher dis = this.getServletContext().getRequestDispatcher("/index.jsp");
				  dis.forward(req, resp);
			  } else {*/
				  HttpSession session = req.getSession();
				  session.setAttribute("id", subId);
				  
				  /*RequestDispatcher dis = this.getServletContext().getRequestDispatcher("/index.jsp");
				  dis.forward(req, resp);*/
			  /*}*/
			  
			} else {
			  System.out.println("Invalid ID token.");
			}
	}
	
}