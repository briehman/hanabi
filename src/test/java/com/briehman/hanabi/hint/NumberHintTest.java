package com.briehman.hanabi.hint;

import com.briehman.hanabi.Player;
import com.briehman.hanabi.deck.Card;
import org.junit.Test;

import static org.junit.Assert.*;
import static com.briehman.hanabi.Color.*;

public class NumberHintTest {
    @Test
    public void matchesCardsByNumber() {
        Hint hint = new NumberHint(new Player("one"), 1);
        assertTrue(hint.matches(new Card(RED, 1)));
        assertFalse(hint.matches(new Card(RED, 2)));
    }
}