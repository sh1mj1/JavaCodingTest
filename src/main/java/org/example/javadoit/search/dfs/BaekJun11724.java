package org.example.javadoit.search.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 11724 연결 요소의 개수
 * 링크 - https://www.acmicpc.net/problem/11724
 * 등급 -  Silver 2
 * 특이사항 - Do it Java 알고리즘 책 문제 023 148page
 * 3초
 */
public class BaekJun11724 {

    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        result = 0;

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                result++;
                dfs(i);
            }
        }

        System.out.println(result);
    }

    static void dfs(int node) {
        visited[node] = true;
        if (graph[node].isEmpty()) {
            return;
        }
        for (int adjNode : graph[node]) {
            if (!visited[adjNode]) {
                dfs(adjNode);
            }
        }
    }


}


/*
슈도 코드

N: 노드 개수, M: 에지 개수
A: 그래프 데이터 저장 인접 리스트

visited: 방문 기록 저장 배열

for(N){
    A 인접 리스트의 각 ArrayList 초기화
}

for(N){
    if(방문하지 않은 노드가 있다면){
        연결 요소 개수 ++
        DFS 실행
    }
}

//DFS 메소드
DFS(){
    if(현재 노드 == 방문 노드) return;
    visited 배열에 현재 노드 방문을 기록.
    현재 노드의 연결 노드 중 방문하지 않은 노드로 DFS 실행. (재귀함수 형태)
}

 */
