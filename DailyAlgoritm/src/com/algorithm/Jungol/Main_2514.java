package com.algorithm.Jungol;//import java.util.NoSuchElementException;
//import java.util.Scanner;
//
//class Queue<E> {
//	E data;
//	Node<E> front;
//	Node<E> rear;
//	int size=0;
//	class Node<E>{
//		private E data;
//		private Node<E> next;
//
//		Node(E data){
//			this.data = data;
//		}
//	}
//
//	void enqueue(E data) {
//		Node<E> node = new Node<E>(data);
//		if(rear!=null) {
//			rear.next = node;
//			size++;
//		}
//		rear = node;
//		if(front==null) {
//			front = rear;
//			size++;
//		}
//	}
//
//	E dequeue() {
//		Node<E> node = front;
//		if(front == null) {
//			throw new NoSuchElementException();
//		}
//		E data = front.data;
//		front = front.next;
//		size--;
//		if(front == null) {
//			rear = null;
//		}
//		return node.data;
//	}
//
//	E peek() {
//		Node<E> node = front;
//		if(front == null) {
//			throw new NoSuchElementException();
//		}
//		E data = front.data;
//		return data;
//	}
//	public boolean isEmpty() {
//		return front == null;
//	}
//	public int size() {
//		return size;
//	}
//}
//public class Main_2514 {
//
//	public static void main(String[] args) {
//		Scanner scan = new Scanner(System.in);
//		String[] sentence = scan.next().split("");
//		Queue<String> queue = new Queue<>();
//
//		int Kcount=0;
//		int Icount=0;
//		for(int i=0;i<sentence.length;i++) {
//			queue.enqueue(sentence[i]);
//		}
//		String first = queue.dequeue();
//		while(!queue.isEmpty()) {
//			if(first.equals("K")) {
//				if(queue.peek().equals("O")) {
//					queue.dequeue();
//					if(queue.peek().equals("I")) {
//						Kcount++;
//					}
//				}
//			}else if(first.equals("I")) {
//				if(queue.peek().equals("O")) {
//					queue.dequeue();
//					if(queue.peek().equals("I")) {
//						Icount++;
//					}
//				}
//			}
//			first = queue.dequeue();
//		}
//		System.out.println(Kcount);
//		System.out.println(Icount);
//	}
//}
