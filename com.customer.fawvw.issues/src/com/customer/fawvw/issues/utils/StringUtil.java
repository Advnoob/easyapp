package com.customer.fawvw.issues.utils;

import java.util.Arrays;

public class StringUtil {
	
	public static boolean containsNo(String contents, String substr) {
		//contents��Ϊ��
		if (!" ".equals(contents)) { //$NON-NLS-1$
			//����,�����ڣ�
			if (contents.contains(",") && !contents.contains("��")) { //$NON-NLS-1$ //$NON-NLS-2$
				String arr[] = contents.split(","); //$NON-NLS-1$
				for (int i=0; i<arr.length; i++) {
					if (arr[i].equalsIgnoreCase(substr)){
						System.out.println("111111111");
						return true;
					}
				}
			}
			
			//������,���ڣ�
			if (!contents.contains(",") && contents.contains("��")) { //$NON-NLS-1$ //$NON-NLS-2$
				String arr[] = contents.split("��"); //$NON-NLS-1$
				for (int i=0; i<arr.length; i++) {
					if (arr[i].equalsIgnoreCase(substr)){
						System.out.println("22222222");
						return true;
					}
				}
			}
			
			//������,Ҳ�����ڣ�
			if (!contents.contains(",") && !contents.contains("��")) { //$NON-NLS-1$ //$NON-NLS-2$
				if (contents.equalsIgnoreCase(substr)){
					//�����Ӵ�
					System.out.println("333333333");
					return true;
				} else {
					//�������Ӵ�
					return false;
				}
			}
			
			//����,Ҳ���ڣ�
			if (contents.contains(",") && contents.contains("��")) { //$NON-NLS-1$ //$NO"��","); //$NON-NLS-1$
				String arr[] = contents.split(","); //$NON-NLS-1$
				for (int i=0; i<arr.length; i++) {
					if (arr[i].equalsIgnoreCase(substr)) {
						return true;
					} else {
						String arr2[] = arr[i].split("��"); //$NON-NLS-1$
						for (int j=0; j<arr2.length; j++) {
							if (arr2[j].equalsIgnoreCase(substr)) {
								System.out.println("4444444444");
								return true;
							}
						}
					}
					
				}
			}
			
		} else {
			return false;
		}
		
		return false;
	}
	
	public static String ArrayToString(String[] array) {
		String str = "";
		
		for (int i = 0; i < array.length; i++) {
			str += array[i] + ",";
		}
		
		if (!"".equals(str)) {
			str = str.substring(0, str.length()-1);
		}
		
		return str;
	}
}
