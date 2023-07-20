package org.example.javadoit.search.binary;

import java.util.Scanner;

/**
 * 문제 - 백준 1300 K 번째 수
 * 링크 - https://www.acmicpc.net/problem/1300
 * 등급 -  gold 2
 * 특이사항 - Do it Java 알고리즘 책 문제 031 190page
 */
public class BaekJun1300 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // N: 배열의 크기, K: 구하고자 하는 index
        int N = scanner.nextInt();
        int K = scanner.nextInt();

        long start = 1, end = K;
        long ans = 0;

        // 이진 탐색
        while (start <= end) {
            long mid = (start + end) / 2;
            long cnt = 0;

            // 중앙값보다 작거나 같은 수는 몇개인지 계산
            for (int i = 1; i <= N; i++) { // i 는 2차원 배열에서 첫열의 값들.
                cnt += Math.min(mid / i, N);
            }

            if (cnt < K) {
                start = mid + 1;
            } else {
                ans = mid;
                end = mid -1;
            }
        }
        System.out.println(ans);
    }


}

/*

N: 배열의 크기, K: 구하고자 하는 index
start: 시작 인덱스 1
end: 종료 인덱스 K

while(시작 인덱스 <= 종료 인덱스){
    mid: 중간 인덱스
    cnt: 중앙값보다 작거나 같은 수

    // 중앙값보다 작거나 같은 수는 몇개인지 계산.
    for(N){
        cnt 에 중앙 인덱스를 i 로 나눈 값과 N 중 작은 값을 더함
    }

    if(cnt < K: 현재 중앙값보다 작거나 같은 수의 개수가 K 보다 작음){
        시작 인덱스 = 중앙 인덱스 + 1
    else(현재 중앙값보다 작거나 같은 수의 개수가 K 보다 크거나 같음){
        종료 인ㄷ게스 = 중앙 인덱스 - 1
        정답 변수에 중앙값 저장
    }
정답 출력
 */