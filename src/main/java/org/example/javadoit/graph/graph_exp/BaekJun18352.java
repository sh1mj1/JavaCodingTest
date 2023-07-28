package org.example.javadoit.graph.graph_exp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 18352 특정 거리의 도시 찾기
 * 링크 - https://www.acmicpc.net/problem/18352
 * 등급 - Silver 2
 * 특이사항 - Do it Java 알고리즘 책 문제 046
 */
public class BaekJun18352 {
    static Queue<Integer> qu = new LinkedList<>();
    static ArrayList<Integer>[] graph;
    static int[] visited;
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        boolean kExisted = false;
        visited = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            visited[i] = -1;
        }
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph[start].add(end);
        }

        bfsFunc(X);

        for (int i = 1; i <= N; i++) {
            if (visited[i] == K) {
                kExisted = true;
                sb.append(i).append("\n");
            }
        }
        if (kExisted) {
            System.out.print(sb);
        } else {
            System.out.println(-1);
        }

    }

    static void bfsFunc(int startNode) {
        qu.add(startNode);
        visited[startNode] = 0;
        while (!qu.isEmpty()) {
            int curNode = qu.poll();
            for (int adjNode : graph[curNode]) {
                if (visited[adjNode] == -1) {
                    qu.add(adjNode);
                    visited[adjNode] = visited[curNode] + 1;
                }
            }
        }
    }
}

/*
 도시의 개수 N, 도로의 개수 M, 거리 정보 K, 출발 도시의 번호 X
 graph: 인접 리스트로 그래프 표현
 visited: 방문 배열
 qu: BFS 탐색 시 사용할 큐

 for(N){ 그래프 초기화 }
 for(M){ 그래프에 에지 정보 추가 }

 bfsFunc(X)

 // bfs 메소드
 bfsFunc(int startNode){
    qu 에 시작 노드 넣기
    visitied[현재노드] = 0;
    int depth = 1;
    while(큐가 빌 때까지){
        현재 노드는 큐에서 poll
        for(adjNode: 현재노드의 인접노드){
            adjNode 를 큐에 넣기
            visited[adjNode] = depth;
         }
         depth++
     }
 }

 */