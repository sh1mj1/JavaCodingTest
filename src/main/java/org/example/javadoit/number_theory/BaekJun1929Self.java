package org.example.javadoit.number_theory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJun1929Self {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int last = (int) (Math.sqrt(N));
        int[] pN = new int[N + 1];

        for (int i = 2; i <= N; i++) {
            pN[i] = i;
        }

        for (int i = 2; i <= last; i++) {
            int curNum = pN[i];
            if (curNum != 0) {
                int j = 2;
                while (curNum * j <= N) {
                    pN[curNum * j] = 0;
                    j++;
                }
            }
        }

        for (int i = M; i <= N; i++) {
            if (pN[i] != 0) {
                System.out.println(pN[i]);
            }
        }
    }
}

/*
M: 시작 범위, N: 종료 범위
pN: 크기가 N+1 인 배열 0, 1, 2, 3, ...  N
인덱스가 i 일 때 pn{i] = i

for(현재 수는 2부터 시작)
    만약 현재수가 0 이 아니면
         인덱스 를 현재수의 배수로 증가시켜 가면서
         그에 해당하는 수를 배열에서 0으로 바꿈.

pN 의 M ~ N 사이이 모든 수를 탐색해가면서
0 이 아니면 출력한다.
 */