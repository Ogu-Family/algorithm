package src.week15.p3;

/**
 * 문제 링크: https://www.acmicpc.net/problem/21944
 * 메모리: 283504 KB
 * 시간: 1736 ms
 * 시간 복잡도: O(MlogN)
 * 공간 복잡도: O(NM)
 */

/**
 * 알고리즘 분류 힌트 참조해서 해결..
 *
 * 자료 구조 + 구현 문제. 이런 문제 풀때마다 느끼지만 맵이랑 해시 만든 사람 상줘야 함.
 * 처음에는 연결 리스트랑 배열 같이써야 하나 생각했는데 PQ를 각 경우마다 전부 만들기로 했다.
 * 메모리 희생 = 속도 향상
 *
 * TreeSet을 쓰면 더 효과적으로 풀 수 있다고 한다.
 */

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class p3_ZZAMBAs {
    static final int ASC = 0, DESC = 1; // {난이도, 문제 번호}를 기준으로 오름차순 혹은 내림차순 정렬을 위한 상수
    static Map<Integer, Problem> map = new HashMap<>(); // {문제 번호, 문제}
    static PriorityQueue<Problem>[] problemPQ = new PriorityQueue[2]; // 모든 문제를 가지고 있으며 0번 행은 ASC, 1번 행은 DESC 순.
    static PriorityQueue<Problem>[][] levelPQ = new PriorityQueue[2][101]; // 난이도 별 문제를 가진 힙. 난이도는 최대 100
    static PriorityQueue<Problem>[][] categoryPQ = new PriorityQueue[2][101]; // 알고리즘 분류 별 문제를 가진 힙. 분류는 최대 100.

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), P, L, G, M, x;
        String cmd;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 101; j++) {
                levelPQ[i][j] = new PriorityQueue<>(levelComparator(i)); // i == 0 : ASC, i > 0 : DESC
                categoryPQ[i][j] = new PriorityQueue<>(levelComparator(i));
            }
            problemPQ[i] = new PriorityQueue<>(levelComparator(i));
        }

        for (int i = 0; i < N; i++) {
            P = sc.nextInt(); L = sc.nextInt(); G = sc.nextInt();
            add(P, L, G);
        }

        M = sc.nextInt();
        for (int i = 0; i < M; i++) {
            cmd = sc.next();

            switch (cmd) {
                case "recommend" -> {
                    G = sc.nextInt();
                    x = Math.max(ASC, sc.nextInt());

                    System.out.println(recommend(G, x));
                }
                case "recommend2" -> {
                    x = Math.max(ASC, sc.nextInt());

                    System.out.println(recommend2(x));
                }
                case "recommend3" -> {
                    x = sc.nextInt();
                    L = sc.nextInt();

                    System.out.println(recommend3(x, L));
                }
                case "add" -> {
                    P = sc.nextInt();
                    L = sc.nextInt();
                    G = sc.nextInt();

                    add(P, L, G);
                }
                case "solved" -> {
                    P = sc.nextInt();

                    solved(P);
                }
            }
        }
    }

    private static void solved(int p) {
        map.get(p).isSolved = true;
    }

    private static void add(int p, int l, int g) {
        Problem problem = new Problem(p, l, g, false);
        map.put(p, problem);

        for (int j = 0; j < 2; j++) {
            problemPQ[j].add(problem);
            levelPQ[j][l].add(problem);
            categoryPQ[j][g].add(problem);
        }
    }

    private static int recommend3(int x, int l) {
        int ret = -1;

        if (x == -1) {
            for (int i = l - 1; i > 0 && ret == -1; i--) {
                while (!levelPQ[DESC][i].isEmpty() && levelPQ[DESC][i].peek().isSolved)
                    levelPQ[DESC][i].poll();

                if (levelPQ[DESC][i].isEmpty())
                    continue;

                ret = levelPQ[DESC][i].peek().number;
            }
        } else {
            for (int i = l; i < 101 && ret == -1; i++) {
                while (!levelPQ[ASC][i].isEmpty() && levelPQ[ASC][i].peek().isSolved)
                    levelPQ[ASC][i].poll();

                if (levelPQ[ASC][i].isEmpty())
                    continue;

                ret = levelPQ[ASC][i].peek().number;
            }
        }

        return ret;
    }

    private static int recommend2(int x) {
        while (problemPQ[x].peek().isSolved)
            problemPQ[x].poll();

        return problemPQ[x].peek().number;
    }

    private static int recommend(int category, int x) {
        while (categoryPQ[x][category].peek().isSolved)
            categoryPQ[x][category].poll();

        return categoryPQ[x][category].peek().number;
    }

    private static Comparator<Problem> levelComparator(int order) {
        if (order == ASC)
            return (o1, o2) -> {
                if (o1.level != o2.level)
                    return o1.level - o2.level;
                return o1.number - o2.number;
            };
        else
            return (o2, o1) -> {
                if (o1.level != o2.level)
                    return o1.level - o2.level;
                return o1.number - o2.number;
            };
    }

    static class Problem {
        int number, level, category;
        boolean isSolved;

        Problem (int number, int level, int category, boolean isSolved) {
            this.number = number;
            this.level = level;
            this.category = category;
            this.isSolved = isSolved;
        }
    }
}

