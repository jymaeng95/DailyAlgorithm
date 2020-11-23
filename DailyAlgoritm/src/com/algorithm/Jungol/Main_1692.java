package com.algorithm.Jungol;

import java.util.Scanner;

public class Main_1692 {

	public static void main(String[] args) {
		Scanner scan= new Scanner(System.in);
		int num1 = scan.nextInt();
		int num2 = scan.nextInt();
		int copy = num2;
		int hund = copy/100;
		copy%=100;
		int ten = copy/10;
		int one = copy%10;
		
		System.out.println(one*num1);
		System.out.println(ten*num1);
		System.out.println(hund*num1);
		System.out.println(num1*num2);
	}	

}
