package com.algorithm.Jungol;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;


class Queue<E> {
	E data;
	Node<E> front;
	Node<E> rear;
	int size=0;
	class Node<E>{
		private E data;
		private Node<E> next;

		Node(E data){
			this.data = data;
		}
	}

	void enqueue(E data) {
		Node<E> node = new Node<E>(data);
		if(rear!=null) {
			rear.next = node;
			size++;
		}
		rear = node;
		if(front==null) {
			front = rear;
			size++;
		}
	}

	E dequeue() {
		Node<E> node = front;
		if(front == null) {
			throw new NoSuchElementException();
		}
		E data = front.data;
		front = front.next;
		size--;
		if(front == null) {
			rear = null;
		}
		return node.data;
	}

	E peek() {
		Node<E> node = front;
		if(front == null) {
			throw new NoSuchElementException();
		}
		E data = front.data;
		return data;
	}
	public boolean isEmpty() {
		return front == null;
	}
	public int size() {
		return size;
	}

}
public class Main_2857 {

	public static void main(String[] args) {
		ArrayList<Queue> list = new ArrayList<>();
		Scanner scan = new Scanner(System.in);
		int max = 0;
		String concat="";
		for(int i=0;i<5;i++) {
			String[] word = scan.next().split("");
			Queue<String> queue = new Queue<>();
			for(int j=0;j<word.length;j++) {
				if(max<word.length) {
					max = word.length;
				}
				queue.enqueue(word[j]);
			}
			list.add(queue);
		}
		for(int i=0;i<max;i++) {
			for(int j=0;j<list.size();j++) {
				if(list.get(j).isEmpty()){
					concat+="";
				}else {
					concat += list.get(j).dequeue();
				}
			}
		}
		System.out.println(concat);
	}	
}
