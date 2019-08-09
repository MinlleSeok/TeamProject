package com.bungae.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.action.Action;
import com.action.ActionForward;
import com.bungae.beans.Bungae;
import com.bungae.beans.BungaeDAO;

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
		Bungae bean = new Bungae();
		bean.setSubject(bungaeName);
		bean.setMmNum(1);
		bean.setContent(bungaeRef);
		bean.setMax(Integer.parseInt(bungaeMax));
		
		
		bdao.insertBungae(bean);
		
		
		return null;
	}

}
