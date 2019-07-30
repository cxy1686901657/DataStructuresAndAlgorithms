package com.qc.search;

/**
 * @author qc
 * @date 2019/7/29
 */

public class InsertValSearch {
    public static void main(String[] args){
        int arr[] = { 1, 8, 10, 89,1000, 1234 };
        int index = insertValSearch(arr, 10,0, arr.length - 1);
        System.out.println(index);
    }
    public static int insertValSearch(int arr[],int findVal,int left,int right){
        if (left > right|| findVal< arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }
//        int mid = (left + right) / 2;
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal) {
            return insertValSearch(arr, findVal, mid + 1, right);
        } else if (findVal < midVal) { // 向左递归
            return insertValSearch(arr, findVal, left, mid - 1);
        } else {
            return mid;
        }
    }

}
