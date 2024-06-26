package src.week29.p2;

/**
 * 문제 링크: https://www.acmicpc.net/problem/5179
 * 메모리: 17784 KB
 * 시간: 196 ms
 * 시간 복잡도: O(KNlogN)
 * 공간 복잡도: O(NM)
 */

/**
 * 구현
 *
 * 플레이어 객체가 자신이 푼 문제에 대한 정보를 가지도록 하여 구현했습니다.
 */

import java.util.*;
import java.util.stream.*;

public class p2_ZZAMBAs {

    static final int SOLVED = -1;
    static final int INCORRECT = 0;
    static final Scanner sc = new Scanner(System.in);
    static final StringBuilder sb = new StringBuilder();
    static int K, N, M, P;
    static List<Player> players;

    public static void main(String[] args) {
        K = sc.nextInt();
        for (int i = 1; i <= K; i++) {
            init(i);
            cal();
            organize();
        }

        System.out.print(sb);
    }

    static void init(int curDataNum) {
        sb.append("Data Set ").append(curDataNum).append(":\n");

        M = sc.nextInt(); N = sc.nextInt(); P = sc.nextInt();

        players = IntStream.range(0, P).mapToObj(i -> new Player(i, M)).collect(Collectors.toList());
    }

    static void cal() {
        for (int i = 0; i < N; i++) {
            int p = sc.nextInt() - 1, m = sc.next().charAt(0) - 65, t = sc.nextInt(), j = sc.nextInt(); // 참가자, 문제, 푼 시간, 정답 여부

            Player player = players.get(p);

            if (player.incorrectCnt[m] == SOLVED) {
                continue;
            }

            if (j == INCORRECT) {
                player.incorrectCnt[m]++;
                continue;
            }

            player.solve(m, t);
        }
    }

    static void organize() {
        players.sort(
            Comparator.comparing(Player::getSolved).reversed().thenComparing(Player::getScore));

        players.forEach(
            player -> sb.append(player.id + 1).append(" ").append(player.solved).append(" ")
                .append(player.score).append('\n'));

        sb.append('\n');
    }

    static class Player {

        private final int id;
        private final int[] incorrectCnt;
        private int solved;
        private int score;

        Player(int id, int problemNum) {
            this.id = id;
            this.solved = 0;
            this.score = 0;
            incorrectCnt = new int[problemNum + 1];
        }

        public void solve(int problem, int time) {
            this.solved++;
            this.score += time + incorrectCnt[problem] * 20;
            this.incorrectCnt[problem] = SOLVED;
        }

        public int getSolved() {
            return solved;
        }

        public int getScore() {
            return score;
        }
    }
}

