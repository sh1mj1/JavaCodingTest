package org.example.javadoit.search.binary;

import java.util.Scanner;

/**
 * 문제 - 백준 2343 기타 레슨
 * 링크 - https://www.acmicpc.net/problem/2443
 * 등급 -  silver 1
 * 특이사항 - Do it Java 알고리즘 책 문제 030 186page
 */
public class BaekJun2343 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // N: 레슨 개수, M: 블루 레이개수
        // A: 기타 레슨 길이 저장 배열
        // start: 이진 탐색 시작 인덱스
        // end: 이진 탐색 종료 인덱스

        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int[] A = new int[N];
        int start = 0;
        int end = 0;

        // start 에 최대 길이의 기타 레슨 저장.
        // end 에 모든 레슨의 총합 저장.
        for (int i = 0; i < N; i++) {
            A[i] = scanner.nextInt();
            if (start < A[i]) {
                start = A[i];
                end += A[i];
            }
        }

        while (start <= end) {
            int mid = (start + end) / 2; // 추정되는 블루레이의 길이의 최소
            int sum = 0;
            int count = 0;

            // mid 값으로 모든 레슨을 저장할 수 있는지 확인
            for (int i = 0; i < N; i++) {
                if (sum + A[i] > mid) {
                    count++;
                    sum = 0;
                }
                sum += A[i];
            }
            // 탐색 후 마지막 블루레이도 카운트 해주어야 함.
            if (sum != 0) {
                count++;
            }

            if (count > M) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        System.out.println(start);
    }

}


/*
N: 레슨 개수, M: 블루레이 개수
A: 기타 레슨 데이터 저장 배열
start: 이진 탐색 시작 인덱스
end: 이진 탐색 종료 인덱스

for(N){
    A 배열 저장
    시작 인덱스 저장(A 배열 중 최댓값)
    종료 인덱스 저장(A 배열 요소의 총합)
}

while(시작 인덱스 <= 종료 인덱스){
    midIndex: 중간 인덱스 - 추정되는 블루레이의 최소값
    sum: 레슨 합
    count: 현재 사용한 블루레이 개수
    for(N){
        만약 sum + 현재 레슨 시간 > 중간 인덱스 이면
        count 을 올리고 sum 을 0 으로 리셋 (현재 블루레이 길이 저장할 수 없으므로 새 블루레이로 교체한다는 의미)
        sum += 현재 레슨 시간값
    }
    sum 이 0이 아니면 마지막 블루레이가 필요하므로 count 값 올리기
    if(count > M: 중간 인덱스 값으로 모든 레슨 저장 불가능) 시작 인덱스 = 중앙 인덱스 + 1
    else(중간 인덱스 값으로 모든 레슨 저장 가능) 종료 인덱스 = 중앙 인덱스 - 1

 */