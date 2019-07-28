package com.qc.sort;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import java.util.logging.Level;

/**
 * @author qc
 * @date 2019/7/23
 */

public class ReviewSort {

    @Test
    public void bubbleSort(int[] arr) {
        int temp = 0;
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
    }

    @Test
    public void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int minValue = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < minValue) {
                    minValue = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = minValue;
            }
        }
    }

    @Test
    public void insertSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int insertIndex = i;
            int insertValue = arr[i + 1];
            boolean flag = false;
            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                flag = true;
                insertIndex--;
            }
            if (flag) {
                arr[insertIndex + 1] = insertValue;
            }
        }
    }

    @Test
    public void shellSort_swap(int[] arr) {
        int temp;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {

                for (int j = i - gap; j >= 0; j = j - gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }

    @Test
    public void shellSort_gression(int[] arr) {
        int temp;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int insertIndex = i;
                int insertValue = arr[insertIndex];
                if (arr[insertIndex] < arr[insertIndex - gap]) {
                    while (insertIndex >= 0 && insertValue < arr[insertIndex - gap]) {
                        arr[insertIndex] = arr[insertIndex - gap];
                        insertIndex--;
                    }
                    arr[insertIndex] = insertValue;
                }
            }
        }
    }

    @Test
    void test() {
//        int[] arr = {-9, 78, 0, 23, -567, 70,56,12,-12,-56,75,998,123,654,-29};
        int[] arr = {-9, 78, 23, -567, 0, 3, 9, -9, 23};
        quickSort_self(arr, 0, arr.length - 1);
        PrintArrUtil.printArr(arr);
    }

    @Test
    public void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int pivot = arr[(left + right) / 2];
        int temp = 0;
        while (l < r) {
//            -9, 78, 0, 23, -567,0,3,9,-9
            while (arr[l] < pivot) {
                l++;
            }
            while (arr[r] > pivot) {
                r--;
            }
            if (l >= r) {
                break;
            }
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            if (arr[l] == pivot) {
                r -= 1;
                System.out.println("arr[l] == pivot");
            }
            //如果交换完后，发现这个arr[r] == pivot值 相等 l++， 后移
            if (arr[r] == pivot) {
                l += 1;
                System.out.println("arr[r] == pivot");
            }
        }
        if (l == r) {
            l += 1;
            r -= 1;
        }
        if (left < r) {
            quickSort(arr, left, r);
        }
        //向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }
    }

    @Test
    public void quickSort_self(int[] arr, int left, int right) {
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
        quickSort_self(arr, left, l - 1);
        quickSort_self(arr, l + 1, right);
    }

    @Test
    public void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    @Test
    public void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left; // 初始化i, 左边有序序列的初始索引
        int j = mid + 1; //初始化j, 右边有序序列的初始索引
        int t = 0; // 指向temp数组的当前索引
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else { //反之,将右边有序序列的当前元素，填充到temp数组
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }
        while (i <= mid) { //左边的有序序列还有剩余的元素，就全部填充到temp
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }

        while (j <= right) { //右边的有序序列还有剩余的元素，就全部填充到temp
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }
        t = 0;
        int templeft = left;
        while (templeft <= right) {
            arr[templeft] = temp[t];
            t++;
        }

    }


    @Test
    public void test22() {
        int[] arr = {1, 213, 412, 123, 5};
        radixSort(arr);
        PrintArrUtil.printArr(arr);
    }

    @Test
    public void radixSort(int[] arr) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        int maxLength = (max + "").length();

        int[][] bucket = new int[10][arr.length];

        int[] bucketCount = new int[10];

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {

            for (int j = 0; j < arr.length; j++) {
                int mour = arr[j] / n % 10;
                bucket[mour][bucketCount[mour]] = arr[j];
                bucketCount[mour]++;
            }

            int index = 0;

            for (int k = 0; k < bucketCount.length; k++) {
                if (bucketCount[k] > 0) {
                    for (int l = 0; l < bucketCount[k]; l++) {
                        arr[index++]=bucket[k][l];
                    }
                }
                bucketCount[k]=0;
            }
        }


    }

}
