package com.second.algorithm.interview.sort;

public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = {10, 27, 45, 28, 17, 1, 3, 29, 32, 27, 7, 9, 30, 12};
        System.out.println("============= 삽입 정렬 (Ascending) ===============");
        insertionSortAscending(arr);

        System.out.println("============= 삽입 정렬 (Descending) ===============");
        arr = new int[]{10, 27, 45, 28, 17, 1, 3, 29, 32, 27, 7, 9, 30, 12};
        insertionSortDescending(arr);

    }

    /**
     * 삽입 정렬
     * 1 인덱스부터 현재 인덱스 보다 작은 인덱스 중에 자신보다 원소 값이 작은 경우까지 인덱스를 탐색하고 해당 자리에 넣어준다.
     * 원소를 넣어주고 이동한 만큼 원소들을 뒤로 밀어준다.
     *
     * @param arr 배열
     */
    private static void insertionSortAscending(int[] arr) {
        for (int index = 1; index < arr.length; index++) {
            int prev = index - 1;
            int temp = arr[index];

            // prev 0까지 && temp가 자기보다 큰 경우까지
            while (prev >= 0 && temp < arr[prev]) {
                // 자리 밀어주기(스왑)
                arr[prev + 1] = arr[prev];
                prev--;
            }

            // 해당 위치에 넣어주기
            arr[prev + 1] = temp;

            // 단계별 출력 확인
            System.out.print("[단계 : " + index + "] ");
            for (int num : arr) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    private static void insertionSortDescending(int[] arr) {
        for (int index = 1; index < arr.length; index++) {
            int temp = arr[index];
            int prev = index - 1;

            while (prev >= 0 && arr[prev] < temp) {
                arr[prev + 1] = arr[prev];
                prev--;
            }
            arr[prev + 1] = temp;

            // 단계별 출력 확인
            System.out.print("[단계 : " + index + "] ");
            for (int num : arr) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
