package org.example.javadoit.graph.minimum_spanning_tree;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 17472
 * 링크 - https://www.acmicpc.net/problem/17472
 * 등급 -  gold 1
 * 특이사항 - Do it Java 알고리즘 책 문제 065
 */
public class BaekJun17472 {

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] map;
    static int N, M, islandNum;
    static boolean[][] visited;
    static ArrayList<ArrayList<int[]>> sumlist;
    static ArrayList<int[]> mlist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 세로
        M = Integer.parseInt(st.nextToken()); // 가로
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; i < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        islandNum = 1;
        sumlist = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    bfsFunc(i, j);
                    islandNum++;
                    sumlist.add(mlist);
                }
            }
        }




    }

    // 1. bfs
    static void bfsFunc(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        mlist = new ArrayList<>();
        int[] start = {i, j};
        queue.offer(start);
        mlist.add(start);
        visited[i][j] = true;
        map[i][j] = islandNum;

        while (!queue.isEmpty()) {
            int now[] = queue.poll();
            int x = now[0];
            int y = now[1];
            for (int d = 0; d < 4; d++) {
                int tempX = dx[d];
                int tempY = dy[d];
                while (x + tempX >= 0 && x + tempX < N && y + tempY >= 0 && y + tempY < M) {
                    if (!visited[x + tempX][y + tempY] && map[x + tempX][y + tempY] != 0) {
                        map[i][j] = islandNum;
                        visited[i][j] = true;
                        int[] temp = {i, j};
                        mlist.add(temp);
                        queue.offer(temp);
                    }
                }
            }
        }
    }

}

/* 슈도 코드

1. BFS 로 섬을 구분.
dx, dy: 네 방향 탐색을 위한 배열
N, M: 행렬의 크기
map: 맵 정보 저장 배열
visited: BFS 을 할 때 방문 여부 저장 배열
sumlist: 모든 섬 정보 저장
mlist: 한 개의 섬 정보 저장.

for(N)
    for(M)
        입력 데이터를 map 에 저장

for(i -> N)
    for(j -> M)
        BFS(i, j) // 모든 위치에서 BFS 을 실행해서 섬을 분리.
        결과를 sumlist 변수에 넣기

2. 에지(다리) 정보를 찾아내서 큐에 저장
queue: 다리 정보를 저장해 줄 큐. 우선순위 큐
for(i => sumlist 크기만큼)
    now = sumlist[i]
    for(j -> now 크기만큼)
        1개의 섬의 모든 위치에서 만들 수 있는 다리 정보를 저장.
         (상,하,좌,우 방향, 4방향 탐색. 그렇게 4개를 next 라고 하면)
         테두리에서 4방향 탐색을 해야 함.

         next 가 같은 섬이면 안됨.
         다른 섬이라면 (같은 섬도 아니고 바다도 아니라면)
            만약 다리 길이(출발 섬으로부터의 거리)가 2 이상이면
                큐에 해당 에지 정보를 넣기
        바다라면
            다리 길이를 연장

3. MST 최소 신장 트리 알고리즘 수행.






parent: 대표 노드 저장 배열



// 1. static 메서드 BFS
BFS(i, j)
    큐에 [i, j] 노드 넣기. 방문 체크. map 에 현재 섬의 값 저장해서 구분해주기.
    i, j 위치에서 네 방향을 탐색해서 1 개 섬의 영역을 저장.

    다음 위치 next(4개)에 대해 방문한 적이 없고 바다가 아니라면
        같은 섬이다. map 에 값을 섬의 값을 저장해서 구분해주기
        방문 체크.
        next 을 저장해두기, queue 에 넣다리 정보


 */