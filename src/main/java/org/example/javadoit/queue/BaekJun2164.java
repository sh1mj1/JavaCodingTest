package org.example.javadoit.queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 문제 - 백준 2164 카드2
 * 링크 - https://www.acmicpc.net/problem/2164
 * 등급 -  Silver 4
 * 특이사항 - Do it Java 알고리즘 책 문제 013 91page
 */

public class BaekJun2164 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Queue<Integer> myQueue = new LinkedList<>();
        int N = sc.nextInt();

        // 카드를 큐에 저장
        for (int i = 1; i <= N; i++) {
            myQueue.add(i);
        }
        // 카드가 1장 남을 때까지 맨 위 버리고, 맨위 카드를 가장 아래로
        while (myQueue.size() > 1) {
            myQueue.poll();
            myQueue.add(myQueue.poll());
        }

        // 마지막으로 남은 카드 출력
        System.out.println(myQueue.poll());
    }
}


/*

N (카드의 개수) myQueue (카드의 저장 자료 구조)

for (카드의 개수만큼 반복){
    큐에 카드 저장
}

while(카드가 1장 남을 때까지){
    맨 위의 카드 버림 : poll()
    맨 위의 카드를 가장 아래로 이동: poll(), add()
}

마지막으로 남은 카드 출력

 */