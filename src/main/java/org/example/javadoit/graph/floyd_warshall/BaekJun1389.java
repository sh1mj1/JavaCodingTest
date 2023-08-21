package org.example.javadoit.graph.floyd_warshall;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 1389 케빈 베이컨의 6단계 법칙
 * 링크 - https://www.acmicpc.net/problem/1389
 * 등급 -  Silver 1
 * 특이사항 - Do it Java 알고리즘 책 문제 063
 */
public class BaekJun1389 {
    static BufferedWriter bw;
    static int N;
    static int[][] graph;
    static int kevinNum = 9_999;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        input();
        floydWarshall();
        checkKevinNum();
        System.out.println(answer);
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        graph = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(graph[i], 9_9999);
            graph[i][0] = 0;
            graph[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[A][B] = 1;
            graph[B][A] = 1;
        }
    }

    private static void floydWarshall() {
        for (int m = 1; m <= N; m++) {
            for (int s = 1; s <= N; s++) {
                for (int e = 1; e <= N; e++) {
                    graph[s][e] = Math.min(graph[s][e], graph[s][m] + graph[m][e]);
                }
            }
        }
    }

    private static void checkKevinNum() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (graph[i][j] != 9_999) {
                    graph[i][0] += graph[i][j];
                }
            }
            if (kevinNum > graph[i][0]) {
                kevinNum = graph[i][0];
                answer = i;
            }
        }
    }

}

/* 슈도 코드
N: 노드(유저) 수, M: 에지(친구 관계) 수
graph: 인접 행렬로 그래프 저장
answer: 정답 변수

graph 에 값을 0 과 ∞ 로 설정
graph 에 에지 데이터 초기화

// Floyd-Warshall
for(m: 1 ~ N){
    for(s: 1 ~ N){
        for(e: 1 ~ N){
            점화식
        }
    }
}

for(i: 1 ~N) {
    for(j: 1 ~N) {
        if(graph[i][j] != ∞){
            graph[0][i] += graph[i][j];
        }
    }
    answer = Math.min(answer, graph[0][i])
}

answer 출력


 */