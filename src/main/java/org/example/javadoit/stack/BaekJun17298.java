package org.example.javadoit.stack;

import java.io.*;
import java.util.Stack;

/**
 * 문제 - 백준 17298 오큰수 구하기
 * 링크 - https://www.acmicpc.net/problem/17298
 * 등급 -  Gold 4
 * 특이사항 - Do it Java 알고리즘 책 문제 012 86page. 1초, 메모리 512MB
 */

public class BaekJun17298 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        // N: 배열 개수, A[]: 수열 배열, ans[]: 오큰수 배열
        int N = Integer.parseInt(bufferedReader.readLine());
        int[] A = new int[N];
        int[] ans = new int[N];

        String[] str = bufferedReader.readLine().split(" ");
        bufferedReader.close();

        // A[] 초기화
        for (int i = 0; i < N; i++)
            A[i] = Integer.parseInt(str[i]);

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && A[i] > A[stack.peek()]) {
                int tempIndex = stack.pop();
                ans[tempIndex] = A[i];
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int tempIndex = stack.pop();
            ans[tempIndex] = -1;
        }

        for (int nge : ans)
            bufferedWriter.write(nge + " ");

        bufferedWriter.flush();
        bufferedWriter.close();

    }


}

/*
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine()); // 수열의 크기

        int[] A = new int[n]; // 수열 배열

        int[] ans = new int[n]; // 정답 배열

        String[] str = bf.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(str[i]);
        }

        Stack<Integer> myStack = new Stack<>();

        myStack.push(0); // 처음에는 항상 스택이 비어있으므로 최초 값을 push 해서 초기화

        for (int i = 1; i < n; i++) {
            // 스택이 비어있지 않고 현재 수열값이 스택 top 인덱스가 가리키는 수열보다 클 경우
            while (!myStack.isEmpty() && A[myStack.peek()] < A[i]) {
                ans[myStack.pop()] = A[i]; // 정답 배열에 오큰수를 현재 수열로 저장하기
            }
            myStack.push(i);    // 신규 데이터 push
        }

        while (!myStack.isEmpty()) {
            // 반복문을 다 돌고 나왔는데 스택이 비어있지 않다면 빌 때까지
            // 스택에 쌓인 index 에 -1 을 넣기
            ans[myStack.pop()] = -1;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < n; i++) {
            // 출력
            bw.write(ans[i] + " ");
        }

        bw.write("\n");
        bw.flush();
    }
 */


/*
N(수열 개수) A[] (수열 배열) ans[](정답 배열)

수열 배열 채우기
최초 스택 초기화

for(N 만큼 반복){
	while(스택 notEmpty &&
		현재 수열 값이 top에 해당하는 수열값보다 클 때까지){
		pop()
		정답 배열에 오큰수를 현재 수열값으로로 저장
	}
	현재 수열값을 스택에 push()
}

while(스택이 빌 때까지){
	스택에 있는 인덱스의 정답 배열에 -1 저장하기
}

정답 배열 출력
 */