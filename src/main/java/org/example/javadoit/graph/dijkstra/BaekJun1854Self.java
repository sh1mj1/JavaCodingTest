package org.example.javadoit.graph.dijkstra;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
//https://velog.io/@torch/2023.05.29
public class BaekJun1854Self {

    private static ArrayList<ArrayList<Node>> adj;

    private static ArrayList<PriorityQueue<Integer>> distance;
    // 다익스트라

    private static void dijkstra(int start, int k) {
        int curr;
        Node node;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        distance.get(start).add(0);
        pq.add(new Node(start, distance.get(start).peek()));
        while (!pq.isEmpty()) {
            node = pq.poll();
            curr = node.index;
            for (Node edge : adj.get(curr)) {
                if (distance.get(edge.index).size() < k || node.dist + edge.dist < distance.get(edge.index).peek()) {
                    distance.get(edge.index).add(node.dist + edge.dist);
                    if (distance.get(edge.index).size() > k) {
                        distance.get(edge.index).remove();
                    }
                    pq.add(new Node(edge.index, node.dist + edge.dist));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n, m, k, a, b, c, i;

        // N, M, K
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        // 간선 입력
        adj = new ArrayList<>();
        for (i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        for (i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            adj.get(a).add(new Node(b, c));
        }

        // 경로 탐색
        distance = new ArrayList<>();
        for (i = 0; i <= n; i++) {
            distance.add(new PriorityQueue<>(Comparator.reverseOrder()));
        }
        dijkstra(1, k);

        // 출력
        for (i = 1; i <= n; i++) {
            if (distance.get(i).size() < k) {
                sb.append("-1").append("\n");
            } else {
                sb.append(distance.get(i).peek()).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    // 정점
    private static class Node implements Comparable<Node> {
        int index, dist;

        Node(int index, int dist) {
            this.index = index;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node other) {
            return this.dist - other.dist;
        }
    }
}
