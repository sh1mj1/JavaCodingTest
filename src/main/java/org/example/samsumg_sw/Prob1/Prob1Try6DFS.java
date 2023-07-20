package org.example.samsumg_sw.Prob1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Prob1Try6DFS {
    static boolean[] visited;
    static Stack<Integer> stack;
    static int[] indexSearched;
//    boolean[] visited = new boolean[n + 1];
//    Stack<Integer> stack = new Stack<>();
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stringTokenizer;
        int testcase = Integer.parseInt(bufferedReader.readLine());

        for (int tcCnt = 1; tcCnt <= testcase; tcCnt++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            int N = Integer.parseInt(stringTokenizer.nextToken()); // 학생(노드) 수
            int M = Integer.parseInt(stringTokenizer.nextToken()); // 마니또 관계(에지) 수

//            visited = new boolean[N + 1];
//            stack = new Stack<>();


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

            int result = findMinimumCost(graph, N);
            bufferedWriter.write("#" + tcCnt + " " + result + "\n");

        }
        bufferedReader.close();
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    public static boolean dfs(int node, boolean[] visited, Stack<Integer> stack, List<List<Edge>> graph, int neighborIndex) {
        if (!visited[node]) {
            stack.push(node);
        }
        visited[node] = true;

        for (int i = neighborIndex; i < graph.get(node).size(); i++) {
//        for(Edge edge: graph.get(node)){
            Edge edge = graph.get(node).get(i);

            int neighbor = edge.to;

            if (!visited[neighbor]) { // 인접노드를 방문하지 않았으면 해당 인접노드에서 다시 dfs
                indexSearched[node] += 1;
                if (dfs(neighbor, visited, stack, graph, indexSearched[neighbor]/*, neighborIndex*/)) {
                    return true;
                }
            } else if (stack.contains(neighbor)) { // 인접 노드를 방문했고 그 노드가 시작 노드라면 return
                if (stack.get(0) == neighbor) {
                    indexSearched[node] += 1;
                    stack.push(neighbor);
                    return true;
                }
            }
            indexSearched[node] += 1;
        }

        int lastNode = stack.pop(); // 그래프를 따라가다가 막다른 길이면 되돌아가기
        if (lastNode != stack.get(0)) {
            indexSearched[lastNode] = 0;
        }

        if (!stack.isEmpty()) {
            dfs(stack.peek(), visited, stack, graph, indexSearched[stack.peek()]);
        }
        return false;
    }

    // 각 노드마다 사이클을 찾는다.
    public static int findMinimumCost(List<List<Edge>> graph, int n) {

        int minimumCost = Integer.MAX_VALUE;


        for (int i = 1; i <= n; i++) { // i 는 각 노드. i번 노드에서 dfs 을 처음 시작

//            for (int l = 0; l < n - 1; l++) { // l 은 노드의 엣지들. 작은 엣지부터 시작
            visited = new boolean[n + 1];
            indexSearched = new int[n + 1];
            stack = new Stack<>();
            stack.push(i);

//            visited[i] = true;


                /*if*/while (dfs(stack.peek(), visited, stack, graph, indexSearched[stack.peek()]/*, l*/)) {
                    int totalCost = 0;

                    // 사이클 가중치의 합 구하기
                    for (int j = 1/*0*/; j < stack.size()-1 ; j++) {
                        int currentAdjNodeSize = graph.get(stack.get(j)).size();
                        for (int k = 0; k < currentAdjNodeSize; k++) {
                            Edge nextEdge = graph.get(stack.get(j)).get(k);
                            if (stack.get(j + 1) == nextEdge.to) {
                                totalCost += nextEdge.cost;
                            }
                        }
                    }

                    minimumCost = Math.min(minimumCost, totalCost);
//                    visited[stack.pop()] = false;
                    stack.pop();
                }
//            visited[stack.pop()] = false;
//            stack.pop();
//            }

        }

        return (minimumCost != Integer.MAX_VALUE) ? minimumCost : -1;
    }

    static class Edge {
        int to;
        int cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

}

