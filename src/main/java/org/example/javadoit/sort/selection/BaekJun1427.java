package org.example.javadoit.sort.selection;

import java.util.Scanner;

/**
 * 문제 - 백준 1427
 * 링크 - https://www.acmicpc.net/problem/1427
 * 등급 -  silver 5
 * 특이사항 - Do it Java 알고리즘 책 문제 017 109page
 */
public class BaekJun1427 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // str: 입력받은 문자. A: 입력받은 문자로 int 배열 만들기
        String str = scanner.next();
        int[] A = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            A[i] = Integer.parseInt(str.substring(i, i + 1));
        }

        for (int i = 0; i < str.length(); i++) {
            int indexOfMax = i;
            for (int j = i+1; j < str.length(); j++) {
                // 최대값 인덱스 찾기
                if (A[indexOfMax] < A[j]) {
                    indexOfMax = j;
                }
            }

            if (i == indexOfMax) {
                continue;
            } else {
                int temp = A[i];
                A[i] = A[indexOfMax];
                A[indexOfMax] = temp;
            }
        }

        for (int item : A) {
            System.out.print(item);
        }


    }
}
