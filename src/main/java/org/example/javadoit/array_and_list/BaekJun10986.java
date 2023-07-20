package org.example.javadoit.array_and_list;

import java.util.Scanner;

/**
 * 문제 - 백준 10986 나머지 합
 * 링크 - https://www.acmicpc.net/problem/10986
 * 등급 -  Gold 3
 * 특이사항 - Do it Java 알고리즘 책 문제 005 52page
 */
public class BaekJun10986 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // N: 수열 수, M: 나누는 수
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        // S: 합 배열, C: 나머지 결과 배열
        long[] S = new long[N];
        long[] C = new long[M];
        S[0] = scanner.nextInt();

        long answer = 0;

        // S 초기화
        for (int i = 1; i < N; i++) {
            S[i] = S[i - 1] + scanner.nextInt();
        }

        for (int i = 0; i < N; i++) {
            long remainder = S[i] % M;

            // 나머지가 0 인 값 == 수열 0 부터 i 까지의 합이 나머지가 0
            if (remainder == 0) answer++;
            // C 에 나머지 결과를 넣어줌. C 초기화
            C[(int) remainder]++;
        }

        for (long temp : C) {
            if (temp > 1) answer += (temp * (temp - 1)) / 2;
        }

        System.out.println(answer);
    }
}