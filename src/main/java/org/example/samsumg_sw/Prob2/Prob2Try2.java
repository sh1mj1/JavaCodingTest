package org.example.samsumg_sw.Prob2;

import java.util.*;

/*
07.12 16:00 PM
어차피 외계 곰팡이의 수는 다 다르므로
각 행의 안전한 셀의 곰팡이 수와
각 열의 안전한 셀의 곰팡이 수가 서로 같은 개수를 세면 됨
이를 구현하기 위해서 집합 사용.
(오답 : 29개의 테스트케이스 중 20개가 맞았습니다.) - 제한 시간 초과
 */
public class Prob2Try2 {
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
            // 이 때 200,000 횟수
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    grid[i][j] = scanner.nextInt();
                    rowMax.put(i, Math.max(rowMax.getOrDefault(i, 0), grid[i][j]));
                    colMax.put(j, Math.max(colMax.getOrDefault(j, 0), grid[i][j]));
                }
            }

            long safeCells = 0;

            for (int q = 0; q < Q; q++) {
                int r = scanner.nextInt();
                int c = scanner.nextInt();
                int x = scanner.nextInt();
                r--; // 0 에 맞춘 인덱스로
                c--;

                // 그리드, 행/열 최대값 갱신
                grid[r][c] = x;
                rowMax.put(r, Math.max(rowMax.getOrDefault(r, 0), x));
                colMax.put(c, Math.max(colMax.getOrDefault(c, 0), x));


                // 계산
                Collection<Integer> rowMaxValues = rowMax.values();
                Set<Integer> rowMaxSet = new HashSet<>(rowMaxValues);
                Collection<Integer> colMaxValues = colMax.values();
                Set<Integer> colMaxSet = new HashSet<>(colMaxValues);

                int originSafeRowMaxCnt = rowMaxSet.size();
                rowMaxSet.removeAll(colMaxSet);
                int temp = rowMaxSet.size();
                int cnt = originSafeRowMaxCnt - temp;

                safeCells += cnt;

            }

            System.out.println("#" + t + " " + safeCells);
        }
    }
}
