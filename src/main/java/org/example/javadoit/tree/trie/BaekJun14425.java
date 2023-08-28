package org.example.javadoit.tree.trie;

import java.util.Scanner;

/**
 * 문제 - 백준 14425 문자열 집합
 * 링크 - https://www.acmicpc.net/problem/14425
 * 등급 -  silver 3
 * 특이사항 - Do it Java 알고리즘 책 문제 069
 */
public class BaekJun14425 {
    static Scanner sc;
    static int N, M, count;
    static Tnode root;


    public static void main(String[] args) {
        input();
        search();
        System.out.println(count);
    }


    private static void input() {
        sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        root = new Tnode();

        while (N > 0) {
            saveWord();
            N--;
        }
    }

    private static void saveWord() {
        String text = sc.next();
        Tnode now = root;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (now.next[c - 'a'] == null) {
                now.next[c - 'a'] = new Tnode();
            }
            now = now.next[c - 'a'];
            if (i == text.length() - 1) {
                now.isEnd = true;
            }
        }
    }

    private static void search() {
        while (M > 0) {
            String text = sc.next();
            Tnode now = root;
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                if (now.next[c - 'a'] == null) {
                    break;
                }
                now = now.next[c - 'a'];
                if (i == text.length() - 1 && now.isEnd) {
                    count++;
                }
            }
            M--;
        }
    }


    static class Tnode {
        Tnode[] next = new Tnode[26];
        boolean isEnd;

        public Tnode(Tnode[] next, boolean isEnd) {
            this.next = next;
            this.isEnd = isEnd;
        }

        public Tnode() {
        }
    }


}

/*  슈도 코드

N: 집합 S 의 문자열 개수, M: 검사할 문자열 개수.
while(N 만큼 반복){
    text: 집합 S 의 문자열
    현재 노드를 루트 노드로 설정.
    for(i 를 text 문자열 길이만큼 반복){
        c: i 번째 문자
        if(c 변수에 해당하는 다음 노드가 null이다)
            신규 노드 생성
            현재 노드를 c 변수 노드로 변경.
        if(i 가 문자열의 마지막이다.)
            isEnd = true
}
count: 정답 변수
while(M){
    text: 검색 문자열
    현재 노드를 루트 노드로 설정.
    for(i 를 text 문자열 길이만큼 반복){
        c: i 번째 문자
        if(c 변수에 해당하는 다음 노드가 null 이다)
            이 문자열 검색 종료
            현재 노드를 c 변수 노드로 변경.
        if(i 가 문자열의 마지막이고, 현재 노드의 isEnd 값이 true이다.)
            count 값 올리기.,
}

count 출력.

static class Tnode
    next: 다음 노드 배열
    isEnd: 마지막 문자열인지 여부
}

 */