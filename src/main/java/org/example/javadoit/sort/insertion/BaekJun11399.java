package org.example.javadoit.sort.insertion;

import java.util.Scanner;

/**
 * 문제 - 백준 11399 ATM
 * 링크 - https://www.acmicpc.net/problem/11399
 * 등급 -  silver 4
 * 특이사항 - Do it Java 알고리즘 책 문제 018 115page
 */
public class BaekJun11399 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // N: 사람 수, A: 걸리는 시간 배열, S: 합 배열
        int N = scanner.nextInt();
        int[] A = new int[N];
        int[] S = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = scanner.nextInt();
        }

        // selection sort
        for (int i = 1; i < N; i++) {
            int insert_point = i;
            int insert_value = A[i];

            // 삽입할 위치 insert_point 찾기
            for (int j = i - 1; j >= 0; j--) {
                if (A[j] < A[i]) {
                    insert_point = j + 1;
                    break;
                }

                if (j == 0) {
                    insert_point = 0;
                }
            }

            // 삽입을 위해 데이터들 한칸씩 이동
            for (int j = i; j > insert_point; j--) {
                A[j] = A[j - 1];
            }
            A[insert_point] = insert_value;
        }

        // 구간 합 배열.
        S[0] = A[0];
        for (int i = 1; i < N; i++) {
            S[i] = S[i - 1] + A[i];
        }

        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum = sum + S[i];
        }

        System.out.println(sum);

    }
}
