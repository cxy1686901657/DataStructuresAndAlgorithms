package com.qc.sort;

/**
 * @author qc
 * @date 2019/7/23
 */

public class PrintArrUtil {
    private PrintArrUtil(){}
    public static void printArr(int[] arr){
        for (int a:arr){
            System.out.printf("%d\t",a);
        }
        System.out.println();
    }
}
