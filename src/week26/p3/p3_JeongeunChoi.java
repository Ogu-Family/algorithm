package algorithm.src.week26.p3;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/16938
 * 메모리: 15400 KB
 * 시간: 144 ms
 * 시간 복잡도: O(2^N)
 * 공간 복잡도: O(M)
 */

/**
 * 조합 + 구현
 * 숫자를 조합을 통해 선택하고, 조건에 맞는 경우 방법의 수를 하나 증가한다.
 */

public class p3_JeongeunChoi {

    private static int N, L, R, X, answer = 0;
    private static int[] A;
    private static boolean[] selected;

    private static void selectProblem(int AIdx, int selectedCnt, int cnt) {
        if (selectedCnt == cnt) {
            if (canUseInCamp()) {
                answer++;
            }
        } else {
            for (int i = AIdx; i < N; i++) {
                selected[i] = true;
                selectProblem(i + 1, selectedCnt + 1, cnt);
                selected[i] = false;
            }
        }
    }

    private static boolean canUseInCamp() {
        int minDifficulty = Integer.MAX_VALUE, maxDifficulty = Integer.MIN_VALUE, difficultySum = 0;
        for (int i = 0; i < N; i++) {
            if (selected[i]) {
                minDifficulty = Math.min(minDifficulty, A[i]);
                maxDifficulty = Math.max(maxDifficulty, A[i]);
                difficultySum += A[i];
            }
        }

        return (difficultySum >= L && difficultySum <= R && (maxDifficulty - minDifficulty) >= X);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        A = new int[N];
        selected = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 2; i <= N; i++) {
            selectProblem(0, 0, i);
        }

        System.out.println(answer);
    }

}
