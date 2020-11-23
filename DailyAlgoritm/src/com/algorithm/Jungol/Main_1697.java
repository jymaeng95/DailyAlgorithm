package com.algorithm.Jungol;//import java.util.NoSuchElementException;
//import java.util.Scanner;
//class Queue<E> {
//	E data;
//	int size = 0;
//	class Node<E> {
//		private E data;
//		private Node<E> next;
//		
//		public Node(E data) {
//			this.data = data;
//			
//		}
//	}
//	private Node<E> front;
//	private Node<E> rear;
//	
//	void enqueue(E item) {
//		Node<E> node = new Node<E>(item);
//		
//		if(rear!=null) {
//			rear.next = node;
//			size++ ;
//		}
//		rear = node;
//		if(front == null) {
//			front = rear;
//			size++;
//		}
//	}
//	E dequeue() {
//		if(front == null) {
//			throw new NoSuchElementException();
//		}
//		E data = front.data;
//		front = front.next;
//		size--;
//		if(front == null ) {
//			rear = null;
//		}
//		return data;
//	}
//	
//	public E peek() {
//		if(front == null) {
//			throw new NoSuchElementException();
//		}
//		return front.data;
//	}
//	public boolean isEmpty() {
//		return front == null;
//	}
//	public int size() {
//		return size;
//	}
//}
//
//public class Main_1697 {
//
//	public static void main(String[] args) {
//		Scanner scan = new Scanner(System.in);
//		int t = scan.nextInt();
//		Queue<Integer> queue = new Queue<>();
//		for(int i=0;i<t;i++) {
//			String order = scan.next();
//			switch(order) {
//			case "i":
//				int data = scan.nextInt();
//				queue.enqueue(data);
//				break;
//			case "c" :
//				System.out.println(queue.size());
//				break;
//			case "o" :
//				if(!queue.isEmpty()) {
//					System.out.println(queue.dequeue());
//				}else {
//					System.out.println("empty");
//				}
//				break;
//			}
//		}
//
//	}
//
//}
