import javax.swing.*;
import java.awt.*;

public class Rules extends JFrame {
    private JFrame rules = new JFrame();
    private JLabel s1, s2, s3, s4, s5, s6, s7, s8;
    private Font fontHeadline = new Font("arial", Font.BOLD, 16);
    private Font fontNormalText = new Font("arial", Font.PLAIN, 12);


    public Rules() {
        super("Regler");
        setLayout(new FlowLayout(FlowLayout.CENTER, 750, 5)); //hgap - för att varje ny label ska hamna på en egen rad
        addText();
    }

    /*fick lägga till texten steg för steg enligt nedan för att jag skulle kunna använda olika font på dem, och
    för att all text skulle synas (lyckades inte "wrappa den" på något smidigt sätt
     */
    private void addText() {
        s1 = new JLabel("Målet med spelet\n");
        s1.setFont(fontHeadline);
        s1.setOpaque(true);
        this.add(s1);
        s2 = new JLabel("Att tömma pyramiden på kort\n");
        s2.setOpaque(true);
        s2.setFont(fontNormalText);
        this.add(s2);
        s3 = new JLabel("Spelregler");
        s3.setOpaque(true);
        s3.setFont(fontHeadline);
        this.add(s3);
        s4 = new JLabel("Patiensen går ut på att tömma pyramiden på kort. Kort tas bort genom att man parar ihop " +
                "två kort som ligger fria så att valören av dessa kort tillsammans blir 13.");
        s4.setOpaque(true);
        s4.setFont(fontNormalText);
        this.add(s4);
        s5 = new JLabel("Man kan para ihop fria kort på flera olika sätt, man kan exempelvis para ihop två kort från " +
                "pyramiden, eller para ihop ett kort från pyramiden tillsammans med handkortet.");
        s5.setOpaque(true);
        s5.setFont(fontNormalText);
        this.add(s5);
        s6 = new JLabel("Det är även tillåtet att nyttja det översta kortet i högen av använda kort, man kan para " +
                "ihop detta kort med handkortet eller med något fritt kort i pyramiden.");
        s6.setOpaque(true);
        s6.setFont(fontNormalText);
        this.add(s6);
        s7 = new JLabel("Ess värderas som 1, knekt värderas som 11, dam värderas som 12, och kung värderas som 13. " +
                "Övriga kort värderas som vanligt.");
        s7.setOpaque(true);
        s7.setFont(fontNormalText);
        this.add(s7);
        s8 = new JLabel("Eftersom kung värderas som 13 så tas kungar bort ensamma utan att paras ihop med något " +
                "annat kort.");
        s8.setOpaque(true);
        s8.setFont(fontNormalText);
        this.add(s8);
    }
}
