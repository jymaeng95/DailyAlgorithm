package com.algorithm.Baekjoon;


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
            if(!bridge.isEmpty() && count - bridge.peek().time >= bridgeLength) {
                curWeight -= bridge.poll().weight;
            }
            if(!que.isEmpty() && curWeight + que.peek().weight <= bridgeWeight){
                Truck truck = que.poll();
                curWeight += truck.weight;
                bridge.offer(new Truck(truck.weight,count));
            }
        }

        bw.write(String.valueOf(count));

        br.close();
        bw.close();
    }

}
