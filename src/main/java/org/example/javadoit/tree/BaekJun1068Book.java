package org.example.javadoit.tree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 문제 - 백준 1068 트리
 * 링크 - https://www.acmicpc.net/problem/1068
 * 등급 -  gold 5
 * 특이사항 - Do it Java 알고리즘 책 문제 068
 */
public class BaekJun1068Book {
    static ArrayList<Integer>[] tree;
    static boolean[] visited;
    static int ans, deleteNode, root;

    public static void main(String[] args) {
        input();

        if (deleteNode == root) {
            System.out.println(0);
        } else {
            dfsFunc(root);
            System.out.println(ans);
        }

    }

    private static void input() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        tree = new ArrayList[N];
        visited = new boolean[N];
        root = 0;

        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            int p = sc.nextInt();
            if (p != -1) {
                tree[i].add(p);
                tree[p].add(i);
            } else {
                root = i;
            }
        }
        deleteNode = sc.nextInt();
    }

    static void dfsFunc(int node) {
        visited[node] = true;
        int childCnt = 0;
        for (int nextNode : tree[node]) {
            if (!visited[nextNode] && nextNode != deleteNode) {
                childCnt++;
                dfsFunc(nextNode);
            }
        }
        if (childCnt == 0) {
            ans++;
        }
    }

}

/* 슈도코드
N: 노드 개수
tree: 트리 데이터 저장 인접 리스트
visited: 방문 기록 저장 배열
answer: 리프 노드 개수 저장 배열
deleteNode: 삭제 노드

tree 인접리스트에 데이터 초기화.

if(deleteNode 가 0 이면) 모두 삭제됨. 0을 출력하고 종료.
else dfs(루트 노드)
    answer 출력

dfs(int node){
    현재 노드 방문 체크
    for(node 의 인전 노드 nextNode 탐색){
        nextNode 가 방문하지 않았고 삭제 노드가 아닐 때
            자식 노드 개수 증가
        dfs(nextNode)
    }
    망냑 자식 노드의 개수가 0 이면 answer 변수 증가.
}

 */