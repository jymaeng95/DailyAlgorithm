import java.util.*;

// 프로그래머스 (LV2 프린터)
public class Solution_42587 {

    // 1. 인쇄 대기목록의 가장 앞에 있는 문서(J)를 대기목록에서 꺼냅니다.
    // 2. 나머지 인쇄 대기목록에서 J보다 중요도가 높은 문서가 한 개라도 존재하면 J를 대기목록의 가장 마지막에 넣습니다.
    // 3. 그렇지 않으면 J를 인쇄합니다
    public int solution(int[] priorities, int location) {
        int answer = 0;
        boolean flag = false;
        List<Integer> list = new ArrayList<>();
        Queue<Integer> priQueue = new LinkedList<>();
        Queue<Integer> indexQueue = new LinkedList<>();

        for(int i=0;i< priorities.length;i++){
            priQueue.add(priorities[i]);
            indexQueue.add(i);
        }
        int i;
        while(!priQueue.isEmpty()){
            i=0;
            flag = false;
            int first = priQueue.remove();
            Iterator<Integer> it = priQueue.stream().iterator();
            while(i<priQueue.size()){
                if(first < it.next()){
                    priQueue.add(first);
                    indexQueue.add(indexQueue.remove());
                    flag = false;
                    break;
                }
                i++;
                flag = true;
            }
            if(flag)
                list.add(indexQueue.remove());
        }
        list.add(indexQueue.remove());
        for(int j=0;j<list.size();j++){
            if(list.get(j) == location) {
                answer = j+1;
                break;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution_42587 sl = new Solution_42587();
        int[] priorities = {1,1,9,1,1,1};
        sl.solution(priorities,0);
    }
}
