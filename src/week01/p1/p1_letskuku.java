package week01.p1;

/**
 * 문제 링크: https://www.acmicpc.net/problem/2606
 * 메모리: 14424 KB
 * 시간: 120 ms
 * 시간 복잡도: O(N^2)
 * 공간 복잡도: O(N^2)
 */

/*
1. 네크워크 상에서 직접 연결되어 있는 컴퓨터 쌍을 입력받아
2. 1번 컴퓨터가 웜 바이러스에 걸렸을 때, 1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수 계산
3. 결과 출력
 */

import java.io.*;
import java.util.*;

public class p1_letskuku {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int totalCom = Integer.parseInt(br.readLine());
        int totalNet = Integer.parseInt(br.readLine());

        boolean[][] board = new boolean[totalCom + 1][totalCom + 1];
        boolean[] visit = new boolean[totalCom + 1];

        for (int i = 0; i < totalNet; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            board[x][y] = true;
            board[y][x] = true;
        }

        int ans = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        visit[1] = true;
        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int i = 1; i <= totalCom; i++) {
                if (board[cur][i] && !visit[i]) {
                    q.add(i);
                    visit[i] = true;
                    ans++;
                }
            }
        }

        System.out.println(ans);
    }
}
