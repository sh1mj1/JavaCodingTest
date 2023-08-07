package org.example.javadoit.graph.topology_sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 1516 게임 개발
 * 링크 - https://www.acmicpc.net/problem/1516
 * 등급 -  gold 3
 * 특이사항 - Do it Java 알고리즘 책 문제 054
 */
public class BaekJun1516Self {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] graph = new ArrayList[N + 1];
        int[] build = new int[N + 1];
        int[] ans = new int[N + 1];
        int[] inDegree = new int[N + 1];
        Queue<Integer> qu = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int next = Integer.parseInt(st.nextToken());
            build[i] = next;
            ans[i] = next;
            next = Integer.parseInt(st.nextToken());

            while (next != -1) {
                graph[next].add(i);
                inDegree[i]++;
                next = Integer.parseInt(st.nextToken());
            }
        }
        br.close();

        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                qu.offer(i);
            }
        }

        while (!qu.isEmpty()) {
            int node = qu.poll();
            for (int adjNode : graph[node]) {
                inDegree[adjNode]--;
                ans[adjNode] = Math.max(ans[adjNode], ans[node] + build[adjNode]);
                if (inDegree[adjNode] == 0) {
                    qu.offer(adjNode);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            System.out.println(ans[i]);
        }

    }

}

/* 슈도코드
N: 건물 종류 수
graph: ArrayList<Integer>[]
build: 각 건물 생산 시간
ans: 정답 배열
inDegree: 진입 차수 배열
qu: 위상 정렬 큐
건물 입력받고 위 자료구조 초기화

qu 에 진입 차수가 0 인 노드들 모두 offer
while(!qu.isEmpty){
    qu 에서 poll 하고 node 에 저장
    node 이 가리키는 노드들(이하 adjNode) 의 진입 차수--;
    ans[adjNode] 의 값 변경
        ans[adjNode] 와 ans[node] + build[adjNode] 중 최대값으로.
    adjNode 의 진입 차수가 0 인것을 qu 에 offer
}

ans 에 저장된 값을 1 ~ N 까지 출력

 */