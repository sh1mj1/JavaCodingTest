package org.example.javadoit.array_and_list;

import java.util.Scanner;
/**
 * 문제 - 백준 1546 평균 구하기
 * 링크 - https://www.acmicpc.net/problem/1546
 * 등급 -  Bronze 1
 * 특이사항 - Do it Java 알고리즘 책 문제 002 38page
 */
public class BaekJun1546 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 과목 수
        int N = sc.nextInt();

        // 배열 A 에 각 점수 저장
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }

        // max 저장, sum 계산
        long max = 0;
        long sum = 0;
        for (int tempInt : A) {
            if (tempInt > max)
                max = tempInt;
            sum += tempInt;
        }
        System.out.println(100.0 * sum / N / max );
    }
}
