package com.algorithm.Jungol;

import java.util.Scanner;

public class Main_1430 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int num1 = scan.nextInt();
		int num2 = scan.nextInt();
		int num3 = scan.nextInt();
		String[] result = Integer.toString(num1*num2*num3).split("");
		
		int[] count = new int[10];
		for(int i=0;i<result.length;i++) {
			switch(result[i]) {
			case "0" :
				count[0]++;
				break;
			case "1" :
				count[1]++;
				break;
			case "2" :
				count[2]++;
				break;
			case "3" :
				count[3]++;
				break;
			case "4" :
				count[4]++;
				break;
			case "5" :
				count[5]++;
				break;
			case "6" :
				count[6]++;
				break;
			case "7" :
				count[7]++;
				break;
			case "8" :
				count[8]++;
				break;
			case "9" :
				count[9]++;
				break;
			}
		}
		for(int i=0;i<count.length;i++) {
			System.out.println(count[i]);
		}
	}

}
