package week26.p1;

/**
 * 문제 링크: https://www.acmicpc.net/problem/13335
 * 메모리: 15320 KB
 * 시간: 152 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/*
1. 다리를 건너는 트럭의 수, 다리의 길이, 다리의 최대하중을 입력 받아
2. 각 트럭의 무게를 큐에 저장
3. 큐에서 하나씩 빼서 다리의 최대하중 이하면 다리 위로 옮김
4. 모든 트럭들이 다리 건너는 시간 출력
 */

import java.util.*;
import java.io.*;

public class p1_letskuku {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        Queue<Integer> trucks = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            trucks.add(Integer.parseInt(st.nextToken()));
        }

        Queue<Integer> bridge = new LinkedList<>(); // 다리 지나는 중인 트럭 저장
        for (int i = 0; i < w; i++) {
            bridge.add(0);
        }

        int truckW = 0; // 다리 위의 있는 트럭들의 무게 합
        int time = 0; // 트럭들이 다리 건너는 시간
        while (!trucks.isEmpty()) {
            truckW -= bridge.poll();
            time++;

            if (truckW + trucks.peek() <= L) {
                bridge.add(trucks.peek());
                truckW += trucks.poll();
            } else {
                bridge.add(0);
            }
        }

        // 아직 다리 위 건너는 중인 트럭들이 모두 건너가는 시간까지 고려
        System.out.println(time + bridge.size());
    }
}
