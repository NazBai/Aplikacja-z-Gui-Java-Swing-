import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class LoginScrean extends JFrame implements ActionListener {

    public static List<Brygadista> brygadistaList = new ArrayList<Brygadista>();
    private static  Brygadista loginedBrygadista;

    private JButton button;
    private JPanel panel;
    private JLabel userLabel;
    private JLabel passwordLabel;
    private JTextField login;
    private JPasswordField password;


    public LoginScrean() {

        loadData();

        userLabel = new JLabel("Login");
        passwordLabel = new JLabel("Haslo");

        login = new JTextField(15);
        password = new JPasswordField(15);

        button = new JButton("OK");

        panel = new JPanel(new GridLayout(3, 1));

        panel.add(userLabel);
        panel.add(login);
        panel.add(passwordLabel);
        panel.add(password);
        panel.add(button);

        this.add(panel, BorderLayout.CENTER);
        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean match = false;


        String log = login.getText().toString();
        String pas = password.getText().toString();

        System.out.println(brygadistaList.size());

        for (Brygadista b : brygadistaList){
            System.out.println(b.getLogin());
            System.out.println(b.getHaslo());
            System.out.println("22" + b.getHaslo());
            if(log.equals(b.getLogin()) && pas.equals(b.getHaslo())){
                System.out.println(222);
                match = true;
                loginedBrygadista = b;
                break;
            }
        }
        if(match){
            new MyFrame(loginedBrygadista);
        }
        else {
            JOptionPane.showMessageDialog(null, "Wrong Try again");
        }
    }

    public void loadData(){
        brygadistaList = PrinterWriter.readBrygadista();
    }


}
