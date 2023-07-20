package org.example.javadoit.search.binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJun2343Self {

    static int N, M;
    static int[] lessons;
    static int result;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lessons = new int[N];
        int sIndex = 0;
        int eIndex = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int curLesson = Integer.parseInt(st.nextToken());
            lessons[i] = curLesson;
            if (curLesson > sIndex) {
                sIndex = curLesson;
            }
            eIndex += curLesson;
        }

        binS(sIndex, eIndex);
        System.out.println(result);
    }

    private static void binS(int sIndex, int eIndex) {
        if (sIndex > eIndex) {
            return;
        } else {
            int mIndex = (sIndex + eIndex) / 2;
            int temp = 0;
            int cnt = 1;
            for (int i = 0; i < N; i++) {
                temp += lessons[i];
                if (temp > mIndex) {
                    cnt++;
                    temp = lessons[i];
                }
            }

            if (cnt <= M) {
                result = mIndex;
                eIndex = mIndex - 1;
                binS(sIndex, eIndex);
            } else {
                sIndex = mIndex + 1;
                binS(sIndex, eIndex);
            }
        }
    }

}


/*
슈도코드
N: 레슨수, M: 블루레이 수
result: 결과값
lessons: 레슨 배열
sIndex: 레슨 영상 중 최대 길이. eIndex: 모든 레슨 영상 길이의 합
for(레슨 수) {
    lessons 초기화. sIndex, eIndex 을 설정
}
binarySearch(sIndex, eIndex);
sout(result)

// 이진 탐색 메소드
binarySearch(sIndex, eIndex){
    if(sIndex>eIndex){ sout(result)}
    else{
        int mIndex = (sIndex+eIndex)/2
        // mIndex 가 이번에 검사하고자 하는 블루레이의 크기
        int temp = 0;
        int cnt = 1; // 블루레이의 개수
        for(N){
            temp 에 현재 레슨 영상 길이를 더한다.
            if(temp > mIndex){
                temp = 0
                cnt++;
                temp에 현재 레슨 영상 길이를 더한다.
            }
        }
        if(cnt <= M) { // 블루레이의 크기를 뎌 줄여야 함.
            result = mIndex;
            eIndex = mIndex - 1;
            binarySearch(sIndex, eIndex)
        }else{ // 블루레이 크기를 더 늘려야 함.
            sIndex = mIndex + 1;
            binarySearch(sIndex, eIndex)
        }
    }
}


 */