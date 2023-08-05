package org.example.javadoit.graph.unionfind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 문제 - 백준 1943 거짓말
 * 링크 - https://www.acmicpc.net/problem/1943
 * 등급 -  gold 4
 * 특이사항 - Do it Java 알고리즘 책 문제 052
 */
public class BaekJun1943 {
    static int[] parent;
    static int[] trueP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int result = 0;
        parent = new int[N + 1];
        ArrayList<ArrayList<Integer>> party = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        trueP = new int[T];
        for (int i = 0; i < T; i++) {
            trueP[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
//
//
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int partyP = Integer.parseInt(st.nextToken());
            party.add(new ArrayList<>());
            int firstPerson = Integer.parseInt(st.nextToken());
            party.get(i).add(firstPerson);
            for (int j = 1; j < partyP; j++) {
                party.get(i).add(j);
                union(firstPerson, j);
            }
        }
//
//

        for (int i = 0; i < M; i++) {
            boolean canLie = true;
            int firstPerson = party.get(i).get(0);
            for (int j = 0; j < trueP.length; j++) {
                if (find(firstPerson) == find(trueP[j])) {
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
    static int find(int node){
        if (parent[node] == node) {
            return node;
        } else {
            return parent[node] = find(parent[node]);
        }
    }

}

/* 슈도코드

N: 사람 수, M: 파티 개수
T: 진실을 아는 사람 수, trueP: 진실을 아는 사람 데이터, party: 파티 데이터
parent: 대표 노드 저장 배열

데이터를 입력받아서 각 자료구조에 저장하기
for(N) { 대표 노드를 자기 자신으로 초기화 }
for(i ~ M 만큼) {
    firstPerson: i 번째 파티의 첫번째 사람
    for(j ~ i 번째 파티의 사람 수만큼){
        // 각 파티에 참여한 사람들을 1개의 그룹으로 만들기
        union(firstPerson, j)
    }
}
for(i ~ M 만큼){
    firstPeople: i 번재 파티의 첫번째 사람
    for(j ~ 진실을 아는 사람들의 수만큼){
        // 각 파티의 대표 노드가 진실을 아는 사람들의 대표 노드와 같다면 과장할 수 없음.
        find(firstPerson) 과 find(trueP[j]) 를 비교
    }
    위 반복문에서 모두 다르면 결과값을 1 증가
}
결과값 출력

// union - find 메서드
union(int a, int b){
    a 와 b 의 대표 노드 찾기
    두 원소의 대표 노드끼리 연결
}

find(int a){
    a 가 대표 노드라면 리턴
    아니라면 a 의 대표 노드값을 find(parent[a]) 값으로 저장. (재귀함수 형태로)
}


 */