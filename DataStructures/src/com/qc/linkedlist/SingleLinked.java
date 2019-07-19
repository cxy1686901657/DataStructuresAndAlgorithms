package com.qc.linkedlist;

import java.util.Stack;

/**
 * @author qc
 * @date 2019/7/19
 */

public class SingleLinked {

    public static void main(String[] args){
        //进行测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        HeroNodeLinkedList heroNodeLinkedList=new HeroNodeLinkedList();

//        //加入
//        heroNodeLinkedList.add(hero1);
//        heroNodeLinkedList.add(hero3);
//        heroNodeLinkedList.add(hero2);
//        heroNodeLinkedList.add(hero4);
        //加入按照编号的顺序
        heroNodeLinkedList.addByOrder(hero1);
        heroNodeLinkedList.addByOrder(hero4);
        heroNodeLinkedList.addByOrder(hero2);
        heroNodeLinkedList.addByOrder(hero3);
        heroNodeLinkedList.list();
    };

    /**
        * @param head
        * @return int
        * @author qc
        * @description 获取到单链表的节点的个数(如果是带头结点的链表，需求不统计头节点)
        * @date 2019/7/19
       */
    public  static  int getLength(HeroNode head){
        if(head.next == null) { //空链表
            return 0;
        }
        int length = 0;
        //定义一个辅助的变量, 这里我们没有统计头节点
        HeroNode cur = head.next;
        while(cur != null) {
            length++;
            cur = cur.next; //遍历
        }
        return length;
    }
     /**
       * @param head
     	 * @param index
       * @return com.qc.linkedlist.HeroNode
       * @author qc
       * @description 找单链表中的倒数第k个结点
       * @date 2019/7/19
      */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        //判断如果链表为空，返回null
        if(head.next == null) {
            return null;//没有找到
        }
        //第一个遍历得到链表的长度(节点个数)
        int size = getLength(head);
        //第二次遍历  size-index 位置，就是我们倒数的第K个节点
        //先做一个index的校验
        if(index <=0 || index > size) {
            return null;
        }
        //定义给辅助变量， for 循环定位到倒数的index
        HeroNode cur = head.next; //3 // 3 - 1 = 2
        for(int i =0; i< size - index; i++) {
            cur = cur.next;
        }
        return cur;

    }


    /**
      * @param head
      * @return void
      * @author qc
      * @description 将单链表反转
      * @date 2019/7/19
     */
    public static void reversetList(HeroNode head) {
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if(head.next == null || head.next.next == null) {
            return ;
        }

        //定义一个辅助的指针(变量)，帮助我们遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null;// 指向当前节点[cur]的下一个节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead 的最前端
        while(cur != null) {
            next = cur.next;//先暂时保存当前节点的下一个节点，因为后面需要使用
            cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur; //将cur 连接到新的链表上
            cur = next;//让cur后移
        }
        //将head.next 指向 reverseHead.next , 实现单链表的反转
        head.next = reverseHead.next;
    }

    /**
      * @param head
      * @return void
      * @author qc
      * @description
      * @date 2019/7/19
     */
    public static void reversePrint(HeroNode head){
        if(head.next == null) {
            return;
        }
        Stack<HeroNode> stack=new Stack<>();

        HeroNode cur=head.next;

        while (cur!=null){
            stack.push(cur);
            cur=cur.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop()); //stack的特点是先进后出
        }
    }

}
class HeroNodeLinkedList{
    //先初始化一个头节点, 头节点不要动, 不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");
    public void add(HeroNode heroNode) {

        //因为head节点不能动，因此我们需要一个辅助遍历 temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while(true) {
            //找到链表的最后
            if(temp.next == null) {//
                break;
            }
            //如果没有找到最后, 将将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        //将最后这个节点的next 指向 新的节点
        temp.next = heroNode;
    }
    //显示链表[遍历]
    public void list() {
        //判断链表是否为空
        if(head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点，不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while(true) {
            //判断是否到链表最后
            if(temp == null) {
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            //将temp后移， 一定小心
            temp = temp.next;
        }
    }

    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
    //(如果有这个排名，则添加失败，并给出提示)
    public void addByOrder(HeroNode heroNode) {
        //因为头节点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
        //因为单链表，因为我们找的temp 是位于 添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false; // flag标志添加的编号是否存在，默认为false
        while(true) {
            if(temp.next == null) {//说明temp已经在链表的最后
                break; //
            }
            if(temp.next.no > heroNode.no) { //位置找到，就在temp的后面插入
                break;
            } else if (temp.next.no == heroNode.no) {//说明希望添加的heroNode的编号已然存在

                flag = true; //说明编号存在
                break;
            }
            temp = temp.next; //后移，遍历当前链表
        }
        //判断flag 的值
        if(flag) { //不能添加，说明编号存在
            System.out.printf("准备插入的英雄的编号 %d 已经存在了, 不能加入\n", heroNode.no);
        } else {
            //插入到链表中, temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    public void update(HeroNode newNode){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        boolean isFind = false; //表示是否找到该节点
        while(true){
            if(temp == null){
                break;
            }
            if(temp.no == newNode.no){
                isFind=true;
                break;
            }
            temp=temp.next;
        }

        if(isFind){
            temp.name = newNode.name;
            temp.nickname = newNode.nickname;
        }else {
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n", newNode.no);
        }

    }

    public void delete(int no){
        HeroNode temp = head;

        if(head.next == null){
            System.out.println("链表为空");
            return;
        }

        boolean isFind = false;
        while (true) {
            if(temp.next == null) {
                break;
            }
            if(temp.next.no == no){
                isFind=true;
                break;
            }
            temp = temp.next;
        }

        //判断flag
        if(isFind) { //找到
            //可以删除
            temp.next = temp.next.next;
        }else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }

}


//定义HeroNode ， 每个HeroNode 对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next; //指向下一个节点
    //构造器
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }
    //为了显示方法，我们重新toString
    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }

}
