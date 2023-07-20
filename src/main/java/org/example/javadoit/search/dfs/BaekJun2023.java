package org.example.javadoit.search.dfs;

import java.util.Scanner;

/**
 * 문제 - 백준 2023 신기한 소수
 * 링크 - https://www.acmicpc.net/problem/2023
 * 등급 -  gold 5
 * 특이사항 - Do it Java 알고리즘 책 문제 024 153page
 */
public class BaekJun2023 {
    static int N;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt();

        DFS(2, 1);
        DFS(3, 1);
        DFS(5, 1);
        DFS(7, 1);

    }

    private static void DFS(int number, int digit) {
        if (digit == N) {
            if (isPrime(number)) {
                System.out.println(number);
            }
            return;
        }

        for (int i = 1; i < 10; i++) {
            // 짝수면 탐색 중지
            if (i % 2 == 0) {
                continue;
            }
            if (isPrime(number * 10 + i)) {
                DFS(number * 10 + i, digit + 1);
            }
        }
    }

    private static boolean isPrime(int number) {
        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}



/*
슈도 코드

N: 자리수

DFS 실행

DFS 메소드(숫자, 자리수){
    if(자리수 == N){
        if(소수){
            소수 출력
        }
        탐색 종료
    }

    for(1 ~ 9 반복){
        if(뒤에 붙는 수가 홀수이면서 소수){
            DFS(현재 수 * 10 + i, 자리수 + 1) // 자리수를 1씩 늘리면서 DFS
        }
    }


소수 구하기 함수(){
    for( (2 ~ 현재 수 / 2) 반복){
        if(나머지가 0이면) return 소수가 아님
    }
    return 소수이다.
}

 */