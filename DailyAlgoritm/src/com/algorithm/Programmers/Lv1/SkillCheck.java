package com.algorithm.Programmers.Lv1;

public class SkillCheck {

    public long solution(int n){
        long i=1;
        long num;
        long answer=0;
        while(true){
                    num = i*i;
                    if(num==n){
                        answer = (i+1) * (i+1);
                        break;
                    }
                    if(num>n){
                        answer = -1;
                        break;
            }
            i++;
        }

        return answer;
    }

    public static void main(String[] args) {
        SkillCheck sk = new SkillCheck();
        System.out.println(sk.solution(3));
    }
}
