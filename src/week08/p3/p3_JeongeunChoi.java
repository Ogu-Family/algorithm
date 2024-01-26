package algorithm.src.week08.p3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 링크: https://www.acmicpc.net/problem/1937
 * 메모리: 37340 KB
 * 시간: 544 ms
 * 시간 복잡도: O(N^4) 보다 작음
 * 공간 복잡도: O(N^2)
 */

/**
 * DP + DFS
 * 처음엔 단순히 dfs 만으로 최대 이동 칸을 계산하였으나 시간 초과가 발생하여
 * 가지치기가 필요하다고 판단을 하였으나
 * 구현하다가 코드가 점점 복잡해져서 이상함을 깨닫고 결국 풀이 참고했습니다..ㅎㅎ
 * 해당 지점에서 시작하여 최대 이동 수를 저장하는 maxCnt 배열을 두고
 * maxCnt 배열에 값이 있는 경우 dfs가 이루어지지 않고 바로 해당 값을 반환하도록 해야 합니다.
 * dfs 구현 시, visited 배열을 사용하는 편인데 여기서는 이전 먹이보다 큰 먹이를 먹는다는 조건으로 방문했던 먹이로 다시 돌아갈 일이 없어서 필요가 없습니다.
 * 저는 visited 계속 두고 구현했는데 필요가 없더라구요!
 */

public class p3_JeongeunChoi {

    static int N, answer = 0;
    static int[][] forest, maxCnt;
    static boolean[][] visited; // 어차피 이전 먹이보다 다음 먹이가 더 커야하므로 이전의 먹이로 돌아가는 경우가 없어서 필요 없음.
    static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};

    private static int dfs(int x, int y) {
        if (maxCnt[x][y] > 0) {
            return maxCnt[x][y];
        }

        int bamboo = forest[x][y];

        maxCnt[x][y]++;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if (checkOutOfRange(nx, ny) && forest[nx][ny] > bamboo) {
                maxCnt[x][y] = Math.max(maxCnt[x][y], dfs(nx, ny) + 1);
            }
        }

        return maxCnt[x][y];
    }

    private static boolean checkOutOfRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        forest = new int[N][N];
        maxCnt = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                forest[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer = Math.max(answer, dfs(i, j));
            }
        }

        System.out.println(answer);
    }
}
