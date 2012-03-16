package com.customer.fawvw.issues.commands.issuestatistic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PartTypeStatusLoader {

	private static Map<String, Object> partType = new HashMap<String, Object>();
	
    private static Map<String, Integer> partStatus = new HashMap<String, Integer>(); //���״̬
    private static Map<String, Integer> bodySize = new HashMap<String, Integer>(); //����ߴ�
    private static Map<String, Integer> partMatch = new HashMap<String, Integer>(); //���ƥ��
    private static Map<String, Integer> pdm = new HashMap<String, Integer>(); //PDM
    private static Map<String, Integer> structure = new HashMap<String, Integer>(); //�ṹ
    private static Map<String, Integer> quality = new HashMap<String, Integer>(); //�������
    private static Map<String, Integer> detail = new HashMap<String, Integer>(); //��ϸ��
    private static Map<String, Integer> other = new HashMap<String, Integer>(); //����
    private static Map<String, Integer> device = new HashMap<String, Integer>(); //���ռ��豸
    private static Map<String, Integer> change = new HashMap<String, Integer>(); //����
    
	
	public static  Map<String,Object> load(ArrayList<HashMap<String, Object>> values){
		
		partStatus.put("red", 0); 
		partStatus.put("green", 0); 
		partStatus.put("yellow", 0); 
		
		bodySize.put("red", 0); 
		bodySize.put("green", 0); 
		bodySize.put("yellow", 0); 
		
		partMatch.put("red", 0); 
		partMatch.put("green", 0); 
		partMatch.put("yellow", 0); 
		
		pdm.put("red", 0); 
		pdm.put("green", 0); 
		pdm.put("yellow", 0); 
		
		structure.put("red", 0); 
		structure.put("green", 0); 
		structure.put("yellow", 0); 
		
		quality.put("red", 0); 
		quality.put("green", 0); 
		quality.put("yellow", 0); 
		
		detail.put("red", 0); 
		detail.put("green", 0); 
		detail.put("yellow", 0); 
		
		other.put("red", 0); 
		other.put("green", 0); 
		other.put("yellow", 0); 
		
		device.put("red", 0); 
		device.put("green", 0); 
		device.put("yellow", 0); 
		
		change.put("red", 0); 
		change.put("green", 0); 
		change.put("yellow", 0); 
		
		for (int k=0; k<values.size(); k++) {
			String parttype = (String)(values.get(k)).get("fv9IssueType");
			if ("���״̬".equals(parttype)) {   
				partStatus = sumLightStatue(partStatus, (String)(values.get(k)).get("fv9RGStatus")); 
			}
			if ("����ߴ�".equals(parttype)) {   
				bodySize = sumLightStatue(bodySize, (String)(values.get(k)).get("fv9RGStatus")); 
			}
			if ("���ƥ��".equals(parttype)) {  
				partMatch = sumLightStatue(partMatch, (String)(values.get(k)).get("fv9RGStatus")); 
			}
			if ("PDM".equals(parttype)) {  
				pdm = sumLightStatue(pdm, (String)(values.get(k)).get("fv9RGStatus")); 
			}
			if ("��ϸ��".equals(parttype)) {
				detail = sumLightStatue(detail, (String)(values.get(k)).get("fv9RGStatus")); 
			}
			if ("�ṹ".equals(parttype)) {
				structure = sumLightStatue(structure, (String)(values.get(k)).get("fv9RGStatus")); 
			}
			if ("�������".equals(parttype)) {
				quality = sumLightStatue(quality, (String)(values.get(k)).get("fv9RGStatus")); 
			}
			if ("����".equals(parttype)) {
				other = sumLightStatue(other, (String)(values.get(k)).get("fv9RGStatus")); 
			}
			if ("���ռ��豸".equals(parttype)) {
				device = sumLightStatue(device, (String)(values.get(k)).get("fv9RGStatus")); 
			}
			if ("����".equals(parttype)) {
				change = sumLightStatue(change, (String)(values.get(k)).get("fv9RGStatus")); 
			}
			
		}
		
		partType.put("partStatus", partStatus); 
		partType.put("bodySize", bodySize); 
		partType.put("partMatch", partMatch); 
		partType.put("pdm", pdm); 
		partType.put("structure", structure); 
		partType.put("quality", quality); 
		partType.put("detail", detail); 
		partType.put("other", other); 
		partType.put("device", device); 
		partType.put("change", change); 
		
		return partType;
				
	} 
	
	public static Map<String,Integer> sumLightStatue (
			Map<String,Integer> parts, 
			String lightStatue){
		
		if ("��".equals(lightStatue)) { 
			parts.put("red", (Integer)parts.get("red")+1);  
		}
		if ("��".equals(lightStatue)) { 
			parts.put("yellow", (Integer)parts.get("yellow")+1);  
		}
		if ("��".equals(lightStatue)) { 
			parts.put("green", (Integer)parts.get("green")+1); 
		}
		
		return parts;
	}
}
