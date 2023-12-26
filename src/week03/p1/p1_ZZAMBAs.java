package src.week03.p1;

/**
 * 문제 링크: https://www.acmicpc.net/problem/2630
 * 메모리: 34792 KB
 * 시간: 460 ms
 * 시간 복잡도: O(N^2 * k) (k는 lgN)
 * 공간 복잡도: O(N^2)
 */

/**
 * 분할 정복 문제. 전체 색종이에 대해 같은 색상인지 판별하고, 아니면 네 군데로 쪼개면서 다시 같은 색상인지 판별.
 *
 * 중요한 부분
 * - 전체 종이의 크기가 N×N(N=2^k, k는 1 이상 7 이하의 자연수)
 * 전체 종이가 모두 같은 색으로 칠해져 있지 않으면 가로와 세로로 중간 부분을 잘라서 똑같은 크기의 네 개의 N/2 × N/2색종이로 나눈다.
 * - 잘라진 종이가 모두 하얀색 또는 모두 파란색으로 칠해져 있거나, 하나의 정사각형 칸이 되어 더 이상 자를 수 없을 때까지 반복한다.
 */

import java.util.Scanner;

public class p1_ZZAMBAs {
    static int MAX_N = 128;
    static boolean[][] board = new boolean[MAX_N][MAX_N]; // false는 white, true는 blue 입니다.
    static int white = 0, blue = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++) {
                int temp = sc.nextInt();
                board[i][j] = temp == 1;
            }

        divideAndConquer(0, 0, N - 1, N - 1);

        System.out.print(white + "\n" + blue);
    }

    public static void divideAndConquer(int startRow, int startCol, int endRow, int endCol) { // 시작점과 끝점을 파라미터로 받으면, 그 사이 모든 칸을 세서 단일 색인지 판단하는 메서드.
        boolean defaultColor = board[startRow][startCol]; // 현재 칸 기본 색상
        boolean isUnicolor = true; // 단일 색 여부

        // 모든 칸을 전부 돌면서
        for(int i = startRow; i <= endRow && isUnicolor; i++) {
            for(int j = startCol; j <= endCol && isUnicolor; j++) {
                if(board[i][j] != defaultColor) // 기본 색상과 다르면
                    isUnicolor = false; // 단일 색에 false로 마크
            }
        }

        if (isUnicolor) { // 단일 색이면 해당 색상 개수를 1 증가시키고 메서드 종료
            if (defaultColor)
                blue++;
            else
                white++;
            return;
        }

        // 단일 색이 아니면 4군데로 쪼개고 다시 이 메서드를 실행.

        int midRow = (startRow + endRow) / 2;
        int midCol = (startCol + endCol) / 2;

        divideAndConquer(startRow, startCol, midRow, midCol);
        divideAndConquer(startRow, midCol + 1, midRow, endCol);
        divideAndConquer(midRow + 1, startCol, endRow, midCol);
        divideAndConquer(midRow + 1, midCol + 1, endRow, endCol);
    }
}
