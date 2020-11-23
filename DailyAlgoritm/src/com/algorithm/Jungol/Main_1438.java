package com.algorithm.Jungol;

import java.util.Scanner;

public class Main_1438 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();
		int[][] width = new int[100][100];
		int x=0;
		int y=0;
		for(int i=0;i<t;i++) {
			x = scan.nextInt();
			y = scan.nextInt();
			for(int j=x;j<x+10;j++) {
				for(int k=y;k<y+10;k++) {
					width[j][k] = 1;
				}
			}
		}
		int count=0;
		for(int i=0;i<width.length;i++) {
			for(int j=0;j<width[i].length;j++) {
				if(width[i][j] == 1) {
					count++;
				}
			}
		}
		System.out.println(count);
	}

}
