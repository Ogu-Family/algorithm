package src.week05.p3;

/**
 * 문제 링크: https://www.acmicpc.net/problem/12784
 * 메모리: 148852 KB
 * 시간: 1004 ms
 * 시간 복잡도: O(N * M = N^2)
 * 공간 복잡도: O(N)
 */

/**
 * 그래프(트리) DP
 * 어떤 정점에서 부모 간선 가중치와 자식 간선 가중치 합을 비교하여 더 작은 값을 선택하면 그것이 최소 다이너마이트 비용이 된다.
 * 1번부터 그 과정을 DFS 돌리면 된다.
 *
 * 문제에서 중요한 부분
 * - 두 섬을 연결하는 다리를 최소한의 개수로 만들어 모든 섬 간의 왕래가 가능 (트리)
 * - 1번섬을 제외한 다리가 하나밖에 없는(리프 노드) 어느 섬에서 유명한 연쇄 살인마 괴도‘루팡’이 자신의 목숨을 노리고 있다
 * - N(1 ≤ N ≤ 1,000) (N이 1일 때 답은 0)
 */

import java.util.ArrayList;
import java.util.Scanner;

public class p3_ZZAMBAs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while(T-- > 0) {
            int N = sc.nextInt(), M = sc.nextInt();
            ArrayList<Pair>[] adv = new ArrayList[N + 1];

            for(int i = 0; i < N + 1; i++)
                adv[i] = new ArrayList<>();

            while(M-- > 0) {
                int s = sc.nextInt(), d = sc.nextInt(), D = sc.nextInt();

                adv[s].add(new Pair(D, d));
                adv[d].add(new Pair(D, s));
            }

            int res = dfs(1, 0, Integer.MAX_VALUE, adv);

            System.out.println(res == Integer.MAX_VALUE ? 0 : res);
        }
    }

    static class Pair {
        public int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int dfs(int idx, int preIdx, int preVal, ArrayList<Pair>[] adj) { // (현재 인덱스, 부모 인덱스, 부모 간선 가중치, 인접 리스트)
        int ret = 0;

        for(Pair adv : adj[idx]) {
            if(adv.y == preIdx)
                continue;
            ret += dfs(adv.y, idx, adv.x, adj);
        }

        return ret != 0 ? Math.min(preVal, ret) : preVal;
    }
}
