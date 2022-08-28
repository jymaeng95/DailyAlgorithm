package com.second.algorithm.interview.collection;

import com.second.algorithm.interview.collection.list.CustomArrayList;

public class CustomArrayListTest {
    public static void main(String[] args) {
        CustomArrayList<Integer> list = new CustomArrayList<>();

        // add
        list.add(1);
        list.add(3);
        list.add(3);
        list.add(4);
        list.add(3);
        System.out.println("list.toString() = " + list.toString());

        // isEmpty
        System.out.println("list.isEmpty() = " + list.isEmpty());
        
        // size
        System.out.println("list.size() = " + list.size());
        
        System.out.println("list.get(0) = " + list.get(0));
        System.out.println("list.get(1) = " + list.get(1));

        // addFirst
        list.addFirst(2);
        System.out.println("list.toString() = " + list.toString());
        
        // get
        System.out.println("list.get(0) = " + list.get(0));
        System.out.println("list.get(1) = " + list.get(1));
        System.out.println("list.get(2) = " + list.get(2));
        
        // indexOf, lastIndexOf
        System.out.println("list.indexOf(3) = " + list.indexOf(3));
        System.out.println("list.lastIndexOf(3) = " + list.lastIndexOf(3));
        
        // remove
        System.out.println("list.remove(1) = " + list.remove(1));
        System.out.println("list.get(1) = " + list.get(1));

        // set
        list.set(0, 10);

        System.out.println(list.toString());

        list.clear();
        System.out.println("list.size() = " + list.size());
        System.out.println("list.isEmpty() = " + list.isEmpty());
    }
}
