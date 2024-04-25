package algorithm.src.week20.p3;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/2660
 * 시간 복잡도: O(N * MlogN) M은 간선의 개수, N은 노드의 개수
 * 공간 복잡도: O(N ^ 2)
 */

/**
 * 다익스트라 알고리즘
 * 1 ~ N번까지의 사람에 대해 다익스트라 알고리즘을 반복하여 점수를 구한다.
 * 특정 사람을 거쳐 아는 사람의 거리가 이전에 계산한 값보다 짧으면 갱신한다.
 * visited 배열로 방문 여부 체크한다.
 * 회장 점수, 회장 수, 회장 멤버를 구한다.
 */

public class p3_JeongeunChoi {

    private static int N, presidentScore = Integer.MAX_VALUE, presidentCnt = 0;
    private static ArrayList<Integer> presidents = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> friends = new ArrayList<>();
    private static int[][] friendScore;
    private static int[] score;
    private static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        friendScore = new int[N + 1][N + 1];
        score = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            friends.add(new ArrayList<>());
            for (int j = 1; j <= N; j++) {
                friendScore[i][j] = Integer.MAX_VALUE;
            }
        }
        while (true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            if (a == -1 && b == -1) {
                break;
            }
            friends.get(a).add(b);
            friends.get(b).add(a);
        }

        for (int i = 1; i <= N; i++) {
            boolean[] visited = new boolean[N + 1];
            q.add(i);
            friendScore[i][i] = 0;
            while (!q.isEmpty()) {
                int current = q.poll();
                if (!visited[current]) {
                    visited[current] = true;
                    for (int j = 0; j < friends.get(current).size(); j++) {
                        int next = friends.get(current).get(j);
                        if (friendScore[i][next] > friendScore[i][current] + 1) {
                            friendScore[i][next] = friendScore[i][current] + 1;
                            q.add(next);
                        }
                    }
                }
            }
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    continue;
                }
                score[i] = Math.max(score[i], friendScore[i][j]);
            }
            presidentScore = Math.min(presidentScore, score[i]);
        }

        for (int i = 1; i <= N; i++) {
            if (score[i] == presidentScore) {
                presidentCnt++;
                presidents.add(i);
            }
        }

        System.out.println(presidentScore + " " + presidentCnt);
        for (int i = 0; i < presidents.size(); i++) {
            System.out.print(presidents.get(i) + " ");
        }
    }
}