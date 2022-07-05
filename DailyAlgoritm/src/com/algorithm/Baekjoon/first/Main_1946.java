package com.algorithm.Baekjoon.first;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 *진영 주식회사는, 다른 모든 지원자와 비교했을 때 서류심사 성적과 면접시험 성적 중 적어도 하나가 다른 지원자보다 떨어지지 않는 자만 선발한다는 원칙을 세웠다.
 *즉, 어떤 지원자 A의 성적이 다른 어떤 지원자 B의 성적에 비해 서류 심사 결과와 면접 성적이 모두 떨어진다면 A는 결코 선발되지 않는다.
 *이러한 조건을 만족시키면서, 진영 주식회사가 이번 신규 사원 채용에서 선발할 수 있는 신입사원의 최대 인원수를 구하는 프로그램을 작성하시오.
 */
/*
 *첫째 줄에는 테스트 케이스의 개수 T(1 ≤ T ≤ 20)가 주어진다. 각 테스트 케이스의 첫째 줄에 지원자의 숫자 N(1 ≤ N ≤ 100,000)이 주어진다.
 *둘째 줄부터 N개 줄에는 각각의 지원자의 서류심사 성적, 면접 성적의 순위가 공백을 사이에 두고 한 줄에 주어진다.
 *두 성적 순위는 모두 1위부터 N위까지 동석차 없이 결정된다고 가정한다.
 */
public class Main_1946 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++){
            int applicant = Integer.parseInt(br.readLine());
            int[][] rank = new int[applicant][2];
            for(int j=0;j<applicant;j++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                rank[j][0] = Integer.parseInt(st.nextToken());
                rank[j][1] = Integer.parseInt(st.nextToken());
            }

            Arrays.parallelSort(rank, (o1,o2) -> Integer.compare(o1[0],o2[0]));
            int count = 1;
            int firstRank = rank[0][1];
            for(int j=1;j<rank.length;j++){
                if(firstRank > rank[j][1]){
                    count++;
                    firstRank = rank[j][1];
                }
            }
            bw.write(String.valueOf(count));
            bw.newLine();
        }

        br.close();
        bw.close();
    }
}
