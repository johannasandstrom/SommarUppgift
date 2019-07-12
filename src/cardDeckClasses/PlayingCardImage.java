package cardDeckClasses;

import javax.swing.*;

import static cardDeckClasses.Suit.*;

public class PlayingCardImage {
    private ImageIcon image;

    PlayingCardImage(Rank rank, Suit suit, boolean isHidden) {
        //baksidan på kortet visas om det är "hidden"
        if (isHidden) {
            image = new ImageIcon(getClass().getResource("playing-card-back-side.jpg"));
            //hämtar bilden för ruter-korten med värde 2-10
        } else if (suit == CLUBS && rank.getValue() > 1 && rank.getValue() < 11) {
            image = new ImageIcon(getClass().getResource(rank.getValue() + "C.jpg"));
            //hämtar bilden för hjärter-korten med värde 2-10
        } else if (suit == HEARTS && rank.getValue() > 1 && rank.getValue() < 11) {
            image = new ImageIcon(getClass().getResource(rank.getValue() + "H.jpg"));
            //hämtar bilden för ruter-korten med värde 2-10
        } else if (suit == DIAMONDS && rank.getValue() > 1 && rank.getValue() < 11) {
            image = new ImageIcon(getClass().getResource(rank.getValue() + "D.jpg"));
            //hämtar bilden för spader-korten med värde 2-10
        } else if (suit == SPADES && rank.getValue() > 1 && rank.getValue() < 11) {
            image = new ImageIcon(getClass().getResource(rank.getValue() + "S.jpg"));
            //nedan tilldelas bilderna till ess-korten
        } else if (suit == CLUBS && rank.getValue() == 1){
            image = new ImageIcon(getClass().getResource("AS.jpg"));
        } else if (suit == HEARTS && rank.getValue() == 1){
            image = new ImageIcon(getClass().getResource("AH.jpg"));
        } else if (suit == DIAMONDS && rank.getValue() == 1){
            image = new ImageIcon(getClass().getResource("AD.jpg"));
        } else if (suit == SPADES && rank.getValue() == 1){
            image = new ImageIcon(getClass().getResource("AS.jpg"));
            //nedan tilldelas bilderna till knekt-korten
        } else if (suit == CLUBS && rank.getValue() == 11){
            image = new ImageIcon(getClass().getResource("JS.jpg"));
        } else if (suit == HEARTS && rank.getValue() == 11){
            image = new ImageIcon(getClass().getResource("JH.jpg"));
        } else if (suit == DIAMONDS && rank.getValue() == 11){
            image = new ImageIcon(getClass().getResource("JD.jpg"));
        } else if (suit == SPADES && rank.getValue() == 11){
            image = new ImageIcon(getClass().getResource("JS.jpg"));
            //nedan tilldelas bilderna till dam-korten
        } else if (suit == CLUBS && rank.getValue() == 12){
            image = new ImageIcon(getClass().getResource("QS.jpg"));
        } else if (suit == HEARTS && rank.getValue() == 12){
            image = new ImageIcon(getClass().getResource("QH.jpg"));
        } else if (suit == DIAMONDS && rank.getValue() == 12){
            image = new ImageIcon(getClass().getResource("QD.jpg"));
        } else if (suit == SPADES && rank.getValue() == 12){
            image = new ImageIcon(getClass().getResource("QS.jpg"));
            //nedan tilldelas bilderna till kung-korten
        } else if (suit == CLUBS && rank.getValue() == 13){
            image = new ImageIcon(getClass().getResource("KS.jpg"));
        } else if (suit == HEARTS && rank.getValue() == 13){
            image = new ImageIcon(getClass().getResource("KH.jpg"));
        } else if (suit == DIAMONDS && rank.getValue() == 13){
            image = new ImageIcon(getClass().getResource("KD.jpg"));
        } else if (suit == SPADES && rank.getValue() == 13) {
            image = new ImageIcon(getClass().getResource("KS.jpg"));
        }
    }

    public ImageIcon getImage() {
        return image;
    }
}
