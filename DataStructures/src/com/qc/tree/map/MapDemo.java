package com.qc.tree.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author qc
 * @date 2019/8/6
 * @description
 * @project DataStructuresAndAlgorithms
 */

public class MapDemo {
    public static void main(String[] args){
        Map map = new Map(8);
//        String Vertexs[] = {"A", "B", "C", "D", "E"};
        String Vertexs[] = {"1", "2", "3", "4", "5", "6", "7", "8"};
        for(String vertex: Vertexs) {
            map.insertVertex(vertex);
        }
        //添加边
        //A-B A-C B-C B-D B-E
//        map.insertEdge(0, 1, 1); // A-B
//        map.insertEdge(0, 2, 1); //
//        map.insertEdge(1, 2, 1); //
//        map.insertEdge(1, 3, 1); //
//        map.insertEdge(1, 4, 1); //
        map.insertEdge(0, 1, 1);
        map.insertEdge(0, 2, 1);
        map.insertEdge(1, 3, 1);
        map.insertEdge(1, 4, 1);
        map.insertEdge(3, 7, 1);
        map.insertEdge(4, 7, 1);
        map.insertEdge(2, 5, 1);
        map.insertEdge(2, 6, 1);
        map.insertEdge(5, 6, 1);

        map.showGraph();

//        map.deepList();
        map.deepList();
    }

}

class Map {
    private ArrayList<String> vertexList; //存储顶点集合
    private int[][] edges; //存储图对应的邻结矩阵
    private int numOfEdges; //表示边的数目
    //定义给数组boolean[], 记录某个结点是否被访问
    private boolean[] isVisited;

    public Map(int n) {
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;
        isVisited=new boolean[n];
    }

    //图中常用的方法
    //返回结点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //显示图对应的矩阵
    public void showGraph() {
        System.err.println("  "+vertexList);
        for (int i=0;i<edges[0].length;i++){
            System.err.print(vertexList.get(i)+" ");
            System.err.println(Arrays.toString(edges[i]));
        }
    }

    //插入结点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }
    //添加边

    /**
     * @param v1     表示点的下标即使第几个顶点  "A"-"B" "A"->0 "B"->1
     * @param v2     第二个顶点对应的下标
     * @param weight 表示
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
    //得到第一个邻接结点的下标 w
    /**
     *
     * @param index
     * @return 如果存在就返回对应的下标，否则返回-1
     */
    public int getFirstNeighbor(int index){
        for(int j = 0; j<vertexList.size();j++){
            if(edges[index][j]>0){
                return j;
            }
        }
        return -1;
    }

    //根据前一个邻接结点的下标来获取下一个邻接结点
    public int getNextNeighbor(int v1, int v2) {
        for(int j = v2 + 1; j < vertexList.size(); j++) {
            if(edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }
    //深度优先遍历算法
    //i 第一次就是 0
    private void dfs(boolean[] isVisited, int i) {
        //首先我们访问该结点,输出
        System.out.print(getValueByIndex(i) + "->");
        //将结点设置为已经访问
        isVisited[i] = true;
        //查找结点i的第一个邻接结点w
        int w = getFirstNeighbor(i);
        while(w != -1) {//说明有
            if(!isVisited[w]) {
                dfs(isVisited, w);
            }
            //如果w结点已经被访问过
            w = getNextNeighbor(i, w);
        }

    }

    //对dfs 进行一个重载, 遍历我们所有的结点，并进行 dfs
    public void dfs() {
        isVisited = new boolean[vertexList.size()];
        //遍历所有的结点，进行dfs[回溯]
        for(int i = 0; i < vertexList.size(); i++) {
            if(!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }
    /**
      * @author qc
      * @description 深度优先遍历
      * @date 2019/8/7
     */
    public void deepList(){
        isVisited=new boolean[vertexList.size()];
        for(int i=0;i<vertexList.size();i++){
            if(!isVisited[i]){
                deepList(isVisited,i);
            }
        }
    }
    /**
      * @param isVisited
    	 * @param i
      * @return void
      * @author qc
      * @description
      * @date 2019/8/7
     */
    private void deepList(boolean[] isVisited, int i) {
        System.err.print(vertexList.get(i)+"-->");
        isVisited[i]=true;

        int j=getFirstNeighbor(i);

        while(j!=-1){
            if(!isVisited[j]){
                deepList(isVisited,j );
            }
            j=getNextNeighbor(i,j );
        }
    }


    private void wideList(boolean[] isVisited,int i){
        int u;
        int w;
        LinkedList queue = new LinkedList();
        //访问结点，输出结点信息
        System.err.print(getValueByIndex(i) + "=>");
        isVisited[i] = true;
        //将结点加入队列
        queue.addLast(i);

        while (!queue.isEmpty()){
            u= (int) queue.removeFirst();
            w=getFirstNeighbor(u);

            while (w!=-1){
                if(!isVisited[w]){
                    System.err.print(getValueByIndex(w) + "=>");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                w = getNextNeighbor(u, w);
            }
        }

    }
    public void wideList(){
        isVisited=new boolean[vertexList.size()];
        for (int i=0;i<vertexList.size();i++){
            if(!isVisited[i]){
                wideList(isVisited, i);
            }
        }
    }

    //返回结点i(下标)对应的数据 0->"A" 1->"B" 2->"C"
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }
}


