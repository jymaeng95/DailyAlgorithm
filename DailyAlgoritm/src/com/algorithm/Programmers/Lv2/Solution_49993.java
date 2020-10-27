package com.algorithm.Programmers.Lv2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//프로그래머스(스킬트리)
public class Solution_49993 {
    public int solution(String skill, String[] skill_trees){
        //내 풀이
        int answer=0;
        String[] sk = skill.split("");
        Map<String, Integer> map = new HashMap<>();
        for(int i=0;i<sk.length;i++){
            map.put(sk[i],i);
        }
        int first;
        boolean is_answer=false;
        for(String str : skill_trees){
            is_answer=false;
            first = 0;
            String[] st = str.split("");
            List<Integer> list = new ArrayList<>();
            for(String skStr : st) {
                if(map.get(skStr) != null){
                    list.add(map.get(skStr));
                }else
                    continue;
            }
            if(list.size()> 0 ) {
                for (int num : list) {

                    if (num == first) {
                        first += 1;
                        is_answer = true;
                    } else {
                        is_answer = false;
                        break;
                    }
                }
            }else
                is_answer = true;
            if(is_answer)
                answer ++;
            System.out.println();
        }

        //정규 표현식으로 푼 이상 풀이
      /*  int answer = 0;
        ArrayList<String> skillTrees = new ArrayList<String>(Arrays.asList(skill_trees));
        //ArrayList<String> skillTrees = new ArrayList<String>();
        Iterator<String> it = skillTrees.iterator();

        while (it.hasNext()) {
            if (skill.indexOf(it.next().replaceAll("[^" + skill + "]", "")) != 0) {
                System.out.println(skillTrees);
                it.remove();
            }
        }
        answer = skillTrees.size();*/
        return answer;
    }

    public static void main(String[] args) {
        Solution_49993 sl = new Solution_49993();
        String skill = "CBD";
        String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA","ABCEF","BCDE","AD","AC","CD","CB","KJL"};
        System.out.println(sl.solution(skill,skill_trees));
    }
}
