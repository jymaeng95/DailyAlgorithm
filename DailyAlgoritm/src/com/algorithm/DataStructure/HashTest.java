package com.algorithm.DataStructure;

import java.util.LinkedList;

class HashTable {
	class Node{
		String key;
		String value;
		public Node(String key, String value) {
			this.key = key;
			this.value = value;
		}
		String value() {
			return value;
		}
		void value(String value) {
			this.value = value;
		}
	}
	
	LinkedList<Node>[] data;
	HashTable(int size) {
		this.data = new LinkedList[size];
	}
	int getHashCode(String key) {
		int hashcode = 0;
		for(char c : key.toCharArray()) {
			hashcode+= c;
		}
		return hashcode;
	}
	int convertToIndex(int hashcode) {
		return hashcode % data.length;
	}
	Node searchKey(LinkedList<Node> list, String key) {
		if(list == null) return null;
		for(Node node: list) {
			if(node.key.equals(key)) {
				return node;
			}
		}
		return null;
	}
	void put(String key, String value) {
		int hashcode = getHashCode(key);
		int index = convertToIndex(hashcode);
		System.out.println(key + " ,hashcode("+hashcode+"), index ("+index+")");
		LinkedList<Node> list = data[index];
		if(list == null) {
			list = new LinkedList<Node>();
			data[index] = list;
		}
		Node node = searchKey(list,key);
		if(node == null) {
			list.add(new Node(key,value));
		}else {
			node.value(value);
		}
	}
	String get(String key) { 
		int hashcode = getHashCode(key);
		int index = convertToIndex(hashcode);
		LinkedList<Node> list = data[index];
		Node node = searchKey(list, key);
		
		return node == null ? "Not Found" : node.value();
	}
}

public class HashTest {

	public static void main(String[] args) {
		HashTable h = new HashTable(3);
		h.put("joon", "He is handsome");
		h.put("maeng", "He is boy");
		h.put("young", "He is guy");
		h.put("joon", "he is update");
		System.out.println(h.get("maeng"));
		System.out.println(h.get("joon"));
		System.out.println(h.get("young"));
		System.out.println(h.get("dfdf"));
	}

}
