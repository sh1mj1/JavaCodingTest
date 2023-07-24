package org.example.javadoit.number_theory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 1016 제곱 ㄴㄴ 수
 * 링크 - https://www.acmicpc.net/problem/1016
 * 등급 - gold 1
 * 특이사항 - Do it Java 알고리즘 책 문제 040
 */
public class BaekJun1016Book {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long min = Long.parseLong(st.nextToken());
        long max = Long.parseLong(st.nextToken());
        int numCnt = (int) (max - min + 1);
        int last = (int) Math.sqrt(max);

        // 최대값과 최소값의 차이만큼 배열 선언
        boolean[] check = new boolean[numCnt];

        // 2의 제곱 수인 4 부터 Max 보다 작거나 같은 값까지 반복
        for (long i = 2; i <= last; i++) {
            long pow = i * i;
            long sIndex = min / pow;
            if (min % pow != 0) {
                sIndex++; // 나머지가 있다면 1을 더해야 min 보다 큰 제곱수에서 시작됨.
            }
            for (long j = sIndex; pow * j <= max; j++) {
                check[(int) ((j * pow) - min)] = true; // 제곱수를 true 로
            }
        }
        int cnt = 0;
        for (int i = 0; i < numCnt; i++) {
            if (!check[i]) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}