import cardDeckClasses.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameBoard extends JPanel {

    //hör ihop med CardLayout (se PanelContainer)
    private CardLayout cl;
    private PanelContainer pc;

    //kortens storlek, index för hjälp med placering
    private final int CARD_HEIGHT = 153;
    private final int CARD_WIDTH = 100;

    //kortleken
    private PlayingCardDeck deck;

    //kortens plats på brädet - de olika raderna
    ArrayList<ArrayList<PlayingCard>> pyramidRowArray;

    //kortens plats på brädet - "slänghögen"
    //("draghögen" = PlayingCardDeck deck)
    ArrayList<PlayingCard> usedCards;

    //typsnittsstilar
    private Font menuHeadlineFont = new Font("arial", Font.BOLD, 50);
    private Font fontButtons = new Font("arial", Font.BOLD, 18);

    //knapparna till spelet
    private JButton toMenu = new JButton("Avsluta spel");
    private JButton undo = new JButton("Ångra drag");

    //platsen där det första kortet ska ritas ut
    private int xPosRow1 = 650;
    private int yPosRow1 = 150;

    //variabel för utritning av övriga kort
    private int xPosCard;
    private int yPosCard;


    public GameBoard(CardLayout cl, PanelContainer pc) {
        this.setSize(pc.getWidth(), pc.getHeight());
        this.setBackground(pc.getBackgroundColor());
        this.cl = cl;
        this.pc = pc;
        this.setLayout(null);
        addButtons();
    }

    private void addButtons() {
        //ångra-knappen (implementeras om tid finns)
        undo.setBounds(1150, 350, 160, 60);
        undo.setFont(fontButtons);
        this.add(undo);
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //vad ska läggas in här? Hur sparar man enklast tidigare drag?
            }
        });

        //till menyn-knappen
        toMenu.setBounds(1150, 430, 160, 60);
        toMenu.setFont(fontButtons);
        this.add(toMenu);
        toMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(pc, "1");
            }
        });
    }

    public void startNewGame() {
        deck = new PlayingCardDeck();
        deck.shuffleCardDeck();
    }

    private void addCards(PlayingCardDeck deck) {
        //kortens plats på brädet - de olika raderna
        pyramidRowArray = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            pyramidRowArray.add(new ArrayList<>());
        }

        //kort hämtas från leken och tilldelas rad och kolumn i pyramiden
        //samt ritas ut
        for (int i = 6; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                PlayingCard c = deck.drawTopCard();
                c.setHidden(false);
                pyramidRowArray.get(i).add(c);
                if(i != 6){
                    c.setClickable(false);
                } else {
                    c.setClickable(true);
                }
                xPosCard = xPosRow1 - (int) (i * 0.6 * CARD_WIDTH) + (int) (j * (CARD_WIDTH + (0.2 * CARD_WIDTH)));
                yPosCard = yPosRow1 + i * 50;
                PlayingCardImage cardImage = new PlayingCardImage(c.getRank(), c.getSuit(), c.isHidden());
                displayImage(cardImage.getUrlAddress(), xPosCard, yPosCard);
            }
        }
    }

    private void displayCardDeck(PlayingCardDeck deck) {
        while (!deck.isEmpty()) {
            PlayingCard c = deck.drawTopCard();
            PlayingCardImage cardImage = new PlayingCardImage(c.getRank(), c.getSuit(), c.isHidden());
            displayImage(cardImage.getUrlAddress(), 20, 540);
        }
    }


    private void displayImage(String url, int x, int y) {
        JLabel jl = new JLabel();
        jl.setIcon(new ImageIcon(getClass().getClassLoader().getResource(url)));
        jl.setOpaque(true);
        jl.setBounds(x, y, CARD_WIDTH, CARD_HEIGHT);
        this.add(jl);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(menuHeadlineFont);
        g.setColor(Color.white);
        g.drawString("PYRAMIDEN", 555, 50);
        addCards(deck);
        displayCardDeck(deck);
    }

}
