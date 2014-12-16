package com.briehman.hanabi.deck;

import com.briehman.hanabi.RuleException;

import java.util.*;

public class Hand {
    private static final int MAX_CARDS = 5;

    private List<Card> cards = new ArrayList<>();

    public Hand(Card... cards) {
        this(Arrays.asList(cards));
    }

    public Hand(List<Card> cards) {
        if (cards.size() > MAX_CARDS)
            throw new RuleException("Maximum number of cards per hand is " + MAX_CARDS);
        this.cards.addAll(cards);
    }

    public int size() {
        return cards.size();
    }

    public Card getCard(int i) {
        return cards.get(i);
    }

    public boolean hasExactCard(Card card) {
        for (Card handCard : cards) {
            if (card == handCard)
                return true;
        }
        return false;
    }

    public void discard(Card card) {
        if (!hasExactCard(card))
            throw new RuleException("Cannot discard card you do not have");

        Iterator<Card> it = cards.iterator();
        while (it.hasNext()) {
            if (card == it.next()) {
                it.remove();
                return;
            }
        }
    }

    public void add(Card card) {
        cards.add(card);
    }
}
