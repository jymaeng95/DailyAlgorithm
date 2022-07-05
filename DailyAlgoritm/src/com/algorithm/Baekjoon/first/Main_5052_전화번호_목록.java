package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_5052_전화번호_목록 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            int size = Integer.parseInt(br.readLine());

            String[] phone = new String[size];
            for (int loop = 0; loop < size; loop++) {
                phone[loop] = br.readLine();
            }

            String rst = getConsistency(phone);
            System.out.println(rst);
        }

        br.close();
    }

    private static String getConsistency(String[] phone) {
        Arrays.sort(phone);
        for(int index = 0; index < phone.length-1; index++) {
            if(phone[index+1].startsWith(phone[index])) return "NO";
        }
        return "YES";
    }
}
