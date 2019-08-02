package com.qc.tree.binarysorttree;

/**
 * @author qc
 * @date 2019/8/1
 */

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {12, 41, 1, 43, 6, 7};

//        int [] arr2=new int[80000];
//        for(int i=0;i<arr2.length;i++){
//            arr2[i]=new Random().nextInt(8);
//        }
//        Instant now = Instant.now();
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
//        Instant now1 = Instant.now();
//        System.out.println(Duration.between(now, now1));


//        binarySortTree.infixOrder();
        System.out.println("---------------------------------");
//         binarySortTree = new BinarySortTree();
//        for (int i = 0; i < arr.length; i++) {
//            binarySortTree.add(new Node(arr[i]));
//        }
//        binarySortTree.preOrder();;
        binarySortTree.delNode(6);
        binarySortTree.infixOrder();
    }
}

class BinarySortTree {
    private Node root;

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

    public Node(int value) {
        this.value = value;
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