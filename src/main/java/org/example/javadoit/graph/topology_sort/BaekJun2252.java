package org.example.javadoit.graph.topology_sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 2252 줄 세우기
 * 링크 - https://www.acmicpc.net/problem/2252
 * 등급 -  gold 3
 * 특이사항 - Do it Java 알고리즘 책 문제 053
 */
public class BaekJun2252 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] D = new int[N + 1];
        ArrayList<Integer>[] graph = new ArrayList[N + 1];
        Queue<Integer> qu = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[A].add(B);
            D[B]++;
        }

        for (int i = 1; i <= N; i++) {
            if (D[i] == 0) {
                qu.offer(i);
            }
        }

        while (!qu.isEmpty()) {
            int cur = qu.poll();
            sb.append(cur).append(" ");
            for (int adjNode : graph[cur]) {
                D[adjNode]--;
                if (D[adjNode] == 0) {
                    qu.offer(adjNode);
                }
            }
        }
        System.out.println(sb);
    }

}

/* 슈도 코드
N: 노드 개수, M: 에지 개수
graph: 그래프 저장 ArrayList<Integer>[]
qu: 위상 정렬을 위해 사용할 큐
Sb: StringBuilder
D[N]: 진입 차수 배열
for(N) { 그래프 생성 }
for(M) { 그래프 에지 데이터 저장, 진입 차수 배열 값 설정 }

for(진입 차수 배열 D 에 대해) { 진입 차수가 0 인 모든 노드 큐에 offer }
while(!qu.isEmpty()) {
    cur = 큐에 있는 값 poll
    sb.append(cur)
    cur 이 가리키는 노드의 진입차수 --;
    cur 이 가리키는 노드 중에서 0 이 된 노드를 큐에 offer
}

sb 출력

 */
