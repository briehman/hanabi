package com.briehman.hanabi;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    List<Card> cards = new ArrayList<>();

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
    }
}
