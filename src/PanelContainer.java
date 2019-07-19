import javax.swing.*;
import java.awt.*;


public class PanelContainer extends JPanel {

    //fönstrets storlek
    private final int PANEL_WIDTH = 1400;
    private final int PANEL_HEIGHT = 750;

    //bakgrundsfärgen
    private final Color backgroundColor = new Color(39, 119, 20);

    //se förklaring nedan ang. hur CardLayout fungerar
    private CardLayout cl = new CardLayout();
    private GameBoard gameBoard = new GameBoard(cl, this);
    private Menu menu = new Menu(cl, this, gameBoard);

    /*PanelContainer skapades endast för att kunna använda layouten CardLayout.
    PanelContainer är "parent" och bestämmer vilka andra paneler som hör till layouten
    (och därmed vilka paneler man kan välja att visa layouten för). Menu/menu och GameBoard/gameBoard
    är i detta fallet de två paneler vi vill kunna byta emellan.
     */
    public PanelContainer() {
        this.setSize(PANEL_WIDTH, PANEL_HEIGHT);
        this.setLayout(cl);
        this.add(menu, "1");
        this.add(gameBoard, "2");
        cl.show(this, "1");
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }
}
