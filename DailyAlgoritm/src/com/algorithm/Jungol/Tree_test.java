package com.algorithm.Jungol;

class Node<E> {
	E data;
	Node<E> left;
	Node<E> right;
}
class Tree<E> {
	public Node<E> root;
	public void setRoot(Node<E> root) {
		this.root = root;
	}
	public Node getRoot() {
		return root; 
	}
	public Node makeNode(Node<E> left, E data,Node<E> right) {
		Node<E> node = new Node<E>();
		node.data = data;
		node.left = left;
		node.right = right;
		return node;
		
	}
}

public class Tree_test {

}
