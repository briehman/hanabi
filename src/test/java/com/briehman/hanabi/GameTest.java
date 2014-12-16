package com.briehman.hanabi;

import com.briehman.hanabi.deck.Card;
import com.briehman.hanabi.hint.ColorHint;
import com.briehman.hanabi.hint.NumberHint;
import com.briehman.hanabi.turn.HintTurn;
import com.briehman.hanabi.turn.OutOfTurnException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static com.briehman.hanabi.Color.*;

public class GameTest {
    private Game g;
    private Player p1 = new Player("Brian");
    private Player p2 = new Player("Katie");
    private Player p3 = new Player("Steve");
    private Player p4 = new Player("Trent");
    private Player p5 = new Player("Samantha");
    private List<Player> players = new ArrayList<>();

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
        g.addPlayers(p1, p2, p3);
        g.start(); // removes hand cards from the deck, creates discards
        // player.hint generates a hint which the game takes
        // player.discard(card) discards a card
        g.turn(new HintTurn(p1, new ColorHint(p2, RED)));
        try {
            g.turn(new HintTurn(p1, new NumberHint(p2, 1)));
            fail("Cannot play two turns in a row!");
        }
        catch (OutOfTurnException e) {}

        g.turn(new HintTurn(p2, new NumberHint(p1, 1)));

        g.turn(new HintTurn(p3, new NumberHint(p2, 1)));

        // and they cycle back to the beginning
        g.turn(new HintTurn(p1, new NumberHint(p3, 1)));
    }

    @Test(expected=OutOfTurnException.class)
    public void outOfOrderTurnsFail() {
        g.addPlayers(p1, p2, p3);
        g.start(); // removes hand cards from the deck, creates discards
        // player.hint generates a hint which the game takes
        // player.discard(card) discards a card
        g.turn(new HintTurn(p2, new ColorHint(p1, RED)));
    }

    @Test(expected=GameOverException.class)
    public void cannotTakeTurnsWhenGameOver() {
        twoPersonSetUp();
        g.bomb();
        g.bomb();
        g.bomb();
        g.turn(new HintTurn(p1, new ColorHint(p2, RED)));
    }

    @Test
    public void scoreZeroToStart() {
        twoPersonSetUp();
        assertEquals(0, g.getScore());
    }

    @Test
    public void winningGameScoreCorrect() {
        twoPersonSetUp();
        for (Color color : Color.values()) {
            for (int i = 0; i < 5; i++) {
                g.getPlayPile().get(color).push(new Card(color, i));
            }
        }

        assertEquals(25, g.getScore());
    }
}