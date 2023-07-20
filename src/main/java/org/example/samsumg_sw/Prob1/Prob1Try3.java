package org.example.samsumg_sw.Prob1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Prob1Try3 {
    static class Edge {
        int to;
        int cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt(); // 테스트 케이스 개수

        for (int tc = 1; tc <= t; tc++) {
            int n = scanner.nextInt(); // 학생 수
            int m = scanner.nextInt(); // 마니또 관계 수

            List<List<Edge>> graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                int c = scanner.nextInt();

                graph.get(x).add(new Edge(y, c));
            }

            int result = findMinimumCost(graph, n);
            System.out.println("#" + tc + " " + result);
        }
    }

    public static boolean dfs(int node, boolean[] visited, Stack<Integer> stack, List<List<Edge>> graph) {
        visited[node] = true;
        stack.push(node);

        for (Edge edge : graph.get(node)) {
            int neighbor = edge.to;

            if (!visited[neighbor]) {
                if (dfs(neighbor, visited, stack, graph)) {
                    return true;
                }
            } else if (stack.contains(neighbor)) {
                if (stack.get(0) == neighbor) {
                    stack.push(neighbor);
                    return true;
                }

            }
        }

        stack.pop();
        return false;
    }

    public static int findMinimumCost(List<List<Edge>> graph, int n) {
        int minimumCost = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            boolean[] visited = new boolean[n + 1];
            Stack<Integer> stack = new Stack<>();

            if (dfs(i, visited, stack, graph)) {
                int totalCost = 0;
//                for (int node : stack) {
//                    for (Edge edge : graph.get(node)) {
//                        totalCost += edge.cost;
//                    }
//                }

                for(int tmemem = 0; tmemem < stack.size(); tmemem++)
                    System.out.print(stack.get(tmemem) + " ");

                System.out.println();

                for (int j = 0; j < stack.size()-1; j++) { // j= 0 -> j < 4..... j = 1 .... j = 2
                    for (int k = 0; k < graph.get(stack.get(j)).size(); k++) { // k = 0 -> k < 1 .... k = 0, k = 1 -> k < 2 ...... k = 0, k = 1 -> k < 2
                        if (stack.get(j+1) == graph.get(stack.get(j)).get(k).to) { // 2 == 2 .... 5 != 3 , 5 == 5 .... 4 != 2, 4 == 4 ...
                            totalCost += graph.get(stack.get(j)).get(k).cost; // 1 ... 1+5 = 6 ... 6+1 = 7 ...
                        }
                    }
                }


                minimumCost = Math.min(minimumCost, totalCost);
            }
        }

        return (minimumCost != Integer.MAX_VALUE) ? minimumCost : -1;
    }
}

