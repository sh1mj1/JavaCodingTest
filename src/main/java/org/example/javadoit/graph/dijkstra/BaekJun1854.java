 package org.example.javadoit.graph.dijkstra;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 1854 K 번째 최단경로 찾기
 * 링크 - https://www.acmicpc.net/problem/1854
 * 등급 -  platinum 4
 * 특이사항 - Do it Java 알고리즘 책 문제 058
 */
public class BaekJun1854 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        // N: 노드, M: 에지, K: 시작 노드, W: 인접 행렬
        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());
        int K = Integer.parseInt(stringTokenizer.nextToken());
        int[][] W = new int[1001][1001];

        PriorityQueue<Integer>[] queue = new PriorityQueue[N + 1];

        for (int i = 0; i < N + 1; i++) {
            queue[i] = new PriorityQueue<>(K, (o1, o2) -> o2 - o1);
        }

        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            int c = Integer.parseInt(stringTokenizer.nextToken());
            W[a][b] = c;
        }
        bufferedReader.close();


        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));
        queue[1].add(0);

        while (!pq.isEmpty()) {
            Node u = pq.poll();
            for (int adjNode = 1; adjNode <= N; adjNode++) {
                // 연결된 모든 노드로 검색하기 (이 때문에 시간 복잡도 측면에서 인접행렬이 불리함.)
                if (W[u.node][adjNode] != 0) {
                    // 저장된 경로가 K 개가 안 될 때는 그냥 추가
                    if (queue[adjNode].size() < K) {
                        queue[adjNode].add(u.weight + W[u.node][adjNode]);
                        pq.add(new Node(adjNode, u.weight + W[u.node][adjNode]));

                    } else if (queue[adjNode].peek() > u.weight + W[u.node][adjNode]) { // 저장된 경로가 K 개 이고 현재 가장 큰 값보다 작다면
                        queue[adjNode].poll(); // 기존 큐에서 최대값을 먼저 삭제
                        queue[adjNode].add(u.weight + W[u.node][adjNode]);
                        pq.add(new Node(adjNode, u.weight + W[u.node][adjNode]));

                    }
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if (queue[i].size() == K) {
                bufferedWriter.write(queue[i].peek() + "\n");
            } else {
                bufferedWriter.write(-1 + "\n");
            }
        }
        bufferedWriter.flush();
        bufferedWriter.close();

    }
    static class Node implements Comparable<Node>{
        int node;
        int weight;

        public Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return o.weight - this.weight;
        }
    }

}


/*
슈도코드
N: 노드 개수, M: 에지 개수
K: 몇 번째 최단 경로를 구해야 하는지 나타내는 변수
W: 그래프 정보 저장 인접 행렬
for(N + 1){ 최단 거리 큐 배열 초기화 }

다익스트라 알고리즘 수행

출발 노드는 우선순위 큐에 넣고 시작

while(큐가 빌 때까지){
    for(노드 개수만큼){
        if(해당 노드와 현재 노드가 연결되어 있으면){
            if(최단 거리 배열 큐에 해당 노드에 관해 저장된 경로가 K 개보다 작으면){
                최단 거리 큐 배열에 거리 정보 삽입, 큐에 선택 노드 추가
            } else if(최단 거리 큐의 마지막 값 > 이전 노드의 값 + 두 노드 사이의 에지 가중치){
                해당 노드에 최단 거리 큐에 마지막값 삭제, 신규값으로 업데이트
                큐에 선택 노드를 추가
            }
        }
    }
}

for(노드 개수){ 우선 순위 큐 크기가 K 이면 큐의 값 출력, 아니면 -1 출력 }

Node {
    node(가리키는 노드)
    weight(에지의 가중치)

    우선순위 큐 정렬 기준을 위한 compareTo 함수
}

 */