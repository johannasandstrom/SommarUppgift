import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel {

    //hör ihop med CardLayout (se PanelContainer)
    private CardLayout cl;
    private PanelContainer pc;
    private GameBoard gb;

    //knapparna till menyn
    private JButton newGame = new JButton("Starta nytt spel");
    private JButton rules = new JButton("Visa regler");
    private JButton endGame = new JButton("Avsluta programmet");

    //typsnittsstilar
    private Font menuHeadlineFont = new Font("arial", Font.BOLD, 50);
    private Font fontButtons = new Font("arial", Font.BOLD, 16);


    public Menu(CardLayout cl, PanelContainer pc, GameBoard gb) {
        this.setSize(pc.getWidth(), pc.getHeight());
        this.setBackground(pc.getBackgroundColor());
        this.cl = cl;
        this.pc = pc;
        this.gb = gb;
        this.setLayout(null);
        addButtons();
    }

    private void addButtons() {
        //nytt spel-knappen
        newGame.setBounds(600, 300, 200, 60);
        newGame.setFont(fontButtons);
        this.add(newGame);
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gb.startNewGame();
                cl.show(pc, "2");
            }
        });

        //regler-knappen
        rules.setBounds(600, 380, 200, 60);
        rules.setFont(fontButtons);
        this.add(rules);
        rules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Rules rules = new Rules();
                rules.setSize(900, 230);
                rules.setLocationRelativeTo(null);
                rules.setResizable(false);
                rules.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                rules.setVisible(true);
            }
        });

        //avsluta programmet-knappen
        endGame.setBounds(600, 460, 200, 60);
        endGame.setFont(fontButtons);
        this.add(endGame);
        endGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(menuHeadlineFont);
        g.setColor(Color.white);
        g.drawString("PYRAMIDEN", 555, 50);
    }
}
