package com.algorithm.Jungol;

import java.util.Scanner;
import java.util.Stack;

public class Main_1221 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();
		Stack<String> stack = new Stack<>();
		int result = 0;
		String num1, num2;
		for(int i=0;i<t;i++) {
			String data = scan.next();
			switch(data) {
			case "+":
				num1 = stack.pop();
				num2 = stack.pop();
				result = Integer.parseInt(num2) + Integer.parseInt(num1);
				stack.push(Integer.toString(result));
				break;
			case "-":
				num1 = stack.pop();
				num2 = stack.pop();
				result = Integer.parseInt(num2) - Integer.parseInt(num1);
				stack.push(Integer.toString(result));
				break;
			case "*":
				if(stack.peek().equals("+") || stack.peek().equals("-")) {
					String ops = stack.pop();
					num1 = stack.pop();
					num2 = stack.pop();
					if(ops.equals("+")) {
						result = Integer.parseInt(num2) + Integer.parseInt(num1);
						stack.push(Integer.toString(result));
						stack.push(data);
					}else {
						result = Integer.parseInt(num2) - Integer.parseInt(num1);
						stack.push(Integer.toString(result));
						stack.push(data);
					}
				}else {
					num1 = stack.pop();
					num2 = stack.pop();	
					result = Integer.parseInt(num2) * Integer.parseInt(num1);
					stack.push(Integer.toString(result));
				}
				break;
			case "/":
				if(stack.peek().equals("+") || stack.peek().equals("-")) {
					String ops = stack.pop();
					num1 = stack.pop();
					num2 = stack.pop();
					if(ops.equals("+")) {
						result = Integer.parseInt(num2) + Integer.parseInt(num1);
						stack.push(Integer.toString(result));
						stack.push(data);

					}else {
						result = Integer.parseInt(num2) - Integer.parseInt(num1);
						stack.push(Integer.toString(result));
						stack.push(data);

					}
				}else {
					num1 = stack.pop();
					num2 = stack.pop();	
					try {
						result = Integer.parseInt(num2) / Integer.parseInt(num1);
					}catch(ArithmeticException e) {
						
					}finally {
						stack.push(Integer.toString(result));
					}
				}
				break;

			default : 
				stack.push(data);
				break;
			}
		}
		System.out.println(stack.pop());
		stack.clear();
	}

}
