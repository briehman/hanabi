package com.briehman.hanabi.hint;

import com.briehman.hanabi.Player;
import com.briehman.hanabi.deck.Card;
import org.junit.Test;

import static org.junit.Assert.*;
import static com.briehman.hanabi.Color.*;

public class ColorHintTest {
    @Test
    public void matchesCardsByColor() {
        Hint hint = new ColorHint(new Player("one"), RED);
        assertTrue(hint.matches(new Card(RED, 1)));
        assertFalse(hint.matches(new Card(GREEN, 1)));
    }
}