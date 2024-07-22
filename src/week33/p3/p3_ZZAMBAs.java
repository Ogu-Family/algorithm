package src.week33.p3;

/**
 * 문제 링크: https://www.acmicpc.net/problem/17292
 * 메모리: 17852 KB
 * 시간: 176 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N)
 */

/**
 * 정렬
 *
 * 1. 조건에 맞게 정렬한다. (compareTo() 리턴 값이 작을수록 작은 값으로 취급)
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class p3_ZZAMBAs {
    static final int SEQ = 1;
    static final int SAME = 2;
    static final int OTHER = 3;

    static Scanner sc = new Scanner(System.in);
    static List<Pair> seqCards = new ArrayList<>(), sameNumCards = new ArrayList<>(), others = new ArrayList<>();

    public static void main(String[] args) {
        List<Card> cards = Arrays.stream(sc.nextLine().split(",")).map(p3_ZZAMBAs::createCard).collect(
            Collectors.toList());
        pairing(cards);

        Collections.sort(seqCards);
        Collections.sort(sameNumCards);
        Collections.sort(others);

        seqCards.forEach(pair -> System.out.println(pair.print()));
        sameNumCards.forEach(pair -> System.out.println(pair.print()));
        others.forEach(pair -> System.out.println(pair.print()));
    }

    static Card createCard(String cardStr) {
        return new Card(cardStr.charAt(0), cardStr.charAt(1) == 'w');
    }

    static void pairing(List<Card> cards) {
        for (int i = 0; i < cards.size() - 1; i++)
            for (int j = i + 1; j < cards.size(); j++) {
                Pair pair = new Pair(cards.get(i), cards.get(j));
                majorSorting(pair);
            }
    }

    static void majorSorting(Pair p) {
        switch (p.getMajorRank()) {
            case SEQ:
                seqCards.add(p);
                break;
            case SAME:
                sameNumCards.add(p);
                break;
            case OTHER:
                others.add(p);
        }
    }

    static class Card {
        char num;
        boolean isWhite;

        Card (char num, boolean isWhite) {
            this.num = num;
            this.isWhite = isWhite;
        }

        int numConvertToint() {
            if (num < 97)
                return num - 48;
            return num - 97 + 10;
        }
    }

    static class Pair implements Comparable<Pair> {
        Card c1;
        Card c2;

        Pair (Card c1, Card c2) {
            this.c1 = c1;
            this.c2 = c2;
        }

        Card getMaxNumCard() {
            return c1.num > c2.num ? c1 : c2;
        }

        Card getMinNumCard() {
            return c1.num < c2.num ? c1 : c2;
        }

        int getMajorRank() {
            Card minCard = getMinNumCard();
            Card maxCard = getMaxNumCard();

            int numDiff = maxCard.numConvertToint() - minCard.numConvertToint();

            if (numDiff == 1 || numDiff == 14)
                return SEQ;

            if (minCard.num == maxCard.num)
                return SAME;

            return OTHER;
        }

        String print() {
            StringBuilder sb = new StringBuilder();
            sb.append(c1.num).append(c1.isWhite ? 'w' : 'b');
            sb.append(c2.num).append(c2.isWhite ? 'w' : 'b');

            return sb.toString();
        }

        @Override
        public int compareTo (Pair p) {

            int isThisSameColor = this.c1.isWhite ^ this.c2.isWhite ? 1 : -1; // 다름 : 같음
            int isPSameColor = p.c1.isWhite ^ p.c2.isWhite ? 1 : -1;

            Card thisMaxCard = this.getMaxNumCard();
            Card thisMinCard = this.getMinNumCard();
            Card pMaxCard = p.getMaxNumCard();
            Card pMinCard = p.getMinNumCard();

            if (isThisSameColor != isPSameColor)
                return isThisSameColor - isPSameColor;

            if (thisMaxCard.num != pMaxCard.num)
                return pMaxCard.num - thisMaxCard.num;

            if (thisMinCard.num != pMinCard.num)
                return pMinCard.num - thisMinCard.num;

            return (thisMaxCard.isWhite ? 1 : -1) - (pMaxCard.isWhite ? 1 : -1);
        }
    }
}
