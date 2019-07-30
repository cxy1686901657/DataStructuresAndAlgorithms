package com.qc.hashtab;

import java.util.Scanner;

/**
 * @author qc
 * @date 2019/7/30
 */

public class HashTabDemo {
    public static void main(String[] args){
        HashTab hashTab = new HashTab(7);
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("add:  添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建 雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }


    }
}

class HashTab {
    private EmpLinkedList[] empLinkedLists;
    private int size;

    public HashTab(int size) {
        this.size = size;
        empLinkedLists = new EmpLinkedList[size];
        for (int i=0;i<size;i++){
            empLinkedLists[i]=new EmpLinkedList();
        }
    }

    public void add(Emp emp) {
        int i = hashFun(emp.getId());
        empLinkedLists[i].add(emp);
    }
    public void list() {
        for(int i = 0; i < size; i++) {
            empLinkedLists[i].list();
        }
    }
    private int hashFun(int no) {
        return no % size;
    }

    public void findEmpById(int id) {
        int i = hashFun(id);
        Emp empById = empLinkedLists[i].findEmpById(id);
        if(empById != null) {//找到
            System.out.println("在第%d条链表中找到 雇员 id = %d\n");
        }else{
            System.out.println("在哈希表中，没有找到该雇员~");
        }
    }
}

class EmpLinkedList {
    private Emp head;

    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        Emp current = head;
        while (true) {
            if (current.next == null) {
                break;
            }
            current = current.next;
        }
        current.next = emp;
    }

    public void list() {
        if (head == null) { //说明链表为空
            System.out.println("链表为空");
            return;
        }
        Emp curEmp = head; //辅助指针
        while (true) {
            System.out.printf(" => id=%d name=%s\t", curEmp.getId(), curEmp.getName());
            if (curEmp.next == null) {//说明curEmp已经是最后结点
                break;
            }
            curEmp = curEmp.next; //后移，遍历
        }
        System.out.println();
    }

    public Emp findEmpById(int no){
        if(head == null) {
            System.out.println("链表为空");
            return null;
        }
        Emp curEmp = head;
        while (true){
            if(curEmp.getId()==no){
                break;
            }
            if(curEmp.next == null) {//说明遍历当前链表没有找到该雇员
                curEmp = null;
                break;
            }
            curEmp=curEmp.next;
        }
        return curEmp;
    }
}

class Emp {
    private Integer id;
    private String name;
    public Emp next;

    public Emp(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

