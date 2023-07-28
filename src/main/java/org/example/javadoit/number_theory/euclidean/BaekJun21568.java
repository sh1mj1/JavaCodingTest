package org.example.javadoit.number_theory.euclidean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 21568 Ax + By = C
 * 링크 - https://www.acmicpc.net/problem/21568
 * 등급 - ?
 * 특이사항 - Do it Java 알고리즘 책 문제 045
 */
public class BaekJun21568 {

    static Stack<Long> quotientStack;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long C = Long.parseLong(st.nextToken());
        long K;

        long gcdVal = gcd(A, B);
        if (C % gcdVal == 0) {
            K = C / gcdVal;
            quotientStack = new Stack<>();
            stackGcd(A, B);

            long tempX = 1;
            long tempY = 0;
            long x = 0;
            long y = 0;
            while (!quotientStack.isEmpty()) {
                long q = quotientStack.pop();
                x = tempY;
                y = tempX - (tempY * q);
                tempX = x;
                tempY = y;
            }
            System.out.println(K * x + " " + K * y);
        } else {
            System.out.println(-1);
        }
    }

    static long gcd(long A, long B) {
        if (B == 0) {
            return A;
        } else {
            return gcd(B, A % B);
        }
    }

    static long stackGcd(long A, long B) {
        while (B != 0) {
            long m = A % B;
            quotientStack.push(A / B);
            A = B;
            B = m;
        }
        if (B == 0) {
            return A;
        } else {
            return stackGcd(B, A % B);
        }
    }

}

/* 슈도코드

A: x의 계수, B: y의 계수, C: 우변 정수
// Ax + By = C
gcdVal: A 와 B 의 최대 공약수 저장
gcdVal = gcd(A, B)
K: 몇 배인지
quotientStack: 몫 스택

if(C 가 gcdVal 의 배수가 아니면) -> -1 출력,
else{
    K = 몇배인지 저장

    먼저 나머지가 0 일 때까지 유클리드 호제법 실행
    quotientGcd(A, B)

    tempX = 1, tempY = 0
    x = 0, y = 0
    while(스택이 빌 때까지){
        q = 스택.pop
        x = tempY
        y = tempX - (tempY * q)
        tempX = x;
        tempY = y;
    }
    K * x 와 K * y 출력.

}




// gcd func
quotientGcd(A, B){
    if(B == 0) -> return B;
    else -> gcd(B, A % B)
}

quotientGcd(A, B){
    quotientStack 스택에 A/B 몫을 추가.
    if(B == 0) -> return B;
    else {
        gcd(B, A % B)
    }
}



 */