package com.briehman.hanabi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static com.briehman.hanabi.Action.*;

public class Game {
    private static final int STARTING_HINTS = 8;
    private static final int STARTING_BOMBS = 4;
    private static final int MIN_PLAYERS = 2;
    private static final int MAX_PLAYERS = 5;

    private List<Player> players = new ArrayList<>();
    private List<Turn> turnHistory = new ArrayList<>();
    private Deck deck = new Deck();

    private int hintsLeft = STARTING_HINTS;
    private int bombsLeft = STARTING_BOMBS;
    private int currentPlayer;
    private boolean isStarted;

    public Game() {}

    public boolean isFinished() { return false; }
    public boolean isStarted() { return isStarted; }

    public void addPlayer(Player p) {
        if (players.contains(p))
            throw new PlayerException(p + " is already in the game!");
        else
            players.add(p);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public void start() {
        if (players.size() < MIN_PLAYERS || players.size() > MAX_PLAYERS)
            throw new RuleException("2 - 5 players allowed");
        currentPlayer = 0;
        isStarted = true;
    }

    public void addPlayers(Player... players) {
        addPlayers(Arrays.asList(players));
    }

    public void addPlayers(List<Player> players) {
        for (Player p : players)
            addPlayer(p);
    }

    public void turn(Turn turn) {
        if (turn.getPlayer() != players.get(currentPlayer)) {
            throw new OutOfTurnException(turn.getPlayer());
        }

        turn.validate(this);
        turnHistory.add(turn);
        turn.complete(this);

        currentPlayer = (currentPlayer + 1) % players.size();
    }

    public List<Turn> turns() {
        return Collections.unmodifiableList(turnHistory);
    }

    public int getHintsLeft() {
        return hintsLeft;
    }

    public void useHint() {
        hintsLeft--;
    }
}