package org.example.javadoit.graph.unionfind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 1717 집합의 표현
 * 링크 - https://www.acmicpc.net/problem/1717
 * 등급 -  gold 4
 * 특이사항 - Do it Java 알고리즘 책 문제 050
 */
public class BaekJun1717Self {
    public static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int query = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (query == 0) {
                union(a, b);
            } else {
                if (checkSame(a, b)) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
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

    static boolean checkSame(int a, int b) {
        a = find(a);
        b = find(b);
        return a == b;
    }

}

/* 슈도코드

N: 원소 개수, M: 질의 개수
parent: 대표 노드 저장 배열
for(N){ 대표 노드를 자기 자신으로 초기화 }
for(M) {
    if(0 이면) 집합 합치기 -> union 연산
    else ->  같은 집합 원소인지 확인하고 결과값 출력
}

// union 연산
union(a, b){
    // a, b 의 대표 노드 찾기
    a = find(a); b = find(b);
    두 원소의 대표 노드끼리 연결하기
}

// find 연산
find(a){
    a 가 대표 노드이면 리턴
    아니면 a 의 대표 노드값을 find(parent[a]) 값으로 저장 -> 재귀함수 형태
}

// checkSame: 두 원소가 같은 집합인지 확인
checkSame(a,b){
    // a 와 b 의 대표 노드 찾기
    a = find(a); b = find(b);
    두 대표 노드가 같으면 true
    아니면 false
}

 */