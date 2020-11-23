package com.algorithm.DataStructure;

//����� ��ũ����Ʈ
class Node {
	int data;
	Node next = null;
	Node(int data) {
		this.data = data;
	}

	//��� �߰� 
	void append(int data) {
		Node end = new Node(data);
		Node node = this;
		while(node.next!=null) {
			node = node.next;
		}
		node.next = end;
	}

	//��� ���� 
	void delete(int data) {
		Node node = this;
		while(node.next!=null) {
			if(node.next.data == data) {
				node.next = node.next.next;
			}else {
				node = node.next;
			}
		}
	}

	//��ũ����Ʈ ������ ��� 
	void retrieve() {
		Node node = this;
		while(node.next!=null) {
			System.out.print(node.data + "->");
			node = node.next;
		}
		//������ ������ ���
		System.out.println(node.data);
	}
}
public class LinkedList_OneWay {


	public static void main(String[] args) {
		Node head = new Node(1);
		head.append(2);
		head.append(3);
		head.append(4);
		head.retrieve();
		head.delete(2);
		head.delete(3);
		head.retrieve();
	}

}
