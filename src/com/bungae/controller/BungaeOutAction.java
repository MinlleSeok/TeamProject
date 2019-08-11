package com.bungae.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.action.ActionForward;
import com.bungae.db.BungaeDAO;

public class BungaeOutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String mmNum2 = request.getParameter("mmNum");
		String bgNum2 = request.getParameter("bgNum");
		
		int mmNum = Integer.parseInt(mmNum2);
		int bgNum = Integer.parseInt(bgNum2);
		
		BungaeDAO bdao = new BungaeDAO();
		int result = bdao.out(mmNum, bgNum, id);
		
		if(result > 0) {
			PrintWriter out = response.getWriter();
			out.print(result);
		}
		
		return null;
	}

}
