package org.example.samsumg_sw.Prob1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 07/11 TUE 새벽 4:15
 * 먼저 연결 요소를 구하자. 그리고 나서
 * 구간합 아이디어와 순서 인덱스를 사용해보자. 말하자면 dfs 을 두개 쓰는 것
 */
public class Prob1Try8_1DFSBeforeConnectedFirstSerach {

    static int seqIndex;
    static int[] nodeSeqArr;
    static int[] weight;
    static int[] weightSum;
    static int rootNode;
    static boolean[] isVisited;

    static int minSum;

    static int[] representNodeArr;

    static Stack<Integer> rootNodesStack;

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

            representNodeArr = new int[N + 1];
            rootNodesStack = new Stack<>();

            // 본격적인 dfs 이전에 연결요소의 대표 노드를 뽑기
            for (int i = 1; i <= N; i++) {
                if (representNodeArr[i] == 0) { // 노드 i 가 한번도 탐ㅅ핵된 적이 없다.
                    rootNode = i;
                    getConnectDFS(i, graph);
                    rootNodesStack.push(rootNode);
                }
            }

            while (!rootNodesStack.isEmpty()) {
                int firstNode = rootNodesStack.pop();

                nodeSeqArr = new int[N + 1]; // 각 노드가 현재 경로의 몇번째에 있는지

                // 시작 노드로 설정, 시작 초기화
                nodeSeqArr[firstNode] = 1;
                rootNode = firstNode;

                // TODO: 2023/07/10 적당히 큰 수로 일단 해놓자.
                weight = new int[N + 3]; // 바로 전 노드에서 현 노드까지 비용
                weightSum = new int[N + 4];  // 현재까지의 가중치 합(구간 합)
                seqIndex = 1; // dfs 탐색 중 현재 경로의 인덱스

                dfsSearch(firstNode, graph); // 시작 노드부터 dfs 시작
            }

            if (minSum == Integer.MAX_VALUE) { // 그래프가 없다면 -1 출력
                minSum = -1;
            }
            System.out.println("#" + tcCnt + " " + minSum);

        }
        bufferedReader.close();

    }

    public static void dfsSearch(int node, List<List<Edge>> graph) {
        seqIndex++;

        for (Edge edge : graph.get(node)) {

            // 가중치 저장
            weight[seqIndex] = edge.weight;
            weightSum[seqIndex] = weightSum[seqIndex - 1] + weight[seqIndex];

            // 탐색 경로 중 지금 노드가 몇번째로 방문했었는지 1 ~ 자연수. (방문 안했었으면 0)
            int thisNodeSeq = nodeSeqArr[edge.node];

            // 첫방문
            if (thisNodeSeq == 0) {
                nodeSeqArr[edge.node] = seqIndex; // 현재 순서에 방문했다고 기록.
                dfsSearch(edge.node, graph);

            } else { // 첫방문이 아니라면

                int tempMinSum = weightSum[seqIndex] - weightSum[thisNodeSeq];
                minSum = Math.min(minSum, tempMinSum);
                weight[seqIndex] = 0;
                weightSum[seqIndex] = 0;

            }

        }

        // 현 노드의 인접 에지를 모두 탐색했을 때 이 곳에 도달.
        // 방문했던 해당 node 에서 탈출.

        seqIndex--;

        nodeSeqArr[node] = 0;
        weight[seqIndex] = 0;
        weightSum[seqIndex] = 0;


    }

    private static void getConnectDFS(int node, List<List<Edge>> graph) {
        if (isVisited[node]) {
            return;
        }
        representNodeArr[node] = rootNode;
        isVisited[node] = true;

        for (Edge edge : graph.get(node)) {
            if (!isVisited[edge.node]) {
                getConnectDFS(edge.node, graph);
            }
        }
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