package src.week21.p3;

/**
 * 문제 링크: https://www.acmicpc.net/problem/1647
 * 메모리: 397692 KB (크루스칼), 470108 KB (프림)
 * 시간: 4008 ms (크루스칼), 4700 ms (프림)
 * 시간 복잡도: O(MlogM)
 * 공간 복잡도: O(M)
 */

/**
 * MST
 *
 * 크루스칼 + 프림
 * MST 만들고 그 중 가장 큰 간선 하나 똑 떼면 유지비가 최소인 마을 2개로 분리된다.
 *
 * 일부 포스팅에서 간선이 많은 경우 프림 알고리즘이 더 효율적이라고 하는데, 오히려 시간이 더 오래 걸린다. 왤까.
 */

// 크루스칼 풀이
import java.util.*;

public class p3_ZZAMBAs{
    static int N, M, A, B, C, maxC;
    static int[] par;
    static ArrayList<Triple<Integer, Integer, Integer>> edges = new ArrayList<>();
    static int res;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt();
        par = new int[N + 1];
        for (int i = 1; i <= N; i++)
            par[i] = i;
        for (int i = 0; i < M; i++) {
            A = sc.nextInt(); B = sc.nextInt(); C = sc.nextInt();
            edges.add(new Triple<>(A, B, C));
        }
        edges.sort((e1, e2) -> e1.r - e2.r);

        edges.forEach(e -> {
            if (find(e.s) != find(e.t)) {
                union(e.s, e.t);
                res += e.r;
                maxC = Math.max(maxC, e.r);
            }
        });

        System.out.println(res - maxC);
    }

    static void union(int idx1, int idx2) {
        int par1 = find(idx1), par2 = find(idx2);

        if (par1 == par2)
            return;

        par[par2] = par1;
    }

    static int find(int idx) {
        if (par[idx] == idx)
            return idx;

        par[idx] = find(par[idx]);
        return par[idx];
    }

    static class Triple<S, T, R> {
        S s;
        T t;
        R r;

        Triple(S s, T t, R r) {
            this.s = s;
            this.t = t;
            this.r = r;
        }
    }
}

/* 프림 풀이
import java.util.*;

public class Main{
    static int N, M, A, B, C, maxC;
    static ArrayList<Pair<Integer, Integer>>[] edges;
    static int res;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt();
        edges = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++)
            edges[i] = new ArrayList<Pair<Integer, Integer>>();
        for (int i = 0; i < M; i++) {
            A = sc.nextInt(); B = sc.nextInt(); C = sc.nextInt();
            edges[A].add(new Pair<>(B, C));
            edges[B].add(new Pair<>(A, C));
        }

        res = mst();

        System.out.println(res - maxC);
    }

    static int mst() {
        int ret = 0, visitedCnt = 0;
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>((p1, p2) -> p1.r - p2.r);
        pq.add(new Pair<>(1, 0));

        while (!pq.isEmpty() && visitedCnt != N) {
            Pair<Integer, Integer> front = pq.poll();

            if (visited[front.t])
                continue;

            visited[front.t] = true;
            visitedCnt++;
            maxC = Math.max(maxC, front.r);
            ret += front.r;

            for (Pair<Integer, Integer> next : edges[front.t]) {
                if (!visited[next.t])
                    pq.add(new Pair<>(next.t, next.r));
            }
        }

        return ret;
    }

    static class Pair<T, R> {
        T t;
        R r;

        Pair(T t, R r) {
            this.t = t;
            this.r = r;
        }
    }
}
*/
