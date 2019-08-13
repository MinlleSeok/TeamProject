package com.bungae.controller;


import java.net.URLDecoder;

import java.sql.Timestamp;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.action.Action;
import com.action.ActionForward;
import com.bungae.db.BungaeDTO;
import com.bungae.db.BungaeDAO;

public class BungaeInsertAction implements Action {

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String jsonStr = request.getParameter("x");
		System.out.println(jsonStr);
		
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = (JSONObject)parser.parse(jsonStr);
		System.out.println(jsonObj);
		
		System.out.println(URLDecoder.decode(jsonObj.get("bungaeName").toString(),"UTF-8"));
		System.out.println(jsonObj.get("bungaeName").toString());
		
		System.out.println((String)jsonObj.get("bungaeName"));
		
		String bungaeDate2 = ((String) jsonObj.get("bungaeDate")) + " " + ((String) jsonObj.get("bungaeHour")) + ":" + ((String) jsonObj.get("bungaeMinute")) + ":00";
		String bungaeName = jsonObj.get("bungaeName").toString();
		String bungaeRef = (String)jsonObj.get("bungaeRef");
		Timestamp bungaeDate = Timestamp.valueOf(bungaeDate2);
		//SimpleDateFormat date = new SimpleDateFormat();
		//Date date2 = (Date) date.parseObject(bungaeDate);  
		String bungaeMax = (String)jsonObj.get("bungaeMax");
		
		
		BungaeDAO bdao = new BungaeDAO();
		BungaeDTO bdto = new BungaeDTO();
		ActionForward forward = new ActionForward();
		
		bdto.setSubject(bungaeName);
		bdto.setMmNum(1);
		bdto.setContent(bungaeRef);
		bdto.setBdate(bungaeDate);
		bdto.setMax(Integer.parseInt(bungaeMax));
		
		
		boolean result = bdao.insertBungae(bdto);
		if(result == true){
			
				
				forward.setRedirect(false);
				forward.setPath("#");

			
		}
		
		return forward;
	}

}
