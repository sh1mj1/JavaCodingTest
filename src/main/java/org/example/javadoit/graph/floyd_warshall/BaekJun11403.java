package org.example.javadoit.graph.floyd_warshall;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 11403 경로 찾기
 * 링크 - https://www.acmicpc.net/problem/11403
 * 등급 -  Silver 1
 * 특이사항 - Do it Java 알고리즘 책 문제 062
 */
public class BaekJun11403 {
    static BufferedWriter bw;
    static int N;
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        input();
        floydWarshall();
        output();
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        graph = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();
    }

    private static void floydWarshall() {
        for (int m = 1; m <= N; m++) {
            for (int s = 1; s <= N; s++) {
                for (int e = 1; e <= N; e++) {
                    if (graph[s][m] == 1 && graph[m][e] == 1) {
                        graph[s][e] = 1;
                    }
                }
            }
        }
    }

    private static void output() throws IOException {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                bw.write(graph[i][j] + " ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }
}

/* 슈도코드
N: 노드 개수
graph[][] : 에지 인접 행렬
for(1 ~ N){
    에지 초기화
}

// floyd-warshall
for(중간 노드 m 이 1 ~ N) {
    for( 시작 노드 s 가 1 ~ N) {
        for( 도착 노드 e 가 1 ~ N) {
            if(D[s][m] == 1 && D[m][e] == 1){
                D[s][e] = 1;
            }
        }
    }
}

출력

중간 노드 m 에 대해서
어떤 출발 노드 s 에서 m 으로 가는 길이 존재(D[s][m] ==1)하고
m 에서 어떤 도착 노드 e 로 가는 길이 존재(D[m][e] == 1)하면
s 에서 e 로 가는 길이 존재한다는 의미임.

if(D[s][m] == 1 && D[m][e] == 1) -> D[s][e] = 1;

플로이드-워셜 알고리즘을 사용하여
노드 1 부터 N 까지 모든 노드를 중간노드로 간주하고
출발 노드와 도착 노드를 1 부터 N 까지 변경해 가면서 검사하면 됨.



 */