package algorithm.src.week19.p3;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/18513
 * 메모리: 57144 KB
 * 시간: 624 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * 처음에 Set으로만 풀다가 시간초과 나서 풀이 참고한 문제
 * bfs
 * 샘터의 위치를 큐에 저장하고
 * 앞 뒤로 탐색하며, 비어 있는 경우 집을 짓고 큐에 넣는다. 위치와 샘터로부터의 거리를 넣는다
 * 집 K개를 모두 지을때까지 반복한다.
 */

public class p3_JeongeunChoi {

    private static int N, K, cnt = 0;
    private static long answer = 0;
    private static final Queue<Node> q = new LinkedList<>();
    private static final Set<Integer> visited = new HashSet<>();

    private static void bfs() {
        while (!q.isEmpty()) {
            Node node = q.poll();
            int location = node.location, interval = node.interval;
            int front = location + 1, back = location - 1;
            if (!visited.contains(front)) {
                q.add(new Node(front, interval + 1));
                visited.add(front);
                answer += interval + 1;
                cnt++;
            }
            if (cnt == K) {
                break;
            }
            if (!visited.contains(back)) {
                q.add(new Node(back, interval + 1));
                visited.add(back);
                answer += interval + 1;
                cnt++;
            }
            if (cnt == K) {
                break;
            }
        }
    }

    private static boolean canPutHouse(int location) {
        return (location >= -100000000 && location <= 100000000) && !visited.contains(location);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int location = Integer.parseInt(st.nextToken());
            q.add(new Node(location, 0));
            visited.add(location);
        }

        bfs();

        System.out.println(answer);
    }

    private static class Node {

        int location, interval;

        public Node(int location, int interval) {
            this.location = location;
            this.interval = interval;
        }
    }

}
