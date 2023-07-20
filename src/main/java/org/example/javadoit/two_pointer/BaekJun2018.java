package org.example.javadoit.two_pointer;

import java.util.Scanner;

/**
 * 문제 - 백준 2018 수들의 합 5
 * 링크 - https://www.acmicpc.net/problem/2018
 * 등급 -  Silver 5
 * 특이사항 - Do it Java 알고리즘 책 문제 006. 56page
 */
public class BaekJun2018 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 정수 N 입력
        int N = scanner.nextInt();

        // 사용 변수 초기화
        int start_index = 1;
        int end_index = 1;
        int count = 1;
        int sum = 1;

        while (end_index < N) {
            if (sum > N) {
                sum = sum - start_index;
                start_index++;
            } else if (sum < N) {
                end_index++;
                sum = sum + end_index;
            } else {
                count++;
                end_index++;
                sum = sum + end_index;
            }
        }
        System.out.println(count);

    }

}
