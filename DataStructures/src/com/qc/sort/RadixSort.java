package com.qc.sort;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

/**
 * @author qc
 * @date 2019/7/28
 */

public class RadixSort {
    public static void main(String[] args) {
        int arr[] = {53, 3, 542, 748, 14, 214};
        radixSort(arr);
        PrintArrUtil.printArr(arr);
        Instant pre = Instant.now();
        int[] arr2 = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr2[i] = (int) (Math.random() * 8000000); //生成一个[0, 8000000) 数
        }
        radixSort(arr2);
        Instant now = Instant.now();
        System.out.println(Duration.between(pre, now));
    }

    public static void radixSort(int[] arr) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        int maxLength = (max + "").length();

        //定义一个二维数组，表示10个桶, 每个桶就是一个一维数组
        //说明
        //1. 二维数组包含10个一维数组
        //2. 为了防止在放入数的时候，数据溢出，则每个一维数组(桶)，大小定为arr.length
        //3. 基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];
        //为了记录每个桶中，实际存放了多少个数据,我们定义一个一维数组来记录各个桶的每次放入的数据个数
        //可以这里理解
        //比如：bucketElementCounts[0] , 记录的就是  bucket[0] 桶的放入数据个数
        int[] bucketElementCounts = new int[10];
//        System.out.println(Arrays.toString(bucketElementCounts));


        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的对应位的值
                int digitOfElement = arr[j] / n % 10;
                //放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
            int index = 0;
            //遍历每一桶，并将桶中是数据，放入到原数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中，有数据，我们才放入到原数组
                if (bucketElementCounts[k] != 0) {
                    //循环该桶即第k个桶(即第k个一维数组), 放入
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出元素放入到arr
                        arr[index++] = bucket[k][l];
                    }
                }
                //第i+1轮处理后，需要将每个 bucketElementCounts[k] = 0 ！！！！
                bucketElementCounts[k] = 0;
            }
        }


//        //first
//        for (int j = 0; j < arr.length; j++) {
//            int digitOfElement = arr[j] / 1 % 10;
//            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
//            bucketElementCounts[digitOfElement]++;
//        }
//        int index=0;
//        for (int k = 0; k < bucketElementCounts.length; k++) {
//            if(bucketElementCounts[k]>0){
//                for(int l = 0; l < bucketElementCounts[k]; l++) {
//                    //取出元素放入到arr
//                    arr[index++] = bucket[k][l];
//                }
//                bucketElementCounts[k]=0;
//            }
//        }
//
//        //second
//        for (int j = 0; j < arr.length; j++) {
//            int digitOfElement = arr[j] / 10 % 10;
//            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
//            bucketElementCounts[digitOfElement]++;
//        }
//         index=0;
//        for (int k = 0; k < bucketElementCounts.length; k++) {
//            if(bucketElementCounts[k]>0){
//                for(int l = 0; l < bucketElementCounts[k]; l++) {
//                    //取出元素放入到arr
//                    arr[index++] = bucket[k][l];
//                }
//                bucketElementCounts[k]=0;
//            }
//        }
//        //third
//        for (int j = 0; j < arr.length; j++) {
//            int digitOfElement = arr[j] / 100 % 10;
//            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
//            bucketElementCounts[digitOfElement]++;
//        }
//        index=0;
//        for (int k = 0; k < bucketElementCounts.length; k++) {
//            if(bucketElementCounts[k]>0){
//                for(int l = 0; l < bucketElementCounts[k]; l++) {
//                    //取出元素放入到arr
//                    arr[index++] = bucket[k][l];
//                }
//                bucketElementCounts[k]=0;
//            }
//        }
    }
}
