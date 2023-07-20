package org.example.samsumg_sw.Prob1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 07/11 TUE 04:18
 * 작은 DFS (visited 사용), 미완.
 */
public class Prob1Try9m10SmallDFS {

    static int minSum;
    static boolean[] visited;
    static int rootNode;
    static boolean isFirstSearch;

    static int weightSum;
    static Stack<Integer> weightStack;

    static int[] weightArr;
    static int[] weightSumArr;

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stringTokenizer;
        int testcase = Integer.parseInt(bufferedReader.readLine()); // testCase 수

        for (int tcCnt = 1; tcCnt <= testcase; tcCnt++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            int N = Integer.parseInt(stringTokenizer.nextToken()); // 학생(노드) 수
            int M = Integer.parseInt(stringTokenizer.nextToken()); // 마니또 관계(에지) 수

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

            minSum = Integer.MAX_VALUE;

            for (int i = 1; i <= N; i++) {
                visited = new boolean[N + 1];
                rootNode = i;
                isFirstSearch = true;
                weightArr = new int[N + 1];
                weightSumArr = new int[N + 1];

                weightSum = 0;
                weightStack = new Stack<>();
                dfsSearch(i, 0, graph); // 시작 노드부터 dfs 시작
            }

            if (minSum == Integer.MAX_VALUE) { // 그래프가 없다면 -1 출력
                minSum = -1;
            }
            System.out.println("#" + tcCnt + " " + minSum);

        }
        bufferedReader.close();

    }

    public static void dfsSearch(int node, int weight, List<List<Edge>> graph) {

        if (!visited[node]) { // 방문 안했으면
            visited[node] = true;
            weightSum += weight;
            weightStack.push(weight);
            for (Edge edge : graph.get(node)) {
                dfsSearch(edge.node, edge.weight, graph);
            }
        } else { // 방문했으면
            if (node == rootNode) { // 그것이 루트노드다?
                weightSum += weight;
                // 최소값과 비교해서 최소값 변경
                minSum = Math.min(weightSum, minSum);
                weightSum -= weightStack.pop();
                return;
            }
        }


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