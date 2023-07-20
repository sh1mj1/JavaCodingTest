package org.example.javadoit.stack;

import java.util.Scanner;
import java.util.Stack;

/**
 * 문제 - 백준 1874 스택으로 오름차순 수열 만들기
 * 링크 - https://www.acmicpc.net/problem/1874
 * 등급 -  Silver 2
 * 특이사항 - Do it Java 알고리즘 책 문제 011 81page
 */

public class BaekJun1874 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // N: 수열의 개수 (8), A[]: 수열 배열 {4,3,6,8,7,5,2,1}
        int N = scanner.nextInt();
        int[] A = new int[N];
        for (int index = 0; index < N; index++) {
            A[index] = scanner.nextInt();
        }

        // Stack
        Stack<Integer> stack = new Stack<>();

        StringBuffer stringBuffer = new StringBuffer();

        int num = 1; // 오름차순 수
        boolean result = true;


        for (int i = 0; i < N; i++) {
            int currentSu = A[i]; // 현재 수열 수
            if (currentSu >= num) {
                while (currentSu >= num) {
                    stack.push(num);
                    num++;
                    stringBuffer.append("+\n");
                }
                stack.pop();
                stringBuffer.append("- \n");
            } else {
                int n = stack.pop();
                if (n != currentSu) {
                    System.out.println("NO");
                    result = false;
                    break;
                } else {
                    // 어차피 n < currentSu 인 경우는 있을 수 없음.
                    // 만약 그러한 경우가 있으려면 자연수가 수열 수와 같아질 때까지 오름차순으로 stack 에 들어가고 나서 pop 을 하고
                    // currentSu < num 인 경우에 생겨야 한다.
                    // 이 경우에 currentSu 가 num 보다 작다는 것은 오름차순으로 num 이 stack 에 들어갔다는 것에 위배되게 된다.
                    // 그러므로 이 조건은 사실상 (n == currentSu) 와 같다..

                    stringBuffer.append("- \n");
                }
            }
        }
        if(result) System.out.println(stringBuffer.toString());


    }
}
