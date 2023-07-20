package org.example.samsumg_sw.Prob2;

import java.util.HashMap;
import java.util.Scanner;

/*
07/12 15:05 PM
29 개 중 2개 정답
시간 초과
 */
public class Prob2Try1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int TC = scanner.nextInt();

        for (int t = 1; t <= TC; t++) {
            // N: 행, M: 열, Q: 질의
            int N = scanner.nextInt();
            int M = scanner.nextInt();
            int Q = scanner.nextInt();

            int[][] grid = new int[N][M];
            HashMap<Integer, Integer> rowMax = new HashMap<>();
            HashMap<Integer, Integer> colMax = new HashMap<>();

            // 그리드에 값 할당하면서 행/열 최대값 갱신
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    grid[i][j] = scanner.nextInt();
                    rowMax.put(i, Math.max(rowMax.getOrDefault(i, 0), grid[i][j]));
                    colMax.put(j, Math.max(colMax.getOrDefault(j, 0), grid[i][j]));
                }
            }

            long safeCells = 0;

            for (int k = 0; k < Q; k++) {
                int r = scanner.nextInt();
                int c = scanner.nextInt();
                int x = scanner.nextInt();
                r--; // 0 에 맞춘 인덱스로
                c--;

                // 그리드, 행/열 최대값 갱신
                grid[r][c] = x;
                rowMax.put(r, Math.max(rowMax.getOrDefault(r, 0), x));
                colMax.put(c, Math.max(colMax.getOrDefault(c, 0), x));

                // 안전한 셀 세기
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        if (grid[i][j] == rowMax.get(i) || grid[i][j] == colMax.get(j)) {
                            safeCells++;
                        }
                    }
                }

            }

            System.out.println("#" + t + " " + safeCells);
        }
    }
}
