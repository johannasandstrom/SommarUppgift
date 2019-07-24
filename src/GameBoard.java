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
    //DeckRound kollar om det är ok att gå igenom kortleken en gång till
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

    //Ram runt kortet som är "valt", samt "icke-ram" för de kort som inte är valda
    private Border border = BorderFactory.createLineBorder(Color.RED, 1);
    private Border emptyBorder = BorderFactory.createEmptyBorder();

    //Variabel som används för att se till att korten i pyramiden hamnar i rätt "lager" (0 = längst fram, föremål med
    // högre siffra visas längre bak)
    private int jPanelDepth = 10000;

    //Konstruktorn - skapar panelen. Är package-private (= inte private, public eller protected)
    GameBoard(CardLayout cl, PanelContainer pc) {
        this.setSize(pc.getWidth(), pc.getHeight());
        this.setBackground(pc.getBackgroundColor());
        this.cl = cl;
        this.pc = pc;
        this.setLayout(null);
        this.setOpaque(true);
    }

    //Ritar upp knapparna på panelen
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

    //Skapar en ny kortlek och blandar den, samt ser till att all grafik ritas upp rätt (package-private)
    void startNewGame() {
        removeAll(); //Tömmer planen på tidigare innehåll - behövs om man startar upp ett nytt spel efter att ha avslutat ett annat
        addButtons();
        chosenCard = null;
        mlDiscarded = null;
        mlDeck = null;
        discardedCards.clear();
        deckImage = new JLabel();
        discardedCardImage = new JLabel();
        deck = new PlayingCardDeck();
        deck.shuffleCardDeck();
        addCards();
        addMouseListenersToPyramidCards();
        deckVisible = true;
        discardedVisible = true;
    }

    //Lägger ut korten i pyramiden på bordet
    private void addCards() {
        //ArrayListorna med Arrays skapas för pyramidens kort och bilderna som hör till
        //Dessa ArrayLists fylls sedan på med en ArrayList för varje rad i pyramiden
        pyramidCards = new ArrayList<>();
        pyramidCardImages = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            pyramidCards.add(new ArrayList<>());
            pyramidCardImages.add(new ArrayList<>());
        }
        //Platsen där det första kortet ska ritas ut:
        int xPosRow1 = 650;
        int yPosRow1 = 150;
        //Variabler för utritning av övriga kort
        int xPosCard;
        int yPosCard;
        //Kort från leken hämtas ut och tilldelas rad och kolumn i pyramiden, samt ritas ut
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

    //Skapar en MouseListener för kortlekshögen
    private void createMouseListenerForDeck() {
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
                displayDiscardedDeck();
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

    //Visar korthögen, så länge det finns kort i den
    private void displayCardDeck(PlayingCardDeck deck) {
        if (!deck.isEmpty()) {
            deckTopCard = deck.getTopCard();
            PlayingCardImage cardImage = new PlayingCardImage(deckTopCard.getRank(), deckTopCard.getSuit(), deckTopCard.isHidden());
            displayDeckImage(cardImage.getUrlAddress(), 20, deckImage);
            if (mlDeck == null) {
                createMouseListenerForDeck();
            }
        }
    }

    //Skapar en MouseListener för slänghögen
    private void createMouseListenerForDiscardedPile() {
        if (mlDiscarded == null) {
            mlDiscarded = new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //Om kortet redan är valt avväljs det
                    if (discardedTopCard.isChosen()) {
                        discardedTopCard.setChosen(false);
                        chosenCard = null;
                        //Om kortet är en kung plockas det bort
                    } else if (discardedTopCard.getRank().getValue() == 13) {
                        discardedCards.remove(discardedTopCard);
                        //Om inget kort är valt väljs detta kort
                    } else if (chosenCard == null) {
                        discardedTopCard.setChosen(true);
                        chosenCard = discardedTopCard;
                        //Om det valda kortets värde plus detta kortets värde är 13 tas båda korten bort
                    } else if ((discardedTopCard.getRank().getValue() + chosenCard.getRank().getValue()) == 13) {
                        discardedCards.remove(discardedTopCard);

                        //Hittar vilket kort i pyramiden som är det valda
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
                    //
                    if (discardedCardImage != null) {
                        if (discardedTopCard.isChosen()) {
                            discardedCardImage.setBorder(border);
                        } else {
                            discardedCardImage.setBorder(emptyBorder);
                        }
                    }
                    if (discardedCards.size() == 0) {
                        discardedVisible = false;
                    } else {
                        discardedVisible = true;
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

    //Visar slängghögen (översta kortet), när det finns kort i den
    private void displayDiscardedDeck() {
        PlayingCardImage discardedTopCardImage;
        if (discardedCards != null && discardedCards.size() > 0) {
            discardedTopCard = discardedCards.get(discardedCards.size() - 1);
            discardedTopCard.setClickable(true);
            discardedTopCardImage = new PlayingCardImage(discardedTopCard.getRank(), discardedTopCard.getSuit(), discardedTopCard.isHidden());
            displayDeckImage(discardedTopCardImage.getUrlAddress(), 130, discardedCardImage);
            createMouseListenerForDiscardedPile();
        }
    }

    //Kollar korten i pyramiden, om de är tillgängliga att klicka på (= om det inte ligger några kort framför dem)
    private void checkCardsAvailable() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j <= i; j++) {
                if (pyramidCards.get(i).get(j) != null && pyramidCards.get(i + 1).get(j) == null && pyramidCards.get(i + 1).get(j + 1) == null) {
                    pyramidCards.get(i).get(j).setClickable(true);
                }
            }
        }
    }

    //Ritar upp ett kort (fram-eller baksida beroende på om det är synligt eller inte), på en viss plats.
    //Används endast för uppritning av pyramiden
    private JLabel displayImage(String url, int x, int y) {
        JLabel jl = new JLabel();
        jl.setIcon(new ImageIcon(getClass().getClassLoader().getResource(url)));
        jl.setOpaque(true);
        jl.setBounds(x, y, CARD_WIDTH, CARD_HEIGHT);
        this.add(jl);
        this.setLayer(jl, jPanelDepth--);
        return jl;
    }

    //Ritar upp översta kortet i kortlek resp. slänghög
    private void displayDeckImage(String url, int x, JLabel jl) {
        jl.setIcon(new ImageIcon(getClass().getClassLoader().getResource(url)));
        jl.setOpaque(true);
        jl.setBounds(x, 540, CARD_WIDTH, CARD_HEIGHT);
        if (!this.isAncestorOf(jl)) {
            this.add(jl);
        }
    }

    //Berättar vad som händer när man klickar på kort i pyramiden
    private void addMouseListenersToPyramidCards() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j <= i; j++) {
                JLabel jl = pyramidCardImages.get(i).get(j);
                PlayingCard card = pyramidCards.get(i).get(j);
                jl.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        //Inget händer om man trycker på ett ställe där det tidigare funnits ett kort...
                        if (card != null) {
                            //... Eller om kortet är dolt av andra kort
                            if (card.isClickable()) {
                                //Om kortet redan är valt avväljs det
                                if (card.isChosen()) {
                                    card.setChosen(false);
                                    chosenCard = null;
                                    //Om kortet är en kung tas det bort (vi måste leta upp vilket kort vi tryckt på
                                    //genom att gå igenom korten i pyramiden -card2- och jämföra referensen till vårt kort - card-)
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
                                    //Om inget kort är valt sedan innan är detta kortet nu valt
                                } else if (chosenCard == null) {
                                    card.setChosen(true);
                                    chosenCard = card;
                                    //Om summan av det valda kortets värde och det klickade kortets värde är 13
                                    //tas båda korten bort (vi måste leta igenom pyramiden för att hitta vilka kort
                                    //det gäller, samt kolla om det är kortet i slänghögen som är valt
                                } else if ((card.getRank().getValue() + chosenCard.getRank().getValue()) == 13) {
                                    if (chosenCard.equals(discardedTopCard)) {
                                        discardedTopCard.setChosen(false);
                                        discardedCardImage.setBorder(emptyBorder);
                                        discardedCards.remove(discardedTopCard);
                                        repaint();
                                    }
                                    for (int i = 0; i < 7; i++) {
                                        for (int j = 0; j <= i; j++) {
                                            PlayingCard card2 = pyramidCards.get(i).get(j);
                                            if (card2 != null && card2.equals(chosenCard)) {
                                                pyramidCards.get(i).get(j).setChosen(false);
                                                pyramidCardImages.get(i).get(j).setBorder(emptyBorder);
                                                pyramidCards.get(i).set(j, null);
                                                pyramidCardImages.get(i).get(j).setVisible(false);
                                            }
                                            if (card2 != null && card2.equals(card)) {
                                                pyramidCards.get(i).set(j, null);
                                                pyramidCardImages.get(i).get(j).setVisible(false);
                                            }
                                        }
                                    }
                                    //Efter att korten tagits bort kollar vi vilka kort som nu är klickbara i pyramiden
                                    checkCardsAvailable();
                                    chosenCard = null;
                                }
                                //Ser till att kortet vi klickade på får sin ram, om den ska ha den (om det är valt)
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

    //Kollar om vi har vunnit
    private boolean isWin() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j <= i; j++) {
                if (pyramidCards.get(i).get(j) != null) {
                    return false;
                }
            }
        }
        return true;
    }

    //Sätter parametrarna för vinn-skärmen
    private void showWinScreen() {
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

    //ser till att grafiken hålls uppdaterad
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(menuHeadlineFont);
        g.setColor(Color.white);
        g.drawString("PYRAMIDEN", 555, 50);

        //Uppdaterar kortlekens bild så länge det finns kort i den
        if (deck != null && deck.cardsLeft() > 0) {
            displayCardDeck(deck);
        }
        //Uppdaterar släng-kortlekens bild när det finns kort i den
        if (discardedCards != null && discardedCards.size() > 0) {
            displayDiscardedDeck();
        }
        //"Döljer" kortleken när den är tom
        if (!deckVisible && deckImage != null) {
            deckImage.setVisible(false);
        }
        //"Döljer" släng-högen när den är tom
        if (!discardedVisible && discardedCardImage != null) {
            discardedCardImage.setVisible(false);
        }
        //Visar vinn-skärmen om vi har vunnit
        showWinScreen();
    }
}
