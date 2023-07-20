package org.example.samsumg_sw.Prob2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
07.12 23:50 PM
New Idea
*/
public class Prob2Try6NewIdes {

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

            int tempResult = 0;
            for (int num : rowMaxSet) {
                if (colMaxSet.contains(num)) {
                    tempResult++;
                }
            }

            for (int q = 0; q < Q; q++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                int r = Integer.parseInt(stringTokenizer.nextToken());
                int c = Integer.parseInt(stringTokenizer.nextToken());
                int x = Integer.parseInt(stringTokenizer.nextToken());
                r--; // 0 에 맞춘 인덱스로
                c--;

                int prevVal = grid[r][c]; // 5
                grid[r][c] = x; // 10

                int nowRowMax = rowMaxArr[r];
                int nowColMax = colMaxArr[c];

                // 타겟셀이 이미 safe 임.
                if (rowMaxSet.contains(prevVal) && colMaxSet.contains(prevVal)) {
                    // 이 때는 갱신만 해주면 됨.
                    rowMaxArr[r] = x;
                    colMaxArr[c] = x;
                    rowMaxSet.remove(prevVal);
                    colMaxSet.remove(prevVal);
                    rowMaxSet.add(x);
                    colMaxSet.add(x);
                } else if (rowMaxSet.contains(nowRowMax) && colMaxSet.contains(nowRowMax) &&
                        rowMaxSet.contains(nowColMax) && colMaxSet.contains(nowColMax)) {
                    // 타겟 셀의 같은 행에 safe 가 있고, 타겟셀의 같은 열에 safe 가 있음

                    // 바뀐 값이 둘 다보다 크면
                    if (x > Math.max(nowRowMax, nowColMax)) {
                        rowMaxSet.remove(nowRowMax);
                        colMaxSet.remove(nowRowMax);
                        rowMaxSet.remove(nowColMax);
                        colMaxSet.remove(nowColMax);

                        rowMaxArr[r] = x;
                        colMaxArr[c] = x;

                        rowMaxSet.add(x);
                        colMaxSet.add(x);
                        if (tempResult > 0) tempResult--;
                    } else if (x > nowRowMax && x < nowColMax) {
                        // 바뀐 값이 같은 행보다 크고 같은 열보다 작다.
                        rowMaxSet.remove(nowRowMax);
                        rowMaxArr[r] = x;
                        rowMaxSet.add(x);
                        if (tempResult > 0) tempResult--;
                    } else if (x > nowColMax && x < nowRowMax) {
                        // 바뀐값이 같은 열보다 크고 같은 행보다 작다.
                        colMaxSet.remove(nowColMax);
                        colMaxArr[c] = x;
                        colMaxSet.add(x);
                        if (tempResult > 0) tempResult--;
                    }
                    // 바뀐 값이 둘 다보다 작다. 아무일도 일어나지 않음


                } else if (rowMaxSet.contains(nowRowMax) && colMaxSet.contains(nowRowMax)) {
                    // 타겟 셀의 같은 행에만 safe 가 있음.

                    // 열은 변경해주어야 함.
                    if (x > nowColMax) {
                        colMaxSet.remove(nowColMax);
                        colMaxArr[c] = x;
                        colMaxSet.add(x);
                    }
                    // 만약 그 safe 셀의 값이 타겟 셀의 변경된 값보다 크면
                    if (x > nowRowMax) {
                        rowMaxSet.remove(nowRowMax);
                        rowMaxArr[r] = x;
                        rowMaxSet.add(x);
                    }
                    // 만약 그 safe 셀의 값이 타겟 셀의 변경된 값보다 크면 아무것도 안함.


                } else if (colMaxSet.contains(nowColMax) && rowMaxSet.contains(nowColMax)) {
                    // 타겟 셀의 같은 열에만 safe 가 있음.

                    // 행은 변경해주어야 함.
                    if (x > nowRowMax) {
                        rowMaxSet.remove(nowRowMax);
                        rowMaxArr[r] = x;
                        rowMaxSet.add(x);
                    }
                    // 그 타겟의 변경된 값이 safe 의 값보다 크면
                    if (x > nowColMax) {
                        colMaxSet.remove(nowColMax);
                        colMaxArr[c] = x;
                        colMaxSet.add(x);
                    }
                    // 만약 그 safe 셀의 값이 타겟 셀의 변경된 값보다 크면 아무것도 안함.
                } else {
                    // 타겟 셀의 같은 열, 행에 safe 가 아무것도 없다.
                    if (x > nowRowMax && x > nowColMax) {
                        rowMaxSet.remove(nowRowMax);
                        rowMaxArr[r] = x;
                        rowMaxSet.add(x);
                        colMaxSet.remove(nowColMax);
                        colMaxArr[c] = x;
                        colMaxSet.add(x);
                        tempResult++;
                    } else if (x > nowRowMax) {
                        rowMaxSet.remove(nowRowMax);
                        rowMaxArr[r] = x;
                        rowMaxSet.add(x);
                    } else if (x > nowColMax) {
                        colMaxSet.remove(nowColMax);
                        colMaxArr[c] = x;
                        colMaxSet.add(x);
                    }

                }
                safeCells += tempResult;
            }

            System.out.println("#" + t + " " + safeCells);
        }
    }


}
