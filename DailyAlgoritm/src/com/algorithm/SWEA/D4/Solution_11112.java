package com.algorithm.SWEA.D4;

import java.io.*;
import java.util.StringTokenizer;

//셀로판
public class Solution_11112 {
    private static int p,q,r;
    private static int a,b,c,d;
    public static void main(String[] args) throws IOException {
        //직사각형 안에 원이 들어가는 경우 (빨강색 N)
        //원 안에 직사각형이 들어가는 경우 (파란색 N)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int TC = Integer.parseInt(br.readLine());
        for(int i =1;i<= TC;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            p = Integer.parseInt(st.nextToken());
            q = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());

            String color = checkColor();

            bw.write("#"+i+" "+color);
            bw.newLine();
        }

        br.close();
        bw.close();
    }

    private static String checkColor() {
        double range = Math.sqrt(Math.pow(c-a,2)+ Math.pow(d-b,2));
        if(p-r >= a && p+r <= c && q-r >= b && q+r <= d)
            return "NY";
        else if(a > p-r && p+r > c && b > q-r && d < q+r && range <= 2*r)
            return "YN";
        return "YY";
    }
}
