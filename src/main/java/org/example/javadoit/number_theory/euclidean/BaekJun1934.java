package org.example.javadoit.number_theory.euclidean;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 1934 최소공배수
 * 링크 - https://www.acmicpc.net/problem/1934
 * 등급 - Bronze 1
 * 특이사항 - Do it Java 알고리즘 책 문제 042
 */
public class BaekJun1934 {

    static int A;
    static int B;
    static int x;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            swap();

            gcd(A, B);
            int res = A * B / x;
            sb.append(res).append("\n");
        }
        br.close();
        System.out.println(sb);

    }

    static void gcd(int A, int B) {
        int temp = A % B;
        if (temp == 0) {
            x = B;
            return;
        }
        A = B;
        B = temp;
        gcd(A, B);
    }

    private static void swap() {
        if (A < B) {
            int temp = A;
            A = B;
            B = temp;
        }
    }
}


/*
슈도코드
T: 테스트 케이스 개수
for(T){
    A: 큰 수
    B: 작은 수 // 순서가 다르면 바꾸기
    최대공약수 x = gcd(A, B)
    결과 res = A * B / x
    res 출력
}

// gcd 메소드
gcd(int A, int B){
    temp = A % B
    if(temp == 0){ // A 가 B 로 나누어 떨어지면
        결과는 B
        break;
    }
    A = B;
    B = temp;
    gcd(A, B)
}





 */