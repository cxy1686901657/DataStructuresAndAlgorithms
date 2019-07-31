package com.qc.tree;

/**
 * @author qc
 * @date 2019/7/30
 */

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        Node a = new Node(1, "a");
        Node b = new Node(2, "b");
        Node c = new Node(3, "c");
        Node d = new Node(4, "d");
        Node e = new Node(5, "e");
        binaryTree.setRoot(a);
        a.setLeft(b);
        a.setRight(c);
        c.setRight(d);
        c.setLeft(e);
//        binaryTree.postOrder();
        binaryTree.postOrderSearch(5);
    }
}

class BinaryTree {
    private Node root;


    public void setRoot(Node root) {
        this.root = root;
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
        if(root != null) {
            //如果只有一个root结点, 这里立即判断root是不是就是要删除结点
            if(root.getId() == no) {
                root = null;
            } else {
                //递归删除
                root.delNode(no);
            }
        }else{
            System.out.println("空树，不能删除~");
        }
    }

}

class Node {
    private int id;
    private String name;
    private Node left;
    private Node right;

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
        if(this.left != null && this.left.getId() == no) {
            this.left = null;
            return;
        }
        //3.如果当前结点的右子结点不为空，并且右子结点 就是要删除结点，就将this.right= null ;并且就返回(结束递归删除)
        if(this.right != null && this.right.getId() == no) {
            this.right = null;
            return;
        }
         //4.我们就需要向左子树进行递归删除
        if(this.left != null) {
            this.left.delNode(no);
        }
        //5.则应当向右子树进行递归删除
        if(this.right != null) {
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