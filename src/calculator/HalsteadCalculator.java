package calculator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class HalsteadCalculator {
	public static void main(String[] args) {
		
		BufferedReader br = null;
		BufferedReader br2 = null;
		try {
			br = new BufferedReader(new FileReader("F:\\CityGuide.java"));
			br2 = new BufferedReader(new FileReader("F:\\CityGuide.java"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CountOprerator countOprerator = new CountOprerator();
		int N1 = countOprerator.countN1(br);
		int n1 = countOprerator.countn1(br2);
				
		System.out.println("Tong so toan tu N1 = "+N1);
		System.out.println("So toan tu n1 = "+n1);
	}
}
