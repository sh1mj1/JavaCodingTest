package org.example.javadoit.graph.minimum_spanning_tree;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 1197 최소 스패닝 트리
 * 링크 - https://www.acmicpc.net/problem/1197
 * 등급 -  gold 4
 * 특이사항 - Do it Java 알고리즘 책 문제 064
 */
public class BaekJun1197 {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        // V: 노드, E: 에지,
        int V = Integer.parseInt(stringTokenizer.nextToken());
        int E = Integer.parseInt(stringTokenizer.nextToken());

        // resultEdge: 사용한 엣지 수 result: 결과 MST 의 가중치의 합
        int resultEdgeCount = 0;
        int result = 0;

        // parent: union-find 배열. 노드의 대표 부모 노드 저장
        parent = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }

        // edgePriorityQueue: 에지 큐 (가중치 기준 오름차순 정렬)
        PriorityQueue<Edge> edgePriorityQueue = new PriorityQueue<>();
        for (int i = 0; i < E; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int start = Integer.parseInt(stringTokenizer.nextToken());
            int end = Integer.parseInt(stringTokenizer.nextToken());
            int weight = Integer.parseInt(stringTokenizer.nextToken());
            edgePriorityQueue.offer(new Edge(start, end, weight));
        }
        bufferedReader.close();


        // 크루스칼 알고리즘. MST 구성
        while (resultEdgeCount < V - 1) {
            Edge nowNode = edgePriorityQueue.poll();
            if (find(nowNode.start) != find(nowNode.end)) {
                union(nowNode.start, nowNode.end);
                result += nowNode.weight;
                resultEdgeCount++;
            }
        }

        bufferedWriter.write(result + "\n");
        bufferedWriter.flush();
        bufferedWriter.close();

    }

    public static int find(int node) {
        if (node == parent[node]) {
            return node;
        } else {
            return parent[node] = find(parent[node]);
        }
    }

    public static void union(int node1, int node2) {
        node1 = find(node1);
        node2 = find(node2);
        if (node1 != node2) {
            parent[node2] = node1;
        }
    }


    static class Edge implements Comparable<Edge> {
        int start;
        int end;
        int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
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
N: 노드 수, M: 에지 수
parent: 대표 노드 저장 배열(union-find 저장 배열)
queue: 에지 정보를 저장할 PriorityQueue

for(M) { queue 에 에지 정보 저장 } // 우선순위 큐이므로 자동 정렬

while( 사용한 에지 수가 노드 - 1 이 될때까지){
    큐에서 에지 정보 가져오기
    에지 시작점과 끝점의 부모 노드가 다르면 -> 연결해도 사이클이 생기지 않으면
    union 연산 수행
    에지의 가중치를 정답 변수에 더하기
}

정답 변수 출력

// 메소드 선언
union(a,b){
    a, b 의 대표 노드 찾기
    두 원소의 대표 노드끼리 연결
}

find(a){
    a 가 대표 노드이면 리턴 a
    아니면
    return find(a) = find(parent[a]); (재귀 함수 형태)
}

pNode implements Comparable {
    출발노드, 도착노드, 가중치
    가중치의 오름차순이 되도록 compareTo 구현
}


 */