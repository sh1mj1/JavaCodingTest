package org.example.javadoit.tree.segment_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 2042 구간 합 구하기
 * 링크 - https://www.acmicpc.net/problem/2042
 * 등급 -  gold 1
 * 특이사항 - Do it Java 알고리즘 책 문제 071
 */
public class BaekJun2042 {

    static long[] tree;
    static long result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        // N: 수의 개수, M: 수의 변경이 일어나는 횟수, K: 구간 합을 구하는 횟수
        long N = Long.parseLong(stringTokenizer.nextToken());
        long M = Long.parseLong(stringTokenizer.nextToken());
        long K = Long.parseLong(stringTokenizer.nextToken());
        long[] leafArr = new long[Math.toIntExact(N + 1)];

        long treeHeight = 1; // 트리 높이
        while (Math.pow(2, treeHeight) < N) {
            treeHeight++;
        }

        long treeSize = (long) Math.pow(2, treeHeight + 1); // 16 (0 ~ 15)
        long leafStartIndex = (long) Math.pow(2, treeHeight); // 8
        long tempLeafStartIndex = leafStartIndex;

        // 트리에 리프노드부터 넣기
        tree = new long[Math.toIntExact(treeSize)];

        // TODO: 2023/07/08 여기서 틀림
        for (int i = 0; i < N; i++) {
            long value = Long.parseLong(bufferedReader.readLine());
            leafArr[i + 1] = value;
            tree[Math.toIntExact(tempLeafStartIndex)] = value; // 8 ~ 15
            tempLeafStartIndex++;
        }


        // 트리에 남은 값들 집어넣기
        while (treeSize - 2 >= 0) { // 14 <= 0. 14는 12, 10 이렇게 줄어듬
            tree[Math.toIntExact((treeSize - 2) / 2)] = tree[Math.toIntExact(treeSize - 2)] + tree[Math.toIntExact(treeSize - 1)]; // tree[7] 에 넣기 -> tree[6] -> tree[5]
            treeSize = treeSize - 2;
        }

        for (int i = 0; i < M + K; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            // a: 질의 유형(1 or 2), s: 시작 인덱스 or 변경될 노드의 인덱스, e: 변경값 or 종료 인덱스
            long a = Long.parseLong(stringTokenizer.nextToken());
            long s = Long.parseLong(stringTokenizer.nextToken());
            long e = Long.parseLong(stringTokenizer.nextToken());

            if (a == 1) {
//                changeValue
                // TODO: 2023/07/08 여기 잘못함? 이거로 바꿔봐도 괜찮을 듯?
                long diff = e - leafArr[Math.toIntExact(s)]; // 기존값과 변경값의 차이 (3)
                long treeIndex = (long) Math.pow(2, treeHeight) + s - 1;

                while (treeIndex > 0) { // 10 , 5 , 2 , 1
                    tree[Math.toIntExact(treeIndex)] = tree[Math.toIntExact(treeIndex)] + diff; // 10번 노드 3 -> 6, 5번 노드, 2번 노드, 1번 노드,
                    treeIndex = treeIndex / 2; // 10 -> (5, 2, 1, 0)
                }

            } else {
//                getSum
                // TODO: 2023/07/09 여기도 바꿔보자
                long treeStartIndex = (long) Math.pow(2, treeHeight) + s - 1;
                long treeEndIndex = (long) Math.pow(2, treeHeight) + e - 1;

                while (treeStartIndex <= treeEndIndex) {
                    if (treeStartIndex % 2 == 1) {
                        // 선택
                        result += tree[Math.toIntExact(treeStartIndex)];
                    }
                    if (treeEndIndex % 2 == 0) {
                        // 선택
                        if (treeStartIndex != treeEndIndex) {
                            result += tree[Math.toIntExact(treeEndIndex)];
                        }
                    }
                    treeStartIndex = (treeStartIndex + 1) / 2;
                    treeEndIndex = (treeEndIndex - 1) / 2;
                }
                System.out.println(result);
                result = 0;
            }

        }

    }

}

/*
슈도 코드

tree: 세그먼트 트리 배열
N: 수의 개수, M: 변경이 일어나는 개수, K: 구간 합을 구하는 개수
treeSize 구하기 2^(트리의 높이+1) == Math.pow(2, 트리의 높이 +1)

leftNodeStartIndex 구하기 == treeSize/2 - 1

tree 배열의 리프 노드 영역에 데이터 입력 받기

초기 트리 생성 setTree(트리의 크기)

for(M + K 번 반복){
    a: 질의 유형, s: 시작 인덱스, e: 변경값 또는 종료 인덱스
    if(a == 1){ 트리 데이터 변경 changeValue(tree 시작 인덱스, e(변경값)) }
    if(a == 2){ 구간 합 함수 호출, 출력 getSum(tree 에서의 시작 인덱스, tree 에서의 종료 인덱스) }
}

// 메소드들
setTree(트리의 마지막 인덱스){
    while(인덱스가 루트가 아닐 때까지 반복){
        (트리의 인덱스 / 2) 부분 (부모노드) 에 현재 index 의 트리값 더하기 (tree[x] = tree[2x] + tree[2x+1])
        index 1 감소
    }
}

changeValue(시작 인덱스, 변경값){
    diff: 현재 노드의 값과 변경된 값의 차이
    while(시작 인덱스가 0보다 크다){
        시작 인덱스의 트리값에 diff 값을 더함
        시작 인덱스 = 시작 인덱스 / 2
    }
}

getSum(시작 인덱스, 종료 인덱스){
    while(시작 인덱스가 종료 인덱스보다 클때까지){
        if(시작 인덱스 % 2 == 1) { 해당 노드의 값을 구간 합에 추가 }
        if(종료 인덱스 % 2 == 0) { 해당 노드의 값을 구간 합에 추가}

        시작 인덱스 = 시작 인덱스 / 2
        종료 인덱스 = 종료 인덱스 /2
    }
    구간합 결과 출력
}

 */