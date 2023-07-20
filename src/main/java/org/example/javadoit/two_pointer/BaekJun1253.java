package org.example.javadoit.two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 1253 좋다. 좋은 수 구하기
 * 링크 - https://www.acmicpc.net/problem/1253
 * 등급 -  Gold 4
 * 특이사항 - Do it Java 알고리즘 책 문제 008. 63page
 */
public class BaekJun1253 {
    public static void main(String[] args) throws IOException {
        BaekJun1253 solve = new BaekJun1253();

        solve.answer();
//        solve.myWrongAnswer();
        // TODO: 2023/06/30 왜 myWrongAnswer() 이 틀렸을까............................
    }

    public void answer() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // 정답
        int count = 0;

        // N; 수의 개수, A: 수 데이터 저장 배열
        int N = Integer.parseInt(bufferedReader.readLine());

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        bufferedReader.close();

        // 배열 정렬
        Arrays.sort(A);

        for (int K : A) {
            int i = 0;    // start_index
            int j = N - 1; // end_index

            while (i < j) {
                if ( (A[i] + A[j]) == K) {
                    if ((A[i] != K) && (A[j] != K)) {
                        count++;
                        break;
                    } else if (A[i] == K) {
                        i++;
                    } else if(A[j] == K){
                        j--;
                    }
                } else if (A[i] + A[j] < K) {
                    i++;
                } else {
                    j--;
                }
            }
        }
        System.out.println(count);
    }


    public void myWrongAnswer() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // 정답
        int count = 0;

        // N; 수의 개수, A: 수 데이터 저장 배열
        int N = Integer.parseInt(bufferedReader.readLine());

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        bufferedReader.close();

        // 배열 정렬
        Arrays.sort(A);

        int index = 0;
        for (int K : A) {
            int i = 0;    // start_index
            int j = N - 1; // end_index

            while (i < j) {
                if ( (A[i] + A[j]) == K) {
                    if (i != index && j != index) {
                        count++;
                        break;
                    } else if (i == index) {
                        i++;
                    } else if(j == index){
                        j--;
                    }
                } else if (A[i] + A[j] < K) {
                    i++;
                } else {
                    j--;
                }
            }
            index++;
        }
        System.out.println(count);
    }


}
