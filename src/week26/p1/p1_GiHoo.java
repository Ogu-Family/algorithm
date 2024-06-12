package src.week26.p1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 문제 링크: https://www.acmicpc.net/problem/13335
 * 메모리: 15688 KB
 * 시간: 156 ms
 * 시간 복잡도: O(truckCount * bridgeWeight ^ 2)
 * 공간 복잡도: O(N)
 */

/**
 * 대기중인 트럭에서 다리의 무게를 넘지 않는 선에서 다리 위로 진입시킨다.
 * 다리 위에 있는 트럭들을 앞으로 1씩 이동시킨다.
 * 다리의 길이와 트럭의 이동 거리가 같아지는 순간 다리 위에서 제거한다.
 * 이때 단위 시간 기준, 그렇기에 한 행위가 발생하면 다음 단위 시간으로 넘겨야 한다. -> 이거 어려움..
 */

public class p1_GiHoo {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int truckCount = Integer.parseInt(st.nextToken());
        int bridgeLength = Integer.parseInt(st.nextToken());
        int bridgeWeight = Integer.parseInt(st.nextToken());
        int time = 0;

        List<Truck> truckList = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < truckCount; i++) {
            int weight = Integer.parseInt(st.nextToken());
            truckList.add(new Truck(0, weight));
        }

        Deque<Truck> deque = new ArrayDeque<>();

        while (true) {
            if (!deque.isEmpty()) {
                for (Truck truck : deque) {
                    truck.goForward();

                    if (truck.distance == bridgeLength) {
                        deque.remove(truck);
                        bridgeWeight += truck.weight;
                    }
                }
            }

            if (!truckList.isEmpty() && truckList.get(0).weight <= bridgeWeight) {
                Truck truck = truckList.get(0);

                deque.addLast(truck);
                bridgeWeight -= truck.weight;
                truckList.remove(truck);
            }

            time++;

            if (truckList.isEmpty() && deque.isEmpty()) {
                break;
            }
        }

        System.out.println(time);
    }

    static class Truck {

        int distance;
        int weight;

        public Truck(int distance, int weight) {
            this.distance = distance;
            this.weight = weight;
        }

        public void goForward() {
            this.distance++;
        }
    }
}


