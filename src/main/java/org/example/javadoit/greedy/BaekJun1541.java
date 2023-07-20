package org.example.javadoit.greedy;

import java.util.Scanner;

/**
 * 문제 - 백준 1541 잃어버린 괄호
 * 링크 - https://www.acmicpc.net/problem/1541
 * 등급 -  silver 2
 * 특이사항 - Do it Java 알고리즘 책 문제 036
 */
public class BaekJun1541 {

    static int answer = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String example = scanner.nextLine();
        String[] str = example.split("-");

        for (int i = 0; i < str.length; i++) {
            int temp = mySum(str[i]);
            if (i == 0) {
                answer += temp;
            } else {
                answer -= temp;
            }
        }
        System.out.println(answer);
    }

    // 나뉜 그룹의 더하기 연산 수행 함수
    static int mySum(String a) {
        int sum = 0;
        String temp[] = a.split("[+]");
        for (int i = 0; i < temp.length; i++) {
            sum += Integer.parseInt(temp[i]);
        }
        return sum;
    }
}


/*
슈도 코드
answer: 정답 변수
들어온 데이터를 "-" 기호를 기준으로 split() 수행

for( 나눠진 데이터 개수만큼 반복){
    결과값 = mySum() 함수 수행
    if(가장 앞 데이터일 때): answer += 결과값
    else: answer 에 결과값 빼기
}

answer 출력

mySum(){ // 현재 String 에 있는 수를 모두 더하는 함수
    현재 들어온 String 값을 "+" 기호 기준으로 split() 함수 수행
    for(나눠진 데이터 개수만큼){
        String 값을 Integer 형으로 변환, 리턴값에 더하기
    }
    전체 합 리턴
}


 */