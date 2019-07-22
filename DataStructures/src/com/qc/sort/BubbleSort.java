package com.qc.sort;

import java.time.Duration;
import java.time.Instant;

/**
 * @author qc
 * @date 2019/7/22
 * @decription 	冒泡排序 的时间复杂度 O(n^2)
 */

public class BubbleSort {
    public static void main(String[] args) {
//        int arr[] = {3, 9, -1, 10, -2};
//        bubbleSort(arr);
//        print(arr);
        Instant pre=Instant.now();
        int[] arr2 = new int[80000];
        for(int i =0; i < 80000;i++) {
            arr2[i] = (int)(Math.random() * 8000000); //生成一个[0, 8000000) 数
        }
        bubbleSort(arr2);
        Instant now=Instant.now();
        System.out.println(Duration.between(pre, now));

    }

    public static void bubbleSort(int[] arr) {
        // 冒泡排序 的时间复杂度 O(n^2), 自己写出
        int temp = 0; // 临时变量
        boolean flag = false; // 标识变量，表示是否进行过交换
        for (int i = 0; i < arr.length - 1; i++) {

            for (int j = 0; j < arr.length - 1 - i; j++) {
                // 如果前面的数比后面的数大，则交换
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag) { // 在一趟排序中，一次交换都没有发生过
                break;
            } else {
                flag = false; // 重置flag!!!, 进行下次判断
            }
        }
    }
    public static void print(int[] array){
        for (int a :array){
            System.out.printf("%d\t",a);
        }
    }
}
