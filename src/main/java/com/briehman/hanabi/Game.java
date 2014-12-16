package com.briehman.hanabi;

import com.briehman.hanabi.deck.Card;
import com.briehman.hanabi.deck.Deck;
import com.briehman.hanabi.turn.OutOfTurnException;
import com.briehman.hanabi.turn.Turn;

import java.util.*;

public class Game {
    private static final int STARTING_HINTS = 8;
    private static final int STARTING_BOMBS = 4;
    private static final int MIN_PLAYERS = 2;
    private static final int MAX_PLAYERS = 5;

    private List<Player> players = new ArrayList<>();
    private Map<Color, Stack<Card>> playPile = new HashMap<>();
    private List<Card> discardPile = new ArrayList<>();

    private Deck deck = new Deck();

    private int hintsLeft = STARTING_HINTS;
    private int bombsLeft = STARTING_BOMBS;
    private int currentPlayer;
    private boolean isStarted;
    private boolean isGameOver;

    public Game() {
        for (Color color : Color.values())
            playPile.put(color, new Stack<Card>());
    }

    public Deck getDeck() { return deck; }
    public boolean isFinished() { return isGameOver; }
    public boolean isStarted() { return isStarted; }

    public Map<Color, Stack<Card>> getPlayPile() { return playPile; }

    public void addPlayer(Player p) {
        if (players.contains(p))
            throw new PlayerException(p + " is already in the game!");
        else
            players.add(p);
    }

    public void start() {
        if (players.size() < MIN_PLAYERS || players.size() > MAX_PLAYERS)
            throw new RuleException("2 - 5 players allowed");

        initializeHands();
        currentPlayer = 0;
        isStarted = true;
    }

    private void initializeHands() {
        int handSize = handSize();
        for (Player player : players) {
            if (player.getHand() == null) {
                player.setHand(deck.drawHand(handSize));
            }
        }
    }

    public int handSize() {
        if (players.size() < 4) {
            return 5;
        }
        else {
            return 4;
        }
    }

    public void addPlayers(Player... players) {
        addPlayers(Arrays.asList(players));
    }

    public void addPlayers(List<Player> players) {
        for (Player p : players)
            addPlayer(p);
    }

    public void turn(Turn turn) {
        if (turn.getPlayer() != players.get(currentPlayer))
            throw new OutOfTurnException(turn.getPlayer());
        else if (isFinished())
            throw new GameOverException();

        turn.validate(this);
        turn.complete(this);

        currentPlayer = (currentPlayer + 1) % players.size();
    }

    public int getHintsLeft() {
        return hintsLeft;
    }

    public void useHint() {
        hintsLeft--;
    }

    public void bomb() {
        bombsLeft--;
        if (bombsLeft == 1)
            isGameOver = true;
    }

    public void receiveHint() {
        hintsLeft = Math.min(hintsLeft + 1, STARTING_HINTS);
    }

    public List<Card> getDiscardPile() {
        return discardPile;
    }

    public int getScore() {
        int score = 0;
        for (Stack<Card> cards: playPile.values())
            score += cards.size();
        return score;
    }
}