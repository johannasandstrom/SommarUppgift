package cardDeckClasses;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;

public class PlayingCardDeck {
    private ArrayList<PlayingCard> cards;

    public PlayingCardDeck() {
        createFullCardDeck();
    }

    private void createFullCardDeck() {
        for (int i = 1; i <= 13; i++) {
            cards.add(new PlayingCard(new Rank(i), Suit.CLUBS));
        }
        for (int i = 1; i <= 13; i++) {
            cards.add(new PlayingCard(new Rank(i), Suit.HEARTS));
        }
        for (int i = 1; i <= 13; i++) {
            cards.add(new PlayingCard(new Rank(i), Suit.SPADES));
        }
        for (int i = 1; i <= 13; i++) {
            cards.add(new PlayingCard(new Rank(i), Suit.DIAMONDS));
        }
    }

    public void shuffleCardDeck() {
        Collections.shuffle(cards);
    }

    //Takes the top card ([0]) of the deck. The card is then deleted from the deck.
    public PlayingCard drawTopCard() {
        if (!deckIsEmpty()) {
            PlayingCard card = cards.get(0);
            cards.remove(0);
            return card;
        }
        System.out.println("The card deck is empty.");
        return null;
    }

    public boolean deckIsEmpty() {
        if (cards == null) {
            return true;
        } else {
            return (cards.size() < 1);
        }
    }

    public void addCardToDeck(@NotNull PlayingCard card) {
        card.setHidden(true);
        cards.add(card);
    }

    public int cardsLeft(){
        return cards.size();
    }

    public void showCard(@NotNull PlayingCard card){
        card.setHidden(false);
    }

    public void printCardDetails(@NotNull PlayingCard card){
        if(!card.isHidden()) {
            System.out.println(card.toString());
        }
    }
}
