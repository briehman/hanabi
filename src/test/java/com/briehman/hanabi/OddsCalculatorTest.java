package com.briehman.hanabi;

import com.briehman.hanabi.deck.Card;
import com.briehman.hanabi.deck.Hand;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static com.briehman.hanabi.Color.*;
import static org.junit.Assert.*;

public class OddsCalculatorTest {
    private OddsCalculator calc;
    Game g = new Game();
    Player p1;
    Player p2;
    Player p3;
    Player p4;

    @Before
    public void setup() {
        g = new Game();
        p1 = new Player("Brian", new Hand(c(RED, 1), c(RED, 1), c(RED, 1), c(RED, 2)));
        p2 = new Player("Katie", new Hand(c(RED, 2), c(RED, 3), c(RED, 3), c(RED, 4)));
        p3 = new Player("Trent", new Hand(c(RED, 4), c(RED, 5), c(BLUE, 1), c(BLUE, 1)));
        p4 = new Player("Jake", new Hand(c(BLUE, 1), c(BLUE, 2), c(BLUE, 2), c(BLUE, 3)));
        g.addPlayers(p1, p2, p3, p4);
        g.start();
        calc = new OddsCalculator(g);
    }

    @Test
    public void calculateOddsFromBeginning() {
        for (Map.Entry<Card, Odds> entry : calc.calculateOdds(p4).entrySet()) {
            Card card = entry.getKey();
            Odds odds = entry.getValue();
            assertEquals(0.0, odds.getOdds(RED), 0.001);
            assertEquals(8.0 / 38, odds.getOdds(BLUE), 0.001);
            assertEquals(10.0 / 38, odds.getOdds(1), 0.001);
        }
    }

    private Card c(Color color, int number) {
        return new Card(color, number);
    }
}