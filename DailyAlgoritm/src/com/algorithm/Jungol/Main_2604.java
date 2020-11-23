package com.algorithm.Jungol;

import java.util.Scanner;
import java.util.Stack;

public class Main_2604 {

	public static void main(String[] args) {
		Stack<String> stack = new Stack<>();
		Scanner scan = new Scanner(System.in);
		
		String input = scan.next();
		String[] dish = input.split("");
		int height=0;
		for(int i=0;i<dish.length;i++) {
			if(stack.isEmpty()) {
				stack.push(dish[i]);
				height+=10;
			}else {
				if(stack.peek().equals(dish[i])) {
					stack.push(dish[i]);
					height+=5;
				}else {
					stack.push(dish[i]);
					height+=10;
				}
			}
		}
		System.out.println(height);
	}

}
