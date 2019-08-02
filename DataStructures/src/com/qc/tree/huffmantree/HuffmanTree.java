package com.qc.tree.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author qc
 * @date 2019/8/1
 */

public class HuffmanTree {
    public static void main(String[] args) {
//        ArrayList<Node> nodes = new ArrayList<>();
//        nodes.add(new Node(1));
//        nodes.add(new Node(5));
//        nodes.add(new Node(3));
//        Collections.sort(nodes);
//        System.out.println(nodes);
        int arr[] = {13, 7, 8, 3, 29, 6, 1};
        Node huffmanTree = createHuffmanTree(arr);
        System.out.println(huffmanTree);
        preOrder(huffmanTree);
    }

    public static void preOrder(Node root){
        if(root!=null){
            root.preOrder();
        }

    }

    public static Node createHuffmanTree(int[] arr) {
        List<Node> nodes = new ArrayList<Node>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        while (nodes.size() > 1) {
            Collections.sort(nodes);

            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            Node parentNode = new Node(leftNode.value + rightNode.value);
            parentNode.left=leftNode;
            parentNode.right=rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            nodes.add(parentNode);

        }
        return nodes.get(0);
    }
}

class Node implements Comparable<Node> {
    int value;
    Node left;
    Node right;

    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public int getValue() {

        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}
