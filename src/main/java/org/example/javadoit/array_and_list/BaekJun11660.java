package org.example.javadoit.array_and_list;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 11660 구간 합 구하기 5
 * 링크 - https://www.acmicpc.net/problem/11660
 * 등급 -  Silver 1
 * 특이사항 - Do it Java 알고리즘 책 문제 004 47page
 */
public class BaekJun11660 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        // N: 수, M: 질의 수
        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());

        // 배열 선언, 초기화
        int[][] A = new int[N + 1][N + 1];
        for (int col = 1; col < N + 1; col++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int row = 1; row < N + 1; row++) {
                A[row][col] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        // 합 배열 선언, 초기화
        long[][] D = new long[N + 1][N + 1];
        for (int col = 1; col < N + 1; col++) {
            for (int row = 1; row < N + 1; row++) {
                D[row][col] = D[row - 1][col] + D[row][col - 1] - D[row - 1][col - 1] + A[row][col];
            }
        }

        // 질의 받고 결과 계산 출력
        for (int q = 0; q < M; q++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int x1 = Integer.parseInt(stringTokenizer.nextToken());
            int y1 = Integer.parseInt(stringTokenizer.nextToken());
            int x2 = Integer.parseInt(stringTokenizer.nextToken());
            int y2 = Integer.parseInt(stringTokenizer.nextToken());

            long result = D[x2][y2] - D[x1 - 1][y2] - D[x2][y1 - 1] + D[x1 - 1][y1 - 1];
            System.out.println(result);
        }

    }

}