package com.qc.binarysearchnorecursion;

/**
 * @author qc
 * @date 2019/8/7
 * @description
 * @project DataStructuresAndAlgorithms
 */

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        int index = binarySearch(arr, 8, 0, arr.length - 1);
        System.out.println("index=" + index);//
    }

    private static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (right + left) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    private static int binarySearch(int[] arr, int target, int left, int right) {
        if(left>right){
            return -1;
        }
        int mid = (left + right) / 2;

        if (arr[mid] < target) {
            return binarySearch(arr, target, mid+1, right);
        } else if (arr[mid] > target) {
            return binarySearch(arr, target, left, mid-1);
        } else{
           return mid;
        }

    }
}
