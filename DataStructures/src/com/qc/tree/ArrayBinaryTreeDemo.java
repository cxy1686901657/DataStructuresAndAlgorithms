package com.qc.tree;

/**
 * @author qc
 * @date 2019/7/30
 */

public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.preOrder(0);
    }
}

class ArrayBinaryTree {
    private int[] arr;//存储数据结点的数组

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }
    //编写一个方法，完成顺序存储二叉树的前序遍历
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        System.out.print(arr[index]+"\t");
        if((index * 2 + 1) < arr.length) {
            preOrder(2 * index + 1 );
        }
        if((index * 2 + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }
}