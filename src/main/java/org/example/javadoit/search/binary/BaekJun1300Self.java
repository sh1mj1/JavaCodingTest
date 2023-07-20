package org.example.javadoit.search.binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJun1300Self {
    static int N, k;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        int sIndex = 1;
        int eIndex = k;

        binSearch(sIndex, eIndex);
        System.out.println(result);
    }

    private static void binSearch(int sIndex, int eIndex) {
        if (sIndex > eIndex) {
            return;
        } else {
            int mIndex = (sIndex + eIndex) / 2;
            int cnt = 0;
            for (int i = 1; i <= N; i++) {
                cnt += Math.min(N, mIndex / i);
            }
            if (cnt < k) {
                sIndex = mIndex + 1;
                binSearch(sIndex, eIndex);
            } else { // cnt >= k
                result = mIndex;
                eIndex = mIndex - 1;
                binSearch(sIndex, eIndex);
            }
        }
    }
}


/*
슈도 코드

N: 배열 A 크기, k: 몇번째 수가 궁금한지.

sIndex = 1; eIndex = k;
result: 정답 변수

binSearch(sIndex, eIndex)
sout(result)

// 이진 탐색 메소드
binSearch(sIndex, eIndex){
    if(sIndex > eIndex){리턴}
    else{
        int mIndex = (sIndex+eIndex)/2;
        for(N만큼){
            각 행에서의 mIndex 보다 작거나 같은 수 의 개수(Math.min(N, mIndex/i)를
            모두 더해서 cnt 에 저장
        }
        if(cnt < k){
            int sIndex = mIndex + 1;
            binSearch(sIndex, eIndex)
        }else{ // cnt >= k
            result = mIndex;
            int eIndex = mIndex-1;
            binSearch(sIndex, eIndex)
        }
    }
}

 */