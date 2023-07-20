package org.example.javadoit.search.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJun2178Self {
    static int N;
    static int M;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] graph;
    static Queue<Point> qu;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N + 1][M + 1]; // (1, 1) -> (0, 0)

        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                graph[i][j] = line[j] - '0';
            }
        }

        qu = new LinkedList<>();
        qu.offer(new Point(0, 0, 1));
        graph[0][0] = 0; // 방문

        bfs();
    }

    static void bfs() {
        while (!qu.isEmpty()) {
            Point curNode = qu.poll();
            int curX = curNode.x;
            int curY = curNode.y;
            int curDepth = curNode.depth;

            for (int i = 0; i < 4; i++) {
                int adjNodeX = curX + dx[i];
                int adjNodeY = curY + dy[i];
                int adjNodeDepth = curDepth + 1;

                if (adjNodeX >= 0 && adjNodeX < N && adjNodeY >= 0 && adjNodeY < M
                        && graph[adjNodeX][adjNodeY] == 1) {
                    if (adjNodeX == N - 1 && adjNodeY == M - 1) {
                        System.out.println(adjNodeDepth);
                        return;
                    }
                    Point adjNode = new Point(adjNodeX, adjNodeY, adjNodeDepth);
                    qu.offer(adjNode);
                    graph[adjNodeX][adjNodeY] = 0;
                }
            }

        }
    }

    static class Point {
        int x;
        int y;
        int depth;

        public Point(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }
}

/*
슈도 코드
N, M: 인접 노드
graph: 2차원 배열, 여기에 노드의 값인 0, 1 저장.
queue: LinkedList 로 queue 을 구현한다.
depth = 1 // 깊이

queue 에 시작 노드 삽입.
시작 노드 방문 표시

bfs 실행

//bfs 메소드
while(큐가 비어있지 않으면){
    큐에서 노드 curNode 를 poll 함.
    만약 curNode 가 (N, M) 이라면 해당 반복문 종료. depth 출력

    curNode 의 인접 노드 큐에 삽입.
    // 이 때 미방문했으며 그래프 안에 있고 값이 1인 노드만 삽입.
    // 인접 노드는 curNode 의 x 좌표에 dx[0 ~ 3], y 좌표에 dy[0 ~ 3] 을 더해서
    curNode 을 방문함 표시. (해당 값일 0으로 변경)

    depth++
}

 */