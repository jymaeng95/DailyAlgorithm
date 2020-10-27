package com.algorithm.Programmers.Lv2;//프로그래머스 (LV2  다리를 지나는 트럭)

import java.util.LinkedList;
import java.util.Queue;

class Truck {
    int weight;
    int second;
    public Truck(int weight,int second) {
        this.weight = weight;
        this.second = second;
    }
}

public class Solution_42583 {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        int curWeight = 0;
        Queue<Truck> truck = new LinkedList<>();
        Queue<Truck> bridge = new LinkedList<>();
        for (int wg : truck_weights) {
            truck.offer(new Truck(wg, 0));
        }
        while (!truck.isEmpty()||!bridge.isEmpty()) {
            answer++;
            if(!bridge.isEmpty()){
                if(answer - bridge.peek().second >= bridge_length){
                    curWeight -= bridge.poll().weight;
                }
            }
            if(!truck.isEmpty()){
                if(curWeight + truck.peek().weight <= weight){
                    curWeight += truck.peek().weight;
                    bridge.offer(new Truck(truck.poll().weight,answer));
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution_42583 sl = new Solution_42583();
        int bridge_length = 3;
        int weight = 15;
        int[] truck_weights = {7,6,1,8,9};

        System.out.println(sl.solution(bridge_length, weight, truck_weights));
    }
}
