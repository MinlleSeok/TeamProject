package com.bungae.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.action.Action;
import com.action.ActionForward;
import com.bungae.db.BungaeDTO;
import com.bungae.db.BungaeDAO;

public class BungaeInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		String jsonStr = request.getParameter("x");
		
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = (JSONObject)parser.parse(jsonStr);
		String bungaeName = (String)jsonObj.get("bungaeName");
		String bungaeRef = (String)jsonObj.get("bungaeRef");
		String bungaeDate = (String)jsonObj.get("ungaeDate");
		String bungaeMax = (String)jsonObj.get("bungaeMax");
		
		BungaeDAO bdao = new BungaeDAO();
		BungaeDTO bdto = new BungaeDTO();
		ActionForward forward = new ActionForward();
		
		bdto.setSubject(bungaeName);
		bdto.setMmNum(1);
		bdto.setContent(bungaeRef);
		bdto.setMax(Integer.parseInt(bungaeMax));
		
		
		boolean result = bdao.insertBungae(bdto);
		if(result == true){
			ArrayList<BungaeDTO> bungaeList = bdao.selectBungae(1);
			
			JSONObject obj = new JSONObject();
				JSONArray jArray = new JSONArray();//배열이 필요할때
				for (int i = 0; i < bungaeList.size(); i++)//배열
				{
				JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
				sObject.put("bungaeName", bungaeList.get(i).getSubject());
				sObject.put("bungaeRef", bungaeList.get(i).getContent());
				sObject.put("bungaeDate", bungaeList.get(i).getBdate());
				sObject.put("bungaeMax", bungaeList.get(i).getMax());
				jArray.add(sObject);
				}
				obj.put("item", jArray);//배열을 넣음
	
				System.out.println(obj.toString());


			
		}
		
		return null;
	}

}
