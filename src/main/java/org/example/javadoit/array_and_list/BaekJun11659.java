package org.example.javadoit.array_and_list;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 11659 구간 합 구하기 4
 * 링크 - https://www.acmicpc.net/problem/11659
 * 등급 -  Silver 3
 * 특이사항 - Do it Java 알고리즘 책 문제 003 44page
 */
public class BaekJun11659 {
    public static void main(String[] args) throws IOException {
        BaekJun11659 solve = new BaekJun11659();
        // 아래 세가지 주석친 문장 중 하나로 실행 가능.
//        solve.solveWithBufferedReaderAndStringTokenizer();
//        solve.solveWithScanner();
//        solve.solveWithBufferedReaderAndStringSplit();
    }

    public void solveWithBufferedReaderAndStringTokenizer() throws IOException {
        // 시간을 줄이기 줄이기위한 bufferedReader
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // 한 단어씩 저장하기 위한 StringTokenizer
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int suNo = Integer.parseInt(stringTokenizer.nextToken()); // 총 수의 개수
        int quizNo = Integer.parseInt(stringTokenizer.nextToken()); // 총 질의 개수

        // 합 배열 선언. 기본값은 모두 0
        long[] S = new long[suNo + 1];

        // 합 배열 초기화
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 1; i <= suNo; i++) {
            S[i] = S[i - 1] + Integer.parseInt(stringTokenizer.nextToken());
        }

        for (int q = 0; q < quizNo; q++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int i = Integer.parseInt(stringTokenizer.nextToken());
            int j = Integer.parseInt(stringTokenizer.nextToken());
            System.out.println(S[j] - S[i - 1]);
        }
    }

    public void solveWithScanner(){
        // 입력받기 위한 Scanner
        Scanner scanner = new Scanner(System.in);
        int suNo = scanner.nextInt(); // 총 수의 개수
        int quizNo = scanner.nextInt(); // 총 질의 개수

        // 합 배열 선언
        long[] S = new long[suNo + 1];

        // 합 배열 초기화
        for (int i = 1; i < suNo + 1; i++) {
            S[i] = S[i-1] + scanner.nextInt();
        }

        for (int q = 0; q < quizNo; q++) {
            int i = scanner.nextInt();
            int j = scanner.nextInt();
            System.out.println(S[j] - S[i-1]);
        }
    }

    public void solveWithBufferedReaderAndStringSplit() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // 한 줄을 읽어서 배열로 저장, 구분 기호는 공백(space)
        String[] arrNo = bufferedReader.readLine().split("\\s");

        int suNo = Integer.parseInt(arrNo[0]); // 수의 개수
        int quizNo = Integer.parseInt(arrNo[1]); // 질의 개수

        // 합 배열 선언
        long[] S = new long[suNo + 1];

        // 한줄을 읽어서 배열로 저장
        String[] arrN = bufferedReader.readLine().split("\\s");

        // 합 배열 초기화
        for (int i = 1; i < suNo + 1; i++) {
            S[i] = S[i - 1] + Integer.parseInt(arrN[i - 1]);
        }

        for (int q = 0; q < quizNo; q++) {
            String[] queryN = bufferedReader.readLine().split("\\s");
            int i = Integer.parseInt(queryN[0]);
            int j = Integer.parseInt(queryN[1]);
            System.out.println(S[j] - S[i - 1]);
        }





    }


}
