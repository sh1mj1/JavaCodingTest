package org.example.javadoit.graph.graph_exp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 1707 이분 그래프
 * 링크 - https://www.acmicpc.net/problem/1707
 * 등급 - Gold 4
 * 특이사항 - Do it Java 알고리즘 책 문제 048
 */
public class BaekJun1707 {
    static BufferedReader br;
    static ArrayList<Integer>[] graph;
    static int[] biSetArr;
    static boolean[] visited;
    static boolean isBipartite;

    public static void main(String[] args) throws IOException {
        testCase();
    }

    private static void testCase() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            input();
        }
    }

    private static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        graph = new ArrayList[V + 1];
        biSetArr = new int[V + 1];
        visited = new boolean[V + 1];
        isBipartite = true;
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int sta = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph[sta].add(end);
            graph[end].add(sta);
        }

        for (int i = 1; i <= V; i++) {
            if (!visited[i] && isBipartite) {
                dfsFunc(i);
            }
        }
        if (isBipartite) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    static void dfsFunc(int node) {
        visited[node] = true;
        for (int adjNode : graph[node]) {
            if (visited[adjNode]) {
                if (biSetArr[adjNode] == biSetArr[node]) {
                    isBipartite = false;
                    return;
                }
            } else {
                biSetArr[adjNode] = (biSetArr[node] + 1) % 2;
                dfsFunc(adjNode);
            }
        }
    }
}

/* 슈도코드
T: 테스트 케이스
graph: 인접 리스트, biSetArr: 이분 집합 배열
visited: 방문 배열
isBipartite = true: 이분인지?


for(T){
    V: 노드 개수, E: 에지개수
    for(V){ graph 초기화 }
    for(E){ 에지 정보 추가 }
    for(1 ~ V){
        만약 방문하지 않았고 아직 이분 그래프로 보이면
        dfsFunc(i)
    }
    if(isBiportite){ YES 출력 } 아니면 {NO 출력}
}

// dfs 메소드
dfsFunc(int node){
    visited[node] = true;
    for(int adjNode: 현재 노드의 인접 노드){
        if(인접 노드가 방문한 노드라면){
            if(biSetArr[인접노드] == biSetArr[node]){ // 현재 노드와 인접노드가 같은 집합이다?
                isBipartite = false;
                return;
            }
        } 아니라면{
            biSetArr[인접노드] = (biSetArr[현재 노드] + 1) % 2 // 0 과 1 왔다갔다.
            dfsFunc(adjNode)
        }
    }
}


 */
