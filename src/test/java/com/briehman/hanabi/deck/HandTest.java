package com.briehman.hanabi.deck;

import com.briehman.hanabi.RuleException;
import org.junit.Test;

import static com.briehman.hanabi.Color.*;

public class HandTest {
    @Test
    public void canHaveFiveOrLessCards() {
        new Hand(new Card(RED, 1));

        new Hand(new Card(RED, 1),
                new Card(BLUE, 1));

        new Hand(new Card(RED, 1),
                new Card(BLUE, 1),
                new Card(GREEN, 1));

        new Hand(new Card(RED, 1),
                new Card(BLUE, 1),
                new Card(GREEN, 1),
                new Card(YELLOW, 1));

        new Hand(new Card(RED, 1),
                new Card(BLUE, 1),
                new Card(GREEN, 1),
                new Card(YELLOW, 1),
                new Card(WHITE, 1));
    }

    @Test(expected=RuleException.class)
    public void cannotHaveSixCards() {
        new Hand(new Card(RED, 1),
                    new Card(BLUE, 1),
                    new Card(GREEN, 1),
                    new Card(YELLOW, 1),
                    new Card(WHITE, 1),
                    new Card(WHITE, 1));
    }

    /*
    @Test
    public void cannotAddTooManyCardsInHand() {
        Hand h = new Hand(new Card(RED, 1),
                new Card(BLUE, 1),
                new Card(GREEN, 1),
                new Card(YELLOW, 1),
                new Card(WHITE, 1));

        try {
            h.add(new Card(RED, 2));
        }
        catch (RuleException e) {}
    }

    @Test
    public void canDiscardAndAdd() {
        Hand h = new Hand(new Card(RED, 1),
                new Card(BLUE, 1),
                new Card(GREEN, 1),
                new Card(YELLOW, 1),
                new Card(WHITE, 1));

        h.discard(1);
        h.add(new Card(BLUE, 2));
    }

    @Test
    public void cannotDiscardNothing() {
        Hand h = new Hand(new Card(RED, 1),
                new Card(BLUE, 1),
                new Card(GREEN, 1),
                new Card(YELLOW, 1));

        h.discard(0);
        h.discard(0);
        h.discard(0);
        h.discard(0);
        try {
            h.discard(0);
        }
        catch (RuleException e) {}
    }
    */
}