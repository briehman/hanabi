package com.briehman.hanabi;

import com.briehman.hanabi.deck.OutOfCardsException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static com.briehman.hanabi.Color.*;

public class DeckTest {
    private static int STANDARD_DECK_SIZE = 50;
    private Deck d;

    @Before
    public void setup() {
        d = new Deck();
    }

    @Test
    public void createStandardDeck() {
        assertEquals(STANDARD_DECK_SIZE, d.getNumCardsLeft());
    }

    @Test
    public void canDrawAllCards() {
        for (int i = 0; i < STANDARD_DECK_SIZE; i++) {
            d.draw();
        }

        try {
            d.draw();
            fail("Should be out of cards!");
        }
        catch (OutOfCardsException e) {}
    }

    @Test
    public void canDrawHand() {
        Hand h = d.drawHand(4);
        assertEquals(4, h.size());

        h = d.drawHand(5);
        assertEquals(5, h.size());
    }

    @Test
    public void canRemoveCards() {
        assertTrue(d.remove(new Card(RED, 1)));
        assertTrue(d.remove(new Card(RED, 1)));
        assertTrue(d.remove(new Card(RED, 1)));
        assertFalse(d.remove(new Card(RED, 1)));
        assertTrue(d.remove(new Card(RED, 5)));
        assertFalse(d.remove(new Card(RED, 5)));
    }

}
