package com.algorithm.LeetCode.hard;

import java.util.*;

public class Solution_719_Find_Kth_Smallest_Pair_Distance {
    public static void main(String[] args) {
        int[] nums = {221238,427286,829789,601893,358469,46342,598804,666075,725560,842824,261672,391778,964604,53621,533121,755551,807344,597092,774256,63098,948199,579547,3196,909877,95910,965027,411050,532303,362036,248585,389213,926908,139846,642116,527660,735487,787738,978186,545605,595011,705832,788214,407493,856161,947455,114342,459338,744156,179687,348085,724897,694016,170072,797188,194673,987604,748362,124575,101059,588749,515935,846183,783882,752572,984672,299643,474986,42219,914392,699717,862520,146910,156305,269341,268361,19009,292092,663200,349590,296229,194323,667426,751544,139621,339144,979848,895018,432652,524214,697568,946806,378351,536203,315145,567426,212742,871720,419529,340374,869276,865407,603396,609939,616585,590359,21031,813301,154998,592050,679203,535025,94441,193548,618901,170094,996902,981372,129574,272754,762181,746001,525671,398300,912121,682153,5904,473065,729188,727720,183096,851216,848578,908443,532556,369537,800932,713898,919800,350740,851358,734571,674194,624352,862486,254700,127824,705571,61267,335237,377466,124085,542898,263565,867445,570415,909045,947063,167541,113129,897668,402701,287017,87703,233079,67973,689618,433223,164387,627387,472038,97781,148808,263578,28836,226968,476149,778104,404041,300170,319580,355689,713032,92394,739268,614862,716425,260673,449432,607545,987946,243679,147202,111207,598535,676528,107358,372982,190321,217552,501122};
        int k = 10000;
        int dist = smallestDistancePair(nums, k);
    }
    private static PriorityQueue<Integer> distance;
    private static boolean[] visited;
    private static Set<String> set;
    public static int smallestDistancePair(int[] nums, int k) {
        distance = new PriorityQueue<>();
        set = new HashSet<>();
        for(int i =0;i<nums.length;i++){
            visited = new boolean[nums.length];
            combination(nums,i,nums.length,2);
        }
        for(int i =1;i<k;i++){
            distance.poll();
        }
        return distance.peek();
    }

    private static void combination(int[] nums, int start, int n, int r) {
        if(r==0){
            getDistance(nums);
            return;
        }
        for(int i = start;i<n;i++){
            visited[i] = true;
            combination(nums,i+1,n,r-1);
            visited[i] = false;
        }
    }

    private static void getDistance(int[] nums) {
        List<Integer> pair = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<nums.length;i++){
            if(visited[i]) {
                pair.add(nums[i]);
                sb.append(i).append(",");
            }
        }
        if(set.contains(sb.toString()))
            return;
        set.add(sb.toString());
        distance.offer(Math.abs(pair.get(0)-pair.get(1)));
    }
}
