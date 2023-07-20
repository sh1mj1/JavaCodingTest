package org.example.javadoit.search.binary;

import java.io.*;
import java.util.*;

public class BaekJun1920Self {
    static ArrayList<Integer> A;
    static int targetData;
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        A = new ArrayList<>(N);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A.add(i, Integer.parseInt(st.nextToken()));
        }
        Collections.sort(A);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            targetData = Integer.parseInt(st.nextToken());
            binarySearch(0, N - 1);
        }
        bw.flush();
        br.close();
        bw.close();
    }

    static void binarySearch(int sIndex, int eIndex) throws IOException {
        if (sIndex > eIndex) {
            bw.write("0" + "\n");
        } else {
            int mIndex = (sIndex + eIndex) / 2;
            if (targetData == A.get(mIndex)) {
                bw.write("1" + "\n");
            } else if (targetData > A.get(mIndex)) {
                sIndex = mIndex + 1;
                binarySearch(sIndex, eIndex);
            } else {
                eIndex = mIndex - 1;
                binarySearch(sIndex, eIndex);
            }
        }
    }
}


/*
슈도코드

N: 수의 개수 A: 배열
A 배열 입력받고 초기화
A 배열 정렬
M: 질의 개수
mIndex: A 배열의 중앙 인덱스
for(질의개수){
    입력반은 수 targetData
    A 배열에 새로 입력받은 수가 있는지 이진 탐색

// 재귀함수로 구현
binarySearch(sIndex, eIndex){
    if(sIndex > eIndex){sout(0); return;}
    else{
        int mIndex = (sIndex + eIndex)/2
        if(A[mIndex] == targetData){ sout(1); return;}
        else if (A[mIndex] > targetData){
            sIndex = mIndex + 1
            binarySearch(sIndex, eIndex, mIndex)
        } else //(A[mIndex] < targetData)
        {
            eIndex = mIndex - 1;
            binarySearch(sIndex, eIndex)
        }
    }
}
 */