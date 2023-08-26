package org.example.javadoit.graph.minimum_spanning_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 문제 - 백준 1414 불우이웃돕기
 * 링크 - https://www.acmicpc.net/problem/1414
 * 등급 -  gold 3
 * 특이사항 - Do it Java 알고리즘 책 문제 066
 */
public class BaekJun1414 {

    static int N, ans;
    static PriorityQueue<Edge> edgeList;
    static int[] parent;
    static boolean canConnect;


    public static void main(String[] args) throws IOException {
        input();
        mstFunc();
        output();
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        edgeList = new PriorityQueue<>();
        ans = 0;

        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                int temp = line[j];
                if (temp != 48) {
                    int w = 0;
                    if (temp >= 97 && temp <= 122) {
                        w = temp - 'a' + 1;

                    } else if (temp >= 65 && temp <= 90) {
                        w = temp - 'A' + 27;
                    }
                    edgeList.offer(new Edge(i + 1, j + 1, w));
                    ans += w;
                }
            }
        }

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        if (N == 1) {
            canConnect = true;
        }
    }

    private static void mstFunc() {
        int edgeCnt = 0;

        while (!edgeList.isEmpty()) {
            Edge cur = edgeList.poll();
            int n1 = find(cur.n1);
            int n2 = find(cur.n2);
            if (n1 != n2) {
                parent[n2] = n1;
                ans -= cur.w;
                edgeCnt++;
            }
            if (edgeCnt == N - 1) {
                canConnect = true;
                break;
            }
        }
    }

    private static void output() {
        if (canConnect) {
            System.out.println(ans);
        } else {
            System.out.println(-1);
        }
    }

    static int find(int n) {
        if (n == parent[n]) {
            return n;
        }
        return parent[n] = find(parent[n]);
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

/* 슈도코드
N: 컴퓨터(노드)의 개수
edgeList: 에지 리스트를 저장할 PriorityQueue
sum = 0: 모든 에지의 가중치의 합을 저장
canConnect: 모든 컴퓨터가 연결될 수 있는지

for(N)
    for(N)
        랜선의 길이 에지리스트를 입력받기
        sum += 에지 가중치
parent: 대표 노드 배열
parent 배열을 각 배열값을 각 인덱스로 지정.
만약 노드가 1개 이면 canConnect = true;

ans = sum;: ans 에다가 연결되는 에지의 가중치를 뺄것임.
edgeCnt = 0: 연결된 에지의 개수
edgeList 가 빌때까지
    현재 에지 cur = edgeList.poll
    n1 = find(cur.n1)
    n2 = find(cur.n2)
    if(n1 != n2)
        n1 과 n2 을 연결.
        ans -= cur.가중치
        edgeCnt++;

    만약 현재 edgeCnt == N-1
        반복문 종료

만약 canConnect
    ans 출력.
아니면
    -1 출력
 */