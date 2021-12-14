package com.algorithm.thisiscodingtest.implementation;

public class P323_Q09_문자열_압축 {
    public static void main(String[] args) {
        String s = "aabbaccc";
        int rst = getZipStr(s);
        System.out.println("rst = " + rst);

    }

    private static int getZipStr(String s) {
        int rst = s.length();
        int length = 1;
        while(length < s.length()) {

            StringBuilder sb = new StringBuilder();
            String prev = s.substring(0,length);
            String present = "";
            int count = 1;
            for(int i=length; i<s.length();i+=length) {

                if(i+length >= s.length()) {
                    present = s.substring(i);
                }
                else {
                    present = s.substring(i,i+length);
                }
                if(prev.equals(present)) {
                    count++;
                }
                else {
                    if(count > 1)
                        sb.append(count);
                    sb.append(prev);
                    prev = present;
                    count = 1;
                }
            }
            if(count > 1)
                sb.append(count);
            sb.append(prev);
            rst = Math.min(rst, sb.length());
            length++;
        }

        return rst;
    }
}
