package org.example.javadoit.graph.graph_exp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 2251 물통
 * 링크 - https://www.acmicpc.net/problem/2251
 * 등급 - Gold 5
 * 특이사항 - Do it Java 알고리즘 책 문제 049
 */
public class BaekJun2251 {

    static int[] sndrIndex = {0, 0, 1, 1, 2, 2};
    static int[] rcvrIndex = {1, 2, 0, 2, 0, 1};
    static boolean[][] visited;
    static int[] capacity;
    static boolean[] answer;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        capacity = new int[3];
        visited = new boolean[201][201];
        answer = new boolean[201];
        capacity[0] = Integer.parseInt(st.nextToken());
        capacity[1] = Integer.parseInt(st.nextToken());
        capacity[2] = Integer.parseInt(st.nextToken());

        bfsFunc();

        for (int i = 0; i < answer.length; i++) {
            if (answer[i]) {
                System.out.print(i + " ");
            }
        }

    }

    static void bfsFunc() {
        Queue<ABbottle> qu = new LinkedList<>();
        qu.add(new ABbottle(0, 0));
        visited[0][0] = true;
        answer[capacity[2]] = true;
        while (!qu.isEmpty()) {
            ABbottle cur = qu.poll();
            int A = cur.A;
            int B = cur.B;
            int C = capacity[2] - A - B;
            for (int i = 0; i < 6; i++) {
                int[] next = {A, B, C};
                next[rcvrIndex[i]] += next[sndrIndex[i]];
                next[sndrIndex[i]] = 0;
                if (next[rcvrIndex[i]] > capacity[rcvrIndex[i]]) {
                    int diff = next[rcvrIndex[i]] - capacity[rcvrIndex[i]];
                    next[rcvrIndex[i]] = capacity[rcvrIndex[i]];
                    next[sndrIndex[i]] = diff;
                }

                if (!visited[next[0]][next[1]]) {
                    qu.offer(new ABbottle(next[0], next[1]));
                    visited[next[0]][next[1]] = true;
                    if (next[0] == 0) {
                        answer[next[2]] = true;
                    }
                }
            }
        }
    }

    static class ABbottle {
        int A;
        int B;

        public ABbottle(int a, int b) {
            A = a;
            B = b;
        }
    }
}


/* 슈도코드

SndrIndex, RcvrIndex: 6사지 경우를 탐색하기 위한 선언 배열
answer: 정답 배열
now: A, B, C 의 값을 저장하는 배열
now 배열 저장.
visited, answer 초기화

BFS 수행
for(answer 배열){
    answer 배열에서 값이 true 인 index 을 정답으로 출력
}

// BFS 메서드
BFS {
    큐 자료구조에 출발 노드 더하기 -> A, B 가 0 인 상태이므로 0,0 노드에서 시작.
    visited 배열에 현재 노드 방문 기록.
    answer 배열에 현재 C 의 값 체크
    while(큐가 빌때까지){
        큐에서 노드 데이터를 poll
        A, B, C 값 초기화
        for(6 가지 케이스 반복 ) { // A->B, A->C, B->A, B->C, C->A, C->B
            받는 물통에 보내려는 물통의 값을 더하기
            보내려는 물통 값을 0으로 업데이트
            if(받는 물통이 넘치면){
                넘치는 만큼 보내는 물통에 다시 넣어주고, 받는 물통은 이 물통의 최대값으로 저장
            }
            현재 노드의 연결 노드 중 방문하지 않은 노드로 큐에 add
            visited 배열에 방문 기록
            if(1번째 물통이 비어있으면) { 3번째 물통의 물의 양을 answer 배열에 기록하기 }
        }
    }
}



// ABbottle 클래스 선언.
// A, B 의 값만 가니고 있으면 C 는 구할 수 있다.
class ABbottle{
    A, B
}

 */