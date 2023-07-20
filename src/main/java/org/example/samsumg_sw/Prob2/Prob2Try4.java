package org.example.samsumg_sw.Prob2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
07.12 18:56:09
이전 코드에서 rowMax 와 colMax 을 기본 array 로 해보자.
그리고 set 에 중복된 개수 세는 것 변경
BufferedReader 로 변경
(오답 : 29개의 테스트케이스 중 23개가 맞았습니다.)

제한시간 초과가 발생하였습니다. 제한시간 초과로 전체 혹은 일부 테스트 케이스는 채점이 되지 않을 수 있습니다.
*/
public class Prob2Try4 {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        int TC = Integer.parseInt(bufferedReader.readLine());
        for (int t = 1; t <= TC; t++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            // N: 행, M: 열, Q: 질의
            int N = Integer.parseInt(stringTokenizer.nextToken());
            int M = Integer.parseInt(stringTokenizer.nextToken());
            int Q = Integer.parseInt(stringTokenizer.nextToken());

            int[][] grid = new int[N][M];
            int[] rowMaxArr = new int[N];
            int[] colMaxArr = new int[M];

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

            long safeCells = 0;

            for (int q = 0; q < Q; q++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                int r = Integer.parseInt(stringTokenizer.nextToken());
                int c = Integer.parseInt(stringTokenizer.nextToken());
                int x = Integer.parseInt(stringTokenizer.nextToken());
                r--; // 0 에 맞춘 인덱스로
                c--;

                // 그리드, 행/열 최대값 갱신
                grid[r][c] = x;
                int originRowValue = rowMaxArr[r];
                int originColValue = colMaxArr[c];
                int newRowValue = Math.max(originRowValue, x);
                int newColValue = Math.max(originColValue, x);

                rowMaxArr[r] = newRowValue;
                colMaxArr[c] = newColValue;

                rowMaxSet.remove(originRowValue);
                colMaxSet.remove(originColValue);

                rowMaxSet.add(newRowValue);
                colMaxSet.add(newColValue);

                // 중복된 원소 세기
                int cnt = 0;
                for (int num : rowMaxSet) {
                    if (colMaxSet.contains(num)) {
                        cnt++;
                    }
                }

                safeCells += cnt;

            }

            System.out.println("#" + t + " " + safeCells);
        }
    }
}



/*
// 변경된 셀이 원래도 safeArea 였다면 그대로
                if (rowMaxArr[r] == prevValue && colMaxArr[c] == prevValue) {
                    rowMaxArr[r] = grid[r][c];
                    colMaxArr[c] = grid[r][c];

                }else if (rowMaxArr[r] == prevValue){
                    // 변경된 셀이 원래 행의 최대였을 때 위, 아래로만 탐색
                    rowMaxArr[r] = grid[r][c];
                    if (r > 0) {
                        upDFS(r - 1, c, grid[r - 1][c]);
                    }
                    if (r < N - 1) {
                        downDFS(r + 1, c, grid[r + 1][c]);
                    }

                } else if (colMaxArr[c] == prevValue) {
                    // 변경된 셀이 원래 열의 최대였을 때 왼/오른쪽으로만 탐색
                    if (c > 0) {
                        leftDFS(r, c - 1, grid[r][c - 1]);
                    }
                    if (c < M - 1) {
                        rightDFS(r, c + 1, grid[r][c - 1]);
                    }
                }
 */