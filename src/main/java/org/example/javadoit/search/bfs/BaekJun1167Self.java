package org.example.javadoit.search.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJun1167Self {

    static ArrayList<Edge>[] graph;
    static int distance[];
    static Queue qu;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int V = Integer.parseInt(br.readLine());
        graph = new ArrayList[V + 1];
        distance = new int[V + 1];

        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
            distance[i] = -1;
        }

        for (int i = 1; i <= V; i++) {
            st = new StringTokenizer(br.readLine());
            int tokenCnt = st.countTokens();
            int node = Integer.parseInt(st.nextToken());
            for (int j = 1; j < tokenCnt - 2; j = j + 2) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[node].add(new Edge(a, b));
            }

        }
        br.close();

        qu = new LinkedList();
        Edge startNode = new Edge(1, -1);
        startNode = bfs(startNode);

        for (int i = 1; i <= V; i++) {
            distance[i] = -1;
        }

        Edge resultNode = bfs(startNode);
        System.out.println(resultNode.weight);
    }

    static Edge bfs(Edge edge) {
        qu.offer(edge);
        distance[edge.node] = 0;

        Edge returnNode = new Edge(1, -1);

        while (!qu.isEmpty()) {
            Edge curNode = (Edge) qu.poll();

            for (Edge adjNode : graph[curNode.node]) {
                if (distance[adjNode.node] == -1) {
                    qu.add(adjNode);
                    distance[adjNode.node] = distance[curNode.node] + adjNode.weight;
                    if (distance[adjNode.node] > returnNode.weight) {
                        returnNode.node = adjNode.node;
                        returnNode.weight = distance[adjNode.node];
                    }
                }
            }
        }
        return returnNode;
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






/*
슈도코드

V: 노드 개수
graph: 그래프를 인접 리스트로
distance 배열: 시작 노드로부터 거리을 저장. 초기 값은 -1
graph 초기화.
queue : bfs 에 사용할 큐. LinkedList 로
// 임의의 노드에서 BFS 시작
tempDestNode = bfs(1) // 임의의 노드에서 가장 먼 노드 정보 저장

resultNode = bfs(tempDestNode.node)
sout(resultNode.weight)


// bfs method
bfs(startNode){
    Node returnNode: 결과로 리턴할 가장 긴 경로의 노드와 그 거리
    큐에 시작노드 넣기
    시작 노드는 거리가 0.
    큐가 빌 때까지 {
        int curNode = queue.poll()
        for(모든 adjNode 에 대해){
            방문하지 않았으면 큐에 add

            // 해당 노드까지의 거리 저장
            시작 노드부터 curNode 까지의 거리인 distance[curNode] 값 + curNode 에서 adjNode 까지의 거리를 distance 에 저장
            returnNode.weight = (기존 returnNode.weight 와 adjNode 까지의 거리 중 최대값 저장)
        }
    }
    return returnNode;
}


class Node{
    int node;
    int weight'
}

 */