package org.example.javadoit.number_theory.euclidean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 1850 최대공약수
 * 링크 - https://www.acmicpc.net/problem/1850
 * 등급 - Silver 1
 * 특이사항 - Do it Java 알고리즘 책 문제 043
 */
public class BaekJun1850 {

    static long A;
    static long B;
    static long digits;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        swap();

        gcd(A, B);
        br.close();
        for (int i = 0; i < digits; i++) {
            sb.append(1);
        }
        System.out.println(sb);
    }

    static void gcd(long A, long B) {
        long temp = A % B;
        if (temp == 0) {
            digits = B;
            return;
        }
        A = B;
        B = temp;
        gcd(A, B);
    }

    private static void swap() {
        if (A < B) {
            long temp = A;
            A = B;
            B = temp;
        }
    }
}


/*
슈도코드

A, B : 자연수
digits: A, B 의 최대공약수
gcd(A, B) // A,B 에 대해 유클리드 호제법

digits 만큼 1을 반복해서 붙여서 출력.

// gcd 메소드
gcd(A, B){
    temp = A % B
    나머지가 0 이면{
        digits = B;
        return;
    }
    아니면 나눈 수를 큰 수로 바꾸고
    나머지를 작은 수(나누는 수)로 바꿔서
    gcd(큰수, 작은수)
}

 */