package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1062_가르침 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        String[] words = new String[N];
        for (int loop = 0; loop < N; loop++) {
            words[loop] = br.readLine();
        }

        int rst = teachAnticWord(N, K, words);
        System.out.println(rst);

        br.close();
    }

    private static int count;
    private static int teachAnticWord(int N, int K, String[] words) {
        /**
         * 1. a,n,t,i,c는 무조건 배워야하는 글자이므로 K개가 5개 이상이면 5개 빼고 남은 글자로 조합 만들기
         * 2. 만들어진 글자 조합으로 모든 단어에 포함되는 글자인지 탐색 -> 하나라도 포함이 안된 경우 다음 단어로
         * 3. 글자 조합 별 최고로 가르칠 수 있는 단어 개수 갱신
         */
        if(K < 5) return 0;

        count = 0;
        boolean[] teachingAlphabet = new boolean[26];
        teachingAlphabet[0] = teachingAlphabet[2] = teachingAlphabet[8] = teachingAlphabet[13] = teachingAlphabet[19] = true;

        teachLetter(0,0, K - 5, words, teachingAlphabet);

        return count;
    }

    private static void teachLetter(int start, int letterCount, int finishCount, String[] words, boolean[] teachingAlphabet) {
        if(letterCount == finishCount) {
            // 2. 만들어진 글자 조합으로 모든 단어에 포함되는 글자인지 탐색 -> 하나라도 포함이 안된 경우 다음 단어로
            int teachingCount = 0;
            for (String word : words) {
                boolean teach = true;
                for (char letter : word.toCharArray()) {
                    if(!teachingAlphabet[letter-97]) {
                        teach = false;
                        break;
                    }
                }
                if(teach) teachingCount++;
            }

            count = Math.max(count, teachingCount);
            return;
        }

        for(int alpha = start; alpha < 26; alpha++) {
            if(!teachingAlphabet[alpha]) {
                teachingAlphabet[alpha] = true;
                teachLetter(alpha +1, letterCount+1, finishCount, words, teachingAlphabet);
                teachingAlphabet[alpha] = false;
            }
        }
    }
}
