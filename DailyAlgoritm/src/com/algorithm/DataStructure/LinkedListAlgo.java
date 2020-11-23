package com.algorithm.DataStructure;

class LinkedList<E> {
	Node<E> header;
	
	class Node<E> {
		private E data;
		private Node<E> next;
		
		Node(E data, Node<E> next){
			this.data = data;
			this.next = next;
		}
	}
	
	LinkedList(){
		header = null;
	}
	
	void append(E data) {
		if(header == null) {
			header = new Node<E>(data, null);
		}else {
			Node<E> node = header;
			while(node.next!=null) {
				node = node.next;
			}
			node.next = new Node<E>(data,null);
		}
	}
	
	void retrieve() {
		Node<E> node = header;
		while(node.next!=null) {
			System.out.print(node.data + "->");
			node = node.next;
		}
		System.out.println(node.data);
	}
	
	void deleteLast() {
		Node<E> node = header;
		Node<E> select;
		while(node.next!=null) {
			select = node;
			node = node.next;
			if(node.next == null) {
				select.next = node.next;
			}
		}
	}
	void delete(E data) {
		Node<E> node = header;
		while(node.next!=null) {
			if(node.next.data == data) {
				node.next = node.next.next;
			}else {
				node = node.next;
			}
		}
	}
	void removeDups() {
		Node<E> node = header;
		Node<E> select;
		while(node!=null && node.next!=null) {
			select = node;
			while(select.next !=null) {
				if(select.next.data == node.data) {
					select.next = select.next.next;
				}else {
					select = select.next;
				}
			}
			node = node.next;
		}
	}
	Node KthToLast(Node<E> first,int k) {
		Node<E> node = first;
		int count=1;
		while(node.next!=null) {
			count++;
			node = node.next;
		}
		node = first;
		for(int i=1;i<count-k+1;i++) {
			node = node.next;
		}
		return node;
	}
	void printKtoLast(int k) {
		Node<E> kth = KthToLast(header, k);
		System.out.println(k+"th Node Data : "+kth.data);
		
	}
	int recursive(Node<E> node, int k) {
		if(node == null) {
			return 0;
		}
		int count = recursive(node.next,k)+1;
		if(count == k) {
			System.out.println(k+"th Node Data : "+node.data);
		}
		return count;
	}
	
//	Node sumeLists(Node<E> l1, Node<E> l2, int carry) {
//		if(l1 == null && l2 == null && carry == 0) {
//			return null;
//		}
//		
//		Node<E> result = new Node<E>(null,null);
//	}
}

public class LinkedListAlgo {
	public static void main(String[] args) {
		//3 2 1 2 4 �Է� 
		//buffer�� ����ϸ� hashSet ��� 
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.append(3);
		list.append(2);
		list.append(1);
		list.append(2);
		list.append(4);
		list.append(5);
		list.append(1);
		list.append(2);
		list.append(4);
		list.append(2);
		list.append(3);
		list.retrieve();
		list.removeDups();
		list.retrieve();
		list.printKtoLast(3);
		
		
	}
}	
