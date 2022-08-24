package com.second.algorithm.interview.sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {10, 27, 45, 28, 17, 1, 3, 29, 32, 27, 7, 9, 30, 12};
//        int[] arr = {9,8,7,6,5,4,3,2,1};
        System.out.println("============= 퀵 정렬 (Ascending) ===============");
        quickSortAscending(0, 0, arr.length - 1, arr);

        int[] arr2 = {10, 27, 45, 28, 17, 1, 3, 29, 32, 27, 7, 9, 30, 12};
        System.out.println("============= 블로그 (Ascending) ===============");
        m_pivot_sort(0, arr2, 0, arr.length - 1);

        System.out.println("============= 퀵 정렬 (Descending) ===============");
        arr = new int[]{10, 27, 45, 28, 17, 1, 3, 29, 32, 27, 7, 9, 30, 12};

    }

    /**
     * 퀵 정렬
     * 분할 정복 방식을 통해 임의의 값을 피봇으로 설정, 피봇 기준 왼쪽은 피봇보다 작은 값, 오른쪽은 피봇보다 큰 값
     *
     * @param arr
     */
    private static void quickSortAscending(int index, int left, int right, int[] arr) {
        if (left >= right) return;

        int pivot = partitionAscending(left, right, arr);
        System.out.print("[단계 : " + index + "] ");
        System.out.println(Arrays.toString(arr));

        // 피봇 기준 분할
        quickSortAscending(index + 1, left, pivot - 1, arr);
        quickSortAscending(index + 1, pivot, right, arr);
    }

    private static int partitionAscending(int left, int right, int[] arr) {
        int mid = (left + right) / 2;
        int pivot = arr[mid];
        System.out.println("pivot = " + pivot);

        int l = left, r = right;
        while (l <= r) {
            // 왼쪽을 피봇보다 큰 값을 만날때까지 이동
            while (pivot > arr[l]) l++;

            // 오른쪽을 피봇보다 작은 값을 만날때까지 이동
            while (pivot < arr[r]) r--;

            // l이 r보다 왼쪽에 있는 경우 서로 Swap
            if (l <= r) {
                swap(l, r, arr);
                l++;
                r--;
            }
        }

        return l;
    }

    private static void swap(int l, int r, int[] arr) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }

    private static void m_pivot_sort(int index , int[] a, int lo, int hi) {

        /*
         *  lo가 hi보다 크거나 같다면 정렬 할 원소가
         *  1개 이하이므로 정렬하지 않고 return한다.
         */
        if(lo >= hi) {
            return;
        }

        /*
         * 피벗을 기준으로 요소들이 왼쪽과 오른쪽으로 약하게 정렬 된 상태로
         * 만들어 준 뒤, 최종적으로 pivot의 위치를 얻는다.
         *
         * 그리고나서 해당 피벗을 기준으로 왼쪽 부분리스트와 오른쪽 부분리스트로 나누어
         * 분할 정복을 해준다.
         *
         * [과정]
         *
         * Partitioning:
         *
         *      left part      a[(right + left)/2]      right part
         * +---------------------------------------------------------+
         * |    element < pivot    |  pivot  |    element >= pivot   |
         * +---------------------------------------------------------+
         *
         *
         *  result After Partitioning:
         *
         *         left part         a[hi]          right part
         * +---------------------------------------------------------+
         * |   element < pivot    |  pivot  |    element >= pivot    |
         * +---------------------------------------------------------+
         *
         *
         *  result : pivot = hi
         *
         *
         *  Recursion:
         *
         * m_pivot_sort(a, lo, pivot)         m_pivot_sort(a, pivot + 1, hi)
         *
         *         left part                           right part
         * +-----------------------+             +-----------------------+
         * |   element <= pivot    |             |    element > pivot    |
         * +-----------------------+             +-----------------------+
         * lo                pivot          pivot + 1                   hi
         *
         */
        int pivot = partition(a, lo, hi);
        System.out.print("[단계 : " + index + "] ");
        System.out.println(Arrays.toString(a));
        m_pivot_sort(index+1,a, lo, pivot);
        m_pivot_sort(index+1,a, pivot + 1, hi);
    }



    /**
     * pivot을 기준으로 파티션을 나누기 위한 약한 정렬 메소드
     *
     * @param a		정렬 할 배열
     * @param left	현재 배열의 가장 왼쪽 부분
     * @param right	현재 배열의 가장 오른쪽 부분
     * @return		최종적으로 위치한 피벗의 위치(hi)를 반환
     */
    private static int partition(int[] a, int left, int right) {

        // lo와 hi는 각각 배열의 끝에서 1 벗어난 위치부터 시작한다.
        int lo = left - 1;
        int hi = right + 1;
        int pivot = a[(left + right) / 2];		// 부분리스트의 중간 요소를 피벗으로 설정


        while(true) {

            /*
             * 1 증가시키고 난 뒤의 lo 위치의 요소가 pivot보다 큰 요소를
             * 찾을 떄 까지 반복한다.
             */
            do {
                lo++;
            } while(a[lo] < pivot);


            /*
             * 1 감소시키고 난 뒤의 hi 위치가 lo보다 크거나 같은 위치이면서
             * hi위치의 요소가 pivot보다 작은 요소를 찾을 떄 까지 반복한다.
             */
            do {
                hi--;
            } while(a[hi] > pivot && lo <= hi);


            /*
             * 만약 hi가 lo보다 크지 않다면(엇갈린다면) swap하지 않고 hi를 리턴한다.
             */
            if(lo >= hi) {
                return hi;
            }


            // 교환 될 두 요소를 찾았으면 두 요소를 바꾼다.
            swap(a, lo, hi);
        }

    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
