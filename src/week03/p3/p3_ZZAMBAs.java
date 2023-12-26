package src.week03.p3;

/**
 * 문제 링크: https://www.acmicpc.net/problem/23796
 * 메모리: 18420 KB
 * 시간: 236 ms
 * 시간 복잡도: O(N^2) (N은 보드판 한 변의 길이. 여기서는 8로 고정)
 * 공간 복잡도: O(N^2)
 */

/**
 * 구현 문제. 투 포인터 개념 사용. 8 * 8 빈 배열(tempBoard) 하나를 선언합니다. 방향키에 따라 이 빈 배열을 채워 갑니다.
 * 원본 배열 포인터가 하나 존재하고, 빈 배열 포인터가 존재합니다. 원본 배열 포인터를 움직이며 빈 공간이면 넘어가고 숫자면 빈 배열 포인터와 비교하며 값을 채웁니다.
 * 방향키가 L인 경우를 예로 들어보겠습니다. 왼쪽부터 오른쪽으로 원본 배열을 보는데, 빈 배열 포인터가 가리키는 값이 0이고, 원본 배열 포인터가 가리키는 값이
 * 숫자면 빈 배열 포인터에 그 값을 채우고 포인터를 이동합니다. 만약 빈 배열 포인터 값과 원본 배열 포인터 값이 같다면 둘을 합하고 빈 배열 포인터를 다음으로 이동합니다.
 * 마지막으로 빈 배열 포인터 값과 원본 배열 포인터 값이 다르면 빈 배열 포인터를 이동한 뒤 그 위치에 원본 배열 값을 넣습니다.
 * 이를 배열 끝까지 반복하여 완성합니다. 이 tempBoard를 출력하면 답이 됩니다.
 *
 * 문제에서 중요한 부분
 * - 눌린 방향키와 같은 방향으로 타일들을 벽 끝까지 밀어 넣는다.
 * - 세 타일 이상 연속해있다면 방향키가 가리키는 쪽의 벽에 가까운 쪽부터 두 개씩 합쳐진다.
 * - 한 번의 방향키 입력에 한 타일이 두 번 이상 합쳐지는 경우는 없다.
 */

import java.util.Scanner;

public class p3_ZZAMBAs {
    static final Scanner sc = new Scanner(System.in);
    static final int BOARD_LEN = 8;
    static long[][] board = new long[BOARD_LEN][BOARD_LEN];
    static long[][] tempBoard = new long[BOARD_LEN][BOARD_LEN];
    static int tempCursor;

    public static void main(String[] args) {
        for (int i = 0; i < BOARD_LEN; i++)
            for (int j = 0; j < BOARD_LEN; j++)
                board[i][j] = sc.nextLong();

        String dir = sc.next();

        switch (dir) {
            case "U": up(); break;
            case "D": down(); break;
            case "L": left(); break;
            case "R": right(); break;
        }

        for (int i = 0; i < BOARD_LEN; i++) {
            for (int j = 0; j < BOARD_LEN; j++) {
                System.out.print(tempBoard[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void up() {
        for (int j = 0; j < BOARD_LEN; j++) {
            tempCursor = 0;
            for (int i = 0; i < BOARD_LEN; i++) {
                fillColumn(i, j, 1);
            }
        }
    }

    private static void down() {
        for (int j = 0; j < BOARD_LEN; j++) {
            tempCursor = BOARD_LEN - 1;
            for (int i = BOARD_LEN - 1; i >= 0; i--) {
                fillColumn(i, j, -1);
            }
        }
    }

    private static void left() {
        for (int i = 0; i < BOARD_LEN; i++) {
            tempCursor = 0;
            for (int j = 0; j < BOARD_LEN; j++) {
                fillRow(i, j, 1);
            }
        }
    }

    private static void right() {
        for (int i = 0; i < BOARD_LEN; i++) {
            tempCursor = BOARD_LEN - 1;
            for (int j = BOARD_LEN - 1; j >= 0; j--) {
                fillRow(i, j, -1);
            }
        }
    }

    private static void fillColumn(int i, int j, int plusValue) {
        if (board[i][j] == 0L)
            return;

        if (tempBoard[tempCursor][j] == 0L) {
            tempBoard[tempCursor][j] = board[i][j];
        }
        else if (tempBoard[tempCursor][j] == board[i][j]){
            tempBoard[tempCursor][j] *= 2L;
            tempCursor += plusValue;
        }
        else {
            tempCursor += plusValue;
            tempBoard[tempCursor][j] = board[i][j];
        }
    }

    private static void fillRow(int i, int j, int plusValue) {
        if (board[i][j] == 0L)
            return;

        if (tempBoard[i][tempCursor] == 0L) {
            tempBoard[i][tempCursor] = board[i][j];
        }
        else if (tempBoard[i][tempCursor] == board[i][j]){
            tempBoard[i][tempCursor] *= 2L;
            tempCursor += plusValue;
        }
        else {
            tempCursor += plusValue;
            tempBoard[i][tempCursor] = board[i][j];
        }
    }
}
