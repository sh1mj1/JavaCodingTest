package org.example.samsumg_sw.Prob1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Prob1Try7DFS {

    static Stack<Integer> pathStack;
    static Stack<Integer> weightStack;
    static int rootNode;
    static int minSum;
    static boolean isFirstStackInit;
    static int[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stringTokenizer;
        int testcase = Integer.parseInt(bufferedReader.readLine()); // testCase 수

        for (int tcCnt = 1; tcCnt <= testcase; tcCnt++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            int N = Integer.parseInt(stringTokenizer.nextToken()); // 학생(노드) 수
            int M = Integer.parseInt(stringTokenizer.nextToken()); // 마니또 관계(에지) 수
            minSum = Integer.MAX_VALUE;
            visited = new int[N + 1];
            visited[0] = Integer.MAX_VALUE;

            // 그래프 초기화
            List<List<Edge>> graph = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList<>());
            }
            for (int i = 0; i < M; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                int X = Integer.parseInt(stringTokenizer.nextToken());
                int Y = Integer.parseInt(stringTokenizer.nextToken());
                int C = Integer.parseInt(stringTokenizer.nextToken());
                graph.get(X).add(new Edge(Y, C));
            }

            int nextSearchIndex = 1;

            while (visited[nextSearchIndex] == 0) {
                isFirstStackInit = true;
                rootNode = nextSearchIndex;
                pathStack = new Stack<>();
                weightStack = new Stack<>();

                // DFS 함수
                dfsSearch(nextSearchIndex, graph);

                // 같은 연결요소에 있다면 해당 노드는 다시 탐색할 필요 없음
                for (int i = 1; i <= N; i++) {
                    if (visited[i] != 0) {
                        nextSearchIndex = i;
                    }
                }

            }


            if (minSum == Integer.MAX_VALUE) { // 그래프가 없다면 -1 출력
                minSum = -1;
            }
            System.out.println("#" + tcCnt + " " + minSum);
        }
        bufferedReader.close();

    }

    public static void dfsSearch(int node, List<List<Edge>> graph) {

        // 하나의 연결 요소임
        visited[node] = rootNode;

        // 초기에 루트 노드를 스택에 넣기
        if (isFirstStackInit) {
            pathStack.push(node);
            weightStack.push(0); // pathStack 과 weightStack 의 인덱스 맞추기 위해
            isFirstStackInit = false;
        }

        for (Edge edge : graph.get(node)) {
            if (pathStack.contains(edge.node)) {
                // 탐색할 노드가 스택에 이미 있다면 해당 인덱스부터 그 노드까지 가중치 더하기
                int startIndex = pathStack.indexOf(edge.node) + 1;
                pathStack.push(edge.node);
                weightStack.push(edge.weight);
                int tempMinSum = 0;
                for (int i = startIndex; i < pathStack.size(); i++) {
                    tempMinSum += weightStack.get(i);
                }

                // 결과에 반영
                minSum = Math.min(minSum, tempMinSum);
                pathStack.pop();
                weightStack.pop();
            } else {
                // 새로운 edge 을 넣고 edge.node 를 탐색
                pathStack.push(edge.node);
                weightStack.push(edge.weight);
                dfsSearch(edge.node, graph);
            }
        }

        pathStack.pop();
        weightStack.pop();
    }


    static class Edge {
        int node;
        int weight;

        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
}