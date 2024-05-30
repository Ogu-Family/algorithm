package algorithm.src.week26.p1;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/13335
 * 메모리: 15264 KB
 * 시간: 144 ms
 * 시간 복잡도: O(n * w)
 * 공간 복잡도: O(n + w)
 */

/**
 * 구현 우선순위큐로 풀다가 삽질 끝에 풀이 참고..ㅎㅎ
 * 1. 우선순위큐에 다리 길이 만큼 0 넣기
 * 2. 우선순위큐에 차가 모두 넣을 때까지 시간 증가
 * 2-1. 우선순위큐에서 원소 하나 제거
 * 2-2. 차를 넣어도 다리 최대 하중을 넘지 않는 경우 넣기 그렇지 않으면 0 넣기
 * 3. 큐가 빌때까지 시간 증가하며 차 제거
 */

public class p1_JeongeunChoi {

    private static int truckCnt, bridgeLength, bridgeMaxLoad;
    private static int[] truckWeights;

    private static int crossBridge() {
        Queue<Integer> q = new LinkedList<>();

        int truckNum = 0, qWeight = 0, time = 0;
        for (int i = 0; i < bridgeLength; i++) {
            q.add(0);
        }

        while (truckNum < truckCnt) {
            qWeight -= q.poll();
            time++;

            if (qWeight + truckWeights[truckNum] <= bridgeMaxLoad) {
                qWeight += truckWeights[truckNum];
                q.add(truckWeights[truckNum]);
                truckNum++;
            } else {
                q.add(0);
            }
        }

        while (!q.isEmpty()) {
            time++;
            q.poll();
        }

        return time;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        truckCnt = Integer.parseInt(st.nextToken());
        bridgeLength = Integer.parseInt(st.nextToken());
        bridgeMaxLoad = Integer.parseInt(st.nextToken());
        truckWeights = new int[truckCnt];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < truckCnt; i++) {
            truckWeights[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(crossBridge());
    }

}
