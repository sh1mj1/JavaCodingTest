package org.example.javadoit.search.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 13023 ABCDE
 * 링크 - https://www.acmicpc.net/problem/13023
 * 등급 -  gold 5
 * 특이사항 - Do it Java 알고리즘 책 문제 025 157page
 */
public class BaekJun13023Self {
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int depth;
    static int isSatisfied = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        for (int i = 0; i < N; i++) {
            depth = 1;
            dfs(i, depth);
            if (isSatisfied == 1) {
                break;
            }
        }
        System.out.println(isSatisfied);
    }

    static void dfs(int node, int depth) {
        if (visited[node]) {
            return;
        }

        visited[node] = true;

        if (depth == 5) {
            isSatisfied = 1;
            return;
        }

        for (int adjNode : graph[node]) {
            if (!visited[adjNode]) {
                dfs(adjNode, depth + 1);
            }
        }
        visited[node] = false;

    }
}