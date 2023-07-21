package org.example.javadoit.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJun1541Self {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] equ = br.readLine().strip().toCharArray();
        br.close();

        int ans = 0;
        int curNum = 0;
        boolean isMinus = false;

        for (int i = 0; i < equ.length; i++) {
            int x = equ[i];
            if (x >= '0' && x <= '9') {
                curNum = curNum * 10 + x - '0';
                if (i == equ.length - 1) {
                    if (isMinus) {
                        ans -= curNum;
                    } else {
                        ans += curNum;
                    }
                }
                continue;
            }

            if (x == '-') {
                if (isMinus) {
                    ans -= curNum;
                } else {
                    ans += curNum;
                }
                isMinus = true;
                curNum = 0;
                continue;
            }
            if (x == '+') {
                if (isMinus) {
                    ans -= curNum;
                } else {
                    ans += curNum;
                }
                curNum = 0;
            }
        }
        System.out.println(ans);
    }
}


/*
슈도 코드

정답 ans = 0;
식 입력받기 equ
현재 수 curNum = 0;
while(equ 에 대해 끝까지){
    현재 입력값이 숫자이면
        curNum = curNum * 10 + 현재 수
        char 형으로 한글자씩 읽기 때문에 여러 문자가 연속으로 숫자이면
        해당 숫자를 제대로 읽어야함.(121 같은 경우) //
        현재 입력값이 마지막 값이라면
            이전에 - 가 나왔다면
                정답에 curNum 빼기
            아니라면
                정답헤 curNum 더하기
    continue;

    현재 입력값이 - 이면
        이전에 - 가 나왔으면
            정답에 curNum 빼기
        아니라면
            정답에 curNum 더하기
        - 가 나왔음을 기록
        curNum = 0;
        continue;
    현재 입력값이 + 이면
        이전에 - 가 나왔으면
            정답에 curNum 빼기
        아니라면
            정답에 curNum 더하기

정답 출력
 */