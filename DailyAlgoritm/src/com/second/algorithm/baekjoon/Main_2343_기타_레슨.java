package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2343_기타_레슨 {
    private static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] minutes = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int disk = 0; disk < N; disk++) {
            minutes[disk] = Integer.parseInt(st.nextToken());
        }

        long rst = getDiskSize(minutes);
        System.out.println(rst);

        br.close();
    }

    private static long getDiskSize(int[] minutes) {
        long min = Arrays.stream(minutes).max().getAsInt();
        long max = Arrays.stream(minutes).sum();
        long minAvailable = max;
        while (min <= max) {
            long mid = (min + max) / 2;

            // 디스크가 많아지는 경우 녹음 길이를 늘려준다.
            int recordedDisk = recordLecture(mid, minutes);
            if(recordedDisk > M) {
                min = mid + 1;
            }
            // 디스크가 작거나 같은 경우 녹음 길이를 줄여준다.
            else {
                minAvailable = Math.min(minAvailable, mid);
                max = mid - 1;

            }
        }

        return minAvailable;
    }

    private static int recordLecture(long mid, int[] minutes) {
        int  recordedDisk = 1;
        long available = 0;

        for (int disk = 0; disk < N; disk++) {
            if (mid < available + minutes[disk]) {
                available = minutes[disk];
                recordedDisk++;
            } else
                available += minutes[disk];
        }
        return recordedDisk;
    }
}
