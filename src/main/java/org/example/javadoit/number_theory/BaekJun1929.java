package org.example.javadoit.number_theory;

import java.util.Scanner;

/**
 * 문제 - 백준 1929 소수 구하기
 * 링크 - https://www.acmicpc.net/problem/1929
 * 등급 -  silver 3
 * 특이사항 - Do it Java 알고리즘 책 문제 037
 */
public class BaekJun1929 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // M: 시작 수, N: 종료 수, A: 소수 배열 1 ~ N
        int M = scanner.nextInt();
        int N = scanner.nextInt();
        int[] A = new int[N + 1];
        for (int i = 2; i < N; i++) {
            A[i] = i;
        }

        // 제곱급까지만 수행
        for (int i = 2; i <= Math.sqrt(N); i++) {
            if (A[i] == 0) {
                continue;
            }
            for (int j = i + i; j <= N; j = j + i) {
                A[j] = 0;
            }
        }

        for (int i = M; i <= N; i++) {
            if (A[i] != 0) {
                System.out.println(A[i]);
            }
        }


    }

}

/*
슈도코드

M: 시작수, N: 종료수
A: 소수 배열
for(N){
    A 배열 초기화
}
for(N 제곱근){
    소수가 아니면 넘어감.
    for(소수의 배수 값을 N 까지 반복){
        이 수가 소수가 아니라는 것을 표시
    }
}

for(M ~ N) A 배열에서 소수인 값 출력
 */