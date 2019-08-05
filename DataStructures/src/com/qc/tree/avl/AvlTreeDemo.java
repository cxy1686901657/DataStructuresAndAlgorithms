package com.qc.tree.avl;

/**
 * @author qc
 * @date 2019/8/5
 * @description
 * @project DataStructuresAndAlgorithms
 */

public class AvlTreeDemo {
    public static void main(String[] args){
//        int[] arr = {4,3,6,5,7,8};
        int[] arr = { 10, 11, 7, 6, 8, 9 };
        AvlTree avlTree = new AvlTree();
        for(int i=0;i<arr.length;i++){
            avlTree.add(new Node(arr[i]));
        }

        avlTree.preOrder();
        System.out.println(avlTree.root.height());
        System.out.println(avlTree.root.left.height());
        System.out.println(avlTree.root.right.height());

    }

}
class AvlTree{
    public Node root;

    public void add(Node node) {
        if (root != null) {
            root.add(node);
        } else {
            root = node;
        }
    }

    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("empty binary sort (search) tree");
        }
    }

    public void preOrder() {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("empty binary sort (search) tree");
        }
    }

    //查找子树
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找子树的父结点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }
    //编写方法:
    //1. 返回的 以node 为根结点的二叉排序树的最小结点的值
    //2. 删除node 为根结点的二叉排序树的最小结点

    /**
     * @param node 传入的结点(当做二叉排序树的根结点)
     * @return 返回的 以node 为根结点的二叉排序树的最小结点的值
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        //循环的查找左子节点，就会找到最小值
        while (target.left != null) {
            target = target.left;
        }
        //这时 target就指向了最小结点
        //删除最小结点
        delNode(target.value);
        return target.value;
    }

    private int delLeftTreeMax(Node left) {
        Node target = left;
        while (target.right != null) {
            target = target.right;
        }
        delNode(target.value);
        return target.value;
    }

    //删除子树
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            Node targetNode = search(value);
            if (targetNode == null) {
                return;
            }
            //如果我们发现当前这颗二叉排序树只有一个结点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            Node parent = searchParent(value);

            if (targetNode.left == null && targetNode.right == null) {
                //如果为叶子节点
                if (parent.left != null && parent.left.value == value) {
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {
                //如果删除结点有两颗子树
//                int minVal = delRightTreeMin(targetNode.right);
//                targetNode.value = minVal;

                int max = delLeftTreeMax(targetNode.left);
                targetNode.value = max;

            } else {
                //如果有一颗子树
                if (targetNode.left != null) {
                    if (parent != null) {
                        //如果 targetNode 是 parent 的左子结点
                        if (parent.left != null) {
                            parent.left = targetNode.left;
                        } else { //  targetNode 是 parent 的右子结点
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {
                    if (parent != null) {
                        //如果 targetNode 是 parent 的左子结点
                        if (parent.left != null) {
                            parent.left = targetNode.right;
                        } else { //如果 targetNode 是 parent 的右子结点
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }


        }


    }

}

class Node {
    int value;
    Node left;
    Node right;
    public void leftRotate() {
        //创建新的结点，以当前根结点的值
        Node newNode = new Node(value);
        newNode.left=left;

        newNode.right=right.left;

        value=right.value;

        //把当前结点的右子树设置成当前结点右子树的右子树
        right = right.right;
        //把当前结点的左子树(左子结点)设置成新的结点
        left = newNode;

    }

    public void rightRotate(){
        Node node = new Node(value);

        node.right=right;

        node.left=left.right;

        value=left.value;

        left=left.left;
        right=node;
    }




    public Node(int value) {
        this.value = value;
    }
    public int height(){
        return Math.max(left==null?0:left.height(),right==null?0:right.height())+1;
    }
    public int leftHeight(){
        if (left != null) {
            return left.height();
        }else {
            return 0;
        }
    }
    public int rightHeight(){
        if (right == null) {
            return 0;
        }
        return right.height();
    }


    //查找待删除元素
    public Node search(int value) {
        if (this.value == value) {
            return this;
        } else if (value < this.value) {
            if (this.left == null) {
                return null;
            } else {
                return this.left.search(value);
            }
        } else {
            if (this.right == null) {
                return null;
            } else {
                return this.right.search(value);
            }
        }
    }

    //查找待删除元素的父节点
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)) {
            return this;
        } else {
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value); //向左子树递归查找
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value); //向右子树递归查找
            } else {
                return null; // 没有找到父结点
            }
        }
    }

    /**
     * @param node 待添加结点
     * @return
     * @author qc
     * @description 添加结点
     * @date 2019/8/2
     */
    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }

        if(rightHeight() - leftHeight() > 1){
            if(right!=null&&right.leftHeight() > right.rightHeight())
            {
                right.rightRotate();
                leftRotate(); //左旋转..
            }else{
                leftRotate();
            }
            return;
        }
        if(leftHeight()-rightHeight()>1){
            if(left!=null&& left.rightHeight()>left.leftHeight()){
                left.leftRotate();
                rightRotate();
            }else {
                rightRotate();
            }
        }

    }

    /**
     * @param
     * @return
     * @author qc
     * @description
     * @date 2019/8/2
     */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {

            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }

    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}