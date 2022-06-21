package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main_10775_공항 {
    private static List<Integer> plane;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());

        plane = new ArrayList<>();
        for (int airplane = 0; airplane < P; airplane++) {
            plane.add(Integer.parseInt(br.readLine()));
        }

        int rst = dockingAirplane(G, P);
        System.out.println(rst);
        br.close();
    }

    private static int dockingAirplane(int g, int p) {
        int[] gates = new int[g + 1];
        for (int gate = 1; gate <= g; gate++) {
            gates[gate] = gate;
        }

        int count = 0;
        for (Integer airplane : plane) {
            // 이용가능한 게이트 찾기
            int availableGate = find(gates, airplane);
            if(availableGate < 1) break;

            // 게이트에 배치 했으니 현재 비행기와 동일한 게이트 번호를 가진 비행기 도킹 시 현재 게이트보다 하나 작은 게이트를 이용 필요
            union(airplane, availableGate - 1, gates);

            count++;
        }

        return count;
    }

    private static void union(Integer airplane, int nextAvailable, int[] gates) {
        int curGate = find(gates, airplane);    // 현재 비행기가 이용중인 게이트 찾기20
        int nextGate = find(gates, nextAvailable);  // 동일한 게이트 번호 비행기가 다음에 도킹시 사용할 게이트 찾기

        if(curGate > nextGate) gates[curGate] = nextGate;
        else gates[nextGate] = curGate;
    }

    private static int find(int[] gates, Integer airplane) {
        if(gates[airplane] != airplane) gates[airplane] = find(gates, gates[airplane]);
        return gates[airplane];
    }
}
