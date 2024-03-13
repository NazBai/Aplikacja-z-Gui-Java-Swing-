import javax.swing.*;
import java.awt.*;

public class ChangePassword extends JDialog {

    JPanel panel;
    JLabel login;
    JLabel oldPassword;
    JLabel newPassword;
    JLabel confirmNewPassword;
    JTextField loginTXT;
    JTextField oldPasTXT;
    JTextField newPasTXT;
    JTextField confirmNewPasTXT;
    JButton button1;
    JButton button2;

    public ChangePassword(Frame frame, boolean modal, Brygadista loginedBrygadista) {
        super(frame, modal);

        this.setSize(400, 300);

        panel = new JPanel();
        panel.setSize(200, 300);
        panel.setLayout(new GridLayout(5, 2));

        login = new JLabel("Login");

        loginTXT = new JTextField();
        loginTXT.setSize(100, 40);

        oldPassword = new JLabel("Stare haslo");

        oldPasTXT = new JTextField();
        oldPasTXT.setSize(100, 40);

        newPassword = new JLabel("Nowe haslo");

        newPasTXT = new JTextField();
        newPasTXT.setSize(100, 40);

        confirmNewPassword = new JLabel();

        confirmNewPasTXT = new JTextField();
        confirmNewPasTXT.setSize(100, 40);

        button1 = new JButton("OK");
        button1.addActionListener((e) -> {
            String guessLog = loginTXT.getText();
            String log = loginedBrygadista.getLogin();
            String oldPas = loginedBrygadista.getHaslo();
            String quessOldPas = oldPasTXT.getText();
            String newPas = newPasTXT.getText();
            String cNewPas = confirmNewPasTXT.getText();

            String all = guessLog + quessOldPas + newPas + cNewPas;

            if (log.equals(guessLog) && oldPas.equals(quessOldPas) && newPas.equals(cNewPas)) {
                loginedBrygadista.setHaslo(newPas);
                JOptionPane.showMessageDialog(null, "Haslo zostalo zmienione");
                this.dispose();
            } else if (log.equals(guessLog) && oldPas.equals(quessOldPas)) {
                JOptionPane.showMessageDialog(null, "Nowe haslo mie zostalo wprowadzone dwa razy");
            } else if (all.isEmpty()){
                JOptionPane.showMessageDialog(null, "Nie mozna zapisac pustych dannych");
            } else {
                JOptionPane.showMessageDialog(null, "Niepoprawne haslo");
            }
        });

        button2 = new JButton("Cancel");
        button2.addActionListener((e) -> {
            this.dispose();
        });

        panel.add(login);
        panel.add(loginTXT);
        panel.add(oldPassword);
        panel.add(oldPasTXT);
        panel.add(newPassword);
        panel.add(newPasTXT);
        panel.add(confirmNewPassword);
        panel.add(confirmNewPasTXT);
        panel.add(button1);
        panel.add(button2);


        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }
}
