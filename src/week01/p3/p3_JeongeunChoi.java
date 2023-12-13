package algorithm.src.week01.p3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class p3_JeongeunChoi {
    static final int FRIDAY = 5;
    static int lectureCnt, creditToGet, answer = 0;
    static List<Lecture> lectures = new ArrayList<>();
    static int[][] schedule = new int[6][11];

    static class Lecture {
        int day, start, end, credit;

        public Lecture(int day, int start, int end) {
            this.day = day;
            this.start = start;
            this.end = end;
            this.credit = end - start + 1;
        }
    }

    public static void selectLecture(int idx, int creditSum) {
        if (creditSum == creditToGet) {
            answer++;
        } else if (creditSum < creditToGet && idx < lectures.size()) {
            for (int i = idx; i < lectures.size(); i++) {
                Lecture lecture = lectures.get(i);
                int day = lecture.day, start = lecture.start, end = lecture.end, credit = lecture.credit;
                boolean canTakeLecture = true;
                for (int j = start; j <= end; j++) {
                    if (schedule[day][j] == 1) {
                        canTakeLecture = false;
                        break;
                    }
                }
                if (canTakeLecture) {
                    for (int j = start; j <= end; j++) {
                        schedule[day][j] = 1;
                    }
                    selectLecture(i + 1, creditSum + credit);
                    for (int j = start; j <= end; j++) {
                        schedule[day][j] = 0;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        lectureCnt = Integer.parseInt(st.nextToken());
        creditToGet = Integer.parseInt(st.nextToken());
        for (int i = 0; i < lectureCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int day = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            if (day != FRIDAY) {
                lectures.add(new Lecture(day, start, end));
            }
        }
        selectLecture(0, 0);

        System.out.println(answer);
    }
}

/**
 * 문제 링크: https://www.acmicpc.net/problem/27375
 * 메모리: 15952 KB
 * 시간: 160 ms
 * 시간 복잡도: O(2^N) -> N은 수업의 개수
 * 공간 복잡도: O(N) -> N은 수업의 개수
 */

/**
 * 백트래킹으로 경우의 수를 전부 고려하되, 조건에 맞지 않는 경우 다시 돌아가는 기법
 * 1. 시간표 상으로 수업이 겹치지 않는지
 * 2. 수강 학점을 넘지 않았는지
 * 금요일 공강을 지키기 위해, 금요일 수업의 경우 lectures 리스트에 넣지 않음.
 */
