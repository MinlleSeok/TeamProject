// 네이버 API 예제 - 회원프로필 조회
package member;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class NaverLoginCheck extends HttpServlet {

        @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(req, resp);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String clientId = "uKiP2ZjjUHfxjMGZTcN7";//애플리케이션 클라이언트 아이디값";
	    String clientSecret = "3_3M0K08dN";//애플리케이션 클라이언트 시크릿값";
	    String code = request.getParameter("code");
	    String state = request.getParameter("state");
	    String redirectURI = URLEncoder.encode("http://localhost:8090/mmProject/NaverLoginCheck", "UTF-8");
	    String apiURL;
	    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
	    apiURL += "client_id=" + clientId;
	    apiURL += "&client_secret=" + clientSecret;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&code=" + code;
	    apiURL += "&state=" + state;
	    String access_token = "";
	    String refresh_token = "";
	    System.out.println("apiURL="+apiURL);
	    
	    try {
	    	
	      URL url = new URL(apiURL);
	      HttpURLConnection con = (HttpURLConnection)url.openConnection();
	      con.setRequestMethod("GET");
	      
	      int responseCode = con.getResponseCode();
	      BufferedReader br;
	      System.out.print("responseCode="+responseCode);
	      
	      if(responseCode==200) { // 정상 호출
	        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	      } else {  // 에러 발생
	        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	      }
	      
	      String inputLine;
	      StringBuffer res = new StringBuffer();
	      
	      while ((inputLine = br.readLine()) != null) {
	        res.append(inputLine);
	      }
	      
	      br.close();
	      
	      if(responseCode==200) {
	    	    System.out.println(res.toString());
	    	    JSONParser parsing = new JSONParser();
	    	    Object obj = parsing.parse(res.toString());
	    	    JSONObject jsonObj = (JSONObject)obj;
	    	                 
	    	    access_token = (String)jsonObj.get("access_token");
	    	    refresh_token = (String)jsonObj.get("refresh_token");
	      }
	      
	      if(access_token != null) {
	    	  try {
	    		  String apiurl = "https://openapi.naver.com/v1/nid/me";
	    		  String header = "Bearer " + access_token; // Bearer 다음에 공백 추가
	    		  URL url2 = new URL(apiurl);
	    		  HttpURLConnection con2 = (HttpURLConnection)url2.openConnection();
	    		  con2.setRequestMethod("GET");
	              con2.setRequestProperty("Authorization", header);
	              int responseCode2 = con2.getResponseCode();
	              BufferedReader br2;
	              
	              if(responseCode2==200) { // 정상 호출
	                  br2 = new BufferedReader(new InputStreamReader(con2.getInputStream()));
	              } else {  // 에러 발생
	                  br2 = new BufferedReader(new InputStreamReader(con2.getErrorStream()));
	              }
	              
	              String inputLine2;
	              StringBuffer res2 = new StringBuffer();
	              
	              while ((inputLine2 = br2.readLine()) != null) {
	                  res2.append(inputLine2);
	              }
	              
	              br2.close();
	              System.out.println(res2.toString());
	              
	              JSONParser parsing2 = new JSONParser();
		    	  Object obj2 = parsing2.parse(res2.toString());
		    	  JSONObject jsonObj2 = (JSONObject)obj2;
		    	  JSONObject resObj2 = (JSONObject)jsonObj2.get("response");
		    	  
		    	  String naverCode = (String)resObj2.get("id");
		    	  String email = (String)resObj2.get("email");
		    	  String name = (String)resObj2.get("name");
		    	  String nickName = (String)resObj2.get("nickname");
		    	  
		    	  String splitToken = access_token.substring(1, 3) + access_token.substring(40, 43);
		    	  
		    	  HttpSession session = request.getSession();
		    	  session.setAttribute("id", email);
		    	  
		    	  // RequestDispatcher dis = request.getRequestDispatcher("../index.jsp");
		    	  // dis.forward(request, response);
		    	  response.sendRedirect("index.jsp");
		    	  
	    	  } catch (Exception e) {
	    		  e.printStackTrace();
	    	  }
	      }
	    } catch (Exception e) {
	      System.out.println(e);
	    }
		

	}
	
		

}