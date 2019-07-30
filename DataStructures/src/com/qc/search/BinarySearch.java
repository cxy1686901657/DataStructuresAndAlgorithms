package com.qc.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qc
 * @date 2019/7/28
 */

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 52, 86, 121,121,121, 234, 456};
//        int i = binarySearch(arr, 4561, 0, arr.length - 1);
        List<Integer> list = binarySearch_repeat(arr, 121, 0, arr.length - 1);
        if (list.isEmpty()) {
            System.out.println("未查到");
        }else {
            System.out.println(list);
        }
//        System.out.println(i);

    }

    public static int binarySearch(int[] arr, int value, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (value > midVal) {
            return binarySearch(arr, value, mid + 1, right);
        } else if (value < midVal) { // 向左递归
            return binarySearch(arr, value, left, mid - 1);
        } else {
            return mid;
        }
    }

    public static List<Integer> binarySearch_repeat(int arr[], int findVal, int left, int right) {
        if (left > right) {
            return new ArrayList<>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {
            return binarySearch_repeat(arr, findVal, mid + 1, right);
        } else if (findVal < midVal) { // 向左递归
            return binarySearch_repeat(arr, findVal, left, mid - 1);
        } else {
            ArrayList<Integer> list = new ArrayList<>();
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                }
                list.add(temp);
                temp--;
            }
            list.add(temp);
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) {
                    break;
                }
                list.add(temp);
                temp++;
            }
            return list;
        }
    }


}
