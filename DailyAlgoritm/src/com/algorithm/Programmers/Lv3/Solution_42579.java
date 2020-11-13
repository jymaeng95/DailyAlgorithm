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
        Map<String,Integer> totalCount = new HashMap<>();   // 합계를 넣어줄 Map
        Map<String,Map<Integer,Integer>> index = new HashMap<>();   // 인덱스와 값을 넣어줄 Map

        for(int i=0; i< genres.length;i++){
            // 키가 있는 경우 인덱스와 값, 합계를 각각 넣어줌
            if(totalCount.containsKey(genres[i])){
                totalCount.replace(genres[i],totalCount.get(genres[i])+plays[i]);
                index.get(genres[i]).put(i,plays[i]);
                continue;
            }
            Map<Integer,Integer> idxAndPlay = new HashMap<>();  // 인덱스와 값을 넣을 Map 생성
            totalCount.put(genres[i],plays[i]);
            idxAndPlay.put(i,plays[i]);
            index.put(genres[i],idxAndPlay);
        }
        List<String> keySet = new ArrayList<>(totalCount.keySet());
        //totalCount Map에서 합계를 기준으로 내림차순으로 정렬
        Collections.sort(keySet, (o1,o2) -> totalCount.get(o2).compareTo(totalCount.get(o1)));
        
        //index Map에서 인덱스의 해당 값으로 내림차순 정렬
        //ex) classic의 경우 0:500, 2:150, 3:800 -> 3:800, 0:500, 2:150 정렬
        for(String key : keySet){
            List<Integer> indexKeySet = new ArrayList<>(index.get(key).keySet());
            Collections.sort(indexKeySet, (o1,o2) -> index.get(key).get(o2).compareTo(index.get(key).get(o1)));
            //상위 두개의 인덱스를 넣어주는데, 한 개인경우 첫번째만 넣어줌
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
