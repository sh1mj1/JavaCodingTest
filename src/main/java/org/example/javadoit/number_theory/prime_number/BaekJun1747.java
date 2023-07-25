package org.example.javadoit.number_theory.prime_number;

import java.io.IOException;
import java.util.Scanner;

/**
 * 문제 - 백준 1747 소수 & 팰린드롬
 * 링크 - https://www.acmicpc.net/problem/1747
 * 등급 - silver 1
 * 특이사항 - Do it Java 알고리즘 책 문제 039
 */
public class BaekJun1747 {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int maxAvailable = 1_003_001;
        int[] exPN = new int[1_003_002];
        boolean isEnd = false;

        for (int i = 2; i <= maxAvailable; i++) {
            exPN[i] = i;
        }

        for (int i = 2; i <= maxAvailable; i++) {
            if (!isEnd) {
                int curNum = exPN[i];
                if (curNum != 0) {
                    int j = 2;
                    while (curNum * j <= maxAvailable) {
                        exPN[curNum * j] = 0;
                        j++;
                    }
                    if (curNum >= N) {
                        char[] curC = String.valueOf(curNum).toCharArray();
                        int sIndex = 0;
                        int eIndex = curC.length - 1;
                        while (sIndex <= eIndex) {
                            if (curC[sIndex] == curC[eIndex]) {
                                sIndex++;
                                eIndex--;
                                if (sIndex >= eIndex) {
                                    System.out.println(curNum);
                                    isEnd = true;
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                    }
                }
            }

        }

    }

}

/*
슈도코드


 */