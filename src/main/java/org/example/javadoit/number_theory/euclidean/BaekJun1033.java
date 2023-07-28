package org.example.javadoit.number_theory.euclidean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 1033 칵테일
 * 링크 - https://www.acmicpc.net/problem/1033
 * 등급 - Gold 2
 * 특이사항 - Do it Java 알고리즘 책 문제 044
 */
public class BaekJun1033 {

    static ArrayList<Node>[] graph;
    static long[][] ans;
    static int[] cnt;
    static long gcd;
    static long lcd;
    static Queue<Node> qu;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N];
        ans = new long[N][2];
        cnt = new int[N];
        gcd = 1;
        lcd = 1;

        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            gcd = 1;
            gcdFunc(p, q);
            p = (int) (p / gcd);
            q = (int) (q / gcd);
            graph[a].add(new Node(b, p, q));
            graph[b].add(new Node(a, q, p));
            cnt[a]++;
            cnt[b]++;
        }
        gcd = 1;
        int start = 0;
        for (int i = 0; i < N; i++) {
            if (cnt[i] > cnt[start]) {
                start = i;
            }
        }

        qu = new LinkedList<>();
        qu.add(new Node(start, 1, 1));
        ans[start][0] = 1;
        ans[start][1] = 1;

        bfsFunc();
        for (int i = 0; i < ans.length; i++) {
            long value = lcd * ans[i][0] / ans[i][1];
            sb.append(value).append(" ");
        }
        System.out.println(sb);
    }

    static void bfsFunc() {
        while (!qu.isEmpty()) {
            Node curNode = qu.poll();
            for (Node adjNode : graph[curNode.node]) {
                if (ans[adjNode.node][0] < 1) {
                    ans[adjNode.node][0] = 1;
                    ans[adjNode.node][1] = 1;

                    adjNode.p *= curNode.p;
                    adjNode.q *= curNode.q;
                    qu.add(adjNode);
                }
            }
            ans[curNode.node][0] = curNode.q;
            ans[curNode.node][1] = curNode.p;

            gcd = 1;
            gcdFunc(lcd, curNode.p);
            lcd = lcd * curNode.p / gcd;
        }
    }

    static void gcdFunc(long a, long b) {
        if (a < b) {
            long temp = a;
            a = b;
            b = temp;
        }
        long temp = a % b;
        if (temp == 0) {
            gcd = b;
            return;
        }
        a = b;
        b = temp;
        gcdFunc(a, b);
    }

    static class Node {
        int node;
        long p; // 분모
        long q; // 분자

        public Node(int node, long p, long q) {
            this.node = node;
            this.p = p;
            this.q = q;
        }
    }
}


/*
슈도코드

graph: 그래프, ans: 2차원 배열, cnt: 배열, qu: 큐, LCD: 최소공배수
ans 에는 분자값 배열과 분모값 배열 저장.
분자값 배열은 방문 배열로도 활용할 것임

graph 초기화
초기화 하면서 cnt 을 각 재료에 맞게 1씩 증가.
cnt 에서 가장 큰 값 start 찾기.

qu 에 start 노드 넣기 // 이 노드는 Node(start, 1, 1): p,q 가 1
ans[start][분자쪽] = 1 // 방문함 표시
ans[start][분모쪽] = 1

bfsFunc()

for(ans 의 모든 노드에 대해){
    출력(LCD * ans[i][분자쪽] / ans[i][분모쪽 + " ")
}

// bfs 메소드
bfsFunc(){
    while(큐가 빌 때까지){
        curNode = qu.poll()
        for(curNode 의 인접 노드 adjNode 에 대해서){
            만약 adjNode 가 방문 안했다면(ans의 분자쪽이 1 이하라면)
                큐에 넣기 이 때 Node(adjNode.노드번호, adjNode.p * curNode.p, adjNode.q * curNode.q)
                ans[인접노드][분자쪽] = 1 // 방문함 표시
                ans[인접노드][분모쪽] = 1
            }
        }
        ans[curNode][분자] = q
        ans[curNode][분모] = p

        LCD = (LCD 와 p) 의 최소공배수로 갱신
    }


class Node{
    노드 번호, p, q
}
 */