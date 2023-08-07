package org.example.javadoit.graph.unionfind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 1043 거짓말
 * 링크 - https://www.acmicpc.net/problem/1043
 * 등급 -  gold 4
 * 특이사항 - Do it Java 알고리즘 책 문제 052
 */
public class BaekJun1043 {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        int result = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int[] truePeople = new int[T];
        for (int i = 0; i < T; i++) {
            truePeople[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer>[] party = new ArrayList[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            party[i] = new ArrayList<>();
            int partyPeopleNum = Integer.parseInt(st.nextToken());
            for (int j = 0; j < partyPeopleNum; j++) {
                party[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 0; i < M; i++) {
            int firstPerson = party[i].get(0);
            for (int j = 1; j < party[i].size(); j++) {
                union(firstPerson, party[i].get(j));
            }
        }

        for (int i = 0; i < M; i++) {
            boolean canLie = true;
            int firstPersonParent = find(party[i].get(0));
            for (int j = 0; j < T; j++) {
                if (firstPersonParent == find(parent[truePeople[j]])) {
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
        if (parent[node] == node) {
            return node;
        } else {
            return parent[node] = find(parent[node]);
        }
    }

}

/* 슈도코드
N: 사람 수, M: 파티 수,
T: 진실을 알고 있는 사람 수, truePeople: 진실을 알고 있는 사람들
party: 각 파티에 있는 사람들 ArrayList<>[]
parent: 대표 노드 저장 배열 -> 인덱스와 같은 배열값을 갖도록 해주기
result = 0: 결과값

for(M) { party 에 데이터 넣어주기 }

for(i ~ M) { party[i] 의 첫번째 사람으로 대표노드로 하여 union 수행 }

for(i ~ M) {
    canLie = true : 거짓말을 할 수 있는지 여부
    firstPersonParent: party[i] 의 첫번째 사람의 대표 노드
    for(j ~ T) {
        firstPersonParent 과 find(trueP[j]) 을 비교.
        만약 다르다면 -> canLie = false; break;
    }
    if(canLie) { result ++ }
}

result 출력.

// union-find method
void union(int a, int b){
    a = find(a)
    b = find(b)
    if(b != a){
        parent[b] = a
    }
}

int find(int node){
    if(parent[node] == node){
        return node;
    } else {
        return parent[node] = find(parent[node]);
    }
}

 */