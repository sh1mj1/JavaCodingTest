package org.example.javadoit.slidingwindow;

import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 11003 최소값 찾기
 * 링크 - https://www.acmicpc.net/problem/11003
 * 등급 -  platinum 5
 * 특이사항 - Do it Java 알고리즘 책 문제 010 최소값 찾기
 */
public class BaekJun11003 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // 출력을 버퍼에 넣고 한 번에 출력하기 위해 BufferedWriter 사용
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        // N: 데이터의 개수, L: 최소값 구하는 범위(슬라이딩 윈도우의 크기)
        int N = Integer.parseInt(stringTokenizer.nextToken());
        int L = Integer.parseInt(stringTokenizer.nextToken());

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        bufferedReader.close();

        Deque<MyNode> mydeque = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            int nowData = Integer.parseInt(stringTokenizer.nextToken());

            // 새로운 값이 들어올 때마다 현재 수보다 큰 값을 덱에서 제거
            // 모두 제거되면 현재 수를 덱에 추가한다. (정렬 대신 이렇게 구현하여 시간 복잡도를 줄임)
            while (!mydeque.isEmpty() && mydeque.getLast().value > nowData) {
                mydeque.removeLast();
            }
            mydeque.addLast(new MyNode(i, nowData));

            // index 범위에서 벗어난 값은 덱에서 제거
            if (mydeque.getFirst().index <= i - L) {
                mydeque.removeFirst();
            }

            bufferedWriter.write(mydeque.getFirst().value + " ");
        }
        bufferedWriter.flush();
        bufferedWriter.close();


    }

    private static class MyNode {
        public int index;
        public int value;

        public MyNode(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
}
