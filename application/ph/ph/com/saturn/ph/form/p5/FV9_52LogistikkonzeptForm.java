package com.saturn.ph.form.p5;

import com.saturn.ph.Form;

public class FV9_52LogistikkonzeptForm extends Form {
	
	private static final String[] attr = {
		"fv9LogistikStyle", "fv9LogistikStatus", "fv9LogistikStatCom", "fv9LogistikStatMab"
	};

	public String[] getAttributes() {
		return attr;
	}

	public String getJspPath() {
		return "/app/pep/5/p2_1.jsp";
	}

	@Override
	public String getType() {
		return "FV9_52Logistikkonzept";
	}

	@Override
	public String getTitle() {
		return "5.2 Logistikkonzept";
	}
}
