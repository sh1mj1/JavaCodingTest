package org.example.javadoit.sort.merge;

import java.io.*;

/**
 * 문제 - 백준 2751 수 정렬하기2
 * 링크 - https://www.acmicpc.net/problem/2751
 * 등급 -  silver 5
 * 특이사항 - Do it Java 알고리즘 책 문제 020 128page
 */
public class BaekJun2751Self {
    static int[] ansArray;
    static int[] tempArray;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        // N: 수의 개수. ansArray: 정답 배열. tempArray: 임시 배열
        int N = Integer.parseInt(bufferedReader.readLine());
        ansArray = new int[N + 1];
        tempArray = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            ansArray[i] = Integer.parseInt(bufferedReader.readLine());
        }

        // 병합 정렬 함수
        mergeSort(1, N);

        for (int i = 1; i <= N; i++) {
            bufferedWriter.write(ansArray[i] + "\n");
        }
        bufferedReader.close();
        bufferedWriter.flush();
        bufferedWriter.close();

    }

    private static void mergeSort(int s, int e) {
        if (e == s) {
            return;
        }

        int m = (e + s) / 2;

        mergeSort(s, m);
        mergeSort(m + 1, e);

        for (int i = s; i <= e; i++) {
            tempArray[i] = ansArray[i];
        }

        int index1 = s;
        int k = s;
        int index2 = m + 1;

        while (index1 <= m && index2 <= e) {
            if (tempArray[index1] > tempArray[index2]) {
                ansArray[k] = tempArray[index2];
                k++;
                index2++;
            }
            else {
                ansArray[k] = tempArray[index1];
                k++;
                index1++;
            }

        }

        // 선택되지 않은 그룹 정리하기
        while (index1 <= m) {
            ansArray[k] = tempArray[index1];
            k++;
            index1++;
        }
        while (index2 <= e) {
            ansArray[k] = tempArray[index2];
            k++;
            index2++;
        }


    }

}