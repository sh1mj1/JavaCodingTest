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
public class BaekJun17472Self {
    static int N, M, islandNum;
    static int[] dRow = {-1, 0, 1, 0};
    static int[] dCol = {0, -1, 0, 1};
    static int[][] map;
    static boolean[][] visited;
    static ArrayList<RowAndCol> islandIndexList;
    static ArrayList<ArrayList<RowAndCol>> sumList;
    static ArrayList<RowAndCol> mList;
    static int[] parent;
    static int ans;
    static boolean canConnect;

    public static void main(String[] args) throws IOException {
        input();
        classifyIsland();
        PriorityQueue<Edge> pqueue = getEdges();
        mstFunc(pqueue);
        output();
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        islandIndexList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                map[i][j] = tmp;
                if (tmp == 1) {
                    islandIndexList.add(new RowAndCol(i, j));
                }
            }
        }
    }

    private static void classifyIsland() {
        islandNum = 0;
        Queue<RowAndCol> queue = new LinkedList();
        sumList = new ArrayList<>();
        for (RowAndCol island : islandIndexList) {
            mList = new ArrayList<>();
            if (!visited[island.row][island.col]) {
                queue.offer(island);
                visited[island.row][island.col] = true;
                islandNum++;
            }

            bfsFunc(queue);
            if (mList.size() > 0) {
                sumList.add(mList);
            }
        }
    }

    private static PriorityQueue<Edge> getEdges() {
        PriorityQueue<Edge> pqueue = new PriorityQueue<>();
        for (int i = 0; i < sumList.size(); i++) {
            ArrayList<RowAndCol> tmpList = sumList.get(i);
            int nowIslandNum = i + 1;
            for (RowAndCol tmp : tmpList) {
                for (int d = 0; d < 4; d++) {
                    int nextRow = tmp.row + dRow[d];
                    int nextCol = tmp.col + dCol[d];

                    if (isInMap(nextRow, nextCol)) {
                        if (map[nextRow][nextCol] == 0) {
                            int bridgeLen = 0;
                            while (true) {
                                nextRow = nextRow + dRow[d];
                                nextCol = nextCol + dCol[d];
                                bridgeLen++;
                                if (!isInMap(nextRow, nextCol) || (map[nextRow][nextCol] == nowIslandNum)) {
                                    break;
                                }

                                if (map[nextRow][nextCol] != 0) {
                                    if (bridgeLen > 1) {
                                        pqueue.offer(new Edge(nowIslandNum, map[nextRow][nextCol], bridgeLen));
                                        break;
                                    }
                                    break;
                                }
                            }
                        }


                    }
                }
            }
        }
        return pqueue;
    }

    private static void mstFunc(PriorityQueue<Edge> pqueue) {
        ans = 0;
        int edgeCnt = 0;
        canConnect = false;
        parent = new int[islandNum+1];
        for (int i = 1; i <= islandNum; i++) {
            parent[i] = i;
        }

        while (!pqueue.isEmpty()) {
            Edge curEdge = pqueue.poll();
            int n1 = find(curEdge.n1);
            int n2 = find(curEdge.n2);
            if (n1 != n2) {
                parent[n2] = n1;
                ans += curEdge.w;
                edgeCnt++;
            }
            if (edgeCnt == islandNum - 1) {
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

    private static boolean isInMap(int nextRow, int nextCol) {
        return nextRow >= 0 && nextCol >= 0 && nextRow < N && nextCol < M;
    }

    private static void bfsFunc(Queue<RowAndCol> queue) {
        while (!queue.isEmpty()) {
            RowAndCol nowIsland = queue.poll();
            mList.add(nowIsland);
            map[nowIsland.row][nowIsland.col] = islandNum;
            for (int d = 0; d < 4; d++) {
                int nextRow = nowIsland.row + dRow[d];
                int nextCol = nowIsland.col + dCol[d];
                if (isInMap(nextRow, nextCol)) {
                    if (!visited[nextRow][nextCol]) {
                        if (map[nextRow][nextCol] != 0) {
                            RowAndCol rowAndCol = new RowAndCol(nextRow, nextCol);
                            visited[nextRow][nextCol] = true;
                            queue.offer(rowAndCol);
                        }
                    }
                }
            }
        }
    }

    static int find(int n) {
        if (n == parent[n]) {
            return n;
        } else {
            return parent[n] = find(parent[n]);
        }
    }

    static class RowAndCol {
        int row, col;

        public RowAndCol(int row, int col) {
            this.row = row;
            this.col = col;
        }
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
