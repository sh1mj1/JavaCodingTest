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
public class BaekJun1753Self {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());
        ArrayList<Node>[] graph = new ArrayList[V + 1];
        boolean[] visited = new boolean[V + 1];
        int[] dist = new int[V + 1];
        PriorityQueue<Node> queue = new PriorityQueue<>();

        for (int i = 1; i <= V; i++) {
            dist[i] = 99_999_999;
            graph[i] = new ArrayList<>();
        }
        dist[K] = 0;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v, w));
        }

        queue.offer(new Node(K, 0));
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int curNode = cur.nodeNum;
            if (visited[curNode]) {
                continue;
            }
            visited[curNode] = true;
            for (Node adjNode : graph[curNode]) {
                int next = dist[curNode] + adjNode.weight;
                if (next < dist[adjNode.nodeNum]) {
                    dist[adjNode.nodeNum] = next;
                    queue.offer(new Node(adjNode.nodeNum, dist[adjNode.nodeNum]));
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            if (dist[i] == 99_999_999) {
                System.out.println("INF");
            } else {
                System.out.println(dist[i]);
            }
        }

    }
    static class Node implements Comparable<Node>{
        int nodeNum;
        int weight;

        public Node(int nodeNum, int weight) {
            this.nodeNum = nodeNum;
            this.weight = weight;
        }


        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

}

/* 슈도코드

V: 노드 개수, E: 에지 개수
K: 출발 노드
graph: 인접 리스트 dist: 최단 거리 배열 visited 방문배열
queue: 우선순위 큐. 거리 우선 정렬

for(V){ dist 을 ∞ 로 초기화 }
dist[K] = 0;
for(E) { 그래프 에지 데이터 설정 }

queue 에 시작 노드 넣기 (K, 0)
while(큐가 빌 때까지){
    int cur = 큐에서 poll
    만약 cur 이 이미 방문했으면  -> 큐의 다음 노드로 넘어감.
    아니면 -> cur 방문함 체크.
    for(cur 의 인접 노드 adjNode 에 대해){
        만약 (dist[cur] + 에지 가중치 < dist[인접 노드]) 이면
            dist 업데이트, 큐에 offer
    }
}

dist 출력. 만약 배열값이 ∞ 이면 INF 출력.

static class Node implements Comparable{
    nodeNum. weight

    거리 우선으로 정렬 기준 설정.
}

 */