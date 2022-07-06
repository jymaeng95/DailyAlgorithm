package com.algorithm.Baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class Main_11047 {
    /*
     * 준규가 가지고 있는 동전은 총 N종류이고, 각각의 동전을 매우 많이 가지고 있다.
     * 동전을 적절히 사용해서 그 가치의 합을 K로 만들려고 한다. 이때 필요한 동전 개수의 최솟값을 구하는 프로그램을 작성하시오.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int money = Integer.parseInt(st.nextToken());
        int[] change = new int[N];
        int count = 0;

        for(int i=0;i<N;i++){
            change[i] = Integer.parseInt(br.readLine());
        }

        for(int i=N-1;i>=0;i--){
            if(money >= change[i]){
                count += money / change[i];
                money %= change[i];
            }
        }

        bw.write(String.valueOf(count));

        br.close();
        bw.close();
    }
}
