package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_14226_이모티콘 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int S = Integer.parseInt(br.readLine());

        int rst = sendEmoticon(S);
        System.out.println(rst);

        br.close();
    }

    private static int sendEmoticon(int s) {
        boolean[][] check = new boolean[2000][1001];        // 스크린에 999, 클립보드에 999인 경우 최대
        Queue<Emoticon> queue = new LinkedList<>();
        queue.add(new Emoticon(1, 0));

        int second = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int loop = 0; loop < size; loop++) {
                Emoticon emoticon = queue.poll();
                int screenEmoticon = emoticon.getScreenEmoticon();
                int clipboardEmoticon = emoticon.getClipboardEmoticon();

                // 화면에 저장된 이모티콘 개수가 동일한 경우 최초 경우
                if (screenEmoticon == s) return second;

                // 화면에 있는 이모티콘이 보내려는 이모티콘보다 작은 경우에만 클리보드에서 복사해서 붙히기
                if (screenEmoticon < s && !check[screenEmoticon + clipboardEmoticon][clipboardEmoticon]) {
                    check[screenEmoticon + clipboardEmoticon][clipboardEmoticon]= true;
                    queue.add(new Emoticon(screenEmoticon + clipboardEmoticon, clipboardEmoticon));
                }

                if (screenEmoticon > 0) {
                    // 화면에서 한개 지우기
                    if (!check[screenEmoticon - 1][clipboardEmoticon]) {
                        check[screenEmoticon - 1][clipboardEmoticon] = true;
                        queue.add(new Emoticon(screenEmoticon - 1, clipboardEmoticon));
                    }

                    // 화면에서 클립보드로 복사하기 (화면 이모티콘이 보내려는 이모티콘 개수 보다 작은 경우에만 복사)
                    if (screenEmoticon < s && !check[screenEmoticon][screenEmoticon]) {
                        check[screenEmoticon][screenEmoticon] = true;
                        queue.add(new Emoticon(screenEmoticon, screenEmoticon));
                    }
                }
            }
            second++;
        }

        return second;
    }

    static class Emoticon {
        private int screenEmoticon;
        private int clipboardEmoticon;

        public Emoticon(int screenEmoticon, int clipboardEmoticon) {
            this.screenEmoticon = screenEmoticon;
            this.clipboardEmoticon = clipboardEmoticon;
        }

        public int getScreenEmoticon() {
            return screenEmoticon;
        }

        public int getClipboardEmoticon() {
            return clipboardEmoticon;
        }
    }
}
