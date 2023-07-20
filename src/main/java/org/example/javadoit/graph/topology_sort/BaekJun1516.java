package org.example.javadoit.graph.topology_sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 1516 게임 개발
 * 링크 - https://www.acmicpc.net/problem/1516
 * 등급 -  gold 3
 * 특이사항 - Do it Java 알고리즘 책 문제 054
 */
public class BaekJun1516 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // N: 건물 종류 수, adjacencyList: 인접리스트, N: 결과 배열
        int N = Integer.parseInt(bufferedReader.readLine());
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();
        int result[] = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // inDegree: 진입 차수배열, selfBuild: 건물 생산 시간 배열
        int[] inDegree = new int[N + 1];
        int[] selfBuild = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            selfBuild[i] = Integer.parseInt(stringTokenizer.nextToken()); // 건물 생산 시간

            while (true) {
                int temp = Integer.parseInt(stringTokenizer.nextToken());
                if (temp == -1) {
                    break;
                }
                adjacencyList.get(temp).add(i); // 인접 리스트의 에지 추가
                inDegree[i]++; // 진입 차수 증가
            }
        }

        // 위상 정렬
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int nowNode = queue.poll();
            for (int nextNode : adjacencyList.get(nowNode)) {
                inDegree[nextNode]--;

                // 시간 업데이트. 선행 건물 조건 중 가장 오래 걸리는 시간을 선택해야 함.
                // 여기서는 result[nextNode] 가 nowNode 이전에 검사했을 때의 선행 시간임.
                // nowNode 에서 nextNode 로 가는 시간을 구해서 서로 비교, 최대값을 취해야 함.
                result[nextNode] = Math.max(result[nextNode], result[nowNode] + selfBuild[nowNode]);
                if (inDegree[nextNode] == 0) {
                    queue.offer(nextNode);
                }

            }
        }

        for (int i = 1; i <= N; i++) {
            System.out.println(result[i] + selfBuild[i]);
        }

    }


}

/*
슈도 코드

N: 건물 종류 수, A: 데이터 저장 인접 리스트
건물의 개수만큼 인접 리스트 선언
진입 차수 배열 선언
건물 생산 배열(자기 자신을 짓는데 걸리는 시간)
for(N){
    인접 리스트 데이터 저장
    진입 차수 배열 초기 데이터 저장
    건물 생산 배열 초기화
}

// 위상 정렬
큐 생성
for(N){
    진입 차수 배열의 값이 0인 건물(노드)를 큐에 삽입
}

while(큐가 빌 때까지){
    현재 노드 = 큐에서 데이터 poll
    for(현재 노드에서 갈 수 있는 노드의 개수){
        타깃 노드 진입 차수 배열--;
        결과 노드 업데이트 = Math.max(현재 저장된 값, 현재 출발 노드 + 비용)
        if(타깃 노드의 진입 차수가 0 이면){
            우선순위 큐에 타깃 노드 추가
        }
    }
}

결과 출력

 */