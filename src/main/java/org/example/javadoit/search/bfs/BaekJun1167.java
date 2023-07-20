package org.example.javadoit.search.bfs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 문제 - 백준 1167 트리의 지름
 * 링크 - https://www.acmicpc.net/problem/1167
 * 등급 -  gold 3
 * 특이사항 - Do it Java 알고리즘 책 문제 028 174page
 */
public class BaekJun1167 {

    // N: 노드 개수, adjacencyList: 인접 리스트
    static ArrayList<Edge>[] adjacencyList;

    // visited: 방문 배열, distance: 거리 저장 배열
    static boolean[] visited;
    static int[] distance;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        adjacencyList = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        distance = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            adjacencyList[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            int nodeNum = scanner.nextInt();
            while (true) {
                int edgeDirection = scanner.nextInt();
                if (edgeDirection == -1) {
                    break;
                }
                int edgeWeight = scanner.nextInt();
                adjacencyList[nodeNum].add(new Edge(edgeDirection, edgeWeight));
            }
        }

        // 임의의 노드 하나 선정
        int startNode = 1;

        bFs(startNode);

        // distance 최대 거리 찾기
        int maxDistance = 0;
        int maxDistanceNode = 0;
        for (int i = 1; i <= N; i++) {
            if (maxDistance < distance[i]) {
                maxDistance = distance[i];
                maxDistanceNode = i;
            }
        }

        visited = new boolean[N + 1];
        distance = new int[N + 1];

        bFs(maxDistanceNode);
        int maxDistance2 = 0;
        for (int i = 1; i <= N; i++) {
            if (maxDistance2 < distance[i]) {
                maxDistance2 = distance[i];
            }
        }

        System.out.println(maxDistance2);

    }

    private static void bFs(int startNode) {
        Queue<Integer> queue = new LinkedList<>();
        visited[startNode] = true;
        queue.add(startNode);

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (Edge edge : adjacencyList[node]) {
                if (!visited[edge.nodeNum]) {
                    queue.add(edge.nodeNum);
                    visited[edge.nodeNum] = true;
                    distance[edge.nodeNum] = distance[node] + edge.value;
                }
            }


        }

    }

    private static class Edge {
        int nodeNum;
        int value;

        public Edge(int nodeNum, int value) {
            this.nodeNum = nodeNum;
            this.value = value;
        }

    }
}

/*
슈도 코드

N: 노드 개수, A: 인접리스트
visited: 방문 배열, distance: 거리 저장 배열

for(N){A 에 각 ArrayList 초기화}
for(M){A 에 그래프 데이터 저장}

visited 배열 초기화
distance 배열 초기화

BFS 실행 (임의의 점을 시작점으로)

visited 배열 초기화
distance 배열 초기화

BFS 실행 (distance 배열에서 가장 큰 값을 지니고 있는 노드를 시작점으로)

distance 배열에서 가장 큰 수 출력

BFS{
    큐 자료구조에 시작 노드 삽입 (add)
    현재 노드의 연결 노드 중 방문하지 않은 노드를 큐에 add
    visited 방문 기록
    현재 노드의 거리 + 엣지의 가중치로 방문하지 않은 거리 배열 업데이트
}

Edge 클래스 {
    e: 목적지 노드
    value: 에지의 가중치
}

 */
