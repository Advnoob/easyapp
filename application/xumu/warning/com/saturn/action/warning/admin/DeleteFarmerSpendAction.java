package com.saturn.action.warning.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saturn.app.web.IAction;
import com.saturn.app.web.IView;
import com.saturn.app.web.view.JspView;
import com.saturn.warning.date.farmer.FarmerSpend;

public class DeleteFarmerSpendAction implements IAction {

	public IView execute(HttpServletRequest request,
			HttpServletResponse response) {

		String idStr = request.getParameter("ids");
		String[] ids = idStr.split("__");
		
		FarmerSpend.removes(ids);
		return new JspView("/app/warning/admin/farmerShow.jsp");
	}

	public String requestMapping() {
		return "/app/warning/farmer/deleteFarmerSpend.action";
	}

}
