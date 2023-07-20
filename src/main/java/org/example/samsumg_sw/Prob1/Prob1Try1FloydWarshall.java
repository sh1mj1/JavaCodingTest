package org.example.samsumg_sw.Prob1;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 07/11 TUE 04:26 플로이드 워셜
 * 이대로 제출.(자바 제한 시간이 2초로 늘어서)
 */
public class Prob1Try1FloydWarshall {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int tcCnt = Integer.parseInt(bufferedReader.readLine()); // test case 수

        for (int cnt = 1; cnt <= tcCnt; cnt++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            // N: 노드 개수, M: 에지 개수,
            int N = Integer.parseInt(stringTokenizer.nextToken());
            int M = Integer.parseInt(stringTokenizer.nextToken());

            // 인접행렬 모두 큰값으로 초기화
            int[][] adjMatrix = new int[N + 1][N + 1];
            for (int i = 0; i < N + 1; i++) {
                Arrays.fill(adjMatrix[i], 399_999_999);
            }

            // 인접 행렬 초기값
            for (int i = 0; i < M; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                int X = Integer.parseInt(stringTokenizer.nextToken());
                int Y = Integer.parseInt(stringTokenizer.nextToken());
                int weight = Integer.parseInt(stringTokenizer.nextToken());

                adjMatrix[X][Y] = Math.min(adjMatrix[X][Y], weight);
            }

            // Floyd-warshall
            // m: 중간 노드, s: 출발노드, e: 도착노드
            for (int m = 1; m <= N; m++) {
                for (int s = 1; s <= N; s++) {
                    for (int e = 1; e <= N; e++) {
                        adjMatrix[s][e] = Math.min(adjMatrix[s][e], adjMatrix[s][m] + adjMatrix[m][e]);
                    }
                }
            }
            int min = 399_999_999;
            for (int i = 1; i <= N; i++) {
                min = Math.min(min, adjMatrix[i][i]);
            }

            if (min < 399_999_999) { // 사이클이 있으면
                bufferedWriter.write("#" + cnt + " " + min + "\n");
            } else {
                bufferedWriter.write("#" + cnt + " " + "-1" + "\n");
            }
        }
        bufferedReader.close();

        bufferedWriter.flush();
        bufferedWriter.close();

    }

}
