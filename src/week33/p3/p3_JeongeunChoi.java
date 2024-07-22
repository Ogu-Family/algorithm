package algorithm.src.week33.p3;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/17292
 * 메모리: 16816 KB
 * 시간: 180 ms
 * 시간 복잡도: O(1)
 * 공간 복잡도: O(1)
 */

/**
 * 정렬 문제
 * 1. 카드 두장씩 한쌍을 만든다.
 * 2. 정렬 기준에 따라 정렬한다.
 */

public class p3_JeongeunChoi {

    private static boolean hasContinuousFront(Card c1, Card c2) {
        return Math.abs(c1.front - c2.front) == 1 || (c1.front == 1 && c2.front == 15) || (c1.front == 15 && c2.front == 1);
    }

    private static boolean hasSameFront(Card c1, Card c2) {
        return c1.front == c2.front;
    }

    private static boolean hasSameBack(Card c1, Card c2) {
        return c1.back == c2.back;
    }

    private static int getSmallFront(CardSet cs) {
        return Math.min(cs.c1.front, cs.c2.front);
    }

    private static int getBigFront(CardSet cs) {
        return Math.max(cs.c1.front, cs.c2.front);
    }

    private static boolean isBigFrontBlack(CardSet cs) {
        if (cs.c1.front > cs.c2.front) {
            return cs.c1.back == 'b';
        } else {
            return cs.c2.back == 'b';
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        Card[] cards = new Card[6];
        for (int i = 0; i < 6; i++) {
            cards[i] = new Card(st.nextToken(","));
        }

        ArrayList<CardSet> cardSets = new ArrayList<>();
        for (int i = 0; i < cards.length; i++) {
            for (int j = i + 1; j < cards.length; j++) {
                cardSets.add(new CardSet(cards[i], cards[j]));
            }
        }
        Collections.sort(cardSets);

        for (int i = 0; i < cardSets.size(); i++) {
            System.out.println(cardSets.get(i));
        }
    }

    static class CardSet implements Comparable<CardSet> {

        Card c1, c2;

        CardSet(Card c1, Card c2) {
            this.c1 = c1;
            this.c2 = c2;
        }

        public int compareTo(CardSet other) {
            if (hasContinuousFront(this.c1, this.c2) && !hasContinuousFront(other.c1, other.c2)) {
                return -1;
            } else if (!hasContinuousFront(this.c1, this.c2) && hasContinuousFront(other.c1,
                    other.c2)) {
                return 1;
            } else if (hasContinuousFront(this.c1, this.c2) && hasContinuousFront(other.c1,
                    other.c2)) {
                return compareToInSameOrder(other);
            }

            if (hasSameFront(this.c1, this.c2) && !hasSameFront(other.c1, other.c2)) {
                return -1;
            } else if (!hasSameFront(this.c1, this.c2) && hasSameFront(other.c1, other.c2)) {
                return 1;
            } else if (hasSameFront(this.c1, this.c2) && hasSameFront(other.c1, other.c2)) {
                return compareToInSameOrder(other);
            }

            return compareToInSameOrder(other);
        }

        public int compareToInSameOrder(CardSet other) {
            if (hasSameBack(this.c1, this.c2) && !hasSameBack(other.c1, other.c2)) {
                return -1;
            } else if (!hasSameBack(this.c1, this.c2) && hasSameBack(other.c1, other.c2)) {
                return 1;
            } else {
                if (getBigFront(this) != getBigFront(other)) {
                    return getBigFront(other) - getBigFront(this);
                } else if (getSmallFront(this) != getSmallFront(other)) {
                    return getSmallFront(other) - getSmallFront(this);
                } else if (isBigFrontBlack(this) && !isBigFrontBlack(other)) {
                    return -1;
                } else if (!isBigFrontBlack(this) && isBigFrontBlack(other)) {
                    return 1;
                }
            }

            return 1;
        }

        public String toString() {
            return c1.frontHex + c1.back + c2.frontHex + c2.back;
        }
    }

    static class Card {

        String frontHex;
        int front;
        char back;

        Card(String str) {
            this.frontHex = str.substring(0, str.length() - 1);
            this.front = Integer.parseInt(frontHex, 16);
            this.back = str.charAt(str.length() - 1);
        }
    }

}
