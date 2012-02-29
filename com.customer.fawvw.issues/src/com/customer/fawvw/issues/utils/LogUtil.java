package com.customer.fawvw.issues.utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

public class LogUtil {
	
	public static BufferedReader bufread;
	    //ָ���ļ�·��������
    private static String readStr =""; //$NON-NLS-1$
   
    public static String generateLogName(String reportName) {
    	String savePath = System.getenv("TEMP"); //$NON-NLS-1$
//    	int n = fms_home.indexOf("fcc");
//    	String savePath = (String)fms_home.subSequence(0, n) + "portal\\temp\\";
//    	System.out.println("savePath = " + savePath);
    	
		String sysDate = DateFormat.getDateInstance().format(new Date());
		
		String logName = reportName + "-" + sysDate + ".log"; //$NON-NLS-1$ //$NON-NLS-2$
		
		savePath += "\\" + logName; //$NON-NLS-1$
		
		return savePath;
    	
	}
    /** *//**
     * �����ı��ļ�.
     * @throws IOException 
     * 
     */
    public static File creatTxtFile(String path) throws IOException{
        
    	File filename = new File(path);
    	if (!filename.exists()) {
            filename.createNewFile();
            System.err.println(filename + "�Ѵ�����"); //$NON-NLS-1$
            
            
        } else {
        	System.err.println(filename + "�Ѵ��ڣ�"); //$NON-NLS-1$
        }
    	return filename;
    }
    
    /** *//**
     * ��ȡ�ı��ļ�.
     * 
     */
    public static String readTxtFile(File filename){
        String read;
        FileReader fileread;
        try {
            fileread = new FileReader(filename);
            bufread = new BufferedReader(fileread);
            try {
                while ((read = bufread.readLine()) != null) {
                    readStr = readStr + read+ "\r\n"; //$NON-NLS-1$
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return readStr;
    }


    
    /** *//**
     * д�ļ�.
     * 
     */
    public static void writeTxtFile(File filename, String newStr) throws IOException{
    	
        FileOutputStream fos = null;   
        OutputStreamWriter osw = null;   
        try {   
            fos = new FileOutputStream(filename);   
            osw = new OutputStreamWriter(fos, "UTF-8");    //$NON-NLS-1$
            osw.write(newStr);   
        } catch (Exception e) {   
            e.printStackTrace();   
        }finally{   
            if(osw!=null){   
                try {   
                    osw.close();   
                } catch (IOException e1) {   
                    e1.printStackTrace();   
                }   
            }   
            if(fos!=null){   
                try {   
                    fos.close();   
                } catch (IOException e1) {   
                    e1.printStackTrace();   
                }   
            }   
        }


    }

    
    public static String initContent() {
    	
		String initContent = "  ���ɱ�����־�ļ�  " + "\r\n" + " ����ʼʱ�䣺" +  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			DateFormat.getDateInstance().format(new Date()) + "\r\n" +  //$NON-NLS-1$
			" ---------��ʼ----------\r\n"; //$NON-NLS-1$
		
		return initContent;
		
	}
    
    public static void main(String[] args) {

	}

} 

