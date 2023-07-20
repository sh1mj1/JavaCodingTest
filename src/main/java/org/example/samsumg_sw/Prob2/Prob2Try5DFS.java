package org.example.samsumg_sw.Prob2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
07.12 20:40 PM
DFS 로 해보자
*/
public class Prob2Try5DFS {

    static int[][] grid;
    static int[] rowMaxArr;
    static int[] colMaxArr;

    static int N;
    static int M;
    static long safeCells;

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        int TC = Integer.parseInt(bufferedReader.readLine());
        for (int t = 1; t <= TC; t++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            // N: 행, M: 열, Q: 질의
            N = Integer.parseInt(stringTokenizer.nextToken());
            M = Integer.parseInt(stringTokenizer.nextToken());
            int Q = Integer.parseInt(stringTokenizer.nextToken());

            grid = new int[N][M];
            rowMaxArr = new int[N];
            colMaxArr = new int[M];

            // Set 만들기
            Set<Integer> rowMaxSet = new HashSet<>();
            Set<Integer> colMaxSet = new HashSet<>();

            // 그리드에 값 할당하면서 행/열 최대값 갱신
            // 이 때 200,000 횟수
            for (int i = 0; i < N; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                for (int j = 0; j < M; j++) {
                    grid[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                    rowMaxArr[i] = Math.max(rowMaxArr[i], grid[i][j]);
                    colMaxArr[j] = Math.max(colMaxArr[j], grid[i][j]);
                    if (i == N - 1) {
                        colMaxSet.add(colMaxArr[j]);
                    }
                }
                rowMaxSet.add(rowMaxArr[i]);
            }

            safeCells = 0;
            for (int num : rowMaxSet) {
                if (colMaxSet.contains(num)) {
                    safeCells++;
                }
            }

            for (int q = 0; q < Q; q++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                int r = Integer.parseInt(stringTokenizer.nextToken());
                int c = Integer.parseInt(stringTokenizer.nextToken());
                int x = Integer.parseInt(stringTokenizer.nextToken());
                r--; // 0 에 맞춘 인덱스로
                c--;

                int prevValue = grid[r][c];

                // 그리드
                grid[r][c] = x;

                // 변경된 셀이 원래도 safeArea 였다면 그대로
                if (rowMaxArr[r] == prevValue && colMaxArr[c] == prevValue) {
                    rowMaxArr[r] = grid[r][c];
                    colMaxArr[c] = grid[r][c];

                } else if (colMaxArr[c] == prevValue) {
                    // 변경된 셀이 원래 열의 최대였을 때 왼/오른쪽으로만 탐색
                    colMaxArr[c] = grid[r][c];

                    if (c > 0) {
                        if (!leftDFS(r, c - 1, x)) { // 왼쪽으로 재귀했는데 행의 최대값을 못찾으면 오른쪽으로
                            if (c < M - 1) {
                                rightDFS(r, c + 1, x);
                            }
                        }
                    }

                } else if (rowMaxArr[r] == prevValue) {
                    // 변경된 셀이 원래 행의 최대였을 때 위, 아래로만 탐색
                    rowMaxArr[r] = grid[r][c];

                    if (r > 0) {
                        if (!upDFS(r - 1, c, x)) { // 위쪽으로 재귀했는데 열의 최대값을 못찾으면 아래쪽으로
                            if (r < N - 1) {
                                downDFS(r + 1, c, x);
                            }
                        }
                    }
                }
//                else {
//
//                }


                int originRowValue = rowMaxArr[r];
                int originColValue = colMaxArr[c];
                int newRowValue = Math.max(originRowValue, x);
                int newColValue = Math.max(originColValue, x);

                rowMaxArr[r] = newRowValue;
                colMaxArr[c] = newColValue;

//                safeCells += cnt;

            }

            System.out.println("#" + t + " " + safeCells);
        }
    }

    private static void downDFS(int r, int c, int changedVal) {
        if (colMaxArr[c] == grid[r][c]) { // 열의 최대값
            if (changedVal > grid[r][c]) { // 바뀐값이 최대보다 큼.
                colMaxArr[c] = changedVal;
                if (rowMaxArr[r] == grid[r][c]) { // 타겟값이 기존에 rowMax & colMax
                    safeCells--;
                }
            }
            return;
        }
        if (r >= N - 1) {
            return;
        }
        downDFS(r, c + 1, changedVal);
    }

    private static boolean upDFS(int r, int c, int changedVal) {
        if (colMaxArr[c] == grid[r][c]) { // 열의 최대값임
            if (changedVal > grid[r][c]) { // 바뀐 값이 최대값보다 큼
                colMaxArr[c] = changedVal; // 바뀐값이 열의 최대값이 됨.
                if (rowMaxArr[r] == grid[r][c]) { //타겟값이 기존에 rowMax & colMax 였음.
                    safeCells--;
                }
            }
            return true; // 최대값 찾았으므로 아래쪽으로 안가도 됨.
        }

        if (r <= 0) { // 현재 더 위쪽으로 갈 수 없다면
            return false;
        }

        return upDFS(r - 1, c, changedVal);
    }


    private static boolean leftDFS(int r, int c, int changedVal) {
        if (rowMaxArr[r] == grid[r][c]) { // 행의 최대값임
            if (changedVal > grid[r][c]) { // 바뀐 값이 최대값보다 큼.
                rowMaxArr[r] = changedVal; // 바뀐값이 행의 최대값이 됨.
                if (colMaxArr[c] == grid[r][c]) { // 타겟값이 기존에 rowMax & colMax 였음.
                    safeCells--;
                }
            }
            return true; // 최대값 찾았으므로 오른쪽으로 안가도 됨.
        }

        if (c <= 0) { // 현재 더 왼쪽으로 갈 수 없으면
            return false;
        }

        return leftDFS(r, c - 1, changedVal);
    }

    private static void rightDFS(int r, int c, int changedVal) {
        if (rowMaxArr[r] == grid[r][c]) { // 행의 최대값임
            if (changedVal > grid[r][c]) { // 바뀐값이 최대값보다 큼
                rowMaxArr[r] = changedVal; // 바뀐값이 최대값이 됨.
                if (colMaxArr[c] == grid[r][c]) { // 타겟값이 기존에 rowMax & colMax 였음
                    safeCells--;
                }
            }
            return;
        }
        if (c >= M - 1) {
            return;
        }
        rightDFS(r, c + 1, grid[r][c + 1]);
    }

}
