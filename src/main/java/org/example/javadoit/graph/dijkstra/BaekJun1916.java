package org.example.javadoit.graph.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 1916 최소비용 구하기
 * 링크 - https://www.acmicpc.net/problem/1916
 * 등급 -  gold 5
 * 특이사항 - Do it Java 알고리즘 책 문제 057
 */
public class BaekJun1916 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        ArrayList<Node>[] graph = new ArrayList[N + 1];
        PriorityQueue<Node> queue = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = 999_999_999;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v, w));
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int arrival = Integer.parseInt(st.nextToken());

        dist[start] = 0;
        queue.offer(new Node(start, 0));
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int curNode = cur.nodeNum;
            if (curNode == arrival) {
                break;
            }
            if (visited[curNode]) {
                continue;
            }
            visited[curNode] = true;
            for (Node adjNode : graph[curNode]) {
                int next = dist[curNode] + adjNode.weight;
                if (dist[adjNode.nodeNum] > next) {
                    dist[adjNode.nodeNum] = next;
                    queue.offer(new Node(adjNode.nodeNum, next));
                }
            }
        }
        System.out.println(dist[arrival]);

    }

    static class Node implements Comparable<Node> {
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

/* 슈도 코드
N: 도시(노드) 개수, M: 버스(에지) 개수
graph: 인접 리스트
dist[N+1]: 최단 거리 배열
visited[N+1]: 방문 배열
queue: PriorityQueue<Node>
그래프 초기화
dist 을 ∞ 로 초기화
for(M){ graph 에지 입력받기 }
start: 출발 도시, arrival: 도착도시

dist[start] = 0
큐에 시작 노드(start, 0) 입력받기
while(큐가 빌 때까지)
    Node cur = 큐에서 poll
    만약 cur 이 도착 노드라면 break;
    만약 cur 가 이미 방문했으면 큐에 다음 것으로 넘어가기
    아니라면 방문함 표시
    for( cur 의 인접 노드 adjNode 에 대해서){
        만약 cur 에서 adjNode 로 가는 것이 기존 dist[adjNode] 보다 작으면
            dist 을 업데이트
            큐에 인접 노드와 업데이트된 값을 노드로 해서 넣기
        }
    }
}
dist[도착도시] 출력


static class Node implements Comparable<Node>{
    nodeNum, weight
    weight 오름차순으로 정렬
}

 */
