package src.week05.p2;

/**
 * 문제 링크: https://www.acmicpc.net/problem/7576
 * 메모리: 348300 KB
 * 시간: 1700 ms
 * 시간 복잡도: O(N * M)
 * 공간 복잡도: O(N * M)
 */

/**
 * 그래프 탐색 문제. N, M이 가로인지 세로인지 헷갈려서 짜증났던 문제.
 * 실제 토마토 수랑 BFS를 완전히 돌렸을 때 익은 토마토 수랑 다르면 모두 익지 않은 것이므로 -1 출력
 *
 * 문제에서 중요한 부분
 * - 정수 1은 익은 토마토, 정수 0은 익지 않은 토마토, 정수 -1은 토마토가 들어있지 않은 칸
 * - 토마토가 모두 익을 때까지의 최소 날짜를 출력해야 한다.
 * - 만약, 저장될 때부터 모든 토마토가 익어있는 상태이면 0을 출력해야 하고, 토마토가 모두 익지는 못하는 상황이면 -1을 출력해야 한다.
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class p2_ZZAMBAs {
    static int[] dr = {1, 0, -1, 0}, dc = {0, 1, 0, -1};
    static int[][] board;
    static int totalTomatoes, matureTomatoes, day = -1; // 실제 토마토 전체 개수, 익은 토마토 수 추적, 다 익은 날짜
    static Queue<Pair> q = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt(), N = sc.nextInt();
        board = new int[N + 2][M + 2];
        totalTomatoes = N * M;

        for(int i = 0; i < N + 2; i++)
            for(int j = 0; j < M + 2; j++){
                if(i == 0 || j == 0 || i == N + 1 || j == M + 1)
                    board[i][j] = -1;
                else{
                    board[i][j] = sc.nextInt();

                    if(board[i][j] == 1){
                        matureTomatoes++;
                        q.add(new Pair(i, j));
                    }
                    else if(board[i][j] == -1)
                        totalTomatoes--;
                }
            }

        bfs();

        System.out.print(matureTomatoes == totalTomatoes ? day : -1);
    }

    static void bfs(){
        while(!q.isEmpty()){
            int qSize = q.size();

            for(int i = 0; i < qSize; i++) {
                Pair cur = q.poll();

                for(int j = 0; j < 4; j++) {
                    int nextR = cur.r + dr[j];
                    int nextC = cur.c + dc[j];

                    if(board[nextR][nextC] == 0) {
                        board[nextR][nextC] = 1;
                        matureTomatoes++;
                        q.add(new Pair(nextR, nextC));
                    }
                }

            }

            day++;
        }
    }

    static class Pair{
        public int r, c;

        public Pair(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
}
