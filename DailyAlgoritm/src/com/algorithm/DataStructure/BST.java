package com.algorithm.DataStructure;

class Tree{
	class Node{
		int data;
		Node left;
		Node right;
		Node (int data) {
			this.data = data;
		}
	}
	Node root;
	public void makeTree(int[] a) {
		root = makeTreeR(a,0,a.length-1);
	}
	private Node makeTreeR(int[] a, int start, int end) {
		if(start>end) return null;
		int mid = (start + end) / 2;
		Node node = new Node(a[mid]);
		node.left = makeTreeR(a, start, mid-1);
		node.right = makeTreeR(a,mid+1,end);
		return node;
	}
	public void searchBTree(Node n, int find) {
		if(find<n.data) {
			System.out.println("small");
			searchBTree(n.left, find);
		}else if(find>n.data) {
			System.out.println("big");
			searchBTree(n.right, find);
		}else {
			System.out.println("find");
		}
	}
}
public class BST {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
