package org.example.javadoit.search.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 2178
 * 링크 - https://www.acmicpc.net/problem/2178
 * 등급 -  silver 1
 * 특이사항 - Do it Java 알고리즘 책 문제 027 169page
 */
public class BaekJun2178 {

    // 상하좌우를 탐색하기 위한 배열 선언
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static int N;
    static int M;

    static int[][] A;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        // N: row, M: column
        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        A = new int[N][M];
        visited = new boolean[N][M];

        // A 배열 초기화
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            String line = stringTokenizer.nextToken();
            for (int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(line.substring(j, j + 1));
            }
        }

        bFs(0, 0);
        System.out.println(A[N - 1][M - 1]);

    }

    private static void bFs(int i, int j) {
        
        // queue 에 현재 노드의 좌표값을 배열 형태로 저장
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        visited[i][j] = true;

        while (!queue.isEmpty()) {
            int[] nowNode = queue.poll();

            for (int k = 0; k < 4; k++) {
                // 상하좌우로 이동했을 때
                int x = nowNode[0] + dx[k];
                int y = nowNode[1] + dy[k];

                // 좌표 유효성 검사 (해당 좌표가 미로 안에 있는지)
                if (x >= 0 && y >= 0 && x < N && y < M) {
                    // 해당 칸이 0 이 아닌지 , 이미 방문하지는 않았는지 검사
                    if (A[x][y] != 0 && !visited[x][y]) {
                        visited[x][y] = true;
                        A[x][y] = A[nowNode[0]][nowNode[1]] + 1; // depth 업데이트
                        queue.add(new int[]{x, y});
                    }

                }
            }
        }


    }

}


/*
슈도 코드

dx, dy: 상하좌우를 탐색하기 위한 define 값 정의 변수
A: 데이터 저장 2차원 행렬
N: row, M: column
visited: 방문 배열

A 배열 초기화
visited 배열 초기화
for(N){
    for(M){
        A 배열에 데이터 저장
    }
}

bfs(0, 0) 실행

A[N-1][M-1] 값 출력

BFS{
    큐 자료 구조에 시작 노드를 삽입 (add)
    visited 배열에 현재 노드 방문 기록
    while(큐가 빌 때까지){
        큐에서 노드 데이터 빼기 (poll)
        for(상하좌우){
            if(유효한 좌표){
                if(이동할 수 있는 칸이면서 방문하지 않은 노드){
                   visited 배열에 방문 기록
                   A 배열에 depth 를 현재 노드의 depth + 1 로 업데이트
                   큐에 데이터 삽입(add)
               }
           }
       }
   }
}



 */