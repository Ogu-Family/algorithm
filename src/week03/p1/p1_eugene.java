package week03.p1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1_eugene {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int white = 0, blue = 0;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        StringTokenizer st;
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        divide(0, 0, N);
        System.out.print(white+"\n"+blue);
    }

    public static void divide(int i, int j, int size) {
        if(isColored(i, j, size)) {
            if(map[i][j] == 0) {
                white++;
            }else {
                blue++;
            }
            return;
        }

        divide(i, j, size/2);
        divide(i, j+size/2, size/2);
        divide(i+size/2, j, size/2);
        divide(i+size/2, j+size/2, size/2);
    }

    public static boolean isColored(int i, int j, int size) {
        int start = map[i][j];

        for(int n=i; n<i+size; n++) {
            for(int m=j; m<j+size; m++) {
                if(map[n][m] != start) return false;
            }
        }
        return true;
    }
}

/**
 * 문제 링크: https://www.acmicpc.net/problem/2630
 * 메모리: 16900 KB
 * 시간: 192 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N * N)
 *
 * 이차원 배열로 색종이 입력받기
 * 분할정복 + 재귀 모든 구역 탐색
 */
