import javax.swing.*;
import java.awt.*;


class PanelContainer extends JPanel {

    //Bakgrundsfärgen
    private final Color backgroundColor = new Color(39, 119, 20);

    /*PanelContainer skapades endast för att kunna använda layouten CardLayout.
    PanelContainer är "parent" och bestämmer vilka andra paneler som hör till layouten
    (och därmed vilka paneler man kan välja att visa layouten för). Menu/menu och GameBoard/gameBoard
    är i detta fallet de två paneler vi vill kunna byta emellan.
     */
    PanelContainer() {
        //Fönstrets storlek och layout
        final int PANEL_WIDTH = 1400;
        final int PANEL_HEIGHT = 750;
        this.setSize(PANEL_WIDTH, PANEL_HEIGHT);
        CardLayout cl = new CardLayout();
        this.setLayout(cl);
        GameBoard gameBoard = new GameBoard(cl, this);
        Menu menu = new Menu(cl, this, gameBoard);
        this.add(menu, "1");
        this.add(gameBoard, "2");
        cl.show(this, "1");
    }

    //Returnerar bakgrundsfärgen (används i Menu och GameBoard)
    Color getBackgroundColor() {
        return backgroundColor;
    }
}
