package week29.p2;

/**
 * 문제 링크: https://www.acmicpc.net/problem/5179
 * 메모리: 16212 KB
 * 시간: 164 ms
 * 시간 복잡도: O(N * logN)
 * 공간 복잡도: O(N * M)
 */

/*
1. 참가자의 제출 이력을 입력 받아
2. 참가자의 총점 계산 후 기준에 맞게 정렬
3. 결과 출력
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class p2_letskuku {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int k = Integer.parseInt(br.readLine());

        int m, n, p, id, num, time, clear;
        int[][] submit; // 문제 맞히기 전 틀린 횟수 저장
        Score[] scores;
        for (int i = 1; i <= k; i++) {
            st = new StringTokenizer(br.readLine());

            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            p = Integer.parseInt(st.nextToken());

            submit = new int[p + 1][m];
            scores = new Score[p + 1];
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());

                id = Integer.parseInt(st.nextToken());
                num = st.nextToken().charAt(0) - 'A';
                time = Integer.parseInt(st.nextToken());
                clear = Integer.parseInt(st.nextToken());

                if (submit[id][num] < 0) {
                    continue;
                }

                if (clear == 0) {
                    submit[id][num]++;
                } else {
                    if (scores[id] == null) {
                        scores[id] = new Score(id, 1, time + submit[id][num] * 20);
                    } else {
                        scores[id].num++;
                        scores[id].grade += (time + submit[id][num] * 20);
                    }

                    submit[id][num] = -1;
                }
            }

            List<Score> result = new ArrayList<>();
            for (Score s : scores) {
                if (s != null) {
                    result.add(s);
                }
            }
            Collections.sort(result);

            System.out.println("Data Set " + i + ":");
            for (Score s : result) {
                if (s.num == 0) {
                    continue;
                }

                System.out.println(s.id + " " + s.num + " " + s.grade);
            }

            System.out.println();
        }
    }

    static class Score implements Comparable<Score> {
        int id, num, grade;

        Score(int id, int num, int grade) {
            this.id = id;
            this.num = num;
            this.grade = grade;
        }

        @Override
        public int compareTo(Score o) {
            int compNum = o.num - this.num;

            if (compNum != 0) {
                return compNum;
            }

            return this.grade - o.grade;
        }
    }
}
