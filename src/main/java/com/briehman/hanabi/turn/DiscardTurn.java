package com.briehman.hanabi.turn;

import com.briehman.hanabi.*;
import com.briehman.hanabi.deck.Card;
import com.briehman.hanabi.deck.Deck;
import com.briehman.hanabi.deck.Hand;

import static com.briehman.hanabi.Action.DISCARD;

public class DiscardTurn extends Turn {
    private Card card;

    public DiscardTurn(Player player, Card card) {
        super(player, DISCARD);
        this.card = card;
    }

    @Override
    public void validate(Game g) {
        player.getHand().hasExactCard(card);
    }

    @Override
    public void complete(Game g) {
        Hand hand = player.getHand();
        hand.discard(card);
        g.getDiscardPile().add(card);
        g.receiveHint();

        Deck deck = g.getDeck();
        if (deck.hasCardLeft())
            hand.add(g.getDeck().draw());
    }
}