import cardDeckClasses.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GameBoard extends JLayeredPane {

    //Hör ihop med CardLayout (se beskrivning i klassen PanelContainer)
    private CardLayout cl;
    private PanelContainer pc;

    //Kortens storlek
    private final int CARD_HEIGHT = 153;
    private final int CARD_WIDTH = 100;

    //Kortleken - härifrån plockas först korten till pyramiden, blir sedan korthögen vi plockar kort ifrån
    //JLabel och MouseListener gör så att vi kan interagera med det översta kortet
    private PlayingCardDeck deck;
    private JLabel deckImage;
    private boolean deckVisible;
    private MouseListener mlDeck;
    private PlayingCard deckTopCard;

    //ArrayListen pyramidCards innehåller en ArrayList för varje rad i pyramiden
    //Till varje kort finns en bild, som ligger i JLabeln med motsvarande index
    private ArrayList<ArrayList<PlayingCard>> pyramidCards;
    private ArrayList<ArrayList<JLabel>> pyramidCardImages;

    //ArrayListen som hanterar "slänghögen". JLabel och MouseListener gör så att vi kan interagera med det översta kortet
    //boolean berättar om kortleken ska synas eller inte (om den är tom)
    private ArrayList<PlayingCard> discardedCards = new ArrayList<>();
    private JLabel discardedCardImage;
    private boolean discardedVisible;
    private MouseListener mlDiscarded;
    private PlayingCard discardedTopCard;

    //Det "aktiva" kort man försöker para ihop med andra kort (vi behöver kunna hämta ut dess värde så vi kan kolla
    //om summan av det valda kortet och det klickade kortet är 13)
    private PlayingCard chosenCard;

    //Typsnittsstilar - för rubrik resp. text på knappar
    private Font menuHeadlineFont = new Font("arial", Font.BOLD, 50);
    private Font fontButtons = new Font("arial", Font.BOLD, 18);

    //Knapparna till spelet (fanns planer på att det även skulle finnas en ångra-knapp)
    private JButton toMenu = new JButton("Avsluta spel");



    //ram runt kortet som är "valt", samt "icke-ram" för kort som inte är valda
    private Border border = BorderFactory.createLineBorder(Color.RED, 1);
    private Border emptyBorder = BorderFactory.createEmptyBorder();

    //siffra som berättar hur "långt fram" ett kort ska visas (0 = längst fram)
    private int jPanelDepth = 10000;

    //konstruktorn - skapar panelen
    public GameBoard(CardLayout cl, PanelContainer pc) {
        this.setSize(pc.getWidth(), pc.getHeight());
        this.setBackground(pc.getBackgroundColor());
        this.cl = cl;
        this.pc = pc;
        this.setLayout(null);
        this.setOpaque(true);
    }

    //ritar upp knapparna på panelen
    private void addButtons() {
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

    //skapar en ny kortlek och blandar den
    public void startNewGame() {
        removeAll();
        addButtons();
        mlDiscarded = null;
        mlDeck = null;
        discardedCards.clear();
        deckImage = new JLabel();
        discardedCardImage = new JLabel();
        deck = new PlayingCardDeck();
        deck.shuffleCardDeck();
        addCards(deck);
        addMouseListeners();
        deckVisible = true;
        discardedVisible = true;
        displayCardDeck(deck);
    }

    //Lägger ut korten i pyramiden på bordet
    private void addCards(PlayingCardDeck deck) {
        //ArrayListorna med Arrays skapas för pyramidens kort och bilderna som hör till
        //Dessa ArrayLists fylls sedan på med en ArrayList för varje rad i pyramiden
        pyramidCards = new ArrayList<>();
        pyramidCardImages = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            pyramidCards.add(new ArrayList<>());
            pyramidCardImages.add(new ArrayList<>());
        }

        //Nedan hämtas kort från leken och tilldelas rad och kolumn i pyramiden, samt ritas ut

        //Platsen där det första kortet ska ritas ut:
        int xPosRow1 = 650;
        int yPosRow1 = 150;

        //Variabler för utritning av övriga kort
        int xPosCard;
        int yPosCard;

        for (int i = 6; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                PlayingCard c = deck.drawTopCard();
                c.setHidden(false);
                pyramidCards.get(i).add(c);
                xPosCard = xPosRow1 - (int) (i * 0.6 * CARD_WIDTH) + (int) (j * (CARD_WIDTH + (0.2 * CARD_WIDTH)));
                yPosCard = yPosRow1 + i * 50;
                PlayingCardImage cardImage = new PlayingCardImage(c.getRank(), c.getSuit(), c.isHidden());
                JLabel jl = displayImage(cardImage.getUrlAddress(), xPosCard, yPosCard);
                this.setLayer(jl, jPanelDepth + i);
                pyramidCardImages.get(i).add(jl);
                if (i != 6) {
                    c.setClickable(false);
                } else {
                    c.setClickable(true);
                }

            }
        }
    }

    //visar korthögen, så länge det finns kort i den
    private void displayCardDeck(PlayingCardDeck deck) {
        if (!deck.isEmpty()) {
            deckTopCard = deck.getTopCard();
            PlayingCardImage cardImage = new PlayingCardImage(deckTopCard.getRank(), deckTopCard.getSuit(), deckTopCard.isHidden());
            displayDeckImage(cardImage.getUrlAddress(), 20, 540, deckImage);
            if (mlDeck == null) {
                mlDeck = new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        discardedVisible = true;
                        discardedCardImage.setVisible(true);
                        deckTopCard = deck.getTopCard();
                        deckTopCard.setHidden(false);
                        discardedCards.add(deckTopCard);
                        deck.drawTopCard();
                        if (deckImage != null && deck.isEmpty()) {
                            deckVisible = false;
                        }
                        displayDiscardedDeck(discardedCards);
                        repaint();
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                    }

                };
                deckImage.addMouseListener(mlDeck);
            }
        }
    }

    //visar slängghögen (översta kortet), när det finns kort i den
    private void displayDiscardedDeck(ArrayList<PlayingCard> discardedCards) {
        PlayingCardImage discardedTopCardImage;
        if (discardedCards != null && discardedCards.size() > 0) {
            discardedTopCard = discardedCards.get(discardedCards.size() - 1);
            discardedTopCard.setClickable(true);
            discardedTopCardImage = new PlayingCardImage(discardedTopCard.getRank(), discardedTopCard.getSuit(), discardedTopCard.isHidden());
            displayDeckImage(discardedTopCardImage.getUrlAddress(), 130, 540, discardedCardImage);
            if (mlDiscarded == null) {
                mlDiscarded = new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (discardedTopCard.isChosen()) {
                            discardedTopCard.setChosen(false);
                            chosenCard = null;
                        } else if (discardedTopCard.getRank().getValue() == 13) {
                            discardedCards.remove(discardedTopCard);
                        } else if (chosenCard == null) {
                            discardedTopCard.setChosen(true);
                            chosenCard = discardedTopCard;
                        } else if ((discardedTopCard.getRank().getValue() + chosenCard.getRank().getValue()) == 13) {
                            discardedCards.remove(discardedTopCard);

                            for (int i = 0; i < 7; i++) {
                                for (int j = 0; j <= i; j++) {
                                    PlayingCard card = pyramidCards.get(i).get(j);
                                    if (card != null && card.equals(chosenCard)) {
                                        pyramidCards.get(i).set(j, null);
                                        pyramidCardImages.get(i).get(j).setVisible(false);
                                    }
                                }
                            }
                            checkCardsAvailable();
                            chosenCard = null;
                        }
                        if (discardedCardImage != null) {
                            if (discardedTopCard.isChosen()) {
                                discardedCardImage.setBorder(border);
                            } else {
                                discardedCardImage.setBorder(emptyBorder);
                            }
                        }
                        if (discardedCards.size() == 0) {
                            discardedVisible = false;
                        }
                        repaint();
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                    }
                };
                discardedCardImage.addMouseListener(mlDiscarded);
            }
        }
    }

    //kollar korten i pyramiden, om de är tillgängliga att klicka på
    private void checkCardsAvailable() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j <= i; j++) {
                if (pyramidCards.get(i).get(j) != null && pyramidCards.get(i + 1).get(j) == null && pyramidCards.get(i + 1).get(j + 1) == null) {
                    pyramidCards.get(i).get(j).setClickable(true);
                }
            }
        }
    }

    //ritar upp ett kort (fram-eller baksida), på en viss plats
    //används endast för uppritning av pyramiden
    private JLabel displayImage(String url, int x, int y) {
        JLabel jl = new JLabel();
        jl.setIcon(new ImageIcon(getClass().getClassLoader().getResource(url)));
        jl.setOpaque(true);
        jl.setBounds(x, y, CARD_WIDTH, CARD_HEIGHT);
        this.add(jl);
        this.setLayer(jl, jPanelDepth--);
        return jl;
    }

    //ritar upp översta kortet i kortlek resp. slänghög
    private void displayDeckImage(String url, int x, int y, JLabel jl) {
        jl.setIcon(new ImageIcon(getClass().getClassLoader().getResource(url)));
        jl.setOpaque(true);
        jl.setBounds(x, y, CARD_WIDTH, CARD_HEIGHT);
        if (!this.isAncestorOf(jl)) {
            this.add(jl);
            this.setLayer(jl, jPanelDepth--);
        }
    }

    //berättar vad som händer när man klickar på kort _i pyramiden_
    private void addMouseListeners() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j <= i; j++) {
                JLabel jl = pyramidCardImages.get(i).get(j);
                PlayingCard card = pyramidCards.get(i).get(j);
                jl.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        //körs endast om det finns ett kort på platsen
                        if (card != null) {
                            //och om kortet är klickbart (inte döljs av andra kort)
                            if (card.isClickable()) {
                                //om kortet redan är valt avväljs det
                                if (card.isChosen()) {
                                    card.setChosen(false);
                                    chosenCard = null;
                                } else if (card.getRank().getValue() == 13) {
                                    for (int i = 0; i < 7; i++) {
                                        for (int j = 0; j <= i; j++) {
                                            PlayingCard card2 = pyramidCards.get(i).get(j);
                                            if (card.equals(card2)) {
                                                pyramidCards.get(i).set(j, null);
                                                pyramidCardImages.get(i).get(j).setVisible(false);
                                            }
                                        }
                                    }
                                    checkCardsAvailable();
                                } else if (chosenCard == null) {
                                    card.setChosen(true);
                                    chosenCard = card;
                                } else if ((card.getRank().getValue() + chosenCard.getRank().getValue()) == 13) {
                                    if (chosenCard.equals(discardedTopCard)) {
                                        discardedCards.remove(discardedTopCard);
                                    }
                                    for (int i = 0; i < 7; i++) {
                                        for (int j = 0; j <= i; j++) {
                                            PlayingCard card2 = pyramidCards.get(i).get(j);
                                            if (card2 != null && card2.equals(chosenCard)) {
                                                pyramidCards.get(i).set(j, null);
                                                pyramidCardImages.get(i).get(j).setVisible(false);
                                            }
                                            if (card2 != null && card2.equals(card)) {
                                                pyramidCards.get(i).set(j, null);
                                                pyramidCardImages.get(i).get(j).setVisible(false);
                                            }
                                        }
                                    }
                                    checkCardsAvailable();
                                    chosenCard = null;
                                }
                                if (card.isChosen()) {
                                    jl.setBorder(border);
                                } else {
                                    jl.setBorder(emptyBorder);
                                }
                                repaint();
                            }

                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                    }
                });
            }
        }
    }

    //kollar om vi har vunnit
    private boolean isWin() {
        boolean isWin = true;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j <= i; j++) {
                if (pyramidCards.get(i).get(j) != null) {
                    return false;
                }
            }
        }
        return isWin;
    }

    //ser till att grafiken hålls uppdaterad
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(menuHeadlineFont);
        g.setColor(Color.white);
        g.drawString("PYRAMIDEN", 555, 50);

        //uppdaterar kortlekens bild så länge det finns kort i den
        if (deck != null && deck.cardsLeft() > 0) {
            displayCardDeck(deck);
        }
        //uppdaterar släng-kortlekens bild  när det finns kort i den
        if (discardedCards != null && discardedCards.size() > 0) {
            displayDiscardedDeck(discardedCards);
        }
        //"döljer" kortleken när den är tom
        if (!deckVisible && deckImage != null) {
            deckImage.setVisible(false);
        }
        //"döljer" släng-högen när den är tom
        if (!discardedVisible && discardedCardImage != null) {
            discardedCardImage.setVisible(false);
        }
        //win-screen, visas när man vinner
        if (isWin()) {
            this.removeAll();
            setBackground(pc.getBackgroundColor());
            JLabel win = new JLabel("Grattis! Du vann!");
            win.setHorizontalAlignment(SwingConstants.CENTER);
            win.setVerticalAlignment(SwingConstants.CENTER);
            win.setVisible(true);
            win.setOpaque(true);
            win.setFont(menuHeadlineFont);
            win.setBounds(480, 300, 450, 200);
            this.add(win, 0);
            addButtons();
        }
    }
}
