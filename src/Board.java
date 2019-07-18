import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Board extends JPanel {

    private State state = State.MENU;

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
    Font menuHeadlineFont = new Font("arial", Font.BOLD, 50);

    public Board() {
        super();
        this.setOpaque(true);
        this.setBackground(backgroundColor);
        this.setLayout(null);
        this.addButtons();
    }

    public void addButtons() {
        //här ska det som hör till menyn finnas (knappar etc)
        newGame.setBounds(600, 300, 200, 60);
        newGame.addActionListener(new ButtonNewGameActionListener());
        this.add(newGame);
        rules.setBounds(600, 380, 200, 60);
        rules.addActionListener(new ButtonRulesActionListener());
        this.add(rules);
        endGame.setBounds(600, 460, 200, 60);
        endGame.addActionListener(new ButtonEndGameActionListener());
        this.add(endGame);
        undo.setBounds(1150, 350, 160, 60);
        undo.addActionListener(new ButtonUndoActionListener());
        this.add(undo);
        toMenu.setBounds(1150, 430, 160, 60);
        toMenu.addActionListener(new ButtonToMenuActionListener());
        toMenu.setEnabled(true);
        this.add(toMenu);
    }


    public void setVisibilityOfContent(State state) {
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
        g.drawString("PYRAMID SOLITAIRE", 440, 80);
        setVisibilityOfContent(state);
        displayImage("images/2C.jpg", 650, 100);
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

    private void displayImage(String url, int x, int y) {
        JLabel jl = new JLabel();
        jl.setIcon(new javax.swing.ImageIcon(getClass().getResource(url)));
        jl.setOpaque(true);
        jl.setBounds(x, y, 100, 153);
        this.add(jl);
    }
}
