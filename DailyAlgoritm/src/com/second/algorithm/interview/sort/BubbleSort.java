package com.second.algorithm.interview.sort;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {10, 27, 45, 28, 17, 1, 3, 29, 32, 27, 7, 9, 30, 12};
        System.out.println("============= 버블 정렬 (Ascending) ===============");
        bubbleSortAscending(arr);

        System.out.println("============= 버블 정렬 (Descending) ===============");
        arr = new int[]{10, 27, 45, 28, 17, 1, 3, 29, 32, 27, 7, 9, 30, 12};
        bubbleSortDescending(arr);

    }

    /**
     * 버블 정렬
     * 시간 복잡도 O(n2) (n-1) + (n-2) + (n-3)... + 2 + 1 = n(n-1) / 2
     * 매번 원소의 2개 비교, 매 회차마다 끝에서 회차까지 정렬된 상태
     */
    private static void bubbleSortAscending(int[] arr) {
        // 배열을 n번 반복하면서 비교
        for (int loop = 0; loop < arr.length; loop++) {
            // 매 회차마다 끝에서 회차만큼까지 정렬된 상태
            for (int index = 1; index < arr.length - loop; index++) {
                // 현재 원소가 이전 원소보다 작은 경우 (Ascending) 서로 교환
                if (arr[index - 1] > arr[index]) {
                    int temp = arr[index -1];
                    arr[index-1] = arr[index];
                    arr[index] = temp;
                }
            }
            // 단계별 출력 확인
            System.out.print("[단계 : " + loop + "] ");
            for (int num : arr) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    private static void bubbleSortDescending(int[] arr) {
        // 배열을 n번 반복
        for (int loop = 0; loop < arr.length; loop++) {
            // 매 회차마다 끝에서 회차만큼 정렬된 상태
            for (int index = 1; index < arr.length - loop; index++) {
                // 현재 원소가 이전 원소보다 큰 경우 (Descending) 스왑
                if(arr[index -1] < arr[index]) {
                    int temp = arr[index - 1];
                    arr[index - 1] = arr[index];
                    arr[index] = temp;
                }
            }

            // 단계별 출력 확인
            System.out.print("[단계 : " + loop + "] ");
            for (int num : arr) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
