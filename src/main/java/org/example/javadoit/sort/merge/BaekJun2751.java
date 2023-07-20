package org.example.javadoit.sort.merge;

import java.io.*;

/**
 * 문제 - 백준 2751 수 정렬하기2
 * 링크 - https://www.acmicpc.net/problem/2751
 * 등급 -  silver 5
 * 특이사항 - Do it Java 알고리즘 책 문제 020 128page
 */
public class BaekJun2751 {
    public static int[] A, tmp;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        // N: 정렬할 개수, A: 정렬할 배열, tmp: 정렬할 때 잠시 사용할 임시 배열
        int N = Integer.parseInt(bufferedReader.readLine());
        A = new int[N + 1];
        tmp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(bufferedReader.readLine());
        }

        merge_sort(1, N);
        for (int i = 1; i <= N; i++) {
            bufferedWriter.write(A[i] + "\n");
        }

        bufferedReader.close();
        bufferedWriter.flush();
        bufferedWriter.close();

    }

    // s: 시작점, e: 종료점, m: 중간점
    private static void merge_sort(int s, int e) {
        // 데이터가 하나이면
        if (e - s < 1) {
            return;
        }

        int m = (e + s) / 2;

        // 재귀 함수 형태로 구현
        merge_sort(s, m);
        merge_sort(m + 1, e);
        for (int i = s; i <= e; i++) {
            tmp[i] = A[i];
        }

        int k = s;
        int index1 = s;
        int index2 = m + 1;

        // 두 그룹 병합하는 로직
        while (index1 <= m && index2 <= e) {
            // 양쪽 그룹의 index 가 가리키는 값을 비교해 더 작은 수를 선택해 배열에 저장,
            // 선택된 데이터의 index 값을 오른쪽으로 한 칸 이동.
            if (tmp[index1] > tmp[index2]) {
                A[k] = tmp[index2];
                k++;
                index2++;
            } else {
                A[k] = tmp[index1];
                k++;
                index1++;
            }

        }

        // 한쪽 그룹이 모두 선택된 후 남아있는 값 정리
        while (index1 <= m) {
            A[k] = tmp[index1];
            k++;
            index1++;
        }
        while (index2 <= e) {
            A[k] = tmp[index2];
            k++;
            index2++;
        }

    }


}


/*
슈도 코드
N: 정렬할 개수, A: 정렬할 배열
tmp: 정렬할 때 잠시 사용할 임시 배열
for(N){
    A 배열 초기화
}

병합 정렬 함수 수행
결과값 출력

병합정렬(s,e){
    s: 시작점, e: 종료점, m: 중간점
    // 재귀함수 형태로 구현
    병합 정렬(s, m)
    병합 정렬(m+1, e)
    for(s ~ e){
    tmp 배열 저장
    }

    // 두 그룹을 병합하는 로직
    index1 -> 앞쪽 그룹 시작점
    index2 -> 뒤쪽 그룹 시작점
    while(index1 <= 중간점 && index2 <= 종료점){
        앞쪽 그룹의 index 가 가리키는 값을 비교한 후 더 작은 수를 선택해 배열에 저장,
        선택된 데이터의 index 값을 오른쪽으로 한 칸 이동
        반복문이 끝난 후 남아있는 데이터 정리
    }

 */