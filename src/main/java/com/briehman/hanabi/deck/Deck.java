package com.briehman.hanabi.deck;

import com.briehman.hanabi.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Deck {
    private List<Card> cards = new ArrayList<>();

    public Deck() {
        for (Color color : Color.values()) {
            // TODO fixme
            cards.add(new Card(color, 1));
            cards.add(new Card(color, 1));
            cards.add(new Card(color, 1));

            cards.add(new Card(color, 2));
            cards.add(new Card(color, 2));

            cards.add(new Card(color, 3));
            cards.add(new Card(color, 3));

            cards.add(new Card(color, 4));
            cards.add(new Card(color, 4));

            cards.add(new Card(color, 5));
        }
        shuffle();
    }

    public int getNumberCards(int number) {
        if (number <= 0 || number > 5)
            throw new IllegalArgumentException("Card number invalid");

        if (number == 1)
            return 3;
        else if (number > 1 && number < 5)
            return 2;
        else
            return 1;
    }

    public int getNumberCards(Color color) {
        return 10;
    }

    public int getNumberColors() {
        return 5;
    }

    public int totalCards() {
        return 50;
    }

    public boolean hasCardLeft() {
        return getNumCardsLeft() > 0;
    }

    public int getNumCardsLeft() {
        return cards.size();
    }

    public Card draw() {
        if (getNumCardsLeft() == 0)
            throw new OutOfCardsException();
        else
            return cards.remove(0);
    }

    private void shuffle() {
        Collections.shuffle(cards);
    }

    public Hand drawHand(int numCards) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < numCards; i++)
            cards.add(draw());
        return new Hand(cards);
    }

    public boolean remove(Card card) {
        for (Iterator<Card> it = cards.iterator(); it.hasNext();) {
            Card c = it.next();
            if (c.equals(card)) {
                it.remove();
                return true;
            }
        }

        return false;
    }

    public boolean remove(Hand hand) {
        for (Card card : hand.cards()) {
            if (!remove(card)) {
                return false;
            }
        }
        return true;
    }
}
