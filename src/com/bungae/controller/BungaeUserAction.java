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
import com.bungae.db.BungaeUserDTO;

public class BungaeUserAction implements Action {

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");

		String mmNum2 = request.getParameter("mmNum");
		String bgNum2 = request.getParameter("bgNum");

		int mmNum = Integer.parseInt(mmNum2);
		int bgNum = Integer.parseInt(bgNum2);

		BungaeDAO bdao = new BungaeDAO();
		ArrayList<BungaeUserDTO> bungaeUserList = bdao.selectBungaeUser(mmNum, bgNum);

		JSONObject obj = new JSONObject();
		JSONArray jArray = new JSONArray();// 배열이 필요할때
		for (int i = 0; i < bungaeUserList.size(); i++)// 배열
		{
			JSONObject sObject = new JSONObject();// 배열 내에 들어갈 json
			sObject.put("bgNum", bungaeUserList.get(i).getBgNum());
			sObject.put("num", bungaeUserList.get(i).getNum());
			sObject.put("mmNum", bungaeUserList.get(i).getMmNum());
			sObject.put("userName", bungaeUserList.get(i).getUserName());
			sObject.put("userPhoto", bungaeUserList.get(i).getUserPhoto());
			jArray.add(sObject);
		}
		obj.put("item", jArray);// 배열을 넣음

		PrintWriter pw = response.getWriter();

		pw.print(obj.toString());

		pw.flush();
		System.out.println(obj.toString());

		return null;
	}

}
