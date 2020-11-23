package com.algorithm.Jungol;

import java.util.Scanner;

public class Main_1157 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();
		int[] arr = new int[t];
		for(int i=0;i<arr.length;i++) {
			arr[i] = scan.nextInt();
		}
		
		int tmp;
		for(int i=0;i<t-1;i++) {
			for(int j=1;j<t;j++) {
				if(arr[j]<arr[j-1]) {
					tmp = arr[j];
					arr[j] = arr[j-1];
					arr[j-1] = tmp;
				}
			}
			for(int k=0;k<arr.length;k++) {
				System.out.print(arr[k] +" ");
			}
			System.out.println();
		}
	}

}
