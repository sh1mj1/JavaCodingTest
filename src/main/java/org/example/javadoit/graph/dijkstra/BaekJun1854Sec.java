package org.example.javadoit.graph.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 1854 K 번째 최단경로 찾기
 * 링크 - https://www.acmicpc.net/problem/1854
 * 등급 -  platinum 4
 * 특이사항 - Do it Java 알고리즘 책 문제 058
 */
public class BaekJun1854Sec {
    static int n, m, k;
    static ArrayList<Edge>[] graph;
    static PriorityQueue<Integer>[] dist;
    static PriorityQueue<Edge> queue;

    public static void main(String[] args) throws IOException {
        input();
        // dijkstra
        dijkstraFunc(k, graph, dist, queue);
        output();
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        dist = new PriorityQueue[n + 1];
        queue = new PriorityQueue<>();


        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = new PriorityQueue<>(k, (o1, o2) -> o2 - o1);
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, c));
        }
        br.close();
    }

    private static void dijkstraFunc(int k, ArrayList<Edge>[] graph, PriorityQueue<Integer>[] dist, PriorityQueue<Edge> queue) {
        queue.offer(new Edge(1, 0));
        dist[1].add(0);
        while (!queue.isEmpty()) {
            Edge cur = queue.poll();
            int curNode = cur.node;
            for (Edge adjEdge : graph[curNode]) {
                int adjNode = adjEdge.node;
                int adjWeight = adjEdge.weight;
                int nextWeight = cur.weight + adjWeight;
                if (dist[adjEdge.node].size() < k) {
                    dist[adjEdge.node].offer(nextWeight);
                    queue.add(new Edge(adjNode, nextWeight));
                } else if (dist[adjEdge.node].peek() > nextWeight) {
                    dist[adjEdge.node].poll();
                    dist[adjEdge.node].offer(nextWeight);
                    queue.add(new Edge(adjNode, nextWeight));
                }
            }
        }
    }

    private static void output() {
        for (int i = 1; i <= n; i++) {
            if (dist[i].size() == k) {
                System.out.println(dist[i].peek());
            } else {
                System.out.println("-1");
            }
        }
    }

    static class Edge implements Comparable<Edge> {
        int node;
        int weight;

        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

}

/* 슈도코드
n: 도시(노드), m: 도로(에지), k
graph: 인접 그래프
dist: 최단 거리 배열 각 원소는 크기가 k 인 우선순위 큐.
    각 우선순위 큐는 내림차순임.
queue: 노드 방문할 때의 우선순위 큐.

그래프 초기화, dist 배열 준비
그래프 에지 정보 설정

// 다익스트라 알고리즘
출발 노드를 우선순위 큐에 넣기
dist 배열에 출발 노드 관련해서 넣기
while(큐가 빌 때까지){
    cur: 큐에서 poll 한 현재 노드
    for(현재 노드에 대한 인접 노드들에 대해서 ){
        if(dist 의 원소(우선순위 큐)가 공간이 있으면, 즉, dist[인접노드].size < k 이면 ){
            dist 배열에 거리 정보 삽입
            queue 에 선택 노드 추가
        }else if( dist 의 인접노드에서 가장 큰 값(poll 한 값) > 현재 가중치 + 두 노드 사이의 에지 가중치) {
            해당 노드에 최단 거리 큐에 마지막 값 삭제
            신규 값 삽입
            queue 에 이 Edge 추가
        }
    }
}

// 출력
for( 1 ~ n){
    dist[i] 의 크기가 k 이면 dist[i].peek 값 출력
    아니면 -1 출력
}

static class Edge {
    node, weight
    weight 을 기준으로 오름차순 정렬
}

*/