package com.briehman.hanabi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hand {
    private List<Card> cards = new ArrayList<>();
    private final int initialHandSize;

    public Hand(Card... cards) {
        this(Arrays.asList(cards));
    }

    public Hand(List<Card> cards) {
        this.initialHandSize = cards.size();

        if (initialHandSize != 4 && initialHandSize != 5)
            throw new RuleException("Hand size must be 4 or 5");

        for (Card card : cards) {
            this.cards.add(card);
        }
    }

    public int size() {
        return cards.size();
    }

    public List<Card> cards() {
        return cards;
    }

    public Card discard(int cardNumber) {
        if (cardNumber >= cards.size() || cardNumber < 0)
            throw new RuleException(String.format("Attempt to discard illegal card %s", cardNumber));
        return cards.remove(cardNumber);
    }

    public void add(Card card) {
        if (cards.size() == initialHandSize)
            throw new RuleException(String.format("Cannot have more than %s cards in hand", initialHandSize));

        cards.add(card);
    }
}
