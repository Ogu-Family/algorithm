package algorithm.src.week18.p3;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/16926
 * 메모리: 41236 KB
 * 시간: 1516 ms
 * 시간 복잡도: O(R*N)
 * 공간 복잡도: O(N*M)
 */

/**
 * 구현
 * 사각형 테두리를 하나씩 안으로 만들어가면서 회전한다.
 * 이때 회전 횟수가 사각형 테두리의 원소 수 만큼 되는 경우 원상 복귀 되는 점을 고려하여 회전 횟수를 사각형 테두리의 원소 수로 나눈 나머지 값으로 설정한다.
 */

public class p3_JeongeunChoi {

    private static int[][] arr;

    private static void rotate(int sX, int sY, int h, int w, int R) {
        for (int i = 0; i < R; i++) {

            int first = arr[sX][sY];
            // 위쪽
            for (int j = sY; j < sY + w - 1; j++) {
                arr[sX][j] = arr[sX][j + 1];
            }

            // 오른쪽
            for (int j = sX; j < sX + h - 1; j++) {
                arr[j][sY + w - 1] = arr[j + 1][sY + w - 1];
            }

            // 아래쪽
            for (int j = sY + w - 1; j > sY; j--) {
                arr[sX + h - 1][j] = arr[sX + h - 1][j - 1];
            }

            // 왼쪽
            for (int j = sX + h - 1; j > sX + 1; j--) {
                arr[j][sY] = arr[j - 1][sY];
            }
            arr[sX + 1][sY] = first;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N, M, R;
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < Math.min(N, M) / 2; i++) {
            int h = N - i * 2, w = M - i * 2;
            rotate(i, i, h, w, R % (h * 2 + w * 2 - 4));
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

    }

}
