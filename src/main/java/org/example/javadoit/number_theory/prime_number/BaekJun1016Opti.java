package org.example.javadoit.number_theory.prime_number;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 1016 제곱 ㄴㄴ 수
 * 링크 - https://www.acmicpc.net/problem/1016
 * 등급 - gold 1
 * 특이사항 - Do it Java 알고리즘 책 문제 040
 */
public class BaekJun1016Opti {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long min = Long.parseLong(st.nextToken()); // 11
        long max = Long.parseLong(st.nextToken()); // 150
        int last = (int) Math.sqrt(max);
        int res = 0;

        int[] pNArr = new int[last + 1];
        List<Integer> pNL = new ArrayList<>();

        int numCnt = (int) (max - min + 1); // 150 - 11 + 1 = 140
        boolean[] check = new boolean[numCnt];

        for (int i = 2; i <= last; i++) {
            pNArr[i] = i;
        }

        for (int i = 2; i <= last; i++) {
            int cur = pNArr[i];
            if (cur != 0) {
                pNL.add(cur);
                int j = 2;
                while (i * j <= last) {
                    pNArr[i * j] = 0;
                    j++;
                }
            }
        }

        for (long pNum : pNL) {
            long pNum_2 = pNum * pNum;
            long sIndex = min / pNum_2;
            if (min % pNum_2 > 0) {
                sIndex++;
            }
            for (long j = sIndex; pNum_2 * j <= max; j++) {
                check[(int) ((j * pNum_2) - min)] = true;
            }
        }

        for (int i = 0; i < numCnt; i++) {
            if (!check[i]) {
                res++;
            }
        }
        System.out.println(res);
    }

}

