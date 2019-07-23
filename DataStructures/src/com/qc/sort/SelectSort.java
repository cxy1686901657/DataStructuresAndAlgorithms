package com.qc.sort;

import java.time.Duration;
import java.time.Instant;

/**
 * @author qc
 * @date 2019/7/22
 */

public class SelectSort {
    public static void main(String[] args) {
//        int[] arr = {101, 34, 119, 1};
//        selectSort(arr);
//        for (int a : arr) {
//            System.out.printf("%d\t\t", a);
//        }


        Instant pre=Instant.now();
        int[] arr2 = new int[80000];
        for(int i =0; i < 80000;i++) {
            arr2[i] = (int)(Math.random() * 8000000); //生成一个[0, 8000000) 数
        }
        selectSort(arr2);
        Instant now=Instant.now();
        System.out.println(Duration.between(pre, now));
    }

    //选择排序时间复杂度是 O(n^2)
    public static void selectSort(int[] arr) {
        int count=0;
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                    count++;
                }
            }
            // 将最小值，放在arr[0], 即交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
        System.out.println(count);
    }
}
