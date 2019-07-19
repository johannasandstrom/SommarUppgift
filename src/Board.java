import cardDeckClasses.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;

public class Board extends JPanel {
    CardLayout cl = new CardLayout();
    PanelContainer pc = new PanelContainer();
    private State state = State.MENU;
    GameBoard gameBoard = new GameBoard(cl, pc);


    //storleken på spelfönstret
    private int width = 1400;
    private int height = 750;

    //färger som används i spelet
    private final Color backgroundColor = new Color(39, 119, 20);

    //knappar som används
    private Button newGame = new Button("Starta ny omgång");
    private Button rules = new Button("Visa regler");
    private Button endGame = new Button("Avsluta programmet");
    private Button toMenu = new Button("Avsluta spel/Till menyn");
    private Button undo = new Button("Ångra senaste draget");

    //font för spelets rubrik
    private Font menuHeadlineFont = new Font("arial", Font.BOLD, 50);
    private Font fontButtons1 = new Font("arial", Font.BOLD, 18);
    private Font fontButtons2 = new Font("arial", Font.BOLD, 12);

    public Board() {
        super();
        this.setOpaque(true);
        this.setBackground(backgroundColor);
        this.setLayout(cl);
        this.add(gameBoard, "1");
        cl.show(this, "main");
        this.addButtons();
    }

    private void addButtons() {
        //här ska det som hör till menyn finnas (knappar etc)
        newGame.setBounds(600, 300, 200, 60);
        newGame.addActionListener(new ButtonNewGameActionListener());
        newGame.setFont(fontButtons1);
        this.add(newGame);
        rules.setBounds(600, 380, 200, 60);
        rules.addActionListener(new ButtonRulesActionListener());
        rules.setFont(fontButtons1);
        this.add(rules);
        endGame.setBounds(600, 460, 200, 60);
        endGame.addActionListener(new ButtonEndGameActionListener());
        endGame.setFont(fontButtons1);
        this.add(endGame);
        undo.setBounds(1150, 350, 160, 60);
        undo.addActionListener(new ButtonUndoActionListener());
        undo.setFont(fontButtons2);
        this.add(undo);
        toMenu.setBounds(1150, 430, 160, 60);
        toMenu.addActionListener(new ButtonToMenuActionListener());
        toMenu.setFont(fontButtons2);
        this.add(toMenu);
    }


    private void setVisibilityOfContent(State state) {
        if (state == State.MENU) {
            newGame.setVisible(true);
            rules.setVisible(true);
            endGame.setVisible(true);
            undo.setVisible(false);
            toMenu.setVisible(false);
        }

        //här ska det som hör till spelet finnas (kortuppritning, knappar etc)
        else if (state == State.GAME) {
            newGame.setVisible(false);
            rules.setVisible(false);
            endGame.setVisible(false);
            undo.setVisible(true);
            toMenu.setVisible(true);
        }

        //här ska grafiken som visas om man vinner finnas + highscore?
        else if (state == State.WIN) {
            newGame.setVisible(false);
            rules.setVisible(false);
            endGame.setVisible(false);
            undo.setVisible(false);
            toMenu.setVisible(true);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(menuHeadlineFont);
        g.setColor(Color.white);
        g.drawString("PYRAMID SOLITAIRE", 440, 50);
        setVisibilityOfContent(state);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    private class ButtonNewGameActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cl.show(Board.this, "1");
            state = State.GAME;
            setVisibilityOfContent(state);
        }
    }

    private class ButtonRulesActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Rules rules = new Rules();
            rules.setSize(900, 230);
            rules.setLocationRelativeTo(null);
            rules.setResizable(false);
            rules.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            rules.setVisible(true);
        }
    }

    private class ButtonEndGameActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    private class ButtonUndoActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Ångra senaste draget...???
        }
    }

    private class ButtonToMenuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            state = State.MENU;
            setVisibilityOfContent(state);
        }
    }
}
