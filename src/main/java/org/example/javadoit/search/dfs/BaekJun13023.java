package org.example.javadoit.search.dfs;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 문제 - 백준 13023 ABCDE
 * 링크 - https://www.acmicpc.net/problem/13023
 * 등급 -  gold 5
 * 특이사항 - Do it Java 알고리즘 책 문제 025 157page
 */
public class BaekJun13023 {

    static boolean visited[];
    static ArrayList<Integer>[] A;
    static boolean arrive = false;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        A = new ArrayList[N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            A[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            A[a].add(b);
            A[b].add(a);
        }

        for (int i = 0; i < N; i++) {
            DFS(i, 1);
            if (arrive) {
                break;
            }
        }
        if (arrive) {
            System.out.println("1");
        } else {
            System.out.println("0");
        }
    }

    private static void DFS(int node, int depth) {
        if (depth == 5 || arrive) {
            arrive = true;
            return;
        }

        visited[node] = true;

        for (int i : A[node]) {
            if (!visited[i]) {
                DFS(i, depth + 1);
            }
        }

        visited[node] = false;
    }

}


/*

슈도 코드

N: 노드 개수, M: 에지 개수
adjacencyList: 그래프 저장 인접 리스트
visited: 방문 기록 저장 배열
arrive: 도착 확인 변수

for(N){
    adjacencyList 의 각 ArryList 초기화
}
for(M){
    adjacencyList 에 그래프 데이터 저장
}

for(N){
    각 노드마다 DFS
    if(arrive) 반복문 종료
}
if(arrive) 1 출력
else 0 출력


// DFS {
    if( 깊이 5 || arrive){
        arrive = true;
        함수 종료
    }
    방문 배열에 현재 노드 방문 기록
    현재 노드의 연결 노드 중 방문하지 않은 노드로 DFS 실행

 */