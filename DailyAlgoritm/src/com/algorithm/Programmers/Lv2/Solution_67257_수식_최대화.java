package com.algorithm.Programmers.Lv2;

import java.util.ArrayList;
import java.util.List;

public class Solution_67257_수식_최대화 {
    public static void main(String[] args) {
        String expression = "100-200*300-500+20";
        long rst = solution(expression);
        System.out.println("rst = " + rst);
    }

    private static final String[][] OPS = {{"-", "*", "+"}, {"-", "+", "*"}, {"+", "-", "*"},
            {"+", "*", "-"}, {"*", "-", "+"}, {"*", "+", "-"}};
    private static List<String> expList;
    private static long solution(String expression) {
        long answer = 0;
        expList = new ArrayList<>();
        StringBuilder operand = new StringBuilder();
        for(char exp : expression.toCharArray()) {
            if(!Character.isDigit(exp)) {
                expList.add(operand.toString());
                expList.add(String.valueOf(exp));
                operand = new StringBuilder();
            }
            else operand.append(exp);
        }
        expList.add(operand.toString());

        for(String[] OP : OPS) {
            List<String> exp = new ArrayList<>(List.copyOf(expList));
            for(String operation : OP) {
                for(int loop = 0; loop < exp.size(); loop++) {
                    if(exp.get(loop).equals(operation)) {
                        long prev = Long.parseLong(exp.get(loop-1));
                        long post = Long.parseLong(exp.get(loop+1));
                        exp.set(loop-1, calculation(prev, post, exp.get(loop)));    // prev 자리에 계산한 값 세팅
                        exp.remove(loop);   // 연산자
                        exp.remove(loop);   // post
                        loop--;
                    }
                }
            }
            answer = Math.max(answer, Math.abs(Long.parseLong(exp.get(0))));
        }
         return answer;
    }

    private static String calculation(long prev, long post, String op) {
        if(op.equals("+")) return String.valueOf(prev + post);
        if(op.equals("-")) return String.valueOf(prev - post);
        return String.valueOf(prev * post);
    }
}
