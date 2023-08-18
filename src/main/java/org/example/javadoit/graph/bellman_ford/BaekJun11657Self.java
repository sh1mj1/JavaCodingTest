package org.example.javadoit.graph.bellman_ford;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 11657 타임머신
 * 링크 - https://www.acmicpc.net/problem/11657
 * 등급 -  gold 4
 * 특이사항 - Do it Java 알고리즘 책 문제 059
 */
public class BaekJun11657Self {
    
    static int N, M;
    static Edge[] graph;
    static long[] ans;
    static boolean isMinusCycle = false;
    public static void main(String[] args) throws IOException {
        input();
        bellmanFord();
        isMinusCycle = checkMinusCycle();
        output(isMinusCycle);
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new Edge[M];
        ans = new long[N + 1];
        Arrays.fill(ans, Integer.MAX_VALUE);
        ans[1] = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph[i] = new Edge(A, B, C);
        }
        br.close();
    }

    private static void bellmanFord() {
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M; j++) {
                if (ans[graph[j].start] != Integer.MAX_VALUE &&
                        ans[graph[j].arrival] > ans[graph[j].start] + graph[j].weight) {
                    ans[graph[j].arrival] = ans[graph[j].start] + graph[j].weight;
                }
            }
        }
    }

    private static boolean checkMinusCycle() {
        for (int i = 0; i < M; i++) {
            if (ans[graph[i].start] != Integer.MAX_VALUE &&
                    ans[graph[i].arrival] > ans[graph[i].start] + graph[i].weight) {
                isMinusCycle = true;
                break;
            }
        }
        return isMinusCycle;
    }

    private static void output(boolean isMinusCycle) {
        if (isMinusCycle) {
            System.out.println("-1");
        } else {
            for (int i = 2; i <= N; i++) {
                if (ans[i] == Integer.MAX_VALUE) {
                    System.out.println("-1");
                } else {
                    System.out.println(ans[i]);
                }
            }
        }
    }

    static class Edge {
        int start;
        int arrival;
        int weight;

        public Edge(int start, int arrival, int weight) {
            this.start = start;
            this.arrival = arrival;
            this.weight = weight;
        }
    }
}

/* 슈도 코드

N: 노드 수 , M: 에지 수
graph: Edge[]
ans[N+1]: 정답 배열
for(M){ 에지 입력 받기 }

for(N-1) {
    모든 에지를 검사
    ans[출발노드] != ∞ 이고 ans[도착] > ans[출발노드] + weight 이면
        ans 을 업데이트
}

모든 에지를 검사
ans[출발노드] != ∞ 이고 ans[도착] > ans[출발노드] + weight 이면
    ans 가 업데이트 되는 것이므로 음수 사이클이 존재하는 것임. -1 출력
아니면
    ans[2] 부터 ans[N] 까지 출력


 */