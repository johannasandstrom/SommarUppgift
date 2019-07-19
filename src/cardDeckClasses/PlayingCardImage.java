package cardDeckClasses;

import static cardDeckClasses.Suit.*;

public class PlayingCardImage {
    private String urlAddress;

    public PlayingCardImage(Rank rank, Suit suit, boolean isHidden) {
        //baksidan på kortet visas om det är "hidden"
        if (isHidden) {
            urlAddress = "playing-card-back-side.jpg";
            //hämtar bilden för ruter-korten med värde 2-10
        } else if (suit == CLUBS && rank.getValue() > 1 && rank.getValue() < 11) {
            urlAddress = rank.getValue() + "C.jpg";
            //hämtar bilden för hjärter-korten med värde 2-10
        } else if (suit == HEARTS && rank.getValue() > 1 && rank.getValue() < 11) {
            urlAddress = rank.getValue() + "H.jpg";
            //hämtar bilden för ruter-korten med värde 2-10
        } else if (suit == DIAMONDS && rank.getValue() > 1 && rank.getValue() < 11) {
            urlAddress = rank.getValue() + "D.jpg";
            //hämtar bilden för spader-korten med värde 2-10
        } else if (suit == SPADES && rank.getValue() > 1 && rank.getValue() < 11) {
            urlAddress = rank.getValue() + "S.jpg";
            //nedan tilldelas bilderna till ess-korten
        } else if (suit == CLUBS && rank.getValue() == 1){
            urlAddress = "AS.jpg";
        } else if (suit == HEARTS && rank.getValue() == 1){
            urlAddress = "AH.jpg";
        } else if (suit == DIAMONDS && rank.getValue() == 1){
            urlAddress = "AD.jpg";
        } else if (suit == SPADES && rank.getValue() == 1){
            urlAddress = "AS.jpg";
            //nedan tilldelas bilderna till knekt-korten
        } else if (suit == CLUBS && rank.getValue() == 11){
            urlAddress = "JS.jpg";
        } else if (suit == HEARTS && rank.getValue() == 11){
            urlAddress = "JH.jpg";
        } else if (suit == DIAMONDS && rank.getValue() == 11){
            urlAddress = "JD.jpg";
        } else if (suit == SPADES && rank.getValue() == 11){
            urlAddress = "JS.jpg";
            //nedan tilldelas bilderna till dam-korten
        } else if (suit == CLUBS && rank.getValue() == 12){
            urlAddress = "QS.jpg";
        } else if (suit == HEARTS && rank.getValue() == 12){
            urlAddress = "QH.jpg";
        } else if (suit == DIAMONDS && rank.getValue() == 12){
            urlAddress = "QD.jpg";
        } else if (suit == SPADES && rank.getValue() == 12){
            urlAddress = "QS.jpg";
            //nedan tilldelas bilderna till kung-korten
        } else if (suit == CLUBS && rank.getValue() == 13){
            urlAddress = "KS.jpg";
        } else if (suit == HEARTS && rank.getValue() == 13){
            urlAddress = "KH.jpg";
        } else if (suit == DIAMONDS && rank.getValue() == 13){
            urlAddress = "KD.jpg";
        } else if (suit == SPADES && rank.getValue() == 13) {
            urlAddress = "KS.jpg";
        }
    }

    public String getUrlAddress(){
        return "./images/" + urlAddress;
    }
}
