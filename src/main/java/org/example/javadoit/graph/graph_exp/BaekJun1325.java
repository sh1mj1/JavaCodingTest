package org.example.javadoit.graph.graph_exp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
public class BaekJun1325 {
    static int N;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        answer = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int end = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            graph[start].add(end);
        }
        int visitCntMax = 0;
        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            bfsFunc(i);
            visitCntMax = Math.max(visitCntMax, answer[i]);
        }

        for (int i = 1; i <= N; i++) {
            if (answer[i] == visitCntMax) {
                System.out.print(i + " ");
            }
        }
    }

    static void bfsFunc(int startNode) {
        Queue<Integer> qu = new LinkedList<>();
        qu.offer(startNode);
        visited[startNode] = true;
        while (!qu.isEmpty()) {
            if (answer[startNode] == N - 1) {
                return;
            }
            int curNode = qu.poll();
            for (int adjNode : graph[curNode]) {
                if (!visited[adjNode]) {
                    qu.offer(adjNode);
                    visited[adjNode] = true;
                    answer[startNode]++;
                }
            }
        }
    }
}

/* 슈도코드

 N, M: 컴퓨터 개수, 신뢰 개수
 graph: 인접 리스트,
 visited: 방문 배열
 qu: BFS 을 위한 큐
 visitCnt : 방문한 노드 개수 배열
 visitCntMax: 방문한 노드가 가장 많을 때 노드 수

 for(N){ graph 초기화}
 for(M){ graph 에 신뢰 관계 추가 }

 for(모든 컴퓨터에 대해서){
    bfsFunc(시작노드)
    visitCntMax = Math.max(visitCnt[시작 노드], visitCntMax);
}

// bfs 메소드
bfs(int startNode){
    큐에 시작 노드 넣기
    visitCnt[시작노드] = 0
    while(큐가 빌 때까지){
        큐에서 뽑은 노드를 현재 노드로 설정
        현재 노드 방문 표시
        visitCnt[시작노드]++
        for(adjNode: 현재 노드의 인접 노드){
            만약 방문한 적이 없는 노드라면{
                큐에 인접 노드를 추가
                인접 노드 방문 표시
                visitCnt[시작노드]++
            }
        }
    }
}



  */
