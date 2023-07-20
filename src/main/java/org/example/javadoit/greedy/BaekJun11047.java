package org.example.javadoit.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 11047
 * 링크 - https://www.acmicpc.net/problem/11047
 * 등급 -  silver 4
 * 특이사항 - Do it Java 알고리즘 책 문제 032
 */
public class BaekJun11047 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
        int index = N - 1;
        int cnt = 0;

        while (K != 0) {
            if (K < coins[index]) {
                index--;
            } else {
                cnt += (K / coins[index]);
                K = K % coins[index];
            }
        }
        System.out.println(cnt);
    }
}

/*
슈도코드

N: 동전 종류, K: 주어진 값.
배열 coins: 동전 종류

index: coins 의 마지막 인덱스 = N -1
cnt: 동전 개수 = 0

while(K 가 0이 될 때까지){
    coin 은 coins[index]
    if(K < coin){ index--;}
    else { // K >= coin
       cnt += (K / coin)
       K = K % coin
       index--;
   }
}
sout(cnt)


 */
