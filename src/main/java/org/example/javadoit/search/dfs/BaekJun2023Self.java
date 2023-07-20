package org.example.javadoit.search.dfs;


import java.util.Scanner;

public class BaekJun2023Self {
    static int[] startDigits = {2, 3, 5, 7};
    static int[] digits = {1, 3, 5, 7, 9};
    static int N;
    static boolean isPrimary;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        for (int i : startDigits) {
            int depth = 1;
            dfs(i, depth);
        }

    }

    static void dfs(int num, int depth) {
        depth++;
        if (depth > N) {
            System.out.println(num);
            return;
        }
        for (int i : digits) {
            isPrimary = true;
            int currentNum = num * 10 + i;
            int rootVal = (int) Math.sqrt(currentNum);

            for (int j = 3; j <= rootVal; j++) {
                if (currentNum % j == 0) {
                    isPrimary = false;
                    break;
                }
            }

            if (isPrimary) {
                dfs(currentNum, depth);
            }

        }

    }
}