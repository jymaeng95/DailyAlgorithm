package com.algorithm.DataStructure;//class LinkedList{
//	Node header;
//	
//	static class Node{
//		int data;
//		Node next = null;
//	}
//	LinkedList() {
//		header = new Node();
//	}
//	void append(int data) {
//		Node end = new Node();
//		end.data = data;
//		Node node = header;
//		while(node.next!=null) {
//			node = node.next;
//		}
//		node.next = end;
//	}
//
//	//��� ���� 
//	void delete(int data) {
//		Node node = header;
//		while(node.next!=null) {
//			if(node.next.data == data) {
//				node.next = node.next.next;
//			}else {
//				node = node.next;
//			}
//		}
//	}
//
//	//��ũ����Ʈ ������ ��� 
//	void retrieve() {
//		Node node = header.next;
//		while(node.next!=null) {
//			System.out.print(node.data + "->");
//			node = node.next;
//		}
//		//������ ������ ���
//		System.out.println(node.data);
//	}
//}
//public class LinkedListTest {
//
//	public static void main(String[] args) {
//		LinkedList ll = new LinkedList();
//		ll.append(1);
//		ll.append(2);
//		ll.append(3);
//		ll.append(4);
//		ll.retrieve();
//		ll.delete(1);
//		ll.retrieve();
//		
//	}
//
//}
