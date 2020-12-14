package com.algorithm.Programmers.Lv3;

public class Solution_43163 {

    public static boolean[] visited;
    public static int count;
    public static void main(String[] args) {
        String begin = "hit";
        String target = "cog";
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
        solution(begin, target, words);
    }

    public static int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length + 1];
        count = Integer.MAX_VALUE;
        dfs(begin, target, words);
        if(count == Integer.MAX_VALUE)
            count = 0;
        return count;
    }

    private static void dfs(String begin, String target, String[] words) {
        dfsUtil(begin, target, words, 0,0);
    }

    private static void dfsUtil(String begin, String target, String[] words,int check, int index) {

        if (begin.equals(target)) {
            count = Math.min(count,check);
            visited[index] = false;
            return;
        }

        visited[index] = true;
        //변환 횟수 증가
        check++;
        for (int i = 0; i < words.length; i++) {
            int wordCount = 0;
            for (int j = 0; j < words[i].length(); j++) {
                //단어 비교 한글자만 바뀐 것 체크
                if (begin.charAt(j) == words[i].charAt(j)) {
                    wordCount++;
                }
            }
            if (wordCount == begin.length() - 1 && !visited[i + 1]) {
                dfsUtil(words[i],target,words,check,i+1);
            }
        }
        //target에 도달하더라도 최소 횟수로 도달하는경우가 없을 수도 있기 때문에
        //방문 처리를 풀어 남은 dfs도 수행하도록 처리
        visited[index] = false;
        return;
    }
}
