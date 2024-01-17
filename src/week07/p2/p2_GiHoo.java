package src.week07.p2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class p2_GiHoo {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<TimeTable> tables = new ArrayList<>();

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            tables.add(new TimeTable(start, end));
        }

        tables.sort((o1, o2) -> {
            if (o1.end == o2.end) {
                return o1.start - o2.start;
            } else {
                return o1.end - o2.end;
            }
        });

        int cnt = 0;
        int now_end = 0;

        for (int i = 0; i < tables.size(); i++) {
            if (i == 0) {
                now_end = tables.get(i).end;
                cnt++;
            } else {
                int start = tables.get(i).start;
                int end = tables.get(i).end;

                if (start >= now_end) {
                    now_end = end;
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
    }

    static class TimeTable {

        int start;
        int end;

        public TimeTable(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}

/**
 * 문제 링크: https://www.acmicpc.net/problem/1931
 * 메모리: 45072 KB
 * 시간: 596 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N)
 *
 * 풀이
 * 문제를 읽고 각 회의 시간을 비교해보면 쉽게 풀 수 있다.
 *
 * 가장 많은 회의를 배정하기 위해
 * 처음에는 가장 빨리 끝나는 회의를 배정한다.
 * 이후에는 이전 회의가 끝나는 시간과 배정하려는 회의의 시작 시간을 비교하여 배정한다.
 * 시작하려는 회의의 시작 시간과 이전 회의의 종료 시간이 같더라도 배정이 가능함
 *
 */
