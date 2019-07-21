package com.qc.linkedlist;


/**
 * @author qc
 * @date 2019/7/20
 */

public class Josepfu {
    public static void main(String[] args) {
        // 测试一把看看构建环形链表，和遍历是否ok
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(6);// 加入5个小孩节点
        circleSingleLinkedList.showBoy();
        circleSingleLinkedList.countBoy(1, 2);
    }
}

class CircleSingleLinkedList {
    // 创建一个first节点,当前没有编号
    private Boy first = null;
    private int nums = 0;

    /**
     * @param nums
     * @return void
     * @author qc
     * @description 添加小孩节点，构建成一个环形的链表
     * @date 2019/7/20
     */
    public void addBoy(int nums) {
        // nums 做一个数据校验
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        this.nums = nums;
        Boy curBoy = null; // 辅助指针，帮助构建环形链表
        // 使用for来创建我们的环形链表
        for (int i = 1; i <= nums; i++) {
            // 根据编号，创建小孩节点
            Boy boy = new Boy(i);
            // 如果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first); // 构成环
                curBoy = first; // 让curBoy指向第一个小孩
            } else {
                curBoy.setNext(boy);//
                boy.setNext(first);//
                curBoy = boy;
            }
        }
    }

    /**
     * @param
     * @return void
     * @author qc
     * @description 遍历当前的环形链表
     * @date 2019/7/20
     */
    public void showBoy() {
        // 判断链表是否为空
        if (first == null) {
            System.out.println("没有任何小孩~~");
            return;
        }
        // 因为first不能动，因此我们仍然使用一个辅助指针完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号 %d \n", curBoy.getNo());
            if (curBoy.getNext() == first) {// 说明已经遍历完毕
                break;
            }
            curBoy = curBoy.getNext(); // curBoy后移
        }
    }

    /**
     * @param startNo  表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @return void
     * @author qc
     * @description 根据用户的输入，计算出小孩出圈的顺序
     * @date 2019/7/21
     */
    public void countBoy(int startNo, int countNum) {
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误， 请重新输入");
            return;
        }
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        while (true) {
            if (first == helper) {
                break;
            }
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("小孩%d出圈", first.getNo());

            first = first.getNext();
            helper.setNext(first);
        }
        //此时 helper==first
        System.out.printf("小孩%d出圈", helper.getNo());

    }


}


// 创建一个Boy类，表示一个节点
class Boy {
    private int no;// 编号
    private Boy next; // 指向下一个节点,默认null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

}
