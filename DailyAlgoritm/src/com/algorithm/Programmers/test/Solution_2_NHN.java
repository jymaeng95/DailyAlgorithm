package com.algorithm.Programmers.test;

import java.util.*;

public class Solution_2_NHN {
    public static void main(String[] args) {
        int[] balance = {30, 30, 30, 30};
        int[][] transaction = {{1, 2, 10}, {2, 3, 20}, {3, 4, 5}, {3, 4, 30}};
        int[] abnormal = {1};

        int[] rst = solution(balance, transaction, abnormal);
        for (int r : rst) {
            System.out.println(r);
        }
    }

    public Solution_2_NHN() {
    }

    private static int[] solution(int[] balance, int[][] transaction, int[] abnormal) {
        int[] answer = new int[balance.length];
        List<Stack<User>> userList = new ArrayList<>();

        for(int index = 0; index <= balance.length; index++) {
            userList.add(new Stack<>());
        }

        // 유저별 스택에 초기자금 넣기
        for(int user = 1; user<= balance.length; user++) {
            userList.get(user).add(new User(user, balance[user-1]));
        }

        // 트랜잭션 이동
        for (int[] trans : transaction) {
            int from = trans[0];
            int to = trans[1];
            int money = trans[2];

            // from의 스택 팝 해서 금액 빼주기 ( pop < money 인경우) pop 금액 빼주고 다음 pop
            while(money > 0) {
                User fromBalance = userList.get(from).pop();
                if(fromBalance.getBalance() < money) {
                    money -= fromBalance.getBalance();

                    // 받는 사람에게 재화 넣어주기
                    userList.get(to).add(fromBalance);
                }
                else {
                    fromBalance.setBalance(fromBalance.getBalance() - money);

                    // 0원이 되지 않으므로 다시 새로운 값으로 스택에 넣어주기
                    userList.get(from).push(fromBalance);

                    // 받는 사람에게 재화 넣어주기 from의 돈
                    userList.get(to).push(new User(fromBalance.getUser(), money));

                    money = 0;
                }
            }
        }

        // 비정상 재화 체크
        for(int index = 1; index <= balance.length; index++) {
            int totalBalance = 0;
            int retrieve = 0;

            // 스택 최종 자금
            for (User user : userList.get(index)) {
                totalBalance += user.getBalance();
                // 비정상 유저 금액 체크
                for (int abnormalUser : abnormal) {
                    if(user.getUser() == abnormalUser) retrieve += user.getBalance();
                }
            }
            answer[index-1] = totalBalance - retrieve;
        }

        return answer;
    }
    static class User {
        private int user;
        private int balance;

        public User(int user, int balance) {
            this.user = user;
            this.balance = balance;
        }

        public int getUser() {
            return user;
        }

        public int getBalance() {
            return balance;
        }

        public void setUser(int user) {
            this.user = user;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }
    }
}
