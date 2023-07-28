package org.example.javadoit.graph.graph_exp;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 1325 효율적인 해킹
 * 링크 - https://www.acmicpc.net/problem/1325
 * 등급 - Silver 1
 * 특이사항 - Do it Java 알고리즘 책 문제 047
 */
public class BaekJun1325Book {

    static int N, M;
    static boolean[] visited;
    static int[] answer;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        answer = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            graph[S].add(E);
        }
        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            BFS(i);
        }
        int maxVal = 0;
        for (int i = 1; i <= N; i++) {
            maxVal = Math.max(maxVal, answer[i]);
        }
        for (int i = 1; i <= N; i++) {
            if (answer[i] == maxVal) {
                System.out.print(i + " ");
            }
        }
    }
    static void BFS(int index){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(index);
        visited[index] = true;
        while (!queue.isEmpty()) {
            int nowNode = queue.poll();
            for (int i : graph[nowNode]) {
                if (!visited[i]) {
                    visited[i] = true;
                    answer[i]++;
                    queue.add(i);
                }
            }
        }
    }
}

/*
N: 노드 개수, M: 에지 개수
ans: 정답 배열
graph: 그래프를 인접 리스트로 표현
visited: 방문 배열
for(N){ 그래프에 ArrayList 초기화 }
for(M){ 그래프에 신뢰 관계 저장 }
for(i 을 1 ~ N){
    방문 배열 초기화
    BFS(i) 실행
}
for(N){ answer 배열에서 가장 큰 수 찾기 }
for(N){ answer 배열에서 maxVal 과 같은 값을 가진 index 출력}

//
BFS {
    큐 자료 구조에 출발 노드 더하기(add 연산)
    visited 배열에 현재 노드 방문 기록
    while(큐가 빌 때까지){
        큐에서 poll
        현재 노드의 연결 노드 중에서 방문하지 않는 노드를 큐에 삽입, visited 배열에 방문 기록
        신규 노드 인덱스의 정답 배열 증가시키기
    }
}
 */
