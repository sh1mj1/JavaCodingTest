package org.example.javadoit.tree.binary_tree;

import java.io.*;

/**
 * 문제 - 백준 1991 트리 순회
 * 링크 - https://www.acmicpc.net/problem/1991
 * 등급 -  silver 1
 * 특이사항 - Do it Java 알고리즘 책 문제 070
 */
public class BeakJun1991 {
    static BufferedWriter bw;
    static int[][] tree;

    public static void main(String[] args) throws IOException {
        input();

        preorder(1);
        bw.write('\n');
        inorder(1);
        bw.write('\n');
        postorder(1);

        bw.flush();
        bw.close();
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        tree = new int[N + 1][2];

        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            int parent = line[0] - 'A' + 1;
            int lChildStr = line[2];
            int rChildStr = line[4];
            if (lChildStr != 46) {
                tree[parent][0] = lChildStr - 'A' + 1;
            }
            if (rChildStr != 46) {
                tree[parent][1] = rChildStr - 'A' + 1;
            }
        }
        br.close();
    }

    static void preorder(int node) throws IOException {
        bw.write((char) node + 'A' - 1);
        if (tree[node][0] != 0)
            preorder(tree[node][0]);
        if (tree[node][1] != 0)
            preorder(tree[node][1]);

    }

    static void inorder(int node) throws IOException {
        if (tree[node][0] != 0)
            inorder(tree[node][0]);
        bw.write((char) node + 'A' - 1);
        if (tree[node][1] != 0)
            inorder(tree[node][1]);
    }

    static void postorder(int node) throws IOException {
        if (tree[node][0] != 0)
            postorder(tree[node][0]);
        if (tree[node][1] != 0)
            postorder(tree[node][1]);
        bw.write((char) node + 'A' - 1);
    }
}

/* 슈도 코드

N: 노드 개수
H: 트리의 높이: 2³ = 8 < 7 일 때 H == 3
bw: BufferedWriter
tree: 2차원 배열. 왼쪽 자식과 오른쪽 자식을 가지도록.
tree[N][0] -> 왼쪽 자식
tree[N][1] -> 오른쪽 자식

트리 데이터 입력받기

전위 순회
bw.write('\n')
중위 순회
bw.write('\n')
후위 순회

bw.flush
bw.close

// 전위 순회
preorderTrav(int node){
    현재 노드를 bw 에 쓰기
    만약 node 가 왼쪽 자식이 있다면
        preorderTrav(왼쪽 자식)
    만약 node 가 오른쪽 자식이 있다면
        preorderTrav(오른쪽 자식)
}

// 중위 순회
inorderTrav(int node){
    만약 node 가 왼쪽 자식이 있다면
        inorderTrav(왼쪽 자식)
    현재 노드를 bw 에 쓰기
    만약 node 가 오른쪽 자식이 있다면
        inorderTrav(오른쪽 자식)
}

// 후위 순회
postorderTrav(int node){
    만약 node 가 왼쪽 자식이 있다면
        postorderTrav(왼쪽 자식)
    만약 node 가 오른쪽 자식이 있다면
        postorderTrav(오른쪽 자식)
    현재 노드를 bw 에 쓰기
}



 */
