package com.second.algorithm.interview.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution_778_Group_Anagrams {
    public static void main(String[] args) {
//        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat" };
        String[] strs = {"ddddddddddg", "dgggggggggg" };
        List<List<String>> rst = groupAnagrams(strs);
        for (List<String> list : rst) {
            System.out.println(list.toString());
        }
    }

    private static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            if(map.isEmpty()) {
                map.put(str, new ArrayList<>());
                map.get(str).add(str);
            } else {
                boolean find = false;
                for (String key : map.keySet()) {
                    int count = 0;
                    if(key.length() == str.length()) {
                        for (char c : str.toCharArray()) {
                            if(key.contains(String.valueOf(c))) count++;
                        }
                    }
                    if (count == str.length()) {
                        map.get(key).add(str);
                        find = true;
                        break;
                    }
                }
                if(!find) {
                    map.put(str, new ArrayList<>());
                    map.get(str).add(str);
                }
            }
        }

        return new ArrayList<>(map.values());
    }
}
