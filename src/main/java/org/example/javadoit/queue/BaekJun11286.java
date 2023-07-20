package org.example.javadoit.queue;


import java.io.*;
import java.util.PriorityQueue;

/**
 * 문제 - 백준 11286 카드2
 * 링크 - https://www.acmicpc.net/problem/11286
 * 등급 -  Silver 1
 * 특이사항 - Do it Java 알고리즘 책 문제 013m4 94page
 */

public class BaekJun11286 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        // N: 연산의 개수
        int N = Integer.parseInt(bufferedReader.readLine());

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> {
            int o1_abs = Math.abs(o1);
            int o2_abs = Math.abs(o2);

            if (o1_abs == o2_abs) {
                return o1 - o2;
            } else {
                return o1_abs - o2_abs;
            }
        });

        for (int i = 0; i < N; i++) {
            int tempInput = Integer.parseInt(bufferedReader.readLine());
            if (tempInput == 0) {
                if (priorityQueue.isEmpty()) {
                    bufferedWriter.write("0 \n");
                } else {
                    bufferedWriter.write(priorityQueue.poll() + "\n");
                }
            } else {
                priorityQueue.add(tempInput);
            }
        }
        bufferedReader.close();
        bufferedWriter.flush();
        bufferedWriter.close();

    }

}


/*

N (질의 요청 개수)

우선순위 큐 선언
- 절대값 기준으로 정렬되도록
- 만약 절대값이 같다면 음수 우선 정렬
for (N 만큼 반복){
    요청이 0일 때 -> 큐가 비어있으면 0, 비어있지 않으면 큐의 front 값 출력 (poll())
    요청이 1일 때 -> 새로운 데이터를 우선순위 큐에 더하기 (add())
}

 */

/*
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> MyQueue = new PriorityQueue<>((o1, o2) -> {
            int first_abs = Math.abs(o1);
            int second_abs = Math.abs(o2);
            if (first_abs == second_abs) {
                return o1 > o2 ? 1 : -1; // 절대값이 같으면 음수 우선 정렬
            } else {
                return first_abs - second_abs; // 절대값을 기준으로 정렬(절대값이 작은 것이 우선)
            }
        });

        for (int i = 0; i < N; i++) {
            int request = Integer.parseInt(br.readLine());

            if (request == 0) {
                if (MyQueue.isEmpty()) {
                    System.out.println("0");
                } else {
                    System.out.println(MyQueue.poll());
                }
            } else {
                MyQueue.add(request);
            }

        }

        System.out.println(MyQueue);
    }
 */