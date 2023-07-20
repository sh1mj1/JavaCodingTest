package org.example.javadoit.sort.bubble;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 문제 - 백준 1377
 * 링크 - https://www.acmicpc.net/problem/1377
 * 등급 -  Gold 2
 * 특이사항 - Do it Java 알고리즘 책 문제 016 104page
 */
public class BaekJun1377 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // N: 수 개수, A: 배열(index, 와 value 을 가짐)
        int N = Integer.parseInt(bufferedReader.readLine());
        IndexAndValue[] A = new IndexAndValue[N];
        for (int i = 0; i < N; i++) {
            A[i] = new IndexAndValue(i, Integer.parseInt(bufferedReader.readLine()));
        }

        // A 의 value 을 기준으로 오름차순 정렬
        Arrays.sort(A);

        int Max = 0; // index 가 왼쪽으로 가장 많이 이동한 값.
        for (int i = 0; i < N; i++) {
            // A[i].index 는 정렬 전 기존 인덱스, i 는 정렬 후 index
            if (Max < A[i].index - i) {
                Max = A[i].index - i;
            }
        }
        System.out.println(Max + 1);
    }

}

class IndexAndValue implements Comparable<IndexAndValue> {
    int index;
    int value;

    public IndexAndValue(int index, int value) {
        super();
        this.index = index;
        this.value = value;
    }

    @Override
    public int compareTo(IndexAndValue o) {
        return this.value - o.value;
    }
}
