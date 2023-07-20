package org.example.javadoit.graph.floyd_warshall;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 11404 플로이드
 * 링크 - https://www.acmicpc.net/problem/11404
 * 등급 -  gold 4
 * 특이사항 - Do it Java 알고리즘 책 문제 061
 */
public class BaekJun11404 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stringTokenizer;
        // n: 도시 개수, m: 노선 개수, distance: 인접행렬
        int n = Integer.parseInt(bufferedReader.readLine());
        int m = Integer.parseInt(bufferedReader.readLine());
        int[][] distance = new int[n + 1][n + 1];

        // distance 초기 설정 출발 도시와 도착 도시가 같으면 0, 아니면 ∞
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    distance[i][j] = 0;
                } else {
                    distance[i][j] = 9_999_999;
                }
            }
        }

        // distance 에 주어진 노선 정보 적용
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int s = Integer.parseInt(stringTokenizer.nextToken());
            int e = Integer.parseInt(stringTokenizer.nextToken());
            int w = Integer.parseInt(stringTokenizer.nextToken());
            if (distance[s][e] > w) {
                distance[s][e] = w;
            }
        }

        bufferedReader.close();

        // Floyd-warshall
        // K: 중간 노드, i: 출발 노드, j: 도착 노드
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (distance[i][j] > distance[i][k] + distance[k][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }

        // 정답 출력
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (distance[i][j] == 9_999_999) {
                    bufferedWriter.write(0 + " ");
                } else {
                    bufferedWriter.write(distance[i][j] + " ");
                }
            }
            bufferedWriter.write("\n");
        }

        bufferedWriter.flush();
        bufferedWriter.close();

    }


}

/*
슈도코드

N: 도시 개수, M: 노선 개수
distance: 노선 데이터를 저장하는 인접 행렬

for(i -> N번 반복){
    for(j -> N번 반복){
        시작 도시와 종료 도시가 같으면 0, 아니면 충분히 큰 수로 저장
    }
}

for(M번 반복){ 노선 데이터를 distance 행렬에 저장 }

for(K -> N 번 반복){
    for(i -> N 번 반복){
        for(j -> N 번 반복){
            distance[i][j] = min(distance[i][j], distance[i][k] + distance[k][j])
        }
    }
}

정답 배열 출력

 */