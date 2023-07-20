package org.example.javadoit.two_pointer;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 문제 - 백준 1940 주몽
 * 링크 - https://www.acmicpc.net/problem/1940
 * 등급 -  Silver 4
 * 특이사항 - Do it Java 알고리즘 책 문제 007. 59page
 */
public class BaekJun1940 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // N: 재료 개수, M: 갑옷이 되는 값(재료의 합), count: 결과값
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int count = 0;

        // 재료 배열 초기화
        int[] ingredients = new int[N];
        for (int i = 0; i < N; i++) {
            ingredients[i] = scanner.nextInt();
        }

        Arrays.sort(ingredients);


        // 투 포인터 i, j
        int i = 0;
        int j = N-1;

        while (i >= j) {
            if (ingredients[i] + ingredients[j] > M) {
                j--;
            } else if (ingredients[i] + ingredients[j] < M) {
                i++;
            } else {
                i++;
                j--;
                count++;
            }
        }
        System.out.println(count);
    }
}
