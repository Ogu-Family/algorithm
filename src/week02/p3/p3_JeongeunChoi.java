package algorithm.src.week02.p3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p3_JeongeunChoi {

    static final char OUR_SOLDIER = 'W', OPPONENT_SOLDIER = 'B';
    static final int OUR_TEAM_NUM = 1, OPPONENT_TEAM_NUM = 2;
    static final int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
    static int weight, height, ourTeamPower = 0, opponentTeamPower = 0;
    static int[][] battleGround = new int[101][101];
    static boolean[][] visited = new boolean[101][101];

    private static void calculateTeamPower() {
        Queue<Soldier> soldierQ = new LinkedList<>();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < weight; j++) {
                if (!visited[i][j]) {
                    int teamNum = battleGround[i][j], soldierCnt = 0;
                    visited[i][j] = true;
                    soldierQ.add(new Soldier(teamNum, i, j));
                    while (!soldierQ.isEmpty()) {
                        Soldier soldier = soldierQ.poll();
                        soldierCnt++;
                        for (int k = 0; k < 4; k++) {
                            int nx = soldier.x + dx[k], ny = soldier.y + dy[k];
                            if (checkBattleGroundIndexOutOfRange(nx, ny) && battleGround[nx][ny] == teamNum && !visited[nx][ny]) {
                                visited[nx][ny] = true;
                                soldierQ.add(new Soldier(teamNum, nx, ny));
                            }
                        }
                    }
                    if (teamNum == OUR_TEAM_NUM) {
                        ourTeamPower += Math.pow(soldierCnt, 2);
                    } else if (teamNum == OPPONENT_TEAM_NUM) {
                        opponentTeamPower += Math.pow(soldierCnt, 2);
                    }
                }
            }
        }

    }

    private static boolean checkBattleGroundIndexOutOfRange(int x, int y) {
        return x >= 0 && x < height && y >= 0 && y < weight;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        weight = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());
        for (int i = 0; i < height; i++) {
            String soldiers = br.readLine();
            for (int j = 0; j < weight; j++) {
                if (soldiers.charAt(j) == OUR_SOLDIER) {
                    battleGround[i][j] = OUR_TEAM_NUM;
                } else if (soldiers.charAt(j) == OPPONENT_SOLDIER) {
                    battleGround[i][j] = OPPONENT_TEAM_NUM;
                }
            }
        }

        calculateTeamPower();

        System.out.println(ourTeamPower + " " + opponentTeamPower);
    }

    static class Soldier {
        int teamNum, x, y;

        public Soldier(int teamNum, int x, int y) {
            this.teamNum = teamNum;
            this.x = x;
            this.y = y;
        }
    }
}

/**
 * 문제 링크: https://www.acmicpc.net/problem/19941
 * 메모리: 16732KB
 * 시간: 176ms
 * 시간 복잡도: O((N+M)^2) N은 전쟁터 가로 크기, M은 전쟁터 세로 크기
 * 공간 복잡도: O(101*101)
 */

/**
 * BFS로 구현
 * 병사의 상하좌우를 탐색하여, 뭉쳐있는 병사의 인원을 구한 후
 * 위력을 계산한다.
 */
