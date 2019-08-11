package com.bungae.controller;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.action.Action;
import com.action.ActionForward;
import com.bungae.db.BungaeDAO;
import com.bungae.db.BungaeDTO;

public class BungaeMoreAction implements Action {

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String mmNum2 = request.getParameter("mmNum");
		String idx2 = request.getParameter("idx");
		int mmNum = Integer.parseInt(mmNum2);
		int idx = Integer.parseInt(idx2);
		BungaeDAO bdao = new BungaeDAO();
		ArrayList<BungaeDTO> bungaeList = bdao.selectBungae(mmNum,idx);
		
		JSONObject obj = new JSONObject();
			JSONArray jArray = new JSONArray();//배열이 필요할때
			for (int i = 0; i < bungaeList.size(); i++)//배열
			{
			JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
			sObject.put("bungaeName", bungaeList.get(i).getSubject());
			sObject.put("bungaeRef", bungaeList.get(i).getContent());
			sObject.put("bungaeDate", bungaeList.get(i).getBdate().toString());
			sObject.put("bungaeMax", bungaeList.get(i).getMax());
			sObject.put("bungaeNum", bungaeList.get(i).getNum());
			jArray.add(sObject);
			}
			obj.put("item", jArray);//배열을 넣음

			PrintWriter pw = response.getWriter();
			
			pw.print(obj.toString());
			pw.flush();
			System.out.println(obj.toString());
			
			//forward.setRedirect(false);
			//forward.setPath("#");
			
		return null;
	}

}
