package com.briehman.hanabi;

import com.briehman.hanabi.deck.Card;
import com.briehman.hanabi.deck.Deck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OddsCalculator {
    private Game game;
    private Map<Color, Integer> colorCount = new HashMap<>();
    private Map<Integer, Integer> numberCount = new HashMap<>();

    public OddsCalculator(Game game) {
        this.game = game;
        for (Color color : Color.values())
            colorCount.put(color, 0);
        for (int i = 1; i <= 5; i++)
            numberCount.put(i, 0);
    }

    public Map<Card, Odds> calculateOdds(Player currentPlayer) {
        List<Card> visibleCards = new ArrayList<>();
        for (Player player : game.getPlayers()) {
            // We can see cards of other players
            if (!player.equals(currentPlayer)) {
                visibleCards.addAll(player.getHand().cards());
            }
        }

        visibleCards.addAll(game.getPlayedCards());
        visibleCards.addAll(game.getDiscardedCards());

        Deck deck = game.getDeck();
        int invisibleCards = deck.totalCards() - visibleCards.size();

        for (Card card : visibleCards) {
            addNumber(card);
            addColor(card);
        }

        Map<Card, Odds> handOdds = new HashMap<>();

        for (Card card : currentPlayer.getHand().cards()) {
            Odds odds = new Odds();

            for (Map.Entry<Integer, Integer> entry : numberCount.entrySet()) {
                Integer number = entry.getKey();
                int remainingNumber = deck.getNumberCards(number) * deck.getNumberColors() - entry.getValue();
                    odds.setOdds(number, 1.0 * remainingNumber / invisibleCards);
            }

            for (Map.Entry<Color, Integer> entry : colorCount.entrySet()) {
                Color color = entry.getKey();
                int remainingColor = deck.getNumberCards(color) - entry.getValue();
                odds.setOdds(color, 1.0 * remainingColor / invisibleCards);
            }
            handOdds.put(card, odds);
        }

        return handOdds;
    }

    private void addNumber(Card card) {
        int number = card.number();
        numberCount.put(number, numberCount.get(number) + 1);
    }

    private void addColor(Card card) {
        Color color = card.color();
        colorCount.put(color, colorCount.get(color) + 1);
    }

    private int totalCards(int i) {
        return 50;
    }
}
