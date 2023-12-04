package week01.p1;

/*
 * 문제 링크: https://www.acmicpc.net/problem/2606
 * 메모리: 14100 KB
 * 시간: 128 ms
 * 시간 복잡도: O(N^2)
 * 공간 복잡도: O(N^2) (?)
 *
 * 1번 컴퓨터를 시작으로 DFS 탐색
 * 1번 컴퓨터와 연결된 모든 컴퓨터를 탐색해 수를 세는 방식으로 인접 행렬 이용해 풀이
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1_eugene {

    static int N, M;
    static int cnt = 0;
    static int[][] map;
    static boolean[] visited;
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int dfs(int idx) {
        visited[idx] = true;

        for(int i=1; i<=N; i++) {
            if(map[idx][i] == 1 && !visited[i]) {
                cnt++;
                dfs(i);
            }
        }

        return cnt;
    }
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        map = new int[N+1][N+1];
        visited = new boolean[N+1];

        for(int i=0; i<M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a][b] = 1;
            map[b][a] = 1;
        }

        System.out.println(dfs(1));
    }
}
