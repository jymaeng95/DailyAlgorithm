package com.algorithm.Baekjoon.first;


import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_13335 {
    static class Truck {
        private int weight;
        private int time;

        public Truck(int weight, int time) {
            this.weight = weight;
            this.time = time;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int truckCount = Integer.parseInt(st.nextToken());
        int bridgeLength = Integer.parseInt(st.nextToken());
        int bridgeWeight = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Queue<Truck> que = new LinkedList<>();
        Queue<Truck> bridge = new LinkedList<>();

        while(st.hasMoreTokens())
            que.add(new Truck(Integer.parseInt(st.nextToken()),0));

        int count = 0;
        int curWeight = 0;

        while(!que.isEmpty() || !bridge.isEmpty()){
            count++;
            //다리의 현재 시간에서 다리에 진입한 시간을 빼줫을 때 다리의 길이보다 크면 다리를 빠져가고 무게를 현재 다리의 무게를 빼준다.
            if(!bridge.isEmpty() && count - bridge.peek().time >= bridgeLength) {
                curWeight -= bridge.poll().weight;
            }

            //현재 다리의 무게에 다음에 들어올 트럭의 무게를 더햇을 때 다리의 무게보다 작거나 같으면 다리에 진입한다.
            if(!que.isEmpty() && curWeight + que.peek().weight <= bridgeWeight){
                Truck truck = que.poll();
                curWeight += truck.weight;

                //다리에 진입한 시간을 다리 큐에 넣어준다.
                bridge.offer(new Truck(truck.weight,count));
            }
        }

        bw.write(String.valueOf(count));

        br.close();
        bw.close();
    }

}
