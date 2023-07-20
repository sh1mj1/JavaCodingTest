package org.example.javadoit.tree.segment_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJun2042Doit {

    static long[] tree;
    static int treeSize;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        // N: 수의 개수, M: 변경이 일어나는 횟수, K: 구간 합을 구하는 횟수
        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());
        int K = Integer.parseInt(stringTokenizer.nextToken());

        int treeHeight = 0; // 3
        int length = N;
        while (length != 0) {
            length /= 2;
            treeHeight++;
        }

        treeSize = (int) Math.pow(2, treeHeight + 1); // 16
//        int treeSize = (int) Math.pow(2, treeHeight + 1); // 16
        int leafNodeStartIndex = treeSize / 2 - 1; // 7

        tree = new long[treeSize + 1];

        // 데이터를 리프 노드에 입력받기
        for (int i = leafNodeStartIndex + 1; i <= leafNodeStartIndex + N; i++) {
            tree[i] = Long.parseLong(bufferedReader.readLine()); // 8 ~ 13
        }

        // TODO: 2023/07/09 변경함
        initTree(treeSize);
//        initTree(treeSize - 1);

        for (int i = 0; i < M + K; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            long a = Long.parseLong(stringTokenizer.nextToken());
            int s = Integer.parseInt(stringTokenizer.nextToken());
            long e = Long.parseLong(stringTokenizer.nextToken());

            if (a == 1) {
                int treeIndex = (int) (Math.pow(2, treeHeight) + s -1);
                // TODO: 2023/07/09 여기 바꿨음.
                changeValue(treeIndex, e);
//                changeValue(leafNodeStartIndex + s, e);
            } else if (a == 2) {
                // TODO: 2023/07/09 여기도 바꿧음
//                s = s + leafNodeStartIndex;
//                e = e + leafNodeStartIndex;
                s= (int) (Math.pow(2, treeHeight) + s - 1);
                e= (int) (Math.pow(2, treeHeight) + e - 1);
                System.out.println(getSum(s, e));
            } else {
                return;
            }
        }
        bufferedReader.close();



    }


    // 메소드
    private static long getSum(int s, long e) {
        long partSum = 0;
        // s == e 일 때는 (s%2 == 1) 과 (e%2 == 0) 중 하나만 만족할 수 밖에 없으므로 굳이 한번 더 분기할 필요없음.
        while (s <= e) {
            if (s % 2 == 1) {
                partSum += tree[s];
                s++;
            }
            if (e % 2 == 0) {
                partSum += partSum + tree[Math.toIntExact(e)];
                e--;
            }
            s = s / 2;
            e = e / 2;
        }
        return partSum;
    }

    private static void changeValue(int index, long value) {
        long diff = value - tree[index];
        while (index > 0) {
            tree[index] = tree[index] + diff;
            index = index / 2;
        }
    }


    // 초기 트리 구성하는 함수 이거 변경해보자....
    private static void initTree(int i) {
        // TODO: 2023/07/09 여기 변경함
        while (i - 2 >= 0) {
            tree[(treeSize - 2) / 2] = tree[(treeSize - 2)] + tree[(treeSize - 1)];
            treeSize -= 2;
        }
//        while (i != 1) {
//            tree[i / 2] += tree[i];
//            i--;
//        }
    }


}