package src.main.java.domain.player;

import src.main.java.domain.card.Card;
import src.main.java.domain.card.Rank;
import src.main.java.domain.card.Suit;
import src.main.java.domain.player.result.DrawCount;
import src.main.java.domain.player.result.LossCount;
import src.main.java.domain.player.result.WinCount;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Player {
    private String name;
    private List<Card> hand;
    private WinCount wins;
    private LossCount losses;
    private DrawCount draws;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.wins = new WinCount();
        this.losses = new LossCount();
        this.draws = new DrawCount();
    }

    public void receiveCard(Card card) {
        hand.add(card);
    }

    public List<Card> getHand() {
        return hand;
    }

    public Map<String, List<String>> getHandDetails() {
        Map<String, List<String>> handDetails = new LinkedHashMap<>();
        for (Card card : hand) {
            String suitName = card.getSuit().getName();
            handDetails.computeIfAbsent(suitName, k -> new ArrayList<>())
                    .add(card.getRank().toString());
        }
        return handDetails;
    }

    public int calculateScore() {
        int score = 0;
        int numAces = 0;

        for (Card card : hand) {
            score += card.getValue();
            if (card.getValue() == 1) {
                numAces++;
            }
        }
        while (numAces > 0 && score <= 11) {
            score += 10;
            numAces--;
        }
        return score;
    }

    public String getName() {
        return name;
    }

    public void incrementWins() {
        wins.increment();
    }

    public void incrementLosses() {
        losses.increment();
    }

    public void incrementDraws() {
        draws.increment();
    }

    public int getWins() {
        return wins.getCount();
    }

    public int getLosses() {
        return losses.getCount();
    }

    public int getDraws() {
        return draws.getCount();
    }
}