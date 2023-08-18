package org.example.javadoit.graph.bellman_ford;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 1219 오민식의 고민
 * 링크 - https://www.acmicpc.net/problem/1219
 * 등급 -  platinum 5
 * 특이사항 - Do it Java 알고리즘 책 문제 060
 */
public class BaekJun1219Book {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M, sCity, eCity;
    static long[] distance, cityMoney;
    static Edge[] edges;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        sCity = Integer.parseInt(st.nextToken());
        eCity = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new Edge[M];
        distance = new long[N];
        cityMoney = new long[N];
        Arrays.fill(distance, Long.MIN_VALUE);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(start, end, price);
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cityMoney[i] = Long.parseLong(st.nextToken());
        }
        distance[sCity] = cityMoney[sCity];

        for (int i = 0; i <= N + N; i++) {
            for (int j = 0; j < M; j++) {
                int start = edges[j].start;
                int end = edges[j].end;
                int price = edges[j].price;

                if (distance[start] == Long.MIN_VALUE) {
                    continue;
                }
                else if (distance[start] == Long.MAX_VALUE) {
                    distance[end] = Long.MAX_VALUE;
                } else if (distance[end] < distance[start] + cityMoney[end] - price) {
                    distance[end] = distance[start] + cityMoney[end] - price;
                    if (i >= N - 1) {
                        distance[end] = Long.MAX_VALUE;
                    }
                }
            }
        }

        if (distance[eCity] == Long.MIN_VALUE) {
            System.out.println("gg");
        } else if (distance[eCity] == Long.MAX_VALUE) {
            System.out.println("Gee");
        } else {
            System.out.println(distance[eCity]);
        }

    }

    static class Edge {
        int start, end, price;

        public Edge(int start, int end, int price) {
            this.start = start;
            this.end = end;
            this.price = price;
        }
    }
}