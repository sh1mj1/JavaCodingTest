package org.example.javadoit.number_theory.prime_number;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BaekJun1456Self {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        int ans = 0;

        int last = (int) Math.sqrt(B);
        int[] pNA = new int[last + 1]; // 최대 10^7 크기
        List<Integer> pNL = new ArrayList<>();
        for (int i = 2; i <= last; i++) {
            pNA[i] = i;
        }

        for (int i = 2; i <= last; i++) {
            int curNum = pNA[i];
            if (curNum != 0) {
                int j = 2;
                pNL.add(curNum);
                while (i * j <= last) {
                    pNA[i * j] = 0;
                    j++;
                }
            }
        }

        for (double curNum : pNL) {
            double temp = 1;
            while (true) {
                temp *= curNum;
                if (curNum >= A / temp && curNum <= B / temp) {
                    ans++;
                } else if (curNum > B / temp) {
                    break;
                }
            }
        }
        System.out.println(ans);
    }
}

/*
슈도 코드

A: 왼쪽 범위, B: 오른쪽 범위
ans = 0

pNA: 소수 판별 집합.
pNA을 인덱스와 같은 값으로 2 .. .루트(B) 초기화
pNL : 소수인 값만 저장할 리스트

for(2부터 루트(B) 까지 ){
    if(현재 수가 0 이 아니다){
        현재수를 리스트에 추가
        배열에서 현재 수의 배수인 인덱스의 값은 모두 0 으로
    }
}

for(pNL 의 모든 수에 대해){
    j = 2
    while( 현재수의 i 제곱한 수 x이 범위에 있으면 계속){
        cnt++
        j++
    }
}

sout(cnt)

5324 894739
941² -> 885481

942² -> 887364

 */