package src.week17.p3;

/**
 * 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/258709
 * 최대 메모리: 159 MB
 * 최대 시간: 799.44ms
 * 시간 복잡도: O(주사위 선택 경우의 수 * 선택한 주사위에서 숫자를 뽑아 더하는 경우의 수 * 정렬) = O(n_C_{n / 2} * 6^{n / 2} * log{6^{n / 2}})로 추정
 * 공간 복잡도: O(6^{n / 2})
 */

/**
 * 조합 + 이분 탐색
 *
 * 1. A가 선택하는 주사위를 n / 2 개 뽑는다.
 * 2. 뽑았으면 그 주사위들 각각에서 숫자를 선택한 뒤 전부 더한다. 그 값을 selectedSum 배열에 저장. 모든 경우에 수에 대해 반복.
 * 3. 안뽑힌 주사위들 각각은 이제 B가 뽑은 주사위다. 이것들에 대해서도 2를 수행. notSelectedSum 배열에 저장.
 * 4. selectedSum 내부 값을 전부 돌면서 이분 탐색을 이용해 notSelectedSum 배열에서 그 값보다 작은 수가 몇개인지 합한다.
 * 5. 이 합한 수가 가장 많은 조합이 정답이다.
 * 3시간동안 고민했음
 */

import java.util.*;

public class p3_ZZAMBAs {
    static ArrayList<Integer> selected = new ArrayList<>(), notSelected = new ArrayList<>(), selectedSum, notSelectedSum;
    static boolean[] isSelected;
    static ArrayList<Integer> answerList = new ArrayList<>();
    static long maxV = 0;

    public int[] solution(int[][] dice) {
        int[] answer = new int[dice.length / 2];
        isSelected = new boolean[dice.length];

        selectAndCal(0, dice.length, dice);

        for (int i = 0; i < dice.length / 2; i++)
            answer[i] = answerList.get(i) + 1;
        return answer;
    }

    void selectAndCal(int idx, int diceLen, int[][] dice) { // 조합 선택
        if (selected.size() == diceLen / 2) {
            cal(dice);

            return;
        }

        if (diceLen - idx == diceLen / 2 - selected.size()) {
            isSelected[idx] = true;
            selected.add(idx);
            selectAndCal(idx + 1, diceLen, dice);

            selected.remove(selected.size() - 1);
            isSelected[idx] = false;
            return;
        }

        isSelected[idx] = true;
        selected.add(idx);
        selectAndCal(idx + 1, diceLen, dice);
        selected.remove(selected.size() - 1);

        isSelected[idx] = false;
        selectAndCal(idx + 1, diceLen, dice);
    }

    void cal(int[][] dice) { // 나올 수 있는 주사위 합을 전부 저장하고, A가 이기는 경우의 수를 전부 계산
        selectedSum = new ArrayList<>(); notSelectedSum = new ArrayList<>(); notSelected = new ArrayList<>();

        for (int i = 0; i < dice.length; i++) {
            if (!isSelected[i])
                notSelected.add(i);
        }

        /*System.out.print("selected: ");
        for(int s : selected) {
            System.out.print(s + " ");
        }
        System.out.print("notSelected: ");
        for(int s : notSelected) {
            System.out.print(s + " ");
        }
        System.out.println();*/

        fillSum(0, 0, selected, selectedSum, dice);
        fillSum(0, 0, notSelected, notSelectedSum, dice);

        Collections.sort(selectedSum);
        Collections.sort(notSelectedSum);

        long res = 0;
        for (int sum : selectedSum) {
            int start = 0, end = notSelectedSum.size() - 1;
            while (start <= end) {
                int mid = (start + end) / 2;
                int val = notSelectedSum.get(mid);

                if (sum <= val) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }

            res += start;
        }

        if (res > maxV) {
            maxV = res;
            answerList = new ArrayList<>();
            for (int selectedNum : selected) {
                answerList.add(selectedNum);
            }
        }
    }

    void fillSum(int idx, int sum, List<Integer> selected, List<Integer> selectedSum, int[][] dice) { // 주사위 합을 전부 저장하는 메서드
        if (idx == selected.size()) {
            selectedSum.add(sum);
            return;
        }

        for (int val : dice[selected.get(idx)]) {
            sum += val;
            fillSum(idx + 1, sum, selected, selectedSum, dice);
            sum -= val;
        }
    }
}
