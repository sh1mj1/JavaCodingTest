package org.example.javadoit.graph.topology_sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 1948 임계경로
 * 링크 - https://www.acmicpc.net/problem/1948
 * 등급 -  Platinum 5
 * 특이사항 - Do it Java 알고리즘 책 문제 054
 */
public class BaekJun1948 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        ArrayList<Edge>[] graph = new ArrayList[N + 1];
        ArrayList<Edge>[] inverseGraph = new ArrayList[N + 1];
        int[] inDegree = new int[N + 1];
        int[] criPath = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> qu = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            inverseGraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[s].add(new Edge(e, w));
            inDegree[e]++;
            inverseGraph[e].add(new Edge(s, w));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int arrival = Integer.parseInt(st.nextToken());
        int firstAns = 0;

        // graph
        qu.offer(start);
        while (!qu.isEmpty()) {
            int node = qu.poll();
            for (Edge adjEdge : graph[node]) {
                int adjNode = adjEdge.node;
                inDegree[adjNode]--;
                if (inDegree[adjNode] == 0) {
                    qu.offer(adjNode);
                }
                criPath[adjNode] = Math.max(criPath[adjNode], criPath[node] + adjEdge.weight);
            }
        }
        firstAns = criPath[arrival];

        int secondAns = 0;

        // inverseGraph
        qu.offer(arrival);
        visited[arrival] = true;
        while (!qu.isEmpty()) {
            int node = qu.poll();
            for (Edge adjEdge : inverseGraph[node]) {
                int adjNode = adjEdge.node;
                if (criPath[node] == criPath[adjNode] + adjEdge.weight) {
                    secondAns++;
                    if (!visited[adjNode]) {
                        qu.offer(adjNode);
                        visited[adjNode] = true;
                    }
                }
            }
        }

        System.out.println(firstAns);
        System.out.println(secondAns);
    }

    static class Edge {
        int node;
        int weight;

        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

}

/* 슈도 코드

N: 도시의 개수
M: 도로 수
graph: 정방향 그래프 인접리스트 , inverseGraph: 역방향 그래프 ArrayList<Node>[]
inDegree: 진입차수 배열, criPath: 임계 경로 배열
queue: 위상 정렬 시 사용
for(M){
    graph, inverseGraph 초기화
    진입차수 배열 초기화
}
start: 출발 도시, arrival: 도착 도시
firstAns: start 에서 arrival 까지 가장 오래 걸리는 경로의 시간

// 정방향 그래프 위상 정렬
queue 에 start 넣기
while(queue 가 빌 때까지){
    큐에서 poll 해서 node 에 저장.
    node 의 인접 노드(이하 adjNode)의 진입 차수 감소;
    adjNode 의 진입차수가 0 이라면 큐에 넣기
    adjNode 의 임계 경로 업데이트
        이 때 criPath[adjNode] = Math.max(criPath[adjNode], criPath[node] + adjNode의 가중치)
}
firstAns = criPath[arrival]

// 역방향 그래프 위상 정렬
visited: 각 도시의 방문 유무 저장 배열
secondAns : 1분도 쉬지 않고 달려야 하는 도로의 수
queue 에 arrival 삽입.
visited[arrival] = true;
while(queue 가 빌 때까지){
    큐에서 poll 해서 node 에 저장
    for(node의 에지 adjEdge 들에 대해){
        만약 criPath[node] == criPath[adjEdge 의 노드] + adjEdge 의 가중치 이면 {
            secondAns++;
            만약 아직 방문하지 않았다면 {
                큐에 adjEdge 의 노드 offer.
                adjEdge 의 노드 방문 기록
            }
        }
    }
}

firstAns 출력
secondAns 출력

 */