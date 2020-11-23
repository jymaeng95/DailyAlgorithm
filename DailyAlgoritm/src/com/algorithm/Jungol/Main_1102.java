package com.algorithm.Jungol;

import java.util.Scanner;
import java.util.Stack;

public class Main_1102 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();
		Stack<Integer> stack = new Stack<>();
		
		for(int i=0;i<t;i++) {
			String order = scan.next();
			switch(order) {
			case "i" :
				int data = scan.nextInt();
				stack.push(data);
				break;
			case "c" :
				System.out.println(stack.size());
				break;
				
			case "o" :
				if(!stack.isEmpty()) {
					System.out.println(stack.pop());
				}else {
					System.out.println("empty");
				}
				break;
			}
		}
	}

}
