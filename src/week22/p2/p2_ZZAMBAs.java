package src.week22.p2;

/**
 * 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/1469
 * 메모리: 20032 KB
 * 시간: 240 ms
 * 시간 복잡도: O(N!)으로 추정
 * 공간 복잡도: O(N)
 */

/**
 * 백트래킹
 *
 * 1. X 원소를 오름차순 정렬
 * 2. 앞에서부터 선택하면서 S를 채움. 채울 수 없으면 다른 원소를 선택하거나 전 단계로 돌아감.
 * 3. S가 만들어지면 S를 출력하고 못 만들어지면 -1을 출력
 */

import java.util.*;

public class p2_ZZAMBAs{
    static int N;
    static int[] X, S;
    static boolean sw;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        X = new int[N];
        visited = new boolean[N];
        S = new int[N * 2];
        for (int i = 0; i < N; i++)
            X[i] = sc.nextInt();
        for (int i = 0; i < N * 2; i++)
            S[i] = -1;
        Arrays.sort(X);

        backtracking(0);

        if (!sw)
            System.out.println(-1);
        else
            Arrays.stream(S).forEach(v -> System.out.print(v + " "));
    }

    static void backtracking(int idx) {
        if (idx == N * 2) {
            sw = true;
            return;
        }

        if (S[idx] != -1) {
            backtracking(idx + 1);
            return;
        }

        for (int i = 0; i < N; i++) {
            int num = X[i];
            int next = idx + num + 1;
            if (next < N * 2 && !visited[i] && S[next] == -1) {
                visited[i] = true;
                S[next] = S[idx] = num;
                backtracking(idx + 1);

                if (sw)
                    return;

                S[next] = S[idx] = -1;
                visited[i] = false;
            }
        }
    }
}

