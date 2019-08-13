package com.bungae.controller;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.action.Action;
import com.action.ActionForward;
import com.bungae.db.BungaeDAO;
import com.bungae.db.BungaeDTO;

public class BungaeListAction implements Action {

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String mmNum2 = request.getParameter("mmNum");
		int mmNum = Integer.parseInt(mmNum2);
		BungaeDAO bdao = new BungaeDAO();
		ArrayList<BungaeDTO> bungaeList = bdao.selectBungae(mmNum);
		
		JSONObject obj = new JSONObject();
			JSONArray jArray = new JSONArray();//배열이 필요할때
			for (int i = 0; i < bungaeList.size(); i++)//배열
			{
			JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
			
			
			sObject.put("bungaeName", bungaeList.get(i).getSubject());
			sObject.put("bungaeRef", bungaeList.get(i).getContent());
			
			Timestamp date2 = bungaeList.get(i).getBdate();
			String bungaeDay = "";
			int bungaeDate = date2.getDate();
			//System.out.println(""+date2.getTime());
			String bungaeTime = "";
			if(date2.getHours() < 10){
				bungaeTime = "0"+date2.getHours() + ":";
			} else {
				bungaeTime = date2.getHours() + ":";
			}
			
			if(date2.getMinutes() < 10){
				bungaeTime += "0"+date2.getMinutes();
			} else{
				bungaeTime += date2.getMinutes();
			}
			
			switch (date2.getDay()) {
			case 0:
				bungaeDay = "일요일";
				break;
			case 1:
				bungaeDay = "월요일";
				break;
			case 2:
				bungaeDay = "화요일";
				break;
			case 3:
				bungaeDay = "수요일";
				break;
			case 4:
				bungaeDay = "목요일";
				break;
			case 5:
				bungaeDay = "금요일";
				break;
			case 6:
				bungaeDay = "토요일";
				break;
			default:
				break;
			}
			date2.getDay();
			
			sObject.put("bungaeDate", bungaeDate);
			sObject.put("bungaeTime", bungaeTime);
			sObject.put("bungaeDay", bungaeDay); 
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
