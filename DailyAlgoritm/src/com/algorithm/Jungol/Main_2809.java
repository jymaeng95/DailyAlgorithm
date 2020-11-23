package com.algorithm.Jungol;

import java.util.Scanner;

public class Main_2809 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();
		if(t==0 || t==1) {
			t= scan.nextInt();
		}
		
		for(int i=1;i<=t;i++) {
			if(t%i==0) {
				System.out.print(i+" ");
			}
		}
	}
	

}
