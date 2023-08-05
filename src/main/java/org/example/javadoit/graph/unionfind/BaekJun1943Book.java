package org.example.javadoit.graph.unionfind;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 문제 - 백준 1943 거짓말
 * 링크 - https://www.acmicpc.net/problem/1943
 * 등급 -  gold 4
 * 특이사항 - Do it Java 알고리즘 책 문제 052
 */
public class BaekJun1943Book {
    static int[] parent;
    static int[] trueP;
    static ArrayList<Integer>[] party;
    static int result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int T = sc.nextInt();
        result = 0;
        trueP = new int[T];

        for (int i = 0; i < T; i++) { // 진실을 아는 사람 저장
            trueP[i] = sc.nextInt();
        }

        party = new ArrayList[M];
        for (int i = 0; i < M; i++) { // 파티 데이터 저장
            party[i] = new ArrayList<>();
            int party_size = sc.nextInt();
            for (int j = 0; j < party_size; j++) {
                party[i].add(sc.nextInt());
            }
        }

        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) { // 대표 노드를 자기 자신으로 초기화
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) { // 각 파티에 참여한 사람들을 1개의 그룹으로 만들기
            int firstPerson = party[i].get(0);
            for (int j = 1; j < party[i].size(); j++) {
                union(firstPerson, party[i].get(j));
            }
        }

        // 각 파티의 대표 노드와 진실을 아는 사람들의 대표 노드가 같다면 과장할 수 없음.
        for (int i = 0; i < M; i++) {
            boolean canLie = true;
            int firstPerson = party[i].get(0);
            for (int j = 0; j < trueP.length; j++) {
                if (find(firstPerson) == find(trueP[j])) {
                    canLie = false;
                    break;
                }
            }
            if (canLie) {
                result++;
            }
        }
        System.out.println(result);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }

    static int find(int node) {
        if (node == parent[node]) {
            return node;
        } else {
            return parent[node] = find(parent[node]);
        }
    }
}