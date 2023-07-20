package org.example.javadoit.search.binary;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 1920 수 찾기
 * 링크 - https://www.acmicpc.net/problem/1920
 * 등급 -  silver 4
 * 특이사항 - Do it Java 알고리즘 책 문제 029 182page
 */
public class BaekJun1920 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        // N: 정렬할 수의 개수, A: 정렬할 수 배열 M: 탐색할 수의 개수 targetArr: 탐색할 수 배열
        int N = Integer.parseInt(bufferedReader.readLine());
        int[] A = new int[N];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        int M = Integer.parseInt(bufferedReader.readLine());
        int[] targetArr = new int[M];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < M; i++) {
            targetArr[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        bufferedReader.close();

        // A 정렬
        Arrays.sort(A);

        for (int target : targetArr) {
            int startIndex = 0;
            int endIndex = N - 1;
            boolean find = false;
            // 이진 탐색
            while (startIndex <= endIndex) {
                int midIndex = (startIndex + endIndex) / 2;
                if (target == A[midIndex]) {
                    find = true;
                    break;
                } else if (target < A[midIndex]) {
                    endIndex = midIndex - 1;
                } else {
                    startIndex = midIndex + 1;
                }
            }
            if (find) {
                bufferedWriter.write("1\n");
            } else {
                bufferedWriter.write("0\n");
            }

        }
        bufferedWriter.flush();
        bufferedWriter.close();

    }

}

/*
슈도 코드
N: 정렬할 수의 개수, M: 탐색할 숫자의 개수
A: 정렬할 배열
for(N){ A 배열 저장}

A 배열 정렬.
for(M){
    target: 찾아야 하는 수

    // 이진 탐색 시작
    start: 시작 인덱스
    end: 종료 인덱스
    while(start <= end){
        mid: 중간 인덱스
        if(mid > target){ 종료 인덱스 = 중간 인덱스 - 1 }
        else if(mid < target){ 시작 인덱스 = 중간 인덱스 + 1 }
        else { 찾음. 종료}
    }

    if(찾음) 1 출력
    else 0 출력


 */