package com.algorithm.Jungol;

import java.util.Scanner;

public class Main_1733 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int [][] pan = new int[19][19];
		for(int i=0;i<pan.length;i++) {
			for(int j=0;j<pan[i].length;j++) {
				pan[i][j] = scan.nextInt();
			}
		}
		int row=0,column=0;
		boolean white=false;
		boolean black=false;
		int color = 0;
		for(int i=0;i<pan.length;i++) {
			for(int j=0;j<pan[i].length;j++) {
				if(pan[i][j] == 1) {
					black = search(pan,i,j,1);
					if(black) {
						column = j;
						color = 1;
						break;
					}
				}else if(pan[i][j] == 2) {
					white = search(pan,i,j,2);
					if(white) {
						column = j;
						color = 2;
						break;
					}
				}else {
					continue;
				}
			}
			if(black||white) {
				row = i;
				break;
			}
		}
		System.out.println(color);
		if(black||white)
			System.out.println((row+1) +" "+(column+1));

	}
	private static boolean search(int[][] pan, int row, int column, int color) {
		boolean rowCheck=false;
		boolean colCheck=false;
		boolean rcrossCheck=false;
		boolean lcrossCheck=false;
		//���� 
		if(column<15) {
			for(int i=0;i<5;i++) {
				rowCheck = check(pan,row,column+i,color);
				if(!rowCheck)
					break;

				//���� ����
				if(rowCheck && column>1) {
					rowCheck = !check(pan,row,column-1,color);
				}
				if(rowCheck && column<14) {
					rowCheck = !check(pan,row,column+5,color);
				}
			}
		}


		//����
		if(row<15) {
			for(int i=0;i<5;i++) {
				colCheck = check(pan,row+i,column,color);
				if(!colCheck)
					break;
			}
			//��������
			if(colCheck&& row>1) {
				colCheck = !check(pan,row-1,column,color);
			}
			if(colCheck && row<14) {
				colCheck = !check(pan,row+5,column,color);
			}
		}
		//������ �밢
		if(row<15 && column<15) {
			for(int i=0;i<5;i++) {
				rcrossCheck = check(pan,row+i,column+i,color);
				if(!rcrossCheck)
					break;
			}
			if(rcrossCheck&& row>0 && column>0) {
				rcrossCheck = !check(pan,row-1,column-1,color);
			}
			if(rcrossCheck&& row<14 && column<14) {
				rcrossCheck = !check(pan,row+5,column+5,color);
			}
		}
		//���ʴ밢
		if(row>3 && column<15) {
			for(int i=0;i<5;i++) {
				lcrossCheck = check(pan,row-i,column+i,color);
				if(!lcrossCheck)
					break;
			}
			if(lcrossCheck&& row>4 && column<14) {
				lcrossCheck = !check(pan,row-5,column+5,color);
			}
			if(lcrossCheck && row<18 && column>4) {
				lcrossCheck = !check(pan,row+1,column-1,color);
			}
		}

		if(rowCheck || colCheck ||  rcrossCheck ||lcrossCheck) {
			return true;
		}
		return false;
	}
	private static boolean check(int[][] pan, int row, int column, int color) {
		if(color!=pan[row][column]) {
			return false;
		}
		return true;
	}

}