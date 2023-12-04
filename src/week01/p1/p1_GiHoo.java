package src.week01.p1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1_GiHoo {
    static int c;
    static int[][] network;
    static boolean[] visited;
    static int answer = 0;

    private static void DFS(int number) {
        for (int i = 0; i < c; i++) {
            if (network[number-1][i] == 1 && !visited[i]) {
                visited[i] = true;
                answer++;
                DFS(i+1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        c = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        network = new int[c][c];
        visited = new boolean[c];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int c1 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            network[c1 - 1][c2 - 1] = 1;
            network[c2 - 1][c1 - 1] = 1;
        }

        int start_number = 1;

        visited[start_number - 1] = true;
        DFS(start_number);

        System.out.print(answer);
    }
}


/**
 * 문제 링크: https://www.acmicpc.net/problem/2606
 * 메모리: 14136 KB
 * 시간: 128 ms
 * 시간 복잡도: O(n^2)
 * 공간 복잡도: O(n^2) -> computer의 수
 *
 *
 * 서로 연결된 컴퓨터를 network 배열에 표현
 * 1번 컴퓨터를 기준으로 시작하여 서로 연결된 컴퓨터를 DFS로 탐색한다.
 * 이때 이미 방문한 컴퓨터 번호를 visited 배열을 통해 재방문 하지 않도록 한다.
 *
 * 문제는 1번을 통해 감염된 컴퓨터의 수
 * 1번은 포함하면 안된다.
 */
