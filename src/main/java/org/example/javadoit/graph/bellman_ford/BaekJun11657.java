package org.example.javadoit.graph.bellman_ford;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 11657 타임머신
 * 링크 - https://www.acmicpc.net/problem/11657
 * 등급 -  gold 4
 * 특이사항 - Do it Java 알고리즘 책 문제 059
 */
public class BaekJun11657 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        // N: 노드 개수, M: 에지 개수, isMinusCycle: 음수 사이클이 있는지
        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());
        boolean isMinusCycle = false;

        // edges: 에지 리스트 배열, distance: 최단 거리 배열
        Edge[] edges = new Edge[M + 1];
        long[] distance = new long[N + 1];

        Arrays.fill(distance, Integer.MAX_VALUE); // 최단 거리 배열 원소들을 ∞ 로 초기화

        // 에지 리스트 배열 초기화
        for (int i = 1; i <= M; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int start = Integer.parseInt(stringTokenizer.nextToken());
            int end = Integer.parseInt(stringTokenizer.nextToken());
            int weight = Integer.parseInt(stringTokenizer.nextToken());
            edges[i] = new Edge(start, end, weight);
        }
        bufferedReader.close();

        // bellman-ford 알고리즘
        distance[1] = 0; // 출발노드 1

        // N 보다 1개 적은 수만큼 반복 (에지의 최대 개수는 N - 1)
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= M; j++) { // 모든 에지 리스트의 에지 비교
                Edge edge = edges[j];
                // 더 작은 최단 거리가 있을 때 업데이트
                if (distance[edge.start] != Integer.MAX_VALUE
                        && distance[edge.end] > distance[edge.start] + edge.weight) {
                    distance[edge.end] = distance[edge.start] + edge.weight;
                }
            }
        }

        // 음수 사이클 존재 여부 확인
        for (int j = 1; j <= M; j++) {
            Edge edge = edges[j];
            if (distance[edge.start] != Integer.MAX_VALUE
                    && distance[edge.end] > distance[edge.start] + edge.weight){
                isMinusCycle = true;
            }
        }

        // 출력
        if (isMinusCycle) {
            bufferedWriter.write(-1 + "\n");
        } else {
            for (int i = 2; i <= N; i++) {
                if (distance[i] == Integer.MAX_VALUE) {
                    bufferedWriter.write(-1 + "\n");
                } else {
                    bufferedWriter.write(distance[i] + "\n");
                }
            }
        }

        bufferedWriter.flush();
        bufferedWriter.close();
    }



    static class Edge {
        int start;
        int end;
        int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

}



/*
슈도 코드

자료구조 선언(그래프 정보, 최단 거리 배열 저장)
N: 노드 개수, M: 에지 개수
Edges (에지 리스트 배열)
거리 배열은 충분히 큰 수로 초기화
for(M){ 에지 리스트 배열에 이 에지 정보 저장 }

// 벨만 포드 알고리즘
거리 배열에 출발 노드를 0 으로 초기화
for(N - 1){
    for(에지 개수){
        현재 에지 데이터 가져오기
        if(출발 노드가 무한대가 아니고,  종료 노드 값< 출발 노드값 + 에지 가중치){
            업데이트 수행 -> 종료 노드값 = 출발 노드값 + 에지 가중치
        }
    }
}

// 음수 사이클 존재 여부 확인
for(에지 개수){
    현재 에지 데이터 가져오기
    if(출발 노드가 무한대가 아니고 종료 노드값 < 출발 노드값 + 에지 가중치){
        업데이트 가능 -> 음수 사이클 존재
    }
}

if(음수 사이클 미 존재) -> 거리 배열 출력 (단, 거리 배열의 값이 무한대면 해당 값에는 -1 출력)
존재 -> -1 출력

// 에지를 담기 위한 별도 클래스
Edge{
    start: 출발노드, end: 종료 노드, value: 에지 가중치
}



 */