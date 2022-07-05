package com.algorithm.Baekjoon.first;

import java.io.*;
import java.util.*;

public class Main_1700 {
    private static int N,K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        List<Integer> order = new ArrayList<>();
        List<Integer> plug = new ArrayList<>(N);
        for(int i=0;i<K;i++) {
            int num = Integer.parseInt(st.nextToken());
            order.add(num);
        }
        int changeCount = 0;
        for(int i=0;i<N;i++)
            plug.add(order.get(i));

        for(int i=N;i<order.size();i++){
            if(!plug.contains(order.get(i))){
                int change = getChange(plug,i,order);
                plug.remove(change);
                plug.add(order.get(i));
                changeCount++;
            }

        }

        bw.write(String.valueOf(changeCount));
        br.close();
        bw.close();
    }

    // 플러그에 꽂힌 기구가 뒤의 순서에 남아있는 경우 최대인덱스 뽑기
    // 플러그에 꽂히 기구가 뒤의 순서에 남아있지 않은 경우 해당 플러그 빼기
    private static int getChange(List<Integer> plug, int i, List<Integer> order) {
        int index = -1;
        int remove = -1;
        boolean flag = false;
        for(int k=0;k<plug.size();k++){
            for (int j = i; j < order.size(); j++) {
                if (order.get(j).equals(plug.get(k))){
                    flag = true;
                    if(index < j){
                        index = j;
                        remove = k;
                        break;
                    }
                }
            }
            if(!flag)
                return k;
            flag = false;
        }
        return remove;
    }
}
