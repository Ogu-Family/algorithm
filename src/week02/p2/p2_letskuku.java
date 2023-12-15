package week02.p2;

/**
 * 문제 링크: https://www.acmicpc.net/problem/11000
 * 메모리: 71728 KB
 * 시간: 712 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N)
 */

/*
1. N개의 수업을 입력받아 시작 시간을 기준으로 오름차순 정렬
2. 끝나는 시간을 우선순위 큐에 넣어 현재 열린 강의실 중 끝나는 시간이 가장 빠른 수업과 비교, 이어서 수업 불가하면 강의실 추가
3. 총 강의실 수 출력
 */

import java.io.*;
import java.util.*;

public class p2_letskuku {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        List<Pair> lectures = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            lectures.add(new Pair(start, end));
        }
        Collections.sort(lectures);

        PriorityQueue<Integer> rooms = new PriorityQueue<>();
        rooms.add(lectures.get(0).y);
        for (int i = 1; i < n; i++) {
            int start = lectures.get(i).x;
            int end = lectures.get(i).y;

            if (rooms.peek() <= start) {
                rooms.poll();
            }
            rooms.add(end);
        }

        System.out.println(rooms.size());
    }

    public static class Pair implements Comparable<Pair> {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pair o) {

            if (this.x == o.x) {
                return Integer.compare(this.y, o.y); // 첫번째 인자값이 같으면 두번째 인자로 오름차순 정렬
            }

            return Integer.compare(this.x, o.x); // 첫번째 인자로 오름차순 정렬
        }
    }
}
