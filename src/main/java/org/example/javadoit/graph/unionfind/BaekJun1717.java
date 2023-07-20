package org.example.javadoit.graph.unionfind;

import java.util.Scanner;

/**
 * 문제 - 백준 1717 집합의 표현
 * 링크 - https://www.acmicpc.net/problem/1717
 * 등급 -  gold 4
 * 특이사항 - Do it Java 알고리즘 책 문제 050
 */
public class BaekJun1717 {
    public static int[] parent;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        parent = new int[N + 1];

        // 대표 노드를 자기 자신으로 초기화
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            int query = scanner.nextInt();
            int a = scanner.nextInt();
            int b = scanner.nextInt();

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

    // 대표 노드끼리 연결
    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }

    // 대표 노드 찾기
    private static int find(int node) {
        if (node == parent[node]) {
            return node;
        } else {
            return parent[node] = find(parent[node]);
        }
    }

    // 각 대표노드가 같은지 확인
    private static boolean checkSame(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) {
            return true;
        }
        return false;
    }

}

/*
슈도 코드

N: 원소 개수, M: 질의 개수
parent(대표 노드 저장 배열)
for(N){
    대표 노드를 자기 자신으로 초기화)
}

for(M){
    if(질의의 첫 값이 0 이면) 집합 합치기 -> union 연산
    else(1) 같은 집합 원소인지 확인하고 결괏값 출력

// union 연산
union(a, b){
    a, b 의 대표 노드 찾기
    두 원소의 대표 노드끼리 연결
}

// find 연산
find(a){
    a 가 대표 노드면 리턴
    아니면 a 의 대표 노드값을 find(parent[a]) 로 저장. -> 재귀함수 형태
}

// 두 원소가 같은 집합인지 확인
checkSame(a, b){
    a, b 의 대표 노드 찾기
    두 대표 노드가 같으면 true,
    아니면 false
}

 */