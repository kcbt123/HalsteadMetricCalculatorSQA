package gui;

import java.util.ArrayList;

import Util.Util;

public class Test {

	public static void main(String[] args) {
		Util util = new Util();
		String file ="import java.io.*;  \r\n" + 
				"public class FileDemo2 {  \r\n" + 
				"    public static void main(String[] args) {  \r\n" + 
				"  \r\n" + 
				"    	/*\r\n" + 
				"    	 * \r\n" + 
				"    	 * 1\r\n" + 
				"    	 */\r\n" + 
				"        String path = \"\";  \r\n" + 
				"        boolean bool = false;  \r\n" + 
				"        try {  \r\n" + 
				"/*\r\n" + 
				"    	 * \r\n" + 
				"    	 * 2\r\n" + 
				"    	 */\r\n" + 
				"\r\n" + 
				"            // createing  new files  \r\n" + 
				"            File file  = new File(\"testFile1.txt\");  \r\n" + 
				"            file.createNewFile();  //�dghasgdh\r\n" + 
				"            System.out.println(file);  \r\n" + 
				"            // createing new canonical from file object  \r\n" + 
				"            File file2 = file.getCanonicalFile();  \r\n" + 
				"            // returns true if the file exists  \r\n" + 
				"            System.out.println(file2);  \r\n" + 
				"            bool = file2.exists();  \r\n" + 
				"            // returns absolute pathname  \r\n" + 
				"            path = file2.getAbsolutePath();  \r\n" + 
				"            System.out.println(bool);  \r\n" + 
				"            // if file exists  \r\n" + 
				"            if (bool) {  \r\n" + 
				"                // prints  \r\n" + 
				"                System.out.print(path + \" Exists? \" + bool);  \r\n" + 
				"            }  \r\n" + 
				"        } catch (Exception e) {  \r\n" + 
				"            // if any error occurs  \r\n" + 
				"            e.printStackTrace();  \r\n" + 
				"        }  \r\n" + 
				"    }  \r\n" + 
				"}  ";
		ArrayList<String> words= util.Split(file);
		int i=0;
		  for (String w : words) {
			   w=w.trim();
			   System.out.println(i+" "+w);
			   i++;
			  }
	}
}
