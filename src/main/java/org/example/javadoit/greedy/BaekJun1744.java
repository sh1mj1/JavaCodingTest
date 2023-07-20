package org.example.javadoit.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 문제 - 백준 1744 수 묶기
 * 링크 - https://www.acmicpc.net/problem/1744
 * 등급 -  gold 5
 * 특이사항 - Do it Java 알고리즘 책 문제 034
 */
public class BaekJun1744 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> posPQ = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> negPQ = new PriorityQueue<>();
        int result = 0;
        int zeroCnt = 0;
        for (int i = 0; i < N; i++) {
            int curNum = Integer.parseInt(br.readLine());
            if (curNum > 0) {
                posPQ.offer(curNum);
            } else if (curNum == 0) {
                zeroCnt++;
            } else { // curNum < 0
                negPQ.offer(curNum);
            }
        }

        while (!posPQ.isEmpty()) {
            if (posPQ.size() > 1) {
                int a = posPQ.poll();
                int b = posPQ.poll();
                int temp = Math.max(a + b, a * b);
                result += temp;
            } else {
                result += posPQ.poll();
            }
        }

        while (!negPQ.isEmpty()) {
            if (negPQ.size() > 1) {
                int a = negPQ.poll();
                int b = negPQ.poll();
                result += (a * b);
            } else {
                if (zeroCnt > 0) {
                    zeroCnt--;
                    negPQ.poll();
                } else {
                    result += negPQ.poll();
                }
            }
        }
        System.out.println(result);
    }
}


/*
슈도코드

N: 수의 개수
int result = 0;
posPQ: 양수우선순위큐, 내림차순 정렬
negPQ: 음수 우선순위 큐. 오름차순 정렬
zeroCnt: 0의 개수

for(N){ 수 입력 받기
    수가 양수면 posPQ 에 담고, 음수면 neqPQ 에 담는다.
    만약 0이면 담지 않고 zeroCnt 을 1 늘린다.
}

while(posPQ 가 빌때까지){
    posPQ 안에 수가 두개 이상이면{
        a = poll, b = poll
        temp = (a*b)와 (a+b) 중 최대
        result += temp
    } 한 개이면{
        result += poll;
    }
}

while(negPQ 가 빌때까지){
    negPQ 안에 수가 두개 이상이면 {
        a = poll, b = poll
        result += a * b
    } 한개이면
        만약 0 이 있었다면{
            zeroCnt--;
            poll;
        }0이 없으면{
            result += poll
        }
    }
}
sout(result)

 */