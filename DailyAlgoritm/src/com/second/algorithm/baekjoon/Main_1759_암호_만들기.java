package com.second.algorithm.baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class Main_1759_암호_만들기 {
    private static int L, C;
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        String[] type = br.readLine().split(" ");
        availableSecret(type);

        br.close();
    }

    private static void availableSecret(String[] type) throws IOException {
        Arrays.sort(type);

        StringBuilder builder = new StringBuilder();
        boolean[] check = new boolean[C];
        makeSecret(0, builder, check, type);

        bw.flush();
    }

    private static void makeSecret(int start, StringBuilder builder, boolean[] check, String[] type) throws IOException {
        if (builder.length() == L) {
            if (checkSecretRule(builder.toString())) {
                bw.write(builder.toString());
                bw.newLine();
            }

            return;
        }

        for (int index = start; index < C; index++) {
            if(!check[index]) {
                check[index] = true;
                makeSecret(index + 1, builder.append(type[index]), check, type);
                builder.deleteCharAt(builder.length() - 1);
                check[index] = false;
            }
        }
    }

    private static boolean checkSecretRule(String secret) {
        int count = 0;
        for (char type : secret.toCharArray()) {
            if(Pattern.compile("[aeiou]").matcher(String.valueOf(type)).find())
                count++;
        }

        if(count < 1) return false;
        return secret.length() - count >= 2;
    }
}
