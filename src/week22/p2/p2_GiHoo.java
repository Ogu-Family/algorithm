package src.week22.p2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제 링크: https://www.acmicpc.net/problem/1469
 * 메모리: 15168 KB
 * 시간: 128 ms
 * 시간 복잡도: O(N!)
 * 공간 복잡도: O(N)
 */

/**
 * 백트래킹 너무 어렵습니다..
 *
 * S(정답) 배열을 -1로 초기화한 후(값이 0부터 들어올 수 있음) X 배열을 정렬하고 백트래킹 시작. -> 사전 순으로 출력
 * 백트래킹 시작하는데, 주의할 부분은 depth가 2*N 까지 진행되어야 하기 때문에 S[depth] != -1 이라는 조건이 들어간다.
 * depth가 2*N 에 도달하더라도, -1이 포함된 배열이 있을 수 있기 때문에 해당 배열을 거르기 위한 조건문 또한 포함
 */

public class p2_GiHoo {
    static int N;
    static int[] X;
    static int[] S;
    static boolean[] visited;
    static boolean isAnswer = false;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        X = new int[N];
        visited = new boolean[N];
        S = new int[N * 2];

        for (int i = 0; i < 2 * N; i++) {
            S[i] = -1;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            X[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(X);

        dfs(0);

        System.out.print((isAnswer) ? sb : -1);
    }

    private static void dfs(int depth) {
        if (!isAnswer) {

            if (depth == 2 * N) {
                for (int i = 0; i < 2 * N; i++) {
                    if (S[i] == -1) return;
                }

                isAnswer = true;
                for (int i = 0; i < 2 * N; i++) {
                    sb.append(S[i]).append(" ");
                }
                return;
            }

            if (S[depth] != -1) {
                dfs(depth+1);
            }

            for (int i = 0; i < N; i++) {
                if (depth + X[i] + 1 < 2 * N && !visited[i] && S[depth] == -1 && S[depth + X[i] + 1] == -1) {
                    visited[i] = true;
                    S[depth] = S[depth + X[i] + 1] = X[i];
                    dfs(depth + 1);
                    S[depth] = S[depth + X[i] + 1] = -1;
                    visited[i] = false;
                }
            }

        }
    }
}
