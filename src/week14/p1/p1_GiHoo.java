package src.week14.p1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 문제 링크: https://www.acmicpc.net/problem/1992
 * 메모리: 14100 KB
 * 시간: 128 ms
 * 시간 복잡도: O(N^2)
 * 공간 복잡도: O(N^2)
 */

/**
 * 분할 정복 문제
 *
 * 0, 0 부터 시작하여 압축이 가능하다면 압축
 * 불가능하다면 크기를 줄여가며 압축 가능한지 확인
 * 크기를 줄인 후에는 영역이 나뉘기에 전, 후로 () 를 붙인다.
 *
 * 해당 문제 헷갈려서 이전에 풀었던 색종이 문제 참고했습니다..
 * 같은 류의 문제인 것 같아요
 * https://www.acmicpc.net/problem/2630
 */

public class p1_GiHoo {

    static int N;
    static int[][] matrix;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        matrix = new int[N][N];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                matrix[i][j] = input.charAt(j) - '0';
            }
        }

        divide(0, 0, N);

        System.out.println(sb);
    }

    public static void divide(int x, int y, int size) {
        if (isPossible(x, y, size)) {
            sb.append(matrix[x][y]);
            return;
        }

        int newSize = size / 2;

        sb.append('(');

        // 왼위, 오위, 왼아, 오아
        divide(x, y, newSize);
        divide(x, y + newSize, newSize);
        divide(x + newSize, y, newSize);
        divide(x + newSize, y + newSize, newSize);

        sb.append(')');


    }

    private static boolean isPossible(int x, int y, int size) {
        int value = matrix[x][y];

        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (value != matrix[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }
}
