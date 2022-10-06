package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_22859_HTML_파싱 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String html = br.readLine();
        String rst = parseHtml(html);
        System.out.println(rst);

        br.close();
    }

    private static String parseHtml(String html) {
        // 1. <main></main>제거
        html = html.replaceAll("<main>|</main>", "");

        // 2. <div 시작 태그 타이틀 변환
        html = convertDivToTitle(html);

        // 3. <p 태그 내 태그 제거
        html = removeAnotherTag(html);

        // 4. 양 끝 공백 제거
        html = html.replaceAll("<p> +", "<p>").replaceAll(" +</p>", "</p>");

        // 5. 공백 2개 이상 치환
        html = html.replaceAll("  +"," ");

        // 6. p태그 제거
        html = html.replaceAll("<p>", "").replaceAll("</p>", "\n");

        return html.trim();
    }

    private static String removeAnotherTag(String html) {
        html = html.replaceAll("<[a-oq-z\\s]+>|</[a-oq-z\\s]+>", "");

        return html;
    }

    private static String convertDivToTitle(String html) {
        // 1. <div  -> 제거
        html = html.replaceAll("<div |</div>", "");

        // 2. title 변환
        html = html .replaceAll("=\""," : ");

        // 3. "> 개행으로 변환
        html = html.replaceAll("\">", "\n");

        return html;
    }
}
