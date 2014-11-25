package com.briehman.hanabi.deck;

import com.briehman.hanabi.Card;
import com.briehman.hanabi.Hand;
import com.briehman.hanabi.RuleException;
import org.junit.Test;

import static org.junit.Assert.*;
import static com.briehman.hanabi.Color.*;

public class HandTest {
    @Test
    public void mustHaveFourOrFiveCards() {
        try {
            new Hand(new Card(RED, 1));
            fail("Cannot create single card hand");
        }
        catch (RuleException e) {}

        try {
            new Hand(new Card(RED, 1),
                    new Card(BLUE, 1));
            fail("Cannot create two card hand");
        }
        catch (RuleException e) {}

        try {
            new Hand(new Card(RED, 1),
                    new Card(BLUE, 1),
                    new Card(GREEN, 1));
            fail("Cannot create three card hand");
        }
        catch (RuleException e) {}

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
}