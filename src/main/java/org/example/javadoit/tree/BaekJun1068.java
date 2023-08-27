package org.example.javadoit.tree;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 문제 - 백준 1068 트리
 * 링크 - https://www.acmicpc.net/problem/1068
 * 등급 -  gold 5
 * 특이사항 - Do it Java 알고리즘 책 문제 068
 */
public class BaekJun1068 {

    static Queue<Integer>[] graph;
    static int N, removeNode, leafCnt, minusCnt;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        input();
        getFirstLeafCnt();
        dfsFunc(removeNode);
        getLastLeafCnt();
        System.out.println(leafCnt);
    }

    private static void input() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        graph = new LinkedList[N];
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int i = 0; i < N; i++) {
            int temp = sc.nextInt();
            if (temp == -1) {
                continue;
            }
            parent[i] = temp;
            graph[temp].add(i);
        }

        removeNode = sc.nextInt();
    }

    private static void getFirstLeafCnt() {
        for (int i = 0; i < N; i++) {
            if (graph[i].isEmpty()) {
                leafCnt++;
            }
        }
    }

    static void dfsFunc(int node) {
        if (graph[node].isEmpty()) {
            minusCnt++;
        }
        for (int nextNode : graph[node]) {
            dfsFunc(nextNode);
        }
        graph[node].clear();
    }

    private static void getLastLeafCnt() {
        if (graph[parent[removeNode]].size() == 1) {
            leafCnt++;
        }
        leafCnt = leafCnt - minusCnt;
    }
}
/* 슈도 코드

N: 노드 개수
graph: 인접 리스트 Queue<Ingeer>[]
인접 리스트 데이터 입력받기. 방향성을 갖는 그래프로.
노드를 삭제하기 전에 트리에서 리프 노드의 개수를 모두 구해서 저장.

삭제할 노드부터 DFS 탐색.
삭제한 노드의 부모 노드가 오직 하나의 자식만을 가지고 있었다면 그 부모 노드도 리프노드가 됨.
처음 세었던 리프노드의 개수와 후에 minusCnt 을 사용해서 최종 리프 노드의 개수 계산.

최종 리프노드의 개수 출력

// dfs 함수
dfs(int node){
    node 가 자식을 갖지 않으면(인접 노드가 없으면) minusCnt++;

    for( 모든 자식(인접노드) nextNode 에 대해서){
        dfs(자식)
    }
    그래프에서 현재 node 를 clear 하기
}
 */