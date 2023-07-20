package org.example.javadoit.debugging_test;

import java.util.Scanner;

public class DebuggingT1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCase = scanner.nextInt();
        int answer = 0;

        // 배열을 int 형으로 선언했기 때문에
        // 계산과정에서 int 형이 저장할 수 없는 범위의 값이 나왔다
        // 배열을 선언할 때 int 형이 아닌 long 형으로 선언했어야 함.
        int A[] = new int[100001];
        int S[] = new int[100001];

        // i <= 100_000 이 되어야 함.
        for (int i = 1; i < 10000; i++) {
            A[i] = (int) (Math.random() * Integer.MAX_VALUE);
            S[i] = S[i - 1] + A[i];
        }
        for (int t = 1; t < testCase; t++) {
            int query = scanner.nextInt();
            for (int i = 0; i < query; i++) {
                int start = scanner.nextInt();
                int end = scanner.nextInt();

                // answer 가 남아있음. answer 을 0 으로 초기화해주는 시점 오류
                answer += S[end] - S[start - 1];

                // 잘못된 변수 사용 오류 testCase -> t
                System.out.println(testCase + " " + answer);
            }
        }
    }
}
