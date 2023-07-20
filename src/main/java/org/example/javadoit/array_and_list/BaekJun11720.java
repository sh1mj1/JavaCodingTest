package org.example.javadoit.array_and_list;

import java.util.Scanner;
/**
 * 문제 - 백준 11720 숫자의 합 구하기
 * 링크 - https://www.acmicpc.net/problem/11720
 * 등급 -  Bronze 2
 * 특이사항 - Do it Java 알고리즘 책 문제 001 35page
 */
public class BaekJun11720 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        String str = sc.next();
        char[] cNum = str.toCharArray();

        int sum = 0;

        for (char tempChar : cNum) {
            sum += tempChar - '0';
        }
        System.out.println(sum);
    }

    public void strToNum() {
        String sNum = "1234";
        int i1 = Integer.parseInt(sNum);
        int i2 = Integer.valueOf(sNum);

        double d1 = Double.parseDouble(sNum);
        double d2 = Double.valueOf(sNum);

        float f1 = Float.parseFloat(sNum);
        float f2 = Float.valueOf(sNum);

        long l1 = Long.parseLong(sNum);
        long l2 = Long.valueOf(sNum);

        short s1 = Short.parseShort(sNum);
        short s2 = Short.valueOf(sNum);

    }

    public void numToStr() {
        int i = 1234;
        String i1 = String.valueOf(i);
        String i2 = Integer.toString(i);

        double d = 1.23;
        String d1 = String.valueOf(d);
        String d2 = Double.toString(d);

        float f = 1.23F;
        String f1 = String.valueOf(f);
        String f2 = Float.toString(f);

        long l = 1234L;
        String l1 = String.valueOf(l);
        String l2 = Long.toString(l);

        short s = 1234;
        String s1 = String.valueOf(s);
        String s2 = Short.toString(s);
    }

}
