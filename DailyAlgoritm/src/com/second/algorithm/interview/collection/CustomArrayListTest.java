package com.second.algorithm.interview.collection;

import com.second.algorithm.interview.collection.list.CustomArrayList;

public class CustomArrayListTest {
    public static void main(String[] args) {
        CustomArrayList<Integer> list = new CustomArrayList<>();

        list.add(1);
        list.add(3);

        System.out.println("list.isEmpty() = " + list.isEmpty());
        System.out.println("list.size() = " + list.size());
        System.out.println("list.get(0) = " + list.get(0));
        System.out.println("list.get(1) = " + list.get(1));

        list.addFirst(2);
        System.out.println("list.get(0) = " + list.get(0));
        System.out.println("list.get(1) = " + list.get(1));
        System.out.println("list.get(2) = " + list.get(2));

        list.clear();
        System.out.println("list.size() = " + list.size());
        System.out.println("list.isEmpty() = " + list.isEmpty());
    }
}
