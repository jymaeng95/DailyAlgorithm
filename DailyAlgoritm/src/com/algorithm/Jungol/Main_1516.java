package com.algorithm.Jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Main_1516 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashMap<String, Integer> map = new HashMap<String,Integer>();
		
		while(true) {
			String sentence = br.readLine();
			if(sentence.equals("END")){
				break;
			}
			String[] word = sentence.split(" ");
			for(int i=0;i<word.length;i++) {
				if(map.containsKey(word[i])) {
					map.put(word[i],map.get(word[i])+1);
				}else {
					map.put(word[i],1);
				}
			}
			ArrayList<String> list = new ArrayList<>();
			list.addAll(map.keySet());
			Collections.sort(list);
			for(int i=0;i<list.size();i++) {
				System.out.println(list.get(i) + " : " + map.get(list.get(i)));
			}
			list.clear();
			map.clear();
		}
	}
}
