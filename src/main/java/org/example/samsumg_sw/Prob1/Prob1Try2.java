package org.example.samsumg_sw.Prob1;

import java.io.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class Prob1Try2 {

    static ArrayList<MyEdge>[] adjList;
    static Stack<Integer> pathStack;
    static Stack<Integer> weightStack;

    static MyEdge[] pathArr;
    static int[] visited;
    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stringTokenizer;
        int tcCnt = Integer.parseInt(bufferedReader.readLine()); // test case 수

        for (int cnt = 1; cnt <= tcCnt; cnt++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            // N: 노드 개수, M: 에지 개수,
            int N = Integer.parseInt(stringTokenizer.nextToken());
            int M = Integer.parseInt(stringTokenizer.nextToken());

            // 인접리스트
            adjList = new ArrayList[N + 1];
            for (int i = 1; i < N + 1; i++) {
                adjList[i] = new ArrayList<>();
            }

            // 인접 리스트 초기화
            for (int i = 0; i < M; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                // s: 출발노드, e: 도착노드, w: 가중치
                int s = Integer.parseInt(stringTokenizer.nextToken());
                int e = Integer.parseInt(stringTokenizer.nextToken());
                int w = Integer.parseInt(stringTokenizer.nextToken());
                adjList[s].add(new MyEdge(e, w));
            }
            // 방문 배열 0 이면 방문하지 않은 곳, 1이면 방문한곳, 2 이면 방문했던 곳인데 부모인 곳으로 돌아옴.
            visited = new int[N + 1];

            pathArr = new MyEdge[M + 1];

            result = 0;


            // TODO: 2023/07/09 union-find 를 통해 그래프의 연결요소가 몇개인지, 대표 노드가 무엇인지 알아내기
            //  그리고 그 대표노드만 dfs 하면 됨..

//            for (int i = 1; i <= N; i++) {
//                if (visited[i] == 0) {
//                    dfsMethod(new MyEdge(i, -1));
//                }
//            }
            int[] tempVisited = new int[N + 1];

            dfsMethod(new MyEdge(1, -1));

        }
    }

    private static void dfsMethod(MyEdge edge) {
        // 단순 이미 방문시 리턴
        if (visited[edge.node] == 1) {
            return;
        }
        // 이미 방문한 곳인데 이것이 제자리로 돌아온 것임.
        if (visited[edge.node] == 2) {
//            visited[edge.node] = 1;

            pathStack.push(edge.node);
            weightStack.push(edge.weight);

            int startIndex = pathStack.indexOf(edge.node);
            // result 는 기존 인덱스부터 스택 크기까지의 합.
            int tempSize = pathStack.size() - 1;
            int tempResult = 0;
            for (int i = tempSize; i > startIndex; i--){
                tempResult += weightStack.get(i);
            }

            pathStack.pop();
            weightStack.pop();


            if (tempResult < result) {
                result = tempResult;
            }

            return;
        }

        // 방문했다 표시.
        visited[edge.node] = 2;
        pathStack.push(edge.node);
        weightStack.push(edge.weight);

        for (MyEdge adjNode : adjList[edge.node]) {
            dfsMethod(adjNode);
        }
//        visited[edge.node] = 1;


    }


    static class MyEdge {
        int node;
        int weight;

        public MyEdge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

}
