package Util;

import java.util.ArrayList;
import java.util.Arrays;

public class Util {
	String operator[] = {"<",">","=","+","-","*","%",";",",","(",")","[","]","{","}",".","/"};
	ArrayList<String> operators = new ArrayList<String>(Arrays.asList(operator));
	public Result Split(String file) {
		Result result = new Result();
		while(file.indexOf("/*")!=-1) {
			result.blockCmts.add(file.substring(file.indexOf("/*"),file.indexOf("*/",file.indexOf("/*"))+2));
			file=file.substring(0,file.indexOf("/*")-1)+file.substring(file.indexOf("*/",file.indexOf("/*"))+2);
			continue;
		}
		//nếu thấy cmt, cắt hết từ nó tới cuối dòng
		while(file.indexOf("//")!=-1) {
			result.lineCmts.add(file.substring(file.indexOf("//"),file.indexOf("\n",file.indexOf("//"))+2));
			file=file.substring(0,file.indexOf("//")-1)+file.substring(file.indexOf("\n",file.indexOf("//")+2));
			continue;
		}

		while(file.indexOf("\"")!=-1) {
			int begin = file.indexOf("\"");
			//tìm dấu hết string
			for(int i=begin+1 ; i<file.length();i++) {
				//i là điểm cần tìm
				if(file.charAt(i)=='\"'&&file.charAt(i-1)!='\\') {
					result.strs.add(file.substring(begin,i+1));
					file=file.substring(0,begin)+file.substring(i+1);
					break;
				}
			}
			continue;
		}
		file=file.replace("(", " ( ");
		file=file=file.replace(")", " ) ");
		file=file.replace("[", " [ ");
		file=file.replace("]", " ] ");
		file=file.replace(",", " , ");
		file=file.replace("=", " = ");
		boolean check = false;//kiểm tra bắt đầu class
		  for (String line : file.split("\\n")) {
			  if(line.indexOf("class")!=-1) {
				  check = true;
			  }
			  if(check!=true) {
				  continue;
			  }
			  //tách từ, ký tự
			  for (String w : line.split("\\s")) {
				  if(w!="\\s"&&w!="\\t"&&w!="\\n") {
					  if(w.length()!=0) {
						  result.words.add(w);
					  }
				  }
			  }
		  }
		  
		return result;
	}
}