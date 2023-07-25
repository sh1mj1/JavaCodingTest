package org.example.javadoit.number_theory.euler_phi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 문제 - 백준 11689 GCDNK1
 * 링크 - https://www.acmicpc.net/problem/11689
 * 등급 - gold 1
 * 특이사항 - Do it Java 알고리즘 책 문제 041
 */
public class BaekJun11689 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        int last = (int) Math.sqrt(n);
        long res = n;

        for (int i = 2; i <= last; i++) {
            if (n % i == 0) {
                res = res - (res / i);
                while (n % i == 0) {
                    n = n / i;
                }
            }
        }
        if (n > 1) {
            res = res - (res / n);
        }
        System.out.println(res);
    }
}


/*
슈도코드

n: 소인수의 곱을 표현, 입력부터, res: 결과(처음에는 n)
for( 2 ~ 루트(n)까지){
    if(현재수 curN 이 n의 소인수이면){
        결과값 res = res - (res/curN)
        n 에서 나누어 떨어지지 않을 때까지 소인수인 curN 으로 나눈 값으로 n 을 갱신.
    }
}

if( n > 1){ // n 이 마지막 소인수일때
    res = res - (res / n)
}
res 출력

 */