package week11.p1;

/**
 * 문제 링크: https://www.acmicpc.net/problem/5639
 * 메모리: 26736 KB
 * 시간: 844 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(NlogN)
 */

/*
1. 트리를 전위 순회한 결과를 입력받아
2. 이진탐색트리의 특성(루트보다 작은 값은 왼쪽 서브트리, 큰 값은 오른쪽 서브트리에 위치)을 고려하여
3. 후위 순회한 결과를 출력
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class p1_letskuku {
    static List<Integer> tree = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String str = br.readLine();
            if (str == null || str.equals("")) {
                break;
            }

            tree.add(Integer.parseInt(str));
        }

        func(0, tree.size() - 1);
    }

    static void func(int start, int end) {
        if (start > end) {
            return;
        }

        int root = tree.get(start);
        int mid = start + 1;
        while (mid <= end && tree.get(mid) < root) {
            mid++;
        }

        func(start + 1, mid - 1);
        func(mid, end);
        System.out.println(root);
    }
}
