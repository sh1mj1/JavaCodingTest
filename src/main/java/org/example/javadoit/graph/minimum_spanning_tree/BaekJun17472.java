package org.example.javadoit.graph.minimum_spanning_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 문제 - 백준 17472 다리 만들기 2
 * 링크 - https://www.acmicpc.net/problem/17472
 * 등급 -  gold 1
 * 특이사항 - Do it Java 알고리즘 책 문제 065
 */
public class BaekJun17472 {

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map;
    static int N, M, islandNum;
    static boolean[][] visited;
    static ArrayList<ArrayList<int[]>> sumlist;
    static ArrayList<int[]> mlist;
    static int[] parent;
    static boolean canConnect;

    public static void main(String[] args) throws IOException {
        input();

        // 1. BFS 로 각 섬을 구분한다.
        classifyIslands();

        // 2. 각 섬에서 다른 섬으로 연결할 수 있는 에지를 확인, 에지 리스트를 만든다.
        PriorityQueue<Edge> pqu = getAllBridges();

        // 3. 최소신장트리(MST) 을 적용
        int ans = mstAlgorithm(pqu);

        output(ans);
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 세로
        M = Integer.parseInt(st.nextToken()); // 가로
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void classifyIslands() {
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
            int row = now[0];
            int col = now[1];
            for (int d = 0; d < 4; d++) {
                int tempRow = dr[d];
                int tempCol = dc[d];
                int nextRow = row + tempRow;
                int nextCol = col + tempCol;
                while (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M) {
                    if (!visited[nextRow][nextCol] && map[nextRow][nextCol] != 0) {
                        map[nextRow][nextCol] = islandNum;
                        visited[nextRow][nextCol] = true;
                        int[] temp = {nextRow, nextCol};
                        mlist.add(temp);
                        queue.offer(temp);
                    } else {
                        break;
                    }
                    if (tempRow > 0) tempRow++;
                    else if (tempRow < 0) tempRow--;
                    else if (tempCol > 0) tempCol++;
                    else if (tempCol < 0) tempCol--;
                    nextRow = row + tempRow;
                    nextCol = col + tempCol;
                }

            }
        }


    }

    // 2. Edge 리스트 만들 때 사용
    static class Edge implements Comparable<Edge> {
        int n1, n2, weight;

        public Edge(int n1, int n2, int weight) {
            this.n1 = n1;
            this.n2 = n2;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }



    }
    private static PriorityQueue<Edge> getAllBridges() {
        PriorityQueue<Edge> pqu = new PriorityQueue();
        for (ArrayList<int[]> nowIsland : sumlist) {
            for (int[] islandIndex : nowIsland) {
                int rowInd = islandIndex[0];
                int colInd = islandIndex[1];
                int nowIslandNum = map[rowInd][colInd];
                for (int d = 0; d < 4; d++) {
                    int dRow = dr[d];
                    int dCol = dc[d];
                    int bridgeLen = 0;
                    int nextRow = rowInd + dRow;
                    int nextCol = colInd + dCol;
                    while (nextRow >= 0 && nextCol >= 0 && nextRow < N && nextCol < M) {
                        int nextIslandNum = map[rowInd + dRow][colInd + dCol];
                        if (nextIslandNum == nowIslandNum) {
                            break;
                        } else if (nextIslandNum != 0) {
                            if (bridgeLen > 1) {
                                pqu.add(new Edge(nowIslandNum, nextIslandNum, bridgeLen));
                            }
                            break;
                        } else {
                            bridgeLen++;
                        }
                        if (dRow < 0) dRow--;
                        else if (dRow > 0) dRow++;
                        else if (dCol < 0) dCol--;
                        else if (dCol > 0) dCol++;
                        nextRow = rowInd + dRow;
                        nextCol = colInd + dCol;
                    }
                }
            }
        }
        return pqu;
    }


    // 3. MST
    private static int mstAlgorithm(PriorityQueue<Edge> pqu) {
        parent = new int[islandNum];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        int ans = 0;
        int edgeCnt = 0;
        while (!pqu.isEmpty()) {
            Edge curEdge = pqu.poll();
            int n1 = find(curEdge.n1);
            int n2 = find(curEdge.n2);
            if (n1 != n2) {
                parent[n2] = n1;
                edgeCnt++;
                ans += curEdge.weight;
            }
            if (edgeCnt == islandNum - 2) {
                canConnect = true;
                break;
            }
        }
        return ans;
    }
    static int find(int n) {
        if (n == parent[n]) {
            return n;
        } else {
            return parent[n] = find(parent[n]);
        }
    }


    private static void output(int ans) {
        if (canConnect) {
            System.out.println(ans);
        } else {
            System.out.println(-1);
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
대표 노드 저장 배열 값을 자기 자신의 indx 로 초기화.
while (큐가 빌 때까지) {
    큐에서 에지 정보를 가져오기
    if 에제의 두 노드를 연결해도 사이클이 생기지 않으면(== 부모 노드가 다르면)
        union 연산
        에지의 가중치를 정답 변수에 더하기
}

사용한 에지가 노드 개수 - 1 만큼이면 가중치의 합을 결과로 출력.
아니면 -1 출력.


// 1-1. static 메서드 BFS
BFS(i, j)
    큐에 [i, j] 노드 넣기. 방문 체크. map 에 현재 섬의 값 저장해서 구분해주기.
    i, j 위치에서 네 방향을 탐색해서 1 개 섬의 영역을 저장.

    다음 위치 next(4개)에 대해 방문한 적이 없고 바다가 아니라면
        같은 섬이다. map 에 값을 섬의 값을 저장해서 구분해주기
        방문 체크.
        next 을 저장해두기, queue 에 넣다리 정보

// 2-1 static class Edge
Edge implements Comparable {
    n1, n2, weight,
    정렬 기준은 가중치의 오름차순.
}

// 3-1. union, find 함수
union(n1, n2){
    find(n1), find(n2) 을 비교
    대표 노드 끼지 연결.
}
find(n){ // 재귀형태
    if(n 이 대표노드면) 리턴
    아니면 return parent(n) = find(parent[n]);
}


 */