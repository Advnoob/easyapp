package com.customer.fawvw.issues.commands.common.loader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MajorLoader {

	
private static Map<String, Object> managingMajors = new HashMap<String, Object>();
	
	
	private static Map<String, Integer> accoutrement;
	private static Map<String, Integer> car;
	private static Map<String, Integer> bodywork;
	private static Map<String, Integer> eleAppliances;
	private static Map<String, Integer> chassis;
	private static Map<String, Integer> total;
	
	
	public static  Map<String,Object> load(ArrayList<HashMap<String, Object>> values){
		accoutrement = new HashMap<String, Integer>();
		car = new HashMap<String, Integer>();
		bodywork = new HashMap<String, Integer>();
		eleAppliances = new HashMap<String, Integer>();
		chassis = new HashMap<String, Integer>();
		total  = new HashMap<String, Integer>();
		
		//װ��
		accoutrement.put("red", 0); //$NON-NLS-1$
		accoutrement.put("green", 0); //$NON-NLS-1$
		accoutrement.put("yellow", 0); //$NON-NLS-1$
		//����
		car.put("red", 0); //$NON-NLS-1$
		car.put("green", 0); //$NON-NLS-1$
		car.put("yellow", 0); //$NON-NLS-1$
		//����
		bodywork.put("red", 0); //$NON-NLS-1$
		bodywork.put("green", 0); //$NON-NLS-1$
		bodywork.put("yellow", 0); //$NON-NLS-1$
		//����
		eleAppliances.put("red", 0); //$NON-NLS-1$
		eleAppliances.put("green", 0); //$NON-NLS-1$
		eleAppliances.put("yellow", 0); //$NON-NLS-1$
		//����
		chassis.put("red", 0); //$NON-NLS-1$
		chassis.put("green", 0); //$NON-NLS-1$
		chassis.put("yellow", 0); //$NON-NLS-1$
		//�����ܳ�
		total.put("red", 0); //$NON-NLS-1$
		total.put("green", 0); //$NON-NLS-1$
		total.put("yellow", 0); //$NON-NLS-1$
		
		
		
		for (int k=0; k<values.size(); k++) {
			
			if ("װ��".equals((String)(values.get(k)).get("fv9ManagingMajor"))) {  //$NON-NLS-2$ //$NON-NLS-1$ //$NON-NLS-1$
				accoutrement = sumLightStatue(accoutrement,(String)(values.get(k)).get("fv9RGStatus")); //$NON-NLS-1$
			}
			
			if ("����".equals((String)(values.get(k)).get("fv9ManagingMajor"))) {  //$NON-NLS-2$ //$NON-NLS-1$ //$NON-NLS-1$
				car = sumLightStatue(car,(String)(values.get(k)).get("fv9RGStatus")); //$NON-NLS-1$
			}
			
			if ("����".equals((String)(values.get(k)).get("fv9ManagingMajor"))) {  //$NON-NLS-2$ //$NON-NLS-1$ //$NON-NLS-1$
				bodywork = sumLightStatue(bodywork,(String)(values.get(k)).get("fv9RGStatus")); //$NON-NLS-1$
			}
			
			if ("����".equals((String)(values.get(k)).get("fv9ManagingMajor"))) {  //$NON-NLS-2$ //$NON-NLS-1$ //$NON-NLS-1$
				eleAppliances = sumLightStatue(eleAppliances,(String)(values.get(k)).get("fv9RGStatus")); //$NON-NLS-1$
			}
			
			if ("����".equals((String)(values.get(k)).get("fv9ManagingMajor"))) {  //$NON-NLS-1$ //$NON-NLS-1$
				chassis = sumLightStatue(chassis,(String)(values.get(k)).get("fv9RGStatus")); //$NON-NLS-1$
			}
			
			if ("�����ܳ�".equals((String)(values.get(k)).get("fv9ManagingMajor"))) {  //$NON-NLS-2$ //$NON-NLS-1$ //$NON-NLS-1$
				total = sumLightStatue(total,(String)(values.get(k)).get("fv9RGStatus")); //$NON-NLS-1$
			}
			
		}
		
		managingMajors.put("accoutrement", accoutrement); //$NON-NLS-1$
		managingMajors.put("car", car); //$NON-NLS-1$
		managingMajors.put("bodywork", bodywork); //$NON-NLS-1$
		managingMajors.put("eleAppliances", eleAppliances); //$NON-NLS-1$
		managingMajors.put("chassis", chassis); //$NON-NLS-1$
		managingMajors.put("total", total); //$NON-NLS-1$
		
		return managingMajors;
				
	} 
	
	
	public static Map<String,Integer> sumLightStatue (Map<String,Integer> parts,String lightStatue){
		
		
		if ("��".equals(lightStatue)) { //$NON-NLS-1$
			parts.put("red", (Integer)parts.get("red")+1); //$NON-NLS-1$ //$NON-NLS-2$
		}
		if ("��".equals(lightStatue)) { //$NON-NLS-1$
			parts.put("yellow", (Integer)parts.get("yellow")+1); //$NON-NLS-1$ //$NON-NLS-2$
		}
		if ("��".equals(lightStatue)) { //$NON-NLS-1$
			parts.put("green", (Integer)parts.get("green")+1); //$NON-NLS-1$ //$NON-NLS-2$
		}
		
		return parts;
	}
	
}
