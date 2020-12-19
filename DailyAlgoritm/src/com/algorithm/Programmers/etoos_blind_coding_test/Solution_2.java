package com.algorithm.Programmers.etoos_blind_coding_test;

import java.util.Stack;
import java.util.StringTokenizer;
/*
당신은 자유롭게 입금과 출금이 가능한 예금 상품을 이용하고 있습니다. 이 예금 상품은 다음과 같은 특징을 가지고 있습니다.

입금 날짜별로 서로 다른 금리가 적용될 수 있습니다.
출금 시에는 가장 최근에 입금된 금액부터 출금되며, 출금된 금액에 대해서는 입금 당시 적용된 금리와 예치 기간으로 이자가 계산됩니다.
12월 31일에는 잔액이 자동으로 모두 출금되며, 출금액에 대해서는 위 2번과 같은 방식으로 이자가 계산됩니다.
이자를 계산할 때, 소수점 이하는 버립니다.
아래의 표는 1월 1일부터 12월 30일까지의 입출금 내역을 바탕으로, 입금액의 예치 기간과 출금액의 출처를 정리한 예시입니다.

거래일	1년 금리(%)	입/출금액(원)	비고
01/01	4	50000	20000(31일 예치), 10000(83일 예치), 20000(364일 예치)
01/11	6	3555	3555(21일 예치)
02/01	0	-23555	01/11(-3555원), 01/01(-20000원)
02/25	5	5000	5000(28일)
03/25	0	-15000	02/25(-5000원), 01/01(-10000원)
06/09	8	43951	43951(205일 예치)
12/30	9	99999	99999(1일 예치)
본 문제에서는 윤년은 존재하지 않는다고 가정합니다. 즉, 2월의 마지막 날은 28일입니다.
입/출금액이 양수이면 입금을, 음수이면 출금을 의미합니다.
입금인 경우, 비고란은 해당 금액을 출금 시까지 예치한 기간을 나타냅니다.
출금인 경우, 비고란은 해당 금액을 어디서 출금했는지 나타냅니다.
02/01에 23555원을 출금했습니다. 가장 최근 입금액부터 출금되므로, 01/11에 입금된 3555원이 모두 출금되고, 01/01에 입금된 금액에서 20000원이 출금됩니다.
01/11에 입금된 3555원은 연이율 6%로 21일간 예치되었으므로, 다음과 같이 이자는 12원으로 결정됩니다.
(3555 X 0.06) X (21 / 365) ≒ 12
01/01에 입금된 50000원 중 출금된 20000원은 연이율 4%로 31일간 예치되었으므로, 다음과 같이 이자는 67원으로 결정됩니다.
(20000 X 0.04) X (31 / 365) ≒ 67
03/25에 15000원을 출금했습니다. 02/25에 입금된 5000원이 모두 출금되고, 01/01에 입금된 금액에서 10000원이 출금됩니다.
02/25에 입금된 5000원은 연이율 5%로 28일간 예치되었으므로, 다음과 같이 이자는 19원으로 결정됩니다.
(5000 X 0.05) X (28 / 365) ≒ 19
01/01에 입금된 50000원 중 출금된 10000원은 연이율 4%로 83일간 예치되었으므로, 다음과 같이 이자는 90원으로 결정됩니다.
(10000 X 0.04) X (83 / 365) ≒ 90
12/31에 잔액 모두가 자동으로 출금됩니다.
12/30에 입금된 99999원은 연이율 9%로 1일간 예치되었으므로, 다음과 같이 이자는 24원으로 결정됩니다.
(99999 X 0.09) X (1 / 365) ≒ 24
06/09에 입금된 43951원은 연이율 8%로 205일간 예치되었으므로, 다음과 같이 이자는 1974원으로 결정됩니다.
(43951 X 0.08) X (205 / 365) ≒ 1974
01/01에 입금된 50000원 중 20000원은 연이율 4%로 364일간 예치되었으므로, 다음과 같이 이자는 797원으로 결정됩니다.
(20000 X 0.04) X (364/ 365) ≒ 797
12 + 67 + 19 + 90 + 24 + 1974 + 797 = 2983
따라서, 당신이 받을 수 있는 이자의 총합은 2983원입니다.
당신의 입/출금 내역을 담은 문자열 배열 ledgers가 매개변수로 주어집니다. 이때, 당신이 받을 수 있는 이자의 총합을 계산하여 return 하도록 solution 함수를 완성해주세요.

제한사항
ledgers의 길이(=입출금 내역의 개수)는 1 이상 364 이하입니다.
ledgers의 원소들은 거래일이 빠른 것부터 오름차순으로 정렬되어 있습니다.
ledgers의 각 원소는 "MM/DD R MONEY" 형식의 문자열입니다.
MM/DD는 01/01부터 12/30까지이며, 입/출금이 발생한 거래일을 나타냅니다.
거래일이 중복되는 경우는 입력으로 주어지지 않습니다. 즉, 하루에 한 번만 거래할 수 있다고 가정합니다.
12/31은 자동으로 모든 잔액이 출금되는 만기일으므로, 거래일로는 주어지지 않습니다.
윤년은 존재하지 않는다고 가정했으므로, 02/29는 거래일로 주어지지 않습니다.
13/15, 05/32과 같이 잘못된 날짜는 거래일로 주어지지 않습니다.
1/1, 5/25, 11/7, 12:12과 같이 5자리의 MM/DD형식을 맞추지 않은 날짜는 거래일로 주어지지 않습니다.
R은 1년 금리(Rate of interest)를 나타내며, 0 이상 9 이하인 정수입니다.
출금인 경우에만 R이 0입니다.
입금인 경우에는 R은 1 이상 9 이하입니다.
MONEY는 입출금액을 나타내며, -99999 이상 99999 이하입니다.
따라서, MONEY의 자릿수는 - 부호를 포함하여 1 이상 6 이하입니다.
MONEY가 0인 경우는 입력으로 주어지지 않습니다.
MONEY가 양수이면 입금을, 음수이면 출금을 의미합니다.
현재 잔액보다 출금하려는 금액이 큰 경우는 입력으로 주어지지 않습니다.
MM/DD, R, MONEY는 하나의 공백(스페이스)으로 구분되어 있습니다.
 */
public class Solution_2 {
    public static void main(String[] args) {
        String[] ledgers = {"04/01 1 40000","05/01 5 20000","08/31 4 10000","11/11 0 -45000"};
        solution(ledgers);
    }
    private static final int[] date = {31,28,31,30,31,30,31,31,30,31,30,31};
    static class Bank {
        private int totalDay;
        private int rate;
        private int money;
        public Bank(int month, int day, int rate, int money) {
            this.rate = rate;
            this.money = money;
            totalDay = calcDate(month,day);
        }
    }

    public static int calcDate(int month,int day) {
        int sum = 0;
        for(int i=0;i<month-1;i++){
            sum += date[i];
        }
        sum += day;
        return sum;
    }
    public static int solution(String[] ledgers) {
        Stack<Bank> stack = new Stack<>();
        int interest = 0;
        for(String s : ledgers){
            StringTokenizer st = new StringTokenizer(s," ");
            String[] date = st.nextToken().split("/");
            int rate = Integer.parseInt(st.nextToken());
            int money = Integer.parseInt(st.nextToken());
            //입금
            if(money > 0) {
                stack.push(new Bank(Integer.parseInt(date[0]),Integer.parseInt(date[1]),rate,money));
                continue;
            }
            while(money < 0) {
                Bank bank = stack.pop();
                if((-1)*money < bank.money) {
                    interest += Math.floor((-1) * money * ((double)bank.rate/100) * ((double)(calcDate(Integer.parseInt(date[0]),Integer.parseInt(date[1])) - bank.totalDay)/365));
                    bank.money += money;
                    stack.push(bank);
                    break;
                }
                interest += Math.floor(bank.money * ((double)bank.rate/100) * ((double)(calcDate(Integer.parseInt(date[0]),Integer.parseInt(date[1])) - bank.totalDay)/365));
                money += bank.money;
            }

        }
        for(Bank bank : stack){
            interest += Math.floor(bank.money * ((double)bank.rate/100) * (365 - bank.totalDay)/365);
        }
        return interest;
    }
}
