package com.algorithm.Jungol;

import java.util.Scanner;

public class Main_1146 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();
	
		int[] arr = new int[t];
		for(int i=0;i<arr.length;i++) {
			arr[i] =scan.nextInt();
		}
		int min, tmp, index;
		for(int i=0;i<t-1;i++) {
			min = arr[i];
			index = i;
			for(int j=i+1;j<t;j++) {
				if(min>arr[j]) {
					min = arr[j];
					index = j;
				}
			}
			tmp = arr[i];
			arr[i] = arr[index];
			arr[index] = tmp;
			for(int k=0;k<arr.length;k++) {
				System.out.print(arr[k] + " ");
			}
			System.out.println();
		}
	}

}
