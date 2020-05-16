package calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class CountOprerator {
	
	String operator[] = {"<",">","=","+","-","*","%",";",",","(",")","[","]","{","}",".","/"};
	String keyWord[] = {"++","--","abstract","assert","boolean","break","byte","case","catch","char","class","const",
			"continue","default","do","double","else","enum","extends","final","finally","float","for","goto","if",
			"implements","import","instanceof","int","interface","long","native","new","package","private","protected",
			"public","return","short","static","super","switch","synchronized","this","throw","transient","try","void",
			"volatile","while"};
	
	public int countN1(BufferedReader br){
		int count =0;
		String textInALine;
        if(br != null){
        	try {
				while ((textInALine = br.readLine()) != null) {
					StringBuffer stringBuffer = new StringBuffer(textInALine);
				    StringTokenizer token = new StringTokenizer(textInALine);
				    
				    while(token.hasMoreTokens()){
				    	String value = token.nextToken();
				    	for(int i=0;i<keyWord.length;i++){
				    		if(value.contains(keyWord[i])){
				    			count++;
				    		}
				    	}
				    }
				                
					for(int i=0;i<stringBuffer.length();i++){
				    	for(int j = 0;j<operator.length;j++){
				    		if(String.valueOf(stringBuffer.charAt(i)).equals(operator[j]) ){
				    			count++;
				    		}
				    	}          
				    }                                           
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
		return count;
	}
	
	public int countn1(BufferedReader br){
		int count =0;
		String textInALine;
        if(br != null){
        	try {
				while ((textInALine = br.readLine()) != null) {
					StringBuffer stringBuffer = new StringBuffer(textInALine);
				    StringTokenizer token = new StringTokenizer(textInALine);
				    
				    while(token.hasMoreTokens()){
				    	String value = token.nextToken();
				    	for(int i=0;i<keyWord.length;i++){
				    		if(value.contains(keyWord[i])){
				    			count++;
				    			keyWord[i] = "...";
				    		}
				    	}
				    }
				                
					for(int i=0;i<stringBuffer.length();i++){
				    	for(int j = 0;j<operator.length;j++){
				    		if(String.valueOf(stringBuffer.charAt(i)).equals(operator[j]) ){
				    			count++;
				    			operator[j] = "...";
				    		}
				    	}          
				    }                                           
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		return count;
	}
}
