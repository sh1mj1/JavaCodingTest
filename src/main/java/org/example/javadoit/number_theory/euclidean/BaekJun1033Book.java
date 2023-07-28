package org.example.javadoit.number_theory.euclidean;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 문제 - 백준 1033 칵테일
 * 링크 - https://www.acmicpc.net/problem/1033
 * 등급 - Gold 2
 * 특이사항 - Do it Java 알고리즘 책 문제 044
 */
public class BaekJun1033Book {
    static ArrayList<cNode>[] A;
    static long lcm;
    static boolean visited[];
    static long D[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int N = sc.nextInt();
        A = new ArrayList[N];
        visited = new boolean[N];
        D = new long[N];
        lcm = 1;
        for (int i = 0; i < N; i++) {
            A[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int p = sc.nextInt();
            int q = sc.nextInt();
            A[a].add(new cNode(b, p, q));
            A[b].add(new cNode(a, q, p));
            lcm *= (p * q / gcd(p, q));
        }
        D[0] = lcm;
        dfsFunc(0);

        // 수를 하나씩 비교해가면서 D 에 있는모든 수에 대해 최대공약수 구하기
        long mgcd = D[0];
        for (int i = 1; i < N; i++) {
            mgcd = gcd(mgcd, D[i]);
        }
        for (int i = 0; i < N; i++) {
            System.out.print(D[i] / mgcd + " ");
        }
    }

    static void dfsFunc(int Node) {
        visited[Node] = true;
        for (cNode adjNode : A[Node]) {
            int next = adjNode.node;
            if (!visited[next]) {
                D[next] = D[Node] * adjNode.q / adjNode.p; // 주어진 비율로 다음 노드값 갱신
                dfsFunc(next);
            }
        }
    }

    static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    static class cNode {
        int node;
        int p;
        int q;

        public cNode(int node, int p, int q) {
            this.node = node;
            this.p = p;
            this.q = q;
        }
    }
}



/*
슈도 코드

N: 재료 개수, A: 그래프, lcm: 최소공배수
D: 각 노드값 저장 배열, visitied: DFS 탐색 시 탐색 여부 저장 배열
변수 초기화 수행
for(에지 개수){
    인접 리스트 배열에 이 에지 정보 저장
    최소 공배수 업데이트
}

0 번 노드에 최소 공배수 저장.
0번 에서 DFs 탐색 수행
DFS 후 업데이트 된 D 배열의 값들의 최대 공약 수 계산
D 배열의 각 값들을 최대 공약수로 나눠서 정답 출력

// 최대 공약수 gcd() 함수 구현
gcd{
    if(b == 0) -> a 가 최대 공약수
    else -> gcd(작은 수, 큰수 % 작은 수)
}

// DFS 메소드
DFS{
    visitied 배열에 현재 노드 방문 기록
    현재 노드의 연결 노드 중 방문하지 않은 노드로
    다음 노드의 값 = 현재 노드의 값 * 비율 로 저장
    DFS 실행(재귀 형태)
}

// 노드 클래스
class cNode{
    다음 노드, 비율 1, 비율 2
}

 */