package com.saturn.action.sldb.person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saturn.app.web.IAction;
import com.saturn.app.web.IView;
import com.saturn.app.web.view.JspView;
import com.saturn.sldb.Person;

public class QueryAction implements IAction {

	public IView execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		String id = request.getParameter("ids");
		Person vo = Person.get(id);
		
		request.setAttribute("person", vo);
		return new JspView("/app/sldb/person/edit.jsp");
	}

	public String requestMapping() {
		return "/app/sldb/person/query.action";
	}

}
