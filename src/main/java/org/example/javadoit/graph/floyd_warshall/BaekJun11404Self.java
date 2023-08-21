package org.example.javadoit.graph.floyd_warshall;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 11404 플로이드
 * 링크 - https://www.acmicpc.net/problem/11404
 * 등급 -  gold 4
 * 특이사항 - Do it Java 알고리즘 책 문제 061
 */
public class BaekJun11404Self {
    static BufferedWriter bw;
    static int N;
    static long[][] graph;

    public static void main(String[] args) throws IOException {
        input();

        // floyd-warshall
        floydWarshall();

        // 출력
        output();
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        graph = new long[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(graph[i], 9_999_999);
            graph[i][i] = 0;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[s][e] = Math.min(graph[s][e], w);
        }
        br.close();
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

    private static void output() throws IOException {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                long tmp = graph[i][j];
                if (tmp == 9_999_999) {
                    bw.write(0 + " ");
                } else {
                    bw.write(tmp + " ");
                }
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

}

/* 슈도코드
N: 노드(도시) 개수
M: 에지(노선) 개수
graph[][] 에지 정보 저장

graph 을 0 과 ∞ 로 초기화. ∞ 는 충분히 큰 수로

// 플로이드 워셜 알고리즘
for (중간경로 m 가 1 ~ n)
    for(출발 도시 s 가 1 ~ n)
        for(도착 도시 e 가 1 ~ n)
            D[s][e] = Math.min(graph[s][e], graph[s][m] + graph[m][e])

// graph 출력
graph[i][j] == ∞ 이면 0 출력.
아니면 graph[i][j] 출력.

 */