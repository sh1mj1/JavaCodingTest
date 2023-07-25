package org.example.javadoit.number_theory.prime_number;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 1456
 * 링크 - https://www.acmicpc.net/problem/1456
 * 등급 -  gold 5
 * 특이사항 - Do it Java 알고리즘 책 문제 038
 */
public class BaekJun1456 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long[] pnA = new long[10000001];

        for (int i = 2; i < pnA.length; i++) {
            pnA[i] = i;
        }

        for (int i = 2; i <= Math.sqrt(pnA.length); i++) {
            if (pnA[i] == 0) {
                continue;
            }
            for (int j = i + i; j < pnA.length; j = j + i) {
                pnA[j] = 0;
            }
        }

        int cnt = 0;
        for (int i = 2; i < 10000001; i++) {
            if (pnA[i] != 0) {
                long temp = pnA[i];
                while ((double) pnA[i] <= (double) B / (double) temp) {
                    if ((double) pnA[i] >= (double) A / (double) temp) {
                        cnt++;
                    }
                    temp = temp * pnA[i];
                }
            }
        }
        System.out.println(cnt);
    }
}