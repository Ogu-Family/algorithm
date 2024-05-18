package week24.p2;

/**
 * 문제 링크: https://www.acmicpc.net/problem/1713
 * 메모리: 14628 KB
 * 시간: 140 ms
 * 시간 복잡도: O(N*M)
 * 공간 복잡도: O(N)
 */

/*
1. 학생회장 후보를 추천받아
2. 이미 사진 게시된 후보면 추천 수 +1
3. 사진 게시되지 않은 후보라면 우선순위에 따라 기존 사진 삭제 후 게시
3. 오름차순으로 결과 출력
 */

import java.io.*;
import java.util.*;

public class p2_letskuku {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        List<Pair> photos = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int cur = Integer.parseInt(st.nextToken());

            int idx = -1;
            int min = 1001;
            boolean added = false;
            for (int j = 0; j < photos.size(); j++) {
                Pair p = photos.get(j);

                // 이미 사진 게시된 학생인지 검사
                if (p.x == cur) {
                    p.y++;
                    added = true;
                    break;
                }

                // 기존 사진 삭제해야할 경우 고려한 삭제 우선순위 계산
                if (p.y < min) {
                    idx = j;
                    min = p.y;
                }
            }

            // 새로운 사진 게시
            if (!added) {
                if (photos.size() == n) {
                    photos.remove(idx);
                }
                photos.add(new Pair(cur, 1));
            }
        }

        List<Integer> result = new ArrayList<>();
        for (Pair photo : photos) {
            result.add(photo.x);
        }
        Collections.sort(result);

        for (Integer res : result) {
            System.out.print(res + " ");
        }
    }

    static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
