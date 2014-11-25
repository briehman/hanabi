package com.briehman.hanabi;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards = new ArrayList<>();

    public Hand(Card... cards) {
        for (Card card : cards) {
            this.cards.add(card);
        }
    }
}
