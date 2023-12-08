package src.week01.p3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class p3_GiHoo {
    static class Class {
        int day;
        int start_time;
        int end_time;

        public Class(int day, int start_time, int end_time) {
            this.day = day;
            this.start_time = start_time;
            this.end_time = end_time;
        }
    }

    static List<Class> classList = new ArrayList<>();
    static int N;
    static int K;
    static int answer = 0;
    static boolean[][] table = new boolean[5][11]; // 월 ~ 목, 1교시 ~ 10교시

    static void DFS(int index, int remainingGrades) {
        if (remainingGrades == 0) {
            answer++;
        } else {
            for (int i = index + 1; i < classList.size(); i++) {
                Class findClass = classList.get(i);
                if(isPossible(findClass)) {
                    changeTable(findClass.day, findClass.start_time, findClass.end_time);
                    DFS(i, remainingGrades - (findClass.end_time - findClass.start_time + 1));
                    restoreTable(findClass.day, findClass.start_time, findClass.end_time);
                }
            }
        }
    }

    // 시간표 상으로 수업이 겹치는지 체크
    static boolean isPossible(Class getClass) {
        int day = getClass.day;
        int start_time = getClass.start_time;
        int end_time = getClass.end_time;

        for (int i = start_time; i <= end_time; i++) {
            if (table[day][i]) return false;
        }
        return true;
    }

    // 시간표 활성화(든는 수업 체크)
    static void changeTable(int day, int start_time, int end_time) {
        for (int i = start_time; i <= end_time; i++) {
            table[day][i] = true;
        }
    }

    // 시간표 원상복구(듣는 수업 제거)
    static void restoreTable(int day, int start_time, int end_time) {
        for (int i = start_time; i <= end_time; i++) {
            table[day][i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int day = Integer.parseInt(st.nextToken());
            int start_time = Integer.parseInt(st.nextToken());
            int end_time = Integer.parseInt(st.nextToken());
            if(day != 5) classList.add(new Class(day, start_time, end_time));
        }

        for (int i = 0; i < classList.size(); i++) {
            Class firstClass = classList.get(i);
            changeTable(firstClass.day, firstClass.start_time, firstClass.end_time);
            DFS(i, K - (firstClass.end_time - firstClass.start_time + 1));
            restoreTable(firstClass.day, firstClass.start_time, firstClass.end_time);
        }
        System.out.println(answer);
    }
}

/**
 * 문제 링크: https://www.acmicpc.net/problem/27375
 * 메모리: 15636 KB
 * 시간: 180 ms
 * 시간 복잡도: O(N^3)
 * 공간 복잡도: O(N^2)
 *
 * 풀이
 * 입력으로 요일이 5(금요일)가 들어온다면 추가하지 않는다.
 * 들어온 수업을 차례대로 반복문을 통해 DFS를 진행한다.
 * DFS 전, 후로 chageTable, restoreTable 메서드를 통해 시간표를 수정한다.
 * DFS 인자로 index가 들어감.
 *  문제를 봤을 때 처음으로 들어가는 수업에 따라 경우가 다르게 됨
 *  그렇기에 이전에 table에 추가했던 수업 다음부터 DFS를 돌려 문제를 풀이
 * DFS 내부에서는 수업이 시간표에 있는지 확인하는 isPossible 수행
 */

