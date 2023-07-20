package org.example.samsumg_sw.Prob1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 07/10 MON 투썸 21:55 제출.
 * 구간합 아이디어와 순서 인덱스를 사용해보자.
 *
 * 채점용 input 파일로 채점한 결과 fail 입니다.
 * (오답 : 20개의 테스트케이스 중 4개가 맞았습니다.)
 * 제한시간 초과가 발생하였습니다. 제한시간 초과로 전체 혹은 일부 테스트 케이스는 채점이 되지 않을 수 있습니다.
 */
public class Prob1Try8DFS {

    static int seqIndex;
    static int[] nodeSeqArr;
    static int[] weight;
    static int[] weightSum;
    static int rootNode;
    static boolean[] isVisited;

    static int minSum;

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stringTokenizer;
        int testcase = Integer.parseInt(bufferedReader.readLine()); // testCase 수

        for (int tcCnt = 1; tcCnt <= testcase; tcCnt++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            int N = Integer.parseInt(stringTokenizer.nextToken()); // 학생(노드) 수
            int M = Integer.parseInt(stringTokenizer.nextToken()); // 마니또 관계(에지) 수

            nodeSeqArr = new int[N + 1];
            isVisited = new boolean[N + 1];

            // 그래프 초기화
            List<List<Edge>> graph = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList<>());
            }
            for (int i = 0; i < M; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                int X = Integer.parseInt(stringTokenizer.nextToken());
                int Y = Integer.parseInt(stringTokenizer.nextToken());
                int C = Integer.parseInt(stringTokenizer.nextToken());
                graph.get(X).add(new Edge(Y, C));
            }

            minSum = Integer.MAX_VALUE;
            int firstSearchNode = 0;

            for (int i = 1; i <= N; i++) {
                if (isVisited[i]) {
                    continue;
                } else {
                    firstSearchNode = i;
                }

                nodeSeqArr = new int[N + 1]; // 각 노드가 현재 경로의 몇번째에 있는지

                // 시작 노드로 설정, 시작 초기화
                nodeSeqArr[firstSearchNode] = 1;
                rootNode = firstSearchNode;

                // TODO: 2023/07/10 적당히 큰 수로 일단 해놓자.
                weight = new int[N + 3]; // 바로 전 노드에서 현 노드까지 비용
                weightSum = new int[N + 4];  // 현재까지의 가중치 합(구간 합)
                seqIndex = 1; // dfs 탐색 중 현재 경로의 인덱스

                dfsSearch(firstSearchNode, graph); // 시작 노드부터 dfs 시작
            }

            if (minSum == Integer.MAX_VALUE) { // 그래프가 없다면 -1 출력
                minSum = -1;
            }
            System.out.println("#" + tcCnt + " " + minSum);

        }
        bufferedReader.close();

    }

    public static void dfsSearch(int node, List<List<Edge>> graph) {
        // 하나의 연결 요소임. (linkedList 에서 제거)
        isVisited[node] = true;
        seqIndex++;

        for (Edge edge : graph.get(node)) {

            // 가중치 저장
            weight[seqIndex] = edge.weight;
            weightSum[seqIndex] = weightSum[seqIndex - 1] + weight[seqIndex];

            // 탐색 경로 중 지금 노드가 몇번째로 방문했었는지 자연수 자연수 (방문 안했었으면 0)
            int thisNodeSeq = nodeSeqArr[edge.node];

            // 첫방문
            if (thisNodeSeq == 0) {
                nodeSeqArr[edge.node] = seqIndex; // 현재 순서에 방문했다고 기록.
                dfsSearch(edge.node, graph);

            } else { // 첫방문이 아니라면

                int tempMinSum = 0;
                tempMinSum = weightSum[seqIndex] - weightSum[thisNodeSeq];
                minSum = Math.min(minSum, tempMinSum);
                weight[seqIndex] = 0;
                weightSum[seqIndex] = 0;

            }

        }

        // 현 노드의 인접 에지를 모두 탐색했을 때 이 곳에 도달.
        // 방문했던 해당 node 에서 탈출.
        nodeSeqArr[node] = 0;
        weight[seqIndex] = 0;
        weightSum[seqIndex] = 0;
        seqIndex--;


    }


    static class Edge {
        int node;
        int weight;

        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

}