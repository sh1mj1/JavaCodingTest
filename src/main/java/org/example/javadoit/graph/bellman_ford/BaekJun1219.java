package org.example.javadoit.graph.bellman_ford;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 1219 오민식의 고민
 * 링크 - https://www.acmicpc.net/problem/1219
 * 등급 -  platinum 5
 * 특이사항 - Do it Java 알고리즘 책 문제 060
 */
public class BaekJun1219 {
    static int N, S, A, M;
    static Edge[] edges;
    static long[] dist;
    static long[] sales;

    public static void main(String[] args) throws IOException {
        input();
        modifiedBellmanFord();
        output();
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new Edge[M];
        dist = new long[N];
        sales = new long[N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(s, e, w);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sales[i] = Long.parseLong(st.nextToken());
            dist[i] = Long.MIN_VALUE;
        }
        dist[S] = sales[S];
        br.close();
    }

    private static void modifiedBellmanFord() {
        for (int i = 0; i < N * 2; i++) {
            for (int j = 0; j < M; j++) {
                Edge curEdge = edges[j];
                if (dist[curEdge.start] == Long.MIN_VALUE) {
                    continue;
                } else if (dist[curEdge.start] == Long.MAX_VALUE) {
                    dist[curEdge.arrival] = Long.MAX_VALUE;
                } else if (dist[curEdge.start] != Integer.MIN_VALUE &&
                        dist[curEdge.arrival] < dist[curEdge.start] - curEdge.weight + sales[curEdge.arrival]) {
                    dist[curEdge.arrival] = dist[curEdge.start] - curEdge.weight + sales[curEdge.arrival];
                    if (i > N - 1) {
                        dist[curEdge.arrival] = Long.MAX_VALUE;
                    }
                }
            }
        }
    }

    private static void output() {
        if (dist[A] == Long.MIN_VALUE) {
            System.out.println("gg");
        } else if (dist[A] == Long.MAX_VALUE) {
            System.out.println("Gee");
        } else {
            System.out.println(dist[A]);
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

/* 슈도코드
N; 노드 수, S: 시작 도시, A: 도착 도시, M: 에지 개수
edges: 그래프 에지 배열
sales: 각 도시에서 버는 돈 배열
dist: 거리 배열 -> 시작 도시는 0 나머지는 -∞
result: 도착도시까지의 거리 저장 변수

그래프 에지 데이터 저장.
// 벨만 포드
for (N + 100 번 반복해서 Relaxation){
    for(모든 에지에 대해서) {
        출발 노드가 방문하지 않은 노드라면 (-∞ 라면) continue;
        출발 노드가 양수 사이클에 연결된 노드라면 (∞ 라면)
            종료 노드의 값을 ∞ 로 업데이트
        종료 노드의 값 < 출발 노드의 값 + 도착 도시에서의 수입 - weight 이면
            종료 노드의 값을 업데이트
            만약 N-1 반복 이후에 업데이트가 되는 것이라면
                이 종료 노드를 양수 사이클 연결 노드로 업데이트
    }
}

// 도착 도시의 값에 따른 결과 출력
도착 도시가 -∞ -> 도착 불가 gg 출력
도착 도시가 ∞ -> 돈을 무한대로 벌 수 있음 Gee 출력
이외의 경우 -> 도착 도시의 값 출력

static class Edge{
    int start, arrival, weight;
}
 */