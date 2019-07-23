package cardDeckClasses;

import java.util.ArrayList;
import java.util.Collections;

public class PlayingCardDeck {
    private ArrayList<PlayingCard> cards = new ArrayList<>();

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

    //tar det översta kortet([0]) från kortleken, och tar bort det från högen.
    public PlayingCard drawTopCard() {
        if (!isEmpty()) {
            PlayingCard card = cards.get(0);
            cards.remove(0);
            return card;
        }
        System.out.println("The card deck is empty.");
        return null;
    }

    //"tittar på" det översta kortet i högen, utan att plocka bort det
    public PlayingCard getTopCard(){
        if (!isEmpty()) {
            PlayingCard card = cards.get(0);
            return card;
        }
        System.out.println("The card deck is empty.");
        return null;
    }

    public boolean isEmpty() {
        if (cards == null) {
            return true;
        } else {
            return (cards.size() < 1);
        }
    }

    public void addCardToDeck(PlayingCard card) {
        card.setHidden(true);
        cards.add(card);
    }

    public int cardsLeft(){
        return cards.size();
    }

    public void showCard(PlayingCard card){
        card.setHidden(false);
    }

    public void printCardDetails(PlayingCard card){
        if(!card.isHidden()) {
            System.out.println(card.toString());
        }
    }
}
