package org.example.samsumg_sw.Prob1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//360ms
public class Prob1Answer {

    public static void main(String[] args) throws IOException {

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final PrintWriter pw = new PrintWriter(System.out);

        final Prob1Answer solution = new Prob1Answer();

        final int t = Integer.parseInt(br.readLine().trim());
        for (int i = 1; i <= t; i++) {
            solution.input(br);
            pw.println("#" + i + " " + solution.solve());
        }

        pw.flush();
        pw.close();

    }

    private static final int MAX_N = 400;
    private static final int MAX_C = 4_000_000;

    int n, m;
    int[][] adj = new int[MAX_N + 1][MAX_N + 1];
    int[][] dist = new int[MAX_N + 1][MAX_N + 1];

    void input(final BufferedReader br) throws IOException {

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 인접행렬 MAX 로 초기화
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                adj[i][j] = MAX_C;
            }
        }

        // 입력으로 인접행렬 변경
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            final int a = Integer.parseInt(st.nextToken());
            final int b = Integer.parseInt(st.nextToken());
            final int c = Integer.parseInt(st.nextToken());
            adj[a][b] = Math.min(adj[a][b], c);
        }

    }

    int solve() {

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dist[i][j] = adj[i][j];
            }
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int answer = MAX_C;
        for (int i = 1; i <= n; i++) {
            answer = Math.min(answer, dist[i][i]);
        }

        return answer == MAX_C ? -1 : answer;

    }

}

