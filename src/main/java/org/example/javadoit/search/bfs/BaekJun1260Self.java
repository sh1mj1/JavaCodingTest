package org.example.javadoit.search.bfs;

import java.io.*;
import java.util.*;

public class BaekJun1260Self {
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static BufferedWriter bw;
    static Queue<Integer> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int startNode = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }
        br.close();

        for (int i = 1; i <= N; i++) {
            Collections.sort(graph[i]);
        }

        visited = new boolean[N + 1];
        dfs(startNode);
        bw.write("\n");
        bw.flush();

        visited = new boolean[N + 1];
        queue = new LinkedList<>();
        queue.offer(startNode);
        visited[startNode] = true;
        bfs();
        bw.flush();
        bw.close();
    }

    static void dfs(int node) throws IOException {
        visited[node] = true;
        bw.write(node + " ");
        for (int adjNode : graph[node]) {
            if (!visited[adjNode]) {
                dfs(adjNode);
            }
        }
    }

    static void bfs() throws IOException {
        while (!queue.isEmpty()) {
            int node = queue.poll();
            bw.write(node + " ");

            for (int adjNode : graph[node]) {
                if (!visited[adjNode]) {
                    queue.offer(adjNode);
                    visited[adjNode] = true;
                }
            }
        }
    }
}