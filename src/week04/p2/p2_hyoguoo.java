/**
 * 문제 링크: https://www.acmicpc.net/problem/20115
 * 메모리: 36232 KB
 * 시간: 364 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * 그리디 문제
 *
 * 가장 많은 양의 에너지 드링크 + 나머지 에너지 드링크의 절반
 * 모든 에너지 드링크를 순회하면서 더하고, 최대값을 저장해둠
 * 순회가 끝나면 최대값 + (총합 - 최대값) / 2
 *         = (총합 + 최대값) / 2
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class EnergyDrink {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bufferedReader.readLine();
        System.out.println(solution(Arrays.stream(bufferedReader.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray()));
    }

    private static double solution(double[] energies) {
        double sum = 0;
        double max = 0;

        for (double energy : energies) {
            sum += energy;
            max = Math.max(max, energy);
        }

        return (sum + max) / 2; // max + (sum - max) / 2 == (sum + max) / 2
    }
}
