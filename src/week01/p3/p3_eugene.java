package week01.p3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p3_eugene {

    static class Lesson {
        private int day, start, end, credit;

        public Lesson(int day, int start, int end, int credit) {
            this.day = day;
            this.start = start;
            this.end = end;
            this.credit = credit;
        }
    }

    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, k;
    static List<Lesson> lessons = new ArrayList<>();
    static boolean[][] visited;  // visited[요일][시간]
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            if (w != 5) {  // 금요일 수업 배열에 넣지 않음
                lessons.add(new Lesson(w, s, e, e - s + 1));
            }
        }

        visited = new boolean[5][11];
        solution(k, 0, 0, lessons);

        System.out.print(ans);
    }

    private static void solution(int K, int cnt, int start, List<Lesson> lessons) {
        if(cnt == K) {
            ans++;
            return;
        }
        for(int i=start; i<lessons.size(); i++) {
            if(isPossible(lessons.get(i))) {
                changeStatus(lessons.get(i), true);  // 시간표에 배정

                solution(K, cnt+lessons.get(i).credit, i+1, lessons);

                changeStatus(lessons.get(i), false);  // 시간표에서 삭제
            }
        }
    }

    private static void changeStatus(Lesson lesson, boolean status) {  // 시간표에 수업을 배정(true) 및 삭제(false)
        for(int i=lesson.start; i<= lesson.end; i++) {
            visited[lesson.day][i] = status;
        }
    }

    private static boolean isPossible(Lesson lesson) {  // 해당 수업이 시간표에 들어갈 수 있는 지 확인
        for(int i=lesson.start; i<= lesson.end; i++) {
            if(visited[lesson.day][i]) return false;
        }
        return true;
    }

}

/*
 * 문제 링크: https://www.acmicpc.net/problem/27375
 * 메모리: 16968 KB
 * 시간: 180 ms
 * 시간 복잡도: O(n^2)
 * 공간 복잡도: O(n)
 *
 * 백 트래킹 으로 모든 경우의 수 찾기
 * Lesson class 생성 해서 배열 사용 최소화
 * 입력시 5(금요일)로 들어오는 lesson은 배열에 넣지 않도록
 */
