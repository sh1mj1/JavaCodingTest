package org.example.javadoit.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 1931 회의실 배정
 * 링크 - https://www.acmicpc.net/problem/1931
 * 등급 -  silver 1
 * 특이사항 - Do it Java 알고리즘 책 문제 035
 */
public class BaekJun1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        int A[][] = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            A[i][0] = Integer.parseInt(st.nextToken());
            A[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o1[0] - o2[0]; // 종료시간이 같으면 시작 시간 기준 오름차순 (시작 시간 빠른 순)
            }
            return o1[1] - o2[1]; // 종료 시간 기준 오름차순 (종료 시간 빠른 순)
        });

        int result = 0;
        int beforeEnd = -1;
        for (int i = 0; i < N; i++) {
            if (A[i][0] >= beforeEnd) {
                beforeEnd = A[i][1];
                result++;
            }
        }
        System.out.println(result);
    }
}

/*
슈도코드

N: 회의 개수, A: 회의 정보
A 배열 정렬 수행. 종료 시간 기준 오름차순. 만약 종료 시간이 같다면 시간 기준 정렬

for(회의 개수만큼 반복){
    if(앞 회의의 종료 시간보다 시작 시간이 빠르거나 같은 회의가 나오면){
        현재 회의 종료 시간으로 종료 시간 업데이트
        정답(회의 수)++
    }
}
정답(회의 수) 출력.

 */
