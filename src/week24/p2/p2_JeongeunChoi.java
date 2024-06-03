package algorithm.src.week24.p2;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/1713
 * 메모리: 15968 KB
 * 시간: 152 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * 구현
 * 1. 사진틀에 공간이 남아있는 경우, 후보를 넣는다.
 * 2. 공간이 없는 경우, 추천 횟수와 날짜를 기준으로 정렬 후 추천 횟수가 가장 적거나 같은 경우 가장 오래된 사진을 삭제한다.
 * 3. 후보를 학생 번호 순으로 출력한다.
 */

public class p2_JeongeunChoi {

    private static int photoFrameCnt, totalRecommendationCnt;
    private static final Candidate[] candidates = new Candidate[101];
    private static final ArrayList<Candidate> candidatesInPhotoFrame = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        photoFrameCnt = Integer.parseInt(br.readLine());
        totalRecommendationCnt = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < totalRecommendationCnt; i++) {
            int studentNum = Integer.parseInt(st.nextToken());
            if (candidates[studentNum] != null) {
                candidates[studentNum].recommendationCnt++;
            } else {
                if (candidatesInPhotoFrame.size() == photoFrameCnt) {
                    Collections.sort(candidatesInPhotoFrame);
                    Candidate candidate = candidatesInPhotoFrame.get(0);
                    candidates[candidate.studentNum] = null;
                    candidatesInPhotoFrame.remove(0);
                }
                candidates[studentNum] = new Candidate(studentNum, 1, i);
                candidatesInPhotoFrame.add(candidates[studentNum]);
            }
        }

        for (int i = 0; i < candidates.length; i++) {
            if (candidates[i] != null) {
                System.out.print(i + " ");
            }
        }
    }

    private static class Candidate implements Comparable<Candidate> {

        int studentNum, recommendationCnt, postingTime;

        Candidate(int studentNum, int recommendationCnt, int postingTime) {
            this.studentNum = studentNum;
            this.recommendationCnt = recommendationCnt;
            this.postingTime = postingTime;
        }

        @Override
        public int compareTo(Candidate other) {
            if (this.recommendationCnt != other.recommendationCnt) {
                return this.recommendationCnt - other.recommendationCnt;
            } else {
                return this.postingTime - other.postingTime;
            }
        }
    }

}
