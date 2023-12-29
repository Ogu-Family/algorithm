package algorithm.src.week04.p2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class p2_JeongeunChoi {

    static int cnt;
    static final ArrayList<Double> energyDrinks = new ArrayList<>();

    private static double makeMaxEnergyDrink() {
        while (energyDrinks.size() != 1) {
            double min = energyDrinks.get(0);
            double max = energyDrinks.get(energyDrinks.size() - 1);
            energyDrinks.remove(0);
            energyDrinks.set(energyDrinks.size() - 1, max + min / 2);
        }

        return energyDrinks.get(0);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        cnt = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        double energyDrink;
        for (int i = 0; i < cnt; i++) {
            energyDrink = Double.parseDouble(st.nextToken());
            energyDrinks.add(energyDrink);
        }

        Collections.sort(energyDrinks);

        System.out.println(makeMaxEnergyDrink());
    }

    /**
     * 문제 링크: https://www.acmicpc.net/problem/20115
     * 메모리: 45012KB
     * 시간: 1504ms
     * 시간 복잡도: O((NlogN)
     * 공간 복잡도: O(N)
     */

/**
 * 그리디 알고리즘
 * 가장 적은 양의 에너지 드링크를 가장 큰 양의 에너지 드링크에 붓는 과정을
 * 에너지 드링크 한개가 남을 때까지 반복
 */
}