package org.example.javadoit.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 문제 - 백준 1715
 * 링크 - https://www.acmicpc.net/problem/1715
 * 등급 -  gold 4
 * 특이사항 - Do it Java 알고리즘 책 문제 033
 */
public class BaekJun1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pQu = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            pQu.offer(Integer.parseInt(br.readLine()));
        }

        int result = 0;
        if (N != 1) {
            while (!pQu.isEmpty()) {
                int a = pQu.poll();
                if (pQu.isEmpty()) {
                    result += a;
                    break;
                }
                int b = pQu.poll();
                int c = a + b;
                result += c;
                if (pQu.isEmpty()) {
                    break;
                } else {
                    pQu.offer(c);
                }
            }
        }
        System.out.println(result);
    }
}


/*
슈도코드

N: 카드 뭉치들의 수
pQu: 각 카드 뭉치의 수를 저장하는 우선순위 큐
우선순위 큐는 오름차순으로 정렬하도록 설정.

각 카드 뭉치의 수를 입력받아 pQu 에 저장
int result;

카드 뭉치의 수가 1이면 그냥 sout(result)
아니라면{
    while(큐가 빌 때까지){
        a = pQu.poll();
        만약 큐가 비어있으면{ result += a; break;}
        아니면
        b = pQu.poll();)
        c = a + b
        result += c
        만약 큐가 비었으면{ break;}
        아니라면 pQu.offer(c)
    }
}
sout(result)

 */