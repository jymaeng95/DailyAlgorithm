package com.algorithm.Jungol;

import java.util.Scanner;

public class Main_1402 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int num = scan.nextInt();
		int index = scan.nextInt();
		
		int count=0;
		for(int i=1;i<=num;i++) {
			if(num%i==0) {
				count++;
			}
			if(count==index) {
				System.out.println(i);
				break;
			}
		}
		if(count<index) {
			System.out.println(0);
		}
	}

}
