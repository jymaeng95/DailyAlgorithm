package com.second.algorithm.interview.sort;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {10, 27, 45, 28, 17, 1, 3, 29, 32, 27, 7, 9, 30, 12};
        System.out.println("============= 힙 정렬 (Ascending) ===============");
        heapSortAscending(arr);

    }

    private static void heapSortAscending(int[] arr) {
        int size = arr.length;
        int lastIndex = size - 1;

        // 정렬할 필요 없는 경우
        if(size < 2) return;

        // 부모 노드만큼 루프 돌아서 최대힙 구성
        int lastParentIndex = getParentIndex(lastIndex);
        for (int index = lastParentIndex; index >= 0; index--) {
            // 최대 힙 구성
            makeMaxHeap(arr, index, lastIndex);
        }
        System.out.println(Arrays.toString(arr));

        // 맨 마지막 노드 부터 하나씩 정렬 이어나가기
        /**
         *  루트 노드에는 항상 최대값이 존재
         *  마지막 위치 부터 인덱스를 줄여가면서 루트노드랑 스왑 = 마지막 인덱스는 정렬된 상태
         *  마지막 인덱스 - 1만큼 최대힙 구성 및 0번 인덱스까지 반복
         */
        for (int index = lastIndex; index >= 0; index--) {
            swap(arr, 0, index);
            makeMaxHeap(arr, 0, index - 1);
        }
    }

    private static void makeMaxHeap(int[] arr, int parentIndex, int lastIndex) {
        int maxValueIndex = parentIndex;

        // 부모 노드가 단말노드가 되는 경우
        while (parentIndex * 2 + 1 <= lastIndex) {
            int leftChildIndex = parentIndex * 2 + 1;
            int rightChildIndex = parentIndex * 2 + 2;

            // 왼쪽 자식 노드와 현재 부모 노드 비교
            if(leftChildIndex <= lastIndex && arr[leftChildIndex] > arr[maxValueIndex]) {
                maxValueIndex = leftChildIndex;
            }

            // 오른쪽 자식 노드와 현재 부모 노드 비교
            if (rightChildIndex <= lastIndex && arr[rightChildIndex] > arr[maxValueIndex]) {
                maxValueIndex = rightChildIndex;
            }

            // 현재까지 최대값을 가진 인덱스와 부모노드를 교환 => 부모노드랑 maxValueindex가 다른 경우
            if (parentIndex != maxValueIndex) {
                // maxValueIndex와 parentIndex 값 교환 후 부모노드를 maxValue Index로 해서 다시 탐색
                swap(arr, parentIndex, maxValueIndex);
                parentIndex = maxValueIndex;
            }
            else return;
        }
    }

    private static void swap(int[] arr, int parentIndex, int childIndex) {
        int temp = arr[parentIndex];
        arr[parentIndex] = arr[childIndex];
        arr[childIndex] = temp;
    }

    // 부모 노드 인덱스 구하기
    private static int getParentIndex(int child) {
        return (child - 1) / 2;
    }

}
