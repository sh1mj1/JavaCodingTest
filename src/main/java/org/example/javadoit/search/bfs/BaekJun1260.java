package org.example.javadoit.search.bfs;

import java.util.*;

/**
 * 문제 - 백준 1260
 * 링크 - https://www.acmicpc.net/problem/1260
 * 등급 -  silver 2
 * 특이사항 - Do it Java 알고리즘 책 문제 026 164page
 */
public class BaekJun1260 {

    static boolean[] visited;
    static ArrayList<Integer>[] A;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // N: 노드 개수, M: 에지 개수, V: 시작점
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int V = scanner.nextInt();

        // A: 인접 리스트, visited: 방문 배열
        A = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = new ArrayList<>();
        }
        for (int i = 1; i <= M; i++) {
            int n1 = scanner.nextInt();
            int n2 = scanner.nextInt();
            A[n1].add(n2);
            A[n2].add(n1);
        }

        // 노드 번호가 작은 것을 먼저 방문하기 위한 정렬
        for (int i = 1; i <= N; i++) {
            Collections.sort(A[i]);
        }

        dfs(V);
        System.out.println();
        visited = new boolean[N + 1];
        bfs(V);

    }

    public static void dfs(int node) {
        System.out.print(node + " ");
        visited[node] = true;

        for (int adjacencyNode : A[node]) {
            if (!visited[adjacencyNode]) {
                dfs(adjacencyNode);
            }
        }
    }

    public static void bfs(int node) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        visited[node] = true;

        while (!queue.isEmpty()) {
            int nowNode = queue.poll();
            System.out.print(nowNode + " ");
            for (int adjacencyNode : A[nowNode]) {
                if (!visited[adjacencyNode]) {
                    visited[adjacencyNode] = true;
                    queue.add(adjacencyNode);
                }
            }
        }
    }

}

/*
슈도 코드

N: 노드 개수, M: 에지 개수, V: 시작점
A: 그래프 데이터 저장 인접 리스트, visitied: 방문 배열
A 인접 리스트에 각 ArrayList 초기화
A 인접 리스트에 그래프 데이터 저장

// 방문할 수 있는 노드가 여러 개일 경우 번호가 작은 것을 먼저 방문하기 위해 정렬.
for(N){
    각 노드와 관련된 에지 정렬
}

DFS(V)
visited 배열 초기화
BFS(V)


DFS{
    현재 노드 출력
    visited 배열에 현재 노드 방문 기록
    현재 노드의 연결 노드 중 방문하지 않은 노드로 DFS 실행(재귀 함수 형태)
}

BFS{
    큐 자료 구조에 시작 노드 삽입(add)
    visited 배열에 현재 노드 방문 기록
    while(큐가 비어있을 때까지){
        큐에서 노드 데이터 가져오기 (poll)
        가져온 노드 출력
        현재 노드의 연결 노드 중 미방문 노드를 큐에 삽입(add 연산)하고 방문 배열에 기록
    }
}

 */