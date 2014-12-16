package com.briehman.hanabi.turn;

import com.briehman.hanabi.*;
import com.briehman.hanabi.deck.Card;
import org.junit.Before;
import org.junit.Test;

import static com.briehman.hanabi.Color.*;
import static org.junit.Assert.*;

public class PlayTurnTest {
    private Game game;
    private Player player;

    @Before
    public void setUp() {
        game = new Game();
        player = new Player("Brian");
    }

    @Test
    public void canPlayOneToStart() {
        PlayTurn turn = new PlayTurn(player, new Card(RED, 1));
        turn.validate(game);
        turn.complete(game);
        assertEquals(PlayResult.SUCCESS, turn.getResult());
    }

    @Test
    public void cannotPlayNonOneToStart() {
        PlayTurn turn = new PlayTurn(player, new Card(BLUE, 3));
        turn.validate(game);
        turn.complete(game);
        assertEquals(PlayResult.FAIL, turn.getResult());
    }

    @Test
    public void canPlayAllOnesToStart() {
        for (Color color : Color.values()) {
            PlayTurn turn = new PlayTurn(player, new Card(color, 1));
            turn.validate(game);
            turn.complete(game);
            assertEquals(PlayResult.SUCCESS, turn.getResult());
        }
    }

    @Test
    public void canPlayAllColorsSequentially() {
        for (Color color : Color.values()) {
            for (int i = 1; i <= 5; i++) {
                PlayTurn turn = new PlayTurn(player, new Card(color, i));
                turn.validate(game);
                turn.complete(game);
                assertEquals(PlayResult.SUCCESS, turn.getResult());
            }
        }
    }

    @Test
    public void gameEndsWhenBombOut() {
        for (int i = 0; i < 3; i++) {
            PlayTurn turn = new PlayTurn(player, new Card(RED, 5));
            turn.validate(game);
            turn.complete(game);
            assertEquals(PlayResult.FAIL, turn.getResult());
        }

        assertTrue(game.isFinished());
    }
}