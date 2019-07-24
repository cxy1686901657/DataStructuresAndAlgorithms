package com.qc.sort;

import java.time.Duration;
import java.time.Instant;

/**
 * @author qc
 * @date 2019/7/23
 */

public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = {-9,78,0,23,-567,70, -1,900, 4561};
        int[] arr = {-9, 78, 0, 23, -567, 70};
        quickSort(arr, 0, arr.length - 1);
        PrintArrUtil.printArr(arr);
        Instant pre = Instant.now();
        int[] arr2 = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr2[i] = (int) (Math.random() * 8000000); //生成一个[0, 8000000) 数
        }
        quickSort(arr2, 0, arr2.length - 1);
        Instant now = Instant.now();
        System.out.println(Duration.between(pre, now));
    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left; //左下标
        int r = right; //右下标
        //pivot 中轴值
        int pivot = arr[(left + right) / 2];
        int temp = 0; //临时变量，作为交换时使用
        while (l < r) {
            //在pivot的左边一直找,找到大于等于pivot值,才退出
            while (arr[l] < pivot) {
                l += 1;
            }
            //在pivot的右边一直找,找到小于等于pivot值,才退出
            while (arr[r] > pivot) {
                r -= 1;
            }
            //如果l >= r说明pivot 的左右两的值，已经按照左边全部是
            //小于等于pivot值，右边全部是大于等于pivot值
            if (l >= r) {
                break;
            }

            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完后，发现这个arr[l] == pivot值 相等 r--， 前移
            if (arr[l] == pivot) {
                r -= 1;
            }
            //如果交换完后，发现这个arr[r] == pivot值 相等 l++， 后移
            if (arr[r] == pivot) {
                l += 1;
            }
        }
        if (l == r) {
            l += 1;
            r -= 1;
        }
        //向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        //向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }

    }

    public static void quickSort2(int[] arr, int left, int right) {
        if (left > right) {
            return;
        }
        int l = left;
        int r = right;
        int base = arr[left];
        while (l != r) {
            while (arr[r] >= base && l < r) {
                r--;
            }
            while (arr[l] <= base && l < r) {
                l++;
            }
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
        }
        arr[left] = arr[l];
        arr[l] = base;
        quickSort2(arr, left, l - 1);
        quickSort2(arr, l + 1, right);

    }


}
