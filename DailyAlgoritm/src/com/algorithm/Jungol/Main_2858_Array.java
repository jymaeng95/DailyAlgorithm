package com.algorithm.Jungol;

import java.util.Scanner;

public class Main_2858_Array {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		String type = scan.next();
		String[] test = type.split("");
		int totalCount=0;
		for(int i=0;i<test.length-1;i++) {
			int laserCount=0;
			int openRunner=0;
			int closeRunner=0;
			if(test[i].equals("(")) {
				for(int j=i;j<test.length;j++) {
					if(test[j].equals("(")) {
						openRunner++;
						if(test[j+1].equals(")")) {
							laserCount++;
						}
					}else {
						closeRunner++;
					}						
					if(openRunner == closeRunner) {
						if(openRunner==1) {
							break;
						}else {
							totalCount += laserCount+1;
							break;
						}
					}
				}
			}
		}
		System.out.println(totalCount);
	}

}
