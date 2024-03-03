package algorithm.src.week13.p3;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/23835
 * 메모리: 38436 KB
 * 시간: 376 ms
 * 시간 복잡도: O(2N-1) -> 인접리스트에서의 DFS가 O(정점의 개수 + 간선의 개수)라고 생각되어 O(N+N-1)로 추정
 * 공간 복잡도: O(N^2)
 */

/**
 * 인접 리스트를 통해 경로를 저장하고
 * 1번 쿼리가 들어오면, dfs를 통해 탐색하며 끝에 도달한 경우의 경로를 저장해두고 해당 경로를 바탕으로 우유 배달 갯수를 저장한다.
 * 2번 쿼리가 들어오면 해당 방에 배달한 우유 갯수를 출력한다.
 */

public class p3_JeongeunChoi {

    static int N, Q;
    static List<Integer> room = new ArrayList<>(), visits;
    static List<List<Integer>> path = new ArrayList<>();
    static List<Boolean> visited;
    static Boolean foundPath;

    private static void dfs(int s, int n, int e) {
        for (int i : path.get(n)) {
            if (foundPath) {
                return;
            } else if (i == e) {
                foundPath = true;
                visits.add(i);
                visited.set(i, true);
                return;
            } else if (!visited.get(i)) {
                visits.add(i);
                visited.set(i, true);
                dfs(s, i, e);
                if (!foundPath) {
                    visited.set(i, false);
                    visits.remove(visits.size() - 1);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i <= N; i++) {
            path.add(new ArrayList<>());
            room.add(0);
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            path.get(a).add(b);
            path.get(b).add(a);
        }

        Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            if (t == 1) {
                int s = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken());
                visited = new ArrayList<>(Collections.nCopies(N + 1, false));
                visits = new ArrayList<>();
                foundPath = false;
                visited.set(s, true);
                dfs(s, s, e);
                for (int j = 0; j < visits.size(); j++) {
                    int cnt = room.get(visits.get(j));
                    room.set(visits.get(j), cnt + j + 1);
                }
            } else if (t == 2) {
                int roomNum = Integer.parseInt(st.nextToken());
                System.out.println(room.get(roomNum));
            }
        }
    }
}