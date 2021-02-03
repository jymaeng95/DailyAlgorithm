package com.algorithm.Programmers.Lv2;

public class Solution_72410_신규_아이디_추천 {
    public static void main(String[] args) {
        solution("abcdefghijklmn.p");
    }

    /*
    1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
    2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
    3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
    4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
    5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
    6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
     만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
    7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
     */
    private static String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private static String num = "0123456789";
    public static String solution(String new_id) {
        //1단계
        new_id = new_id.toLowerCase();

        //2단계
        StringBuilder sb = new StringBuilder();
        for (char c : new_id.toCharArray()) {
            if (alphabet.contains(String.valueOf(c)) || c == '.' || c == '-' || c == '_' || num.contains(String.valueOf(c)))
                sb.append(c);
        }

        //3단
        StringBuilder sb2 = new StringBuilder();
        for(char c :  sb.toString().toCharArray()){
            if(c == '.') {
                if (sb2.length() != 0 && sb2.charAt(sb2.length() - 1) == '.')
                    continue;
            }
            sb2.append(c);
        }

        //4단계
        if(sb2.length() > 0) {
            if (sb2.charAt(0) == '.')
                sb2.deleteCharAt(0);
            if (sb2.length() != 0 && sb2.charAt(sb2.length() - 1) == '.')
                sb2.deleteCharAt(sb2.length() - 1);
        }
        //5단계
        if(sb2.length() == 0 )
            sb2.append("a");

        //6단계
        if(sb2.length() > 15)
            sb2.delete(15,sb2.length());
        if(sb2.charAt(sb2.length()-1)=='.')
            sb2.deleteCharAt(sb2.length()-1);

        //7단계
        if(sb2.length() <=2 ){
            char last = sb2.charAt(sb2.length()-1);
            for(int i = sb2.length();i<3;i++){
                sb2.append(last);
            }
        }
        return sb2.toString();
    }
}
