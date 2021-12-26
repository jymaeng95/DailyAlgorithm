package com.algorithm.thisiscodingtest.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class P359_Q23_국영수 {
    static class Grade implements Comparable<Grade> {
        private String name;
        private int korean;
        private int english;
        private int math;

        public String getName() {
            return name;
        }

        public Grade(String name, int korean, int english, int math) {
            this.name = name;
            this.korean = korean;
            this.english = english;
            this.math = math;
        }

/*        국어 점수가 감소하는 순서로
        국어 점수가 같으면 영어 점수가 증가하는 순서로
        국어 점수와 영어 점수가 같으면 수학 점수가 감소하는 순서로
        모든 점수가 같으면 이름이 사전 순으로 증가하는 순서로 (단, 아스키 코드에서 대문자는 소문자보다 작으므로 사전순으로 앞에 온다.)*/
        @Override
        public int compareTo(Grade o) {
            if(this.korean == o.korean) {
                if(this.english == o.english) {
                    if(this.math == o.math) {
                        return this.name.compareTo(o.name);
                    }
                    return Integer.compare(o.math, this.math);
                }
                return Integer.compare(this.english, o.english);
            }
            return Integer.compare(o.korean, this.korean);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Grade> list = new ArrayList<>();
        for(int i =0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            list.add(new Grade(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(list);
        for(Grade gd : list) {
            System.out.println(gd.getName());
        }

        br.close();
    }
}
