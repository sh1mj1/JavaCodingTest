package org.example.javadoit.graph.unionfind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 1976 여행 가자
 * 링크 - https://www.acmicpc.net/problem/1976
 * 등급 -  gold 4
 * 특이사항 - Do it Java 알고리즘 책 문제 051
 */
public class BaekJun1976 {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        parent = new int[N + 1];
        int[][] city = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int[] route = new int[M];
        for (int i = 0; i < M; i++) {
            route[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (city[i][j] > 0) {
                    union(i, j);
                }
            }
        }

        int startParent = parent[route[0]];
        boolean isNO = false;

        for (int i = 1; i < M; i++) {
            int cur = find(route[i]);
            if (cur != startParent) {
                isNO = true;
                break;
            }
        }

        if (isNO) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
        }
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }

    static int find(int node) {
        if (node == parent[node]) {
            return node;
        } else {
            return parent[node] = find(parent[node]);
        }
    }

}

/* 슈도코드
N: 도시 수, M: 여행 계획에 속한 도시 수
parent: 각 도시의 대표 노드를 저장할 배열
city: 인접 행렬 형태로 그래프 표현
route: 여행 계획 순서에 맞게 저장한 도시 배열

for(N){
    for(N){ city 초기화 }
}
for(N) { parent 배열 초기화. 값 = 인덱스 }
for(M) { route 에 여행 도시 경로 저장 }

for(N) {
    for(N) {만약 인접 행렬에서 도시가 연결되어 있다고 하면 union 연산}
}

startParent = parent[route[0]]
for(M){
    만약 route 의 부모가 startParent 와 다르면
    isNO
}

isNO 이면 -> NO 출력
아니면 YES 출력

 */