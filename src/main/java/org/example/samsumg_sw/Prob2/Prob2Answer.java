package org.example.samsumg_sw.Prob2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// 960ms
public class  Prob2Answer{

    public static void main(String[] args) throws IOException {

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final PrintWriter pw = new PrintWriter(System.out);

        final Prob2Answer solution = new Prob2Answer();

        final int t = Integer.parseInt(br.readLine().trim());
        for (int i = 1; i <= t; i++) {
            solution.input(br);
            pw.println("#" + i + " " + solution.solve(br));
        }

        pw.flush();
        pw.close();

    }

    private static final int MAX_N = 200_000;
    private static final int MAX_M = 200_000;

    private int n, m, q;
    private final int[][] maxRow = new int[MAX_N + 1][3];
    private final int[][] maxColumn = new int[MAX_M + 1][3];

    void input(final BufferedReader br) throws IOException {

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            maxRow[i][0] = 0;
        }
        for (int i = 1; i <= m; i++) {
            maxColumn[i][0] = 0;
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            final int[] mr = maxRow[i];
            for (int j = 1; j <= m; j++) {
                final int x = Integer.parseInt(st.nextToken());
                final int[] mc = maxColumn[j];
                if (mr[0] < x) {
                    mr[0] = x;
                    mr[1] = i;
                    mr[2] = j;
                }
                if (mc[0] < x) {
                    mc[0] = x;
                    mc[1] = i;
                    mc[2] = j;
                }
            }
        }
    }


    int solve(final BufferedReader br) throws IOException {

        StringTokenizer st;

        int numPeaks = 0;
        for (int i = 1; i <= n; i++) {
            final int[] mr = maxRow[i];
            for (int j = 1; j <= m; j++) {
                if (mr[0] == maxColumn[j][0]) {
                    numPeaks++;
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < q; i++) {

            st = new StringTokenizer(br.readLine());
            final int r = Integer.parseInt(st.nextToken());
            final int c = Integer.parseInt(st.nextToken());
            final int x = Integer.parseInt(st.nextToken());

            final int[] mr = maxRow[r];
            final int[] mc = maxColumn[c];

            if (mr[0] == mc[0]) {

                mr[0] = mc[0] = x;

            } else {

                if (mr[0] < x) {
                    if (maxRow[mr[1]][0] == maxColumn[mr[2]][0]) {
                        numPeaks--;
                    }
                    mr[0] = x;
                    mr[1] = r;
                    mr[2] = c;
                }

                if (mc[0] < x) {
                    if (maxRow[mc[1]][0] == maxColumn[mc[2]][0]) {
                        numPeaks--;
                    }
                    mc[0] = x;
                    mc[1] = r;
                    mc[2] = c;
                }

                if (mr[0] == mc[0]) {
                    numPeaks++;
                }
            }
            answer += numPeaks;

        }
        return answer;

    }

}