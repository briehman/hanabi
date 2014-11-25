package com.briehman.hanabi;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static com.briehman.hanabi.Color.*;

public class GameTest {
    private Game g;
    //private Player p1 = new Player("Brian", new Hand(c(YELLOW, 3), c(GREEN, 3), c(GREEN, 2), c(RED, 2), c(YELLOW, 4)));
    private Player p1 = new Player("Brian");
    //private Player p2 = new Player("Katie", new Hand(c(BLUE, 4), c(WHITE, 3), c(WHITE, 2), c(YELLOW, 1), c(YELLOW, 1)));
    private Player p2 = new Player("Katie");
    private Player p3 = new Player("Steve");
    private Player p4 = new Player("Trent");
    private Player p5 = new Player("Samantha");
    private List<Player> players = new ArrayList<>();

    private Card c(Color color, int number) {
        return new Card(color, 1);
    }

    @Before
    public void setup() {
        g = new Game();
        players.addAll(Arrays.asList(p1, p2, p3, p4, p5));
    }

    private void twoPersonSetUp() {
        g.addPlayers(playerSlice(2));
        g.start();
    }

    @Test
    public void newGameNotFinishedOrStarted() {
        assertFalse(g.isFinished());
        assertFalse(g.isStarted());
    }

    private List<Player> playerSlice(int n) {
        List<Player> newList = new ArrayList<>();
        for (int i = 0; i < n; i++)
            newList.add(players.get(i));
        return newList;
    }

    @Test
    public void twoToFivePlayersOk() {
        for (int numPlayers = 2; numPlayers < 5; numPlayers++) {
            Game g = new Game();
            g.addPlayers(playerSlice(numPlayers));
            g.start();
            assertTrue(g.isStarted());
            assertFalse(g.isFinished());
        }
    }

    @Test(expected=RuleException.class)
    public void onePlayerCannotPlay() {
        g.addPlayer(p1);
        g.start();
    }

    @Test(expected=RuleException.class)
    public void sixPlayersCannotPlay() {
        g.addPlayers(players);
        g.addPlayer(new Player("Notgonnaplay"));
        g.start();
    }


    @Test(expected=GameException.class)
    public void addPlayerTwiceFails() {
        g.addPlayer(p1);
        g.addPlayer(p1);
    }

    @Test
    public void turnsMustGoInOrder() {
        g.addPlayers(p1, p2);
        g.start(); // removes hand cards from the deck, creates discards
        // player.hint generates a hint which the game takes
        // player.discard(card) discards a card
        g.turn(new HintTurn(p1, new ColorHint(p2, RED)));
        assertEquals(1, g.turns().size());
        try {
            g.turn(new HintTurn(p1, new NumberHint(p2, 1)));
            fail("Cannot play two turns in a row!");
        }
        catch (OutOfTurnException e) {}

        g.turn(new HintTurn(p2, new NumberHint(p1, 1)));
        assertEquals(2, g.turns().size());

        // and they cycle back to the beginning
        g.turn(new HintTurn(p1, new NumberHint(p2, 1)));
        assertEquals(3, g.turns().size());
    }

    @Test(expected=OutOfHintsException.class)
    public void hintsGetUsedUp() {
        twoPersonSetUp();
        int numTurns = g.getHintsLeft() / 2;
        for (int i = 0; i < numTurns; i++) {
            g.turn(new HintTurn(p1, new ColorHint(p2, RED)));
            g.turn(new HintTurn(p2, new NumberHint(p1, 1)));
        }
        assertEquals(0, g.getHintsLeft());
        g.turn(new HintTurn(p1, new ColorHint(p2, RED)));
    }

    @Test
    public void playersCanDiscardCards() {
        g.addPlayers(p1, p2);
        g.start();
        // removes hand cards from the deck, creates discards
        // player.hint generates a hint which the game takes
        // player.discard(card) discards a card
        g.turn(new HintTurn(p1, new ColorHint(p2, RED)));
        assertEquals(1, g.turns().size());
        try {
            g.turn(new HintTurn(p1, new NumberHint(p2, 1)));
            fail("Cannot play two turns in a row!");
        }
        catch (OutOfTurnException e) {}

        g.turn(new HintTurn(p2, new NumberHint(p1, 1)));
        assertEquals(2, g.turns().size());

        // and they cycle back to the beginning
        g.turn(new HintTurn(p1, new NumberHint(p2, 1)));
        assertEquals(3, g.turns().size());
    }
}
