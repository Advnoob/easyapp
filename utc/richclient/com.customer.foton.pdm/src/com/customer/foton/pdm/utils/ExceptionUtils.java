package com.customer.foton.pdm.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * ���ڻ���쳣�Ķ�ջ��Ϣ
 * 
 * @author LeslieGu
 */
public class ExceptionUtils {
	
	private ExceptionUtils() {
		
	}
	
	/**
	 * ����쳣�Ķ�ջ��Ϣ
	 */
	public static String getExMessage(Exception e) {
		String result = null;
		PrintWriter writer = null;
		try {
			Writer w = new StringWriter();
			writer = new PrintWriter(w);
			e.printStackTrace(writer);
			writer.flush();

			result = w.toString();
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			writer.close();
		}
		return result;
	}
}
