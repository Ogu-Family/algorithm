package algorithm.src.week23.p3;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/1493
 * 메모리: 69292 KB
 * 시간: 2276 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * 풀이 참고한 문제
 * 그리디 알고리즘, 분할 정복
 * 1. 가장 큰 큐브부터 탐색하며, 넣을 수 있으면 넣는다.
 * 2. 큐브를 넣은 경우, 남은 공간을 세개의 박스로 나눈다.
 * 3. 재귀 호출을 통해 남은 공간이 있는 경우에 대해 1번 과정을 반복한다.
 */

public class p3_JeongeunChoi {

    private static int length, width, height, cubeTypeCnt, requiredCubeCnt = 0;
    private static List<CubeInfo> cubeInfos = new ArrayList<>();
    private static boolean canFill = true;

    private static void fillCube(int l, int w, int h) {
        if (l == 0 || w == 0 || h == 0) {
            return;
        }
        canFill = false;
        int cubeSize = 0;
        for (int i = 0; i < cubeTypeCnt; i++) {
            cubeSize = cubeInfos.get(i).size;
            int cubeCnt = cubeInfos.get(i).cnt;
            if (cubeSize <= l && cubeSize <= w && cubeSize <= h && cubeCnt > 0) {
                requiredCubeCnt++;
                cubeInfos.get(i).cnt--;
                canFill = true;
                break;
            }
        }

        if (canFill) {
            fillCube(l - cubeSize, cubeSize, cubeSize);
            fillCube(l, w - cubeSize, cubeSize);
            fillCube(l, w, h - cubeSize);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        length = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());
        cubeTypeCnt = Integer.parseInt(br.readLine());
        for (int i = 0; i < cubeTypeCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int size = (int) Math.pow(2, Integer.parseInt(st.nextToken()));
            int cnt = Integer.parseInt(st.nextToken());
            cubeInfos.add(new CubeInfo(size, cnt));
        }

        Collections.sort(cubeInfos);
        fillCube(length, width, height);
        requiredCubeCnt = canFill ? requiredCubeCnt : -1;
        System.out.println(requiredCubeCnt);
    }

    private static class CubeInfo implements Comparable<CubeInfo> {

        int size, cnt;

        public CubeInfo(int size, int cnt) {
            this.size = size;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(CubeInfo other) {
            return other.size - this.size;
        }
    }

}
