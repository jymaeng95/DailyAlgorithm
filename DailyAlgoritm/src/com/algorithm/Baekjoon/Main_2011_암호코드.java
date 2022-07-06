package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2011_암호코드 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String secret = br.readLine();

        long rst = countDecrypt(secret);
        System.out.println(rst);

        br.close();
    }

    private static long countDecrypt(String secret) {
        if (secret.charAt(0) == '0') return 0;
        if (secret.length() == 1) return 1;
        long[] dp = new long[secret.length() + 1];
        dp[1] = 1;
        int number = Integer.parseInt(secret.substring(0, 2));
        if(secret.charAt(1) == '0')  {
            if(number > 26) return 0;
            else dp[2] = dp[1];
        }
        else {
            if(number > 26 || number < 10) dp[2] = dp[1];
            else dp[2] = dp[1] + 1;
        }
        if (number <= 10 || secret.charAt(1) == '0' || number > 26) dp[2] = 1;
        else dp[2] = dp[1] + 1;

        for (int index = 3; index <= secret.length(); index++) {
            number = Integer.parseInt(secret.substring(index - 2, index));

            // 두 자리수가 0인 경우는 잘못된 암호인 경우
            if (number == 0) return 0;

            if(secret.charAt(index-1) == '0') {
                if(number > 26) return 0;
                else dp[index] = (dp[index - 2] % 1000000);
            }
            else {
                if(number > 26 || number < 10) dp[index] = (dp[index - 1] % 1000000);
                else dp[index] = (dp[index - 2] + dp[index - 1]) % 1000000;
            }
        }
        return dp[secret.length()];
    }
}