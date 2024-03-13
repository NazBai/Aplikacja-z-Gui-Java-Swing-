import javax.swing.*;
import java.awt.*;

public class PersonalWindow extends JDialog {

    private Brygadista loginedBrygadista;
    private MyFrame frame;

    private JPanel panel;
    private JLabel name;
    private JLabel surname;
    private JLabel dataUrodzenia;
    private JButton button1;
    private JButton button2;


    public PersonalWindow(Frame frame, boolean modal, Brygadista loginedBrygadista){
        super(frame, modal);

        this.setSize(200, 300);

        this.loginedBrygadista = loginedBrygadista;

        panel = new JPanel();
        panel.setSize(200, 300);
        panel.setLayout(new GridLayout(4, 1));

        name = new JLabel(loginedBrygadista.getImie());
        surname = new JLabel((loginedBrygadista.getNazwisko()));
        dataUrodzenia = new JLabel(loginedBrygadista.getDataUrodzenia());

        button1 = new JButton("Zmien haslo");
        button2 = new JButton("Cancel");
        button1.addActionListener((e) -> {
            new ChangePassword(frame, true, loginedBrygadista);
        });
        button2.addActionListener((e) -> {
            this.dispose();
        });

        panel.add(name);
        panel.add(surname);
        panel.add(dataUrodzenia);
        panel.add(button1);
        panel.add(button2);

        this.add(panel);
        this.setVisible(true);

    }

}
