package com.algorithm.Jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Scanner;

public class Main_1880 {

	public static void main(String[] args) throws IOException {
		HashMap<String,String> map = new HashMap<String,String>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] key = "abcdefghijklmnopqrstuvwxyz".split("");
		String[] value = br.readLine().split("");
		for(int i=0;i<26;i++) {
			map.put(key[i],value[i]);
			map.put(key[i].toUpperCase(),value[i].toUpperCase());
		}
		String[] test=br.readLine().split("");
		String enc="";
		for(int i=0;i<test.length;i++) {
			if(test[i].equals(" ")) {
				enc+=" ";
			}else {
				enc+=map.get(test[i]);
			}
		}
		System.out.println(enc);
	}
}
