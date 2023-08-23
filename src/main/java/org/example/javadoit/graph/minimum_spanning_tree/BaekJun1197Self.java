package org.example.javadoit.graph.minimum_spanning_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 1197 최소 스패닝 트리
 * 링크 - https://www.acmicpc.net/problem/1197
 * 등급 -  gold 4
 * 특이사항 - Do it Java 알고리즘 책 문제 064
 */
public class BaekJun1197Self {

    static int V;
    static PriorityQueue<Edge> pQu;
    static int[] parent;
    static int ans, edgeCnt;

    public static void main(String[] args) throws IOException {
        input();
        mstFunc();
        System.out.println(ans);
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        parent = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }

        pQu = new PriorityQueue<>();
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pQu.offer(new Edge(n1, n2, w));
        }
        br.close();
    }

    private static void mstFunc() {
        while (edgeCnt < V - 1) {
            Edge curEdge = pQu.poll();
            int n1Parent = find(curEdge.n1);
            int n2Parent = find(curEdge.n2);
            if (n1Parent != n2Parent) {
                parent[n2Parent] = n1Parent;
                edgeCnt++;
                ans += curEdge.w;
            }
        }
    }

    static int find(int n) {
        if (n == parent[n]) {
            return n;
        } else {
            return parent[n] = find(parent[n]);
        }
    }

    static class Edge implements Comparable<Edge> {
        int n1, n2, w;

        public Edge(int n1, int n2, int w) {
            this.n1 = n1;
            this.n2 = n2;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }
}

/* 슈도 코드
V: 노드 개수, E: 에지 개수
pQu: 그래프의 에지를 저장할 우선순위 큐
parent: 부모 배열(대표노드 배열)
ans = 0
edgeCnt = 0

parent 의 배열 값을 각 인덱스와 똑같이 설정.
pQu 에 에지 데이터 입력받기 : 오름차순으로 저장 될것임.

while(edgeCnt < V-1)
    cur = pQu 에서 poll 한 것.
    if cur 의 노드1 과 노드2 가 서로 대표노드가 다르면
        노드1 과 노드2 의 부모노드를 똑같이 하기
        edgeCnt++;
        ans += cur의 가중치

ans 출력.


// union 함수
union(n1, n2){
    n1 = find(n1)
    n2 = find(n2)
    if n1 != n2 이면
        parent[n2] = n1;

// find 함수: 재귀 함수 형태
find(n){
    if n == 유니온 파인드배열[n]
        return n;
    else
        return parent[n] = find[parent[n]];
}



static class Edge implements Comparable{
    n1, n2, w

    // 정렬 함수
    w 을 기준으로 오름차순으로 정렬
}

 */