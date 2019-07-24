package com.qc.sort;

import java.time.Duration;
import java.time.Instant;

/**
 * @author qc
 * @date 2019/7/23
 * @description *******
 */

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//        PrintArrUtil.printArr(arr);
//        shell1(arr);
//        PrintArrUtil.printArr(arr);
//        shell2(arr);
//        PrintArrUtil.printArr(arr);
//        shell3(arr);
//        PrintArrUtil.printArr(arr);
        shellSort(arr);
        PrintArrUtil.printArr(arr);
//        Instant pre=Instant.now();
//        int[] arr2 = new int[8000000];
//        for(int i =0; i < 8000000;i++) {
//            arr2[i] = (int)(Math.random() * 8000000); //生成一个[0, 8000000) 数
//        }
//        shellSort2(arr2);
//        Instant now=Instant.now();
//        System.out.println(Duration.between(pre, now));

    }

    public static void shell1(int[] arr) {
        int temp;
        for (int i = arr.length / 2; i < arr.length; i++) {
            for (int j = i - arr.length / 2; j >= 0; j -= 5) {
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                    System.out.println("111");
                }
            }
        }
    }

    public static void shell3(int[] arr) {
        int temp;
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j -= 1) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    System.out.println("333");
                }
            }
        }
    }

    public static void shell2(int[] arr) {
        int temp;
        for (int i = 2; i < arr.length; i++) {
            for (int j = i - 2; j >= 0; j -= 2) {
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                    System.out.println("222");
                }
            }
        }

    }




    public static void shellSort(int[] arr) {
        int temp;
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                // 遍历各组中所有的元素(共gap组，每组有个元素), 步长gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    // 如果当前元素大于加上步长后的那个元素，说明交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
    }

    public static void shellSort2(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 从第gap个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int insertIndex = i;
                int insertValue = arr[insertIndex];
                if (arr[insertIndex] < arr[insertIndex - gap]) {
                    while (insertIndex - gap >= 0 && insertValue < arr[insertIndex - gap]) {
                        //移动
                        arr[insertIndex] = arr[insertIndex-gap];
                        insertIndex -= gap;
                    }
                    //当退出while后，就给temp找到插入的位置
                    arr[insertIndex] = insertValue;

                }

            }
        }


    }


}
