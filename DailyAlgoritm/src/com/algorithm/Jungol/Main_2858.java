package com.algorithm.Jungol;

import java.util.Scanner;
	import java.util.Stack;
	
	public class Main_2858 {
	
		public static void main(String[] args) {
			Stack<String> stack = new Stack<>();
			Scanner scan = new Scanner(System.in);
			
			String type =scan.next();
			String[] stick = type.split("");
			int count=0;
			for(int i=0;i<stick.length;i++) {
				if(stick[i].equals("(")) {
					stack.push(stick[i]);
				}else {
					if(stick[i-1].equals("(")) {
						stack.pop();
						count += stack.size();
					}else {
						stack.pop();
						count++;
					}
				}
			}
			System.out.println(count);
		}
	
	}
