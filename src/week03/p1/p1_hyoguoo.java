/**
 * 문제 링크: https://www.acmicpc.net/problem/2630
 * 메모리: 18408KB
 * 시간: 200ms
 * 시간 복잡도: O(N^2 * logN)
 * 공간 복잡도: O(N^2)
 */

/**
 * 분할 정복
 * 전체 색종이에 대해 같은 색상인지 검증
 * 맞는 경우 해당 색상 개수 증가
 * 아닌 경우 4등분하여 다시 검증
 * 4등분하여 검증하는 과정에서 네 영역으로 나누어 재귀 호출
 *
 * 시간 복잡도는 아래 링크 참고하였습니다..!(재귀가 들어가면 시간 복잡도 계산이 어려운 것 같네요)
 * https://www.acmicpc.net/board/view/112536
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ColorPaper {

    static int zeroCount = 0;
    static int oneCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine());
        int[][] paper = new int[n][n];
        for (int i = 0; i < n; i++) {
            paper[i] = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        recursion(paper, n, 0, 0);

        System.out.print(zeroCount + "\n" + oneCount);
    }

    private static void recursion(int[][] paper, int length, int startX, int startY) {
        // 같은 색상인지 검증
        if (isCompletedSquare(paper, length, startX, startY, paper[startX][startY])) return;

        int half = length / 2;

        // 1x1 색종이인 경우
        if (half == 0) {
            recursion(paper, half, startX, startY);
            return;
        }

        // 네 영역으로 나누어 재귀 호출
        recursion(paper, half, startX, startY);
        recursion(paper, half, startX + half, startY);
        recursion(paper, half, startX, startY + half);
        recursion(paper, half, startX + half, startY + half);
    }

    private static boolean isCompletedSquare(int[][] paper, int length, int startX, int startY, int reference) {
        for (int x = startX; x < startX + length; x++) {
            for (int y = startY; y < startY + length; y++) {
                if (paper[x][y] != reference) return false;
            }
        }

        if (reference == 0) zeroCount++;
        if (reference == 1) oneCount++;

        return true;
    }
}
