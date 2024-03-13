import javax.swing.*;
import java.awt.*;

public class LeftSidePane extends JPanel {

    private CentralPane centralPane;

    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;

    public LeftSidePane(CentralPane centralPane){

        this.centralPane = centralPane;

        button1 = new JButton("Pracownicy");
        button2 = new JButton("Zlecenia");
        button3 = new JButton("Dzialy");
        button4 = new JButton("Pracy");

        this.setLayout(new FlowLayout());
        this.setBackground(Color.BLUE);

        button1.addActionListener((e) -> {
            centralPane.showPracownicy();
        });
        button2.addActionListener((e) -> {
            centralPane.showZlecenia();
        });
        button3.addActionListener((e) -> {
            centralPane.showDzialy();
        });
        button4.addActionListener((e) -> {
            centralPane.showPracy();
        });

        this.add(button1);
        this.add(button2);
        this.add(button3);
        this.add(button4);


        this.setPreferredSize(new Dimension(150, 300));
        this.setBorder(BorderFactory.createLineBorder(Color.black, 10));
    }
}
