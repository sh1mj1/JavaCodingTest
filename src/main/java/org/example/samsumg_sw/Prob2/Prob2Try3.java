package org.example.samsumg_sw.Prob2;

import java.util.*;

/*
07.12 16:41 PM
이전에서 처음 grid 을 초기화할 때
rowMaxSet, colMaxSet
query 을 받을 때마다 rowMaxSet, colMaxSet 에 삭제와 추가.
그리고 두 집합을 뺏을 때의 줄어든 값만큼 safeCell 임.

(오답 : 29개의 테스트케이스 중 21개가 맞았습니다.) - 시간초과
 */
public class Prob2Try3 {
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
            // Set 만들기
            Collection<Integer> rowMaxValues = rowMax.values();
            Set<Integer> rowMaxSet = new HashSet<>(rowMaxValues);
            Collection<Integer> colMaxValues = colMax.values();
            Set<Integer> colMaxSet = new HashSet<>(colMaxValues);

            long safeCells = 0;

            for (int q = 0; q < Q; q++) {
                int r = scanner.nextInt();
                int c = scanner.nextInt();
                int x = scanner.nextInt();
                r--; // 0 에 맞춘 인덱스로
                c--;

                // 그리드, 행/열 최대값 갱신
                grid[r][c] = x;
                int originRowValue = rowMax.getOrDefault(r, 0);
                int originColValue = colMax.getOrDefault(c, 0);
                int newRowValue = Math.max(originRowValue, x);
                int newColValue = Math.max(originColValue, x);

                rowMax.put(r, newRowValue);
                colMax.put(c, newColValue);

                rowMaxSet.remove(originRowValue);
                colMaxSet.remove(originColValue);

                rowMaxSet.add(newRowValue);
                colMaxSet.add(newColValue);

                Set<Integer> tempSet = new HashSet<>(rowMaxSet);
                int originSetCnt = tempSet.size();
                tempSet.removeAll(colMaxSet);
                int temp = tempSet.size();
                int cnt = originSetCnt - temp;
                safeCells += cnt;

            }

            System.out.println("#" + t + " " + safeCells);
        }
    }
}
