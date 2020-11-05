package com.algorithm.Programmers.winter_internship;

public class EncryptMessage {
    public static void main(String[] args) {
        solution("qyyigoptvfb","abcdefghijk",3);
        solution("xjk","ewy",0);
        System.out.println(-5%3);
    }

    public static String solution(String encrypted_text, String key, int rotation) {
        String answer = "";
        String alpah = " abcdefghijklmnopqrstuvwxyz";
        StringBuilder enc = new StringBuilder(encrypted_text);
        StringBuilder keyText = new StringBuilder(key);
        int size = enc.length();
        rotation %= enc.length();
        if(rotation<0){
            for(int i=size-1;i>=size+rotation;i--) {
                enc.insert(0,enc.charAt(size-1));
            }
            enc.delete(size+rotation,size);
        }else{
            for(int i=0;i<rotation;i++){
                enc.append(enc.charAt(i));
            }
            enc.delete(0,rotation);

        }
        for(int i=0;i<enc.length();i++){
            int where = alpah.indexOf(String.valueOf(enc.charAt(i))) - (alpah.indexOf(String.valueOf(key.charAt(i))));
            if(where < 1){
                answer+=alpah.charAt(where+26);
            }else{
                answer += alpah.charAt(where);
            }
        }
        return answer;
    }
}
