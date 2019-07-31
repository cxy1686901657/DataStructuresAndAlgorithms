package com.qc.tree.threadedbinarytree;

/**
 * @author qc
 * @date 2019/7/30
 */

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //测试一把中序线索二叉树的功能
        Node root = new Node(1, "tom");
        Node node2 = new Node(3, "jack");
        Node node3 = new Node(6, "smith");
        Node node4 = new Node(8, "mary");
        Node node5 = new Node(10, "king");
        Node node6 = new Node(14, "dim");

        //二叉树，后面我们要递归创建, 现在简单处理使用手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //测试中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        Node leftNode = node5.getLeft();
        Node rightNode = node5.getRight();
        threadedBinaryTree.infixOrder();
        System.out.println("10号结点的前驱结点是 ="  + leftNode); //3
        System.out.println("10号结点的后继结点是="  + rightNode); //1
        threadedBinaryTree.threadedNodes();
        //测试: 以10号节点测试
         leftNode = node5.getLeft();
         rightNode = node5.getRight();
        System.out.println("10号结点的前驱结点是 ="  + leftNode); //3
        System.out.println("10号结点的后继结点是="  + rightNode); //1
        threadedBinaryTree.threadedList4Infix();
    }
}

class ThreadedBinaryTree {
    private Node root;

    public void setRoot(Node root) {
        this.root = root;
    }

    private Node pre = null;


    public void threadedList4Infix(){
        Node node=root;

        while(node!=null){

            while (node.getLeftType()==0){
                node=node.getLeft();
            }
            System.out.println(node);

            while (node.getRightType()==1){
                node=node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }



    public void threadedNodes() {
        this.threadedNodes(root);
    }

    public void threadedNodes(Node node) {
        if (node == null) {
            return;
        }

        threadedNodes(node.getLeft());
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            //让前驱结点的右指针指向当前结点
            pre.setRight(node);
            //修改前驱结点的右指针类型
            pre.setRightType(1);
        }
        pre=node;

        threadedNodes(node.getRight());
    }

    public void preOrder() {
        root.preOrder();
    }

    public void infixOrder() {
        root.infixOrder();
    }

    public void postOrder() {
        root.postOrder();
    }

    public void preOrderSearch(int no) {
        if (root != null) {
            Node node = root.preOrderSearch(no);
            System.out.println(node);
        } else {
            System.out.println("tree is empty");
        }
    }

    public void infixOrderSearch(int no) {
        if (root != null) {
            Node node = root.infixOrderSearch(no);
            System.out.println(node);
        } else {
            System.out.println("tree is empty");
        }
    }

    public void postOrderSearch(int no) {
        if (root != null) {
            Node node = root.postOrderSearch(no);
            System.out.println(node);
        } else {
            System.out.println("tree is empty");
        }
    }

    public void delNode(int no) {
        if (root != null) {
            //如果只有一个root结点, 这里立即判断root是不是就是要删除结点
            if (root.getId() == no) {
                root = null;
            } else {
                //递归删除
                root.delNode(no);
            }
        } else {
            System.out.println("空树，不能删除~");
        }
    }

}


class Node {
    private int id;
    private String name;
    private Node left;
    private Node right;

    private int leftType; //0 子树 1前后区
    private int rightType;

    public int getLeftType(){
        return this.leftType;
    }
    public int getRightType(){
        return this.rightType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public void preOrder() {
        System.out.println(this);
        if (left != null) {
            this.left.preOrder();
        }
        if (right != null) {
            this.right.preOrder();
        }
    }

    public void infixOrder() {
        if (left != null) {
            this.left.preOrder();
        }
        System.out.println(this);
        if (right != null) {
            this.right.preOrder();
        }
    }

    public void postOrder() {
        if (left != null) {
            this.left.preOrder();
        }
        if (right != null) {
            this.right.preOrder();
        }
        System.out.println(this);
    }

    public Node preOrderSearch(int no) {
        System.out.println("进入前序遍历");
        if (this.getId() == no) {
            return this;
        }
        Node node = null;
        if (this.left != null) {
            node = this.left.preOrderSearch(no);
        }
        if (node != null) {
            return node;
        }
        if (this.right != null) {
            node = this.right.preOrderSearch(no);
        }
        return node;
    }

    public Node infixOrderSearch(int no) {


        Node node = null;
        if (this.left != null) {
            node = this.left.infixOrderSearch(no);
        }
        if (node != null) {
            return node;
        }
        System.out.println("进入中序遍历");
        if (this.getId() == no) {
            return this;
        }
        if (this.right != null) {
            node = this.right.infixOrderSearch(no);
        }
        return node;
    }

    public Node postOrderSearch(int no) {

        Node node = null;
        if (this.left != null) {
            node = this.left.postOrderSearch(no);
        }
        if (node != null) {
            return node;
        }
        if (this.right != null) {
            node = this.right.postOrderSearch(no);
        }
        if (node != null) {
            return node;
        }
        System.out.println("进入后序遍历");
        if (this.getId() == no) {
            return this;
        }
        return node;
    }

    public void delNode(int no) {
        //思路
		/*
		 * 	1. 因为我们的二叉树是单向的，所以我们是判断当前结点的子结点是否需要删除结点，而不能去判断当前这个结点是不是需要删除结点.
			2. 如果当前结点的左子结点不为空，并且左子结点 就是要删除结点，就将this.left = null; 并且就返回(结束递归删除)
			3. 如果当前结点的右子结点不为空，并且右子结点 就是要删除结点，就将this.right= null ;并且就返回(结束递归删除)
			4. 如果第2和第3步没有删除结点，那么我们就需要向左子树进行递归删除
			5.  如果第4步也没有删除结点，则应当向右子树进行递归删除.
		 */
        //2. 如果当前结点的左子结点不为空，并且左子结点 就是要删除结点，就将this.left = null; 并且就返回(结束递归删除)
        if (this.left != null && this.left.getId() == no) {
            this.left = null;
            return;
        }
        //3.如果当前结点的右子结点不为空，并且右子结点 就是要删除结点，就将this.right= null ;并且就返回(结束递归删除)
        if (this.right != null && this.right.getId() == no) {
            this.right = null;
            return;
        }
        //4.我们就需要向左子树进行递归删除
        if (this.left != null) {
            this.left.delNode(no);
        }
        //5.则应当向右子树进行递归删除
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
