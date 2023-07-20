package org.example.javadoit.sort.quick;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 11004 K번째 수
 * 링크 - https://www.acmicpc.net/problem/11004
 * 등급 -  silver 5
 * 특이사항 - Do it Java 알고리즘 책 문제 019 121page
 */
public class BaekJun11004 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        // N: 수의 개수, K: 값을 구하려는 index, A: 배열
        int N = Integer.parseInt(stringTokenizer.nextToken());
        int K = Integer.parseInt(stringTokenizer.nextToken());
        int A[] = new int[N];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        bufferedReader.close();


        quickSort(A, 0, N - 1, K - 1);
        System.out.println(A[K - 1]);
    }

    private static void quickSort(int[] sortArray, int sortStartIndex, int sortEndIndex, int K) {
        if (sortStartIndex < sortEndIndex) {
            int pivot = partition(sortArray, sortStartIndex, sortEndIndex);

            // K 번째 수가 pivot 이면 더이상 구할 필요가 없음
            if (pivot == K) {
                return;
            }
            // K 가 pivot 보다 작으면 왼쪽 그룹만 정렬 수행
            else if (K < pivot) {
                quickSort(sortArray, sortStartIndex, pivot - 1, K);
            }
            // K  가 pivot 보다 크면 오른쪽 그룹만 정렬 수행
            else {
                quickSort(sortArray, pivot + 1, sortEndIndex, K);
            }
        }

    }

    private static int partition(int[] sortArray, int sortStartIndex, int sortEndIndex) {
        if (sortStartIndex + 1 == sortEndIndex) {
            if (sortArray[sortStartIndex] > sortArray[sortEndIndex]) swap(sortArray, sortStartIndex, sortEndIndex);
            return sortEndIndex;
        }
        int M = (sortStartIndex + sortEndIndex) / 2;
        swap(sortArray, sortStartIndex, M);  // 중앙값을 1번째 요소로 이동하기
        int pivot = sortArray[sortStartIndex];
        int i = sortStartIndex + 1, j = sortEndIndex;

        while (i <= j) {
            while (pivot < sortArray[j] && j > 0) {   // 피벗보다 작은 수가 나올 때까지 j--
                j--;
            }
            while (pivot > sortArray[i] && i < sortArray.length - 1) {  // 피벗보다 큰 수가 나올 때까지 i++
                i++;
            }
            if (i <= j) {
                swap(sortArray, i++, j--);  // 찾은 i와 j를 교환하기
            }
        }
        // i == j 피벗의 값을 양쪽으로 분리한 가운데에 오도록 설정하기
        sortArray[sortStartIndex] = sortArray[j];
        sortArray[j] = pivot;
        return j;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


}
