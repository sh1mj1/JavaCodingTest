package org.example.javadoit.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 11725
 * 링크 - https://www.acmicpc.net/problem/11725
 * 등급 -  silver 2
 * 특이사항 - Do it Java 알고리즘 책 문제 067
 */
public class BaekJun11725 {

    static ArrayList<Integer>[] graph;
    static int[] parent;
    static boolean[] visited;
    static int N;
    public static void main(String[] args) throws IOException {
        input();
        dfsFunc(1);
        output();
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        parent = new int[N+1];
        visited = new boolean[N+1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph[start].add(end);
            graph[end].add(start);
        }
    }

    static void dfsFunc(int node) {
        visited[node] = true;
        for (int nextNode : graph[node]) {
            if (!visited[nextNode]) {
                parent[nextNode] = node;
                dfsFunc(nextNode);
            }
        }
    }

    private static void output() {
        for (int i = 2; i <= N; i++) {
            System.out.println(parent[i]);
        }
    }
}


/* 슈도코드
N: 노드 개수
graph: 인접 리스트 ArrayList<ArrayList<Integer>>
graph 에 데이터 입력 받기
parent: 각 노드으 ㅣ부모 노드를 저장하는 배열
visited: 방문 배열

DFS(1)

parent 의 데이터를 인덱스 2부터 값 출력

// DFS 함수
DFS(int node){

    for(현재 node 와 인접 노드 next 들에 대해서){
        parent[next] = node;
        DFS(node)
    }
}

 */