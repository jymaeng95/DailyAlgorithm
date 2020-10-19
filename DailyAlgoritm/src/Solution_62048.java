// 프로그래머스(LV2 멀쩡한 사각형)
public class Solution_62048 {
    public long solution(long w, long h) {
        long answer;
        long gcd = getGCD(w,h);
        // 사각형 (최대 가로 , 세로 만큼 무조건 겹침 + 중복되는 갯수는 최대공약수 갯수)
        long wrongSquare = w+h-gcd;
        answer = w*h - wrongSquare;
        return answer;
    }

    //최대 공약수 (유클리드 호제법)
    public long getGCD(long w, long h){
        while(h!=0){
            long r = w%h;
            w=h;
            h=r;
        }
        return w;
    }

    public static void main(String[] args) {
        Solution_62048 sl = new Solution_62048();
        sl.solution(1,12);
    }
}
