package org.example.javadoit.graph.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 1753 최단 경로
 * 링크 - https://www.acmicpc.net/problem/1753
 * 등급 -  gold 4
 * 특이사항 - Do it Java 알고리즘 책 문제 056
 */
public class BaekJun1753 {

    static int V, E, K;
    static int[] distance;
    static boolean[] visited;
    static ArrayList<Edge>[] list;
    static PriorityQueue<Edge> q = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        // V: 노드, E: 에지, K: 출발노드
        V = Integer.parseInt(stringTokenizer.nextToken());
        E = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(bufferedReader.readLine());

        // distance: 거리 배열 (0 과 ∞), visited: 방문 배열, list: 인접 리스트
        distance = new int[V + 1];
        visited = new boolean[V + 1];
        list = new ArrayList[V + 1];

        for (int i = 1; i <= V; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i <= V; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= E; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int u = Integer.parseInt(stringTokenizer.nextToken());
            int v = Integer.parseInt(stringTokenizer.nextToken());
            int w = Integer.parseInt(stringTokenizer.nextToken());
            list[u].add(new Edge(v, w));
        }

        // 시작 노드 설정
        distance[K] = 0;
        q.add(new Edge(K, 0));

        while (!q.isEmpty()) {
            Edge now = q.poll();
            int nowVertex = now.vertex;

            if (visited[nowVertex]) continue;

            visited[nowVertex] = true;

            for (Edge tempEdge : list[nowVertex]) {
                int nextVertex = tempEdge.vertex;
                int nextWeight = tempEdge.weight;

                // 최소 거리로 업데이트, 업데이트하면 해당 노드 큐에 삽입
                if (distance[nextVertex] > distance[nowVertex] + nextWeight) {
                    distance[nextVertex] = distance[nowVertex] + nextWeight;
                    q.add(new Edge(nextVertex, distance[nextVertex]));
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            if (visited[i]) {
                System.out.println(distance[i]);
            } else {
                System.out.println("INF");
            }
        }

    }

    static class Edge implements Comparable<Edge> {
        int vertex;
        int weight;

        public Edge(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }


        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

}


/*
슈도 코드

자료 구조 선언(인접리스트: 그래프, 최단 거리 저장 배열, 노드 방문 여부 저장 배열)

V: 노드 개수, E: 에지 개수, K: 출발 노드
거리 배열의 ∞ 은 충분 히 큰수로 초기화

for(V){ 인접리스트 초기화 }
for(E){ 인접 리스트 배열에 이 에지 정보 저장 }

// 다익스트라 알고리즘 수행
출발 노드는 우선순위 큐에 넣고 시작-> 거리가 최소인 노드 선택
while(큐가 빌 때까지){
    현재 선택된 노드가 방문된 적이 있는지 확인
    현재 노드를 방문 노드로 업데이트
    for( 현재 선택 노드의 에지 개수){
        if(타겟 노드 방문 전 && 현재 선택 노드 최단 거리 + 비용 < 타겟 노드의 최단 거리){
            타겟 노드 최단 거리 업데이트
            우선순위 큐에 타겟 노드 추가
        }
    }
}

완성된 거리 배열 탐색, 출력

// 가중치가 있는 그래프를 담기 위한 클래스 별도 구현
Edge{
    vertex(가리키는 노드)
    weight(에지 가중치)
    우선 순위 큐 정렬 기준을 위한 compareTo 함수
}



 */