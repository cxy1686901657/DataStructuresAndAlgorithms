package com.qc.sort;

import java.time.Duration;
import java.time.Instant;

/**
 * @author qc
 * @date 2019/7/23
 */

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};
//        PrintArrUtil.printArr(arr);
//        insertSort1(arr);
//        PrintArrUtil.printArr(arr);
//        insertSort2(arr);
//        PrintArrUtil.printArr(arr);
//        insertSort3(arr);
//        PrintArrUtil.printArr(arr);
        insertSort(arr);
        PrintArrUtil.printArr(arr);


        Instant pre=Instant.now();
        int[] arr2 = new int[80000];
        for(int i =0; i < 80000;i++) {
            arr2[i] = (int)(Math.random() * 8000000); //生成一个[0, 8000000) 数
        }
        insertSort(arr2);
        Instant now=Instant.now();
        System.out.println(Duration.between(pre, now));

    }

    public static void insertSort(int[] arr) {
        int insertVal = 0;
        int insertIndex = 0;
        int count=0;
        for (int i = 0; i < arr.length - 1; i++) {
            insertVal=arr[i+1];
            insertIndex=i;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
                count++;
//                System.out.println("互换了");
            }
            if (insertIndex+1!=i) {
                arr[insertIndex + 1] = insertVal;
            }
        }
        System.out.println(count);
    }
    //first
    public static void insertSort1(int[] arr) {
        int insertVal = arr[1];
        int insertIndex = 1 - 1;
//       第1轮 {101, 34, 119, 1};          101, 101, 119, 1                    => {34, 101, 119, 1}
        boolean flag = false;
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
            flag = true;
            System.out.println("互换了");
        }
        if (flag) {
            arr[insertIndex + 1] = insertVal;
        }
    }
    //second

    //       第2轮 {34, 101, 119, 1};          101, 101, 119, 1                    => {34, 101, 119, 1}
    public static void insertSort2(int[] arr) {
        int insertVal = arr[2];
        int insertIndex = 2 - 1;
        boolean flag = false;
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
            flag = true;
            System.out.println("互换了");
        }
        if (flag) {
            arr[insertIndex + 1] = insertVal;
        }
    }


    private static void insertSort3(int[] arr) {
        //       第3轮 {34, 101, 119, 119};  {34, 101, 101, 119}  {34, 34, 101, 119} {1, 34, 101, 119}
        int insertVal = arr[3];
        int insertIndex = 3 - 1;
        boolean flag = false;
        while (insertIndex >= 0 && arr[insertIndex] > insertVal) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
            flag = true;
            System.out.println("互换了");
        }
        if (flag) {
            arr[insertIndex + 1] = insertVal;
        }
    }

}
