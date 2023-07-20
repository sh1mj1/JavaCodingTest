package org.example.javadoit.slidingwindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 12891 DNA 비밀번호
 * 링크 - https://www.acmicpc.net/problem/12891
 * 등급 -  Silver 2
 * 특이사항 - Do it Java 알고리즘 책 문제 009. 67page
 */

public class BaekJun12891 {
    // checkArr: 비밀번호 체크 배열. myArr: 현재 상태 배열

    static int[] checkArr = new int[4];
    static int[] myArr = new int[4];
    static int checkSecret = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        // S: DNA 문자열의 길이, P: 윈도우 크기
        int S = Integer.parseInt(stringTokenizer.nextToken());
        int P = Integer.parseInt(stringTokenizer.nextToken());
        int result = 0;

        // A: DNA 문자열 (CCTGGATTG)
        char[] A = bufferedReader.readLine().toCharArray();

        // 부분 문자열에 포함되어야할 최소 개수 입력받기 (checkArr)
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < 4; i++) {
            checkArr[i] = Integer.parseInt(stringTokenizer.nextToken());
            if (checkArr[i] == 0) checkSecret++;
        }

        // 초기 윈도우에 따라 myArr 초기화
        for (int i = 0; i < P; i++) {
            addToMyArr(A[i]);
        }

        if (checkSecret == 4) result++;

        // 슬라이딩 윈도우가 이동하면서 add 와 remove
        for (int i = P; i < S; i++) {
            int j = i - P;
            addToMyArr(A[i]);
            removeFromMyArr(A[j]);
            if (checkSecret == 4) result++;
        }
        System.out.println(result);
    }

    private static void addToMyArr(char c) {
        switch (c) {
            case 'A':
                myArr[0]++;
                if (myArr[0] == checkArr[0]) checkSecret++;
                break;
            case 'C':
                myArr[1]++;
                if (myArr[1] == checkArr[1]) checkSecret++;
                break;

            case 'G':
                myArr[2]++;
                if (myArr[2] == checkArr[2]) checkSecret++;
                break;

            case 'T':
                myArr[3]++;
                if (myArr[3] == checkArr[3]) checkSecret++;
                break;

        }
    }

    private static void removeFromMyArr(char c) {
        switch (c) {
            case 'A':
                if (myArr[0] == checkArr[0]) checkSecret--;
                myArr[0]--;
                break;

            case 'C':
                if (myArr[1] == checkArr[1]) checkSecret--;
                myArr[1]--;
                break;

            case 'G':
                if (myArr[2] == checkArr[2]) checkSecret--;
                myArr[2]--;
                break;

            case 'T':
                if (myArr[3] == checkArr[3]) checkSecret--;
                myArr[3]--;
                break;

        }
    }

}


