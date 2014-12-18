package com.briehman.hanabi.deck;

import com.briehman.hanabi.Color;
import com.briehman.hanabi.Player;
import com.briehman.hanabi.RuleException;
import com.briehman.hanabi.hint.ColorHint;
import com.briehman.hanabi.hint.Hint;
import com.briehman.hanabi.hint.NumberHint;
import org.junit.Test;

import static com.briehman.hanabi.Color.*;
import static org.junit.Assert.*;

public class HandTest {
    @Test
    public void canHaveFiveOrLessCards() {
        new Hand(c(RED, 1));

        new Hand(c(RED, 1),
                c(BLUE, 1));

        new Hand(c(RED, 1),
                c(BLUE, 1),
                c(GREEN, 1));

        new Hand(c(RED, 1),
                c(BLUE, 1),
                c(GREEN, 1),
                c(YELLOW, 1));

        new Hand(c(RED, 1),
                c(BLUE, 1),
                c(GREEN, 1),
                c(YELLOW, 1),
                c(WHITE, 1));
    }

    @Test(expected=RuleException.class)
    public void cannotHaveSixCards() {
        new Hand(c(RED, 1),
                    c(BLUE, 1),
                    c(GREEN, 1),
                    c(YELLOW, 1),
                    c(WHITE, 1),
                    c(WHITE, 1));
    }

    @Test
    public void canAddHintsToHand() {
        Hand hand = new Hand(c(RED, 1),
                c(BLUE, 1),
                c(GREEN, 1),
                c(YELLOW, 1),
                c(WHITE, 1));
        Player p = new Player("one", hand);
        Hint hint = new NumberHint(p, 1);
        hand.addHint(hint);
        for (Card card : hand.cards()) {
            assertTrue(card.getHints().contains(hint));
        }
    }

    private Card c(Color color, int number) {
        return new Card(color, number);
    }
}