package com.algorithm.Programmers.Lv3;

// 프로그래머스 Lv3 베스트 앨범
import java.util.*;

public class Solution_42579 {
    public static void main(String[] args) {
        String[] genres = {"classic","pop","classic","classic","pop"};
        int[] plays = {500,600,150,800,2500};
        solution(genres,plays);
    }
    public static int[] solution(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList<>();
        Map<String,Integer> totalCount = new HashMap<>();
        Map<String,Map<Integer,Integer>> index = new HashMap<>();
        for(int i=0; i< genres.length;i++){
            if(totalCount.containsKey(genres[i])){
                totalCount.replace(genres[i],totalCount.get(genres[i])+plays[i]);
                index.get(genres[i]).put(i,plays[i]);
                continue;
            }
            Map<Integer,Integer> idxAndPlay = new HashMap<>();
            totalCount.put(genres[i],plays[i]);
            idxAndPlay.put(i,plays[i]);
            index.put(genres[i],idxAndPlay);
        }
        List<String> keySet = new ArrayList<>(totalCount.keySet());

        Collections.sort(keySet, (o1,o2) -> totalCount.get(o2).compareTo(totalCount.get(o1)));
        for(String key : keySet){
            List<Integer> indexKeySet = new ArrayList<>(index.get(key).keySet());
            Collections.sort(indexKeySet, (o1,o2) -> index.get(key).get(o2).compareTo(index.get(key).get(o1)));

            answer.add(indexKeySet.get(0));
            if(indexKeySet.size()>1)
                answer.add(indexKeySet.get(1));
        }
        int[] returnValue = new int[answer.size()];
        for(int i=0;i<answer.size();i++){
            returnValue[i] = answer.get(i);
        }
        return returnValue;
    }
}
