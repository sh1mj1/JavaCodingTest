package org.example.javadoit.sort.bubble;

import java.util.Scanner;



public class BaekJun2750 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // N: 정렬할 수 개수 , A: 배열
        int N = scanner.nextInt();
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = scanner.nextInt();
        }

        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N - 1 - i; j++) {
                if (A[j] > A[j + 1]) {
                    int temp = A[j];
                    A[j] = A[j + 1];
                    A[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            System.out.println(A[i]);
        }

    }
}


/*
슈도 코드

N (정렬할 수 개수)
A (정렬할 배열 선언)

for (i: 0 ~ N-1)
    for (j: - ~ N-1-i)
        현재 배열의 값보다 1칸 오른쪽 배열의 값이 더 작으면 두 수 바꾸기

A 배열 출력

 */

/*
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 정렬할 개수

        int[] A = new int[N]; // 정렬할 수 배열

        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt(); // 배열 초기화
        }

        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N - 1 -i; j++) {
                if (A[j] > A[j + 1]) {
                    int temp = A[j];
                    A[j] = A[j + 1];
                    A[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            System.out.println(A[i]);
        }

    }
 */