// 프로그래머스 (LV2 주식가격)
public class Solution_42584 {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        int count;
        for(int i=0;i< prices.length-1;i++){
            count = 0;
            for(int j=i+1;j< prices.length;j++){
                if(prices[i]>prices[j]){
                    count ++;
                    break;
                }
                count++;
            }
            answer[i] = count;
        }
        answer[prices.length-1] = 0;

        return answer;
    }

    public static void main(String[] args) {
        Solution_42584 sl = new Solution_42584();
        int[] prices = {1,2,3,2,3};
        sl.solution(prices);
    }
}
