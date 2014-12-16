package com.briehman.hanabi.turn;

import com.briehman.hanabi.*;
import com.briehman.hanabi.deck.Card;

import java.util.Stack;

import static com.briehman.hanabi.Action.PLAY;

public class PlayTurn extends Turn {
    private Player player;
    private Card card;
    private PlayResult result;

    public PlayTurn(Player player, Card card) {
        super(player, PLAY);
        this.player = player;
        this.card = card;
    }

    public Player getPlayer() { return player; }
    public Card getCard() { return card; }
    public PlayResult getResult() { return result; }

    @Override
    public void validate(Game g) {}

    @Override
    public void complete(Game g) {
        Stack<Card> playedCards = g.getPlayPile().get(card.color());
        if ((playedCards.isEmpty() && card.number() == 1)
                || (!playedCards.isEmpty() && playedCards.peek().number() == card.number() - 1)) {
            result = PlayResult.SUCCESS;
            playedCards.push(card);
        }
        else {
            result = PlayResult.FAIL;
            g.bomb();
        }
    }
}
