import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MyFrame extends JFrame {

    private Brygadista loginedBrygadista;

    private JPanel topSidePane;
    private CentralPane centralPanel;
    private LeftSidePane leftSidePane;

    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton initialButton;

    public MyFrame(Brygadista loginedBrygadista) {

        this.loginedBrygadista = loginedBrygadista;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 700);
        this.setLayout(new BorderLayout());

        topSidePane = new JPanel();
        centralPanel = new CentralPane(this);
        leftSidePane = new LeftSidePane(centralPanel);


        button1 = new JButton("Dodaj pracownika");
        button2 = new JButton("Dodaj dzial");
        button3 = new JButton("Dodaj zlecenie");

        topSidePane.setLayout(new FlowLayout());

        topSidePane.setBackground(Color.darkGray);
        topSidePane.setPreferredSize(new Dimension(500, 100));
        topSidePane.setBorder(BorderFactory.createLineBorder(Color.green));

        initialButton = new JButton();
        initialButton.setText("Welcome" + loginedBrygadista.getInicial());
        initialButton.setForeground(Color.WHITE);
        initialButton.setBackground(Color.darkGray);
        initialButton.addActionListener((e) -> {
            new PersonalWindow(this, true, loginedBrygadista);
        });

        button1.addActionListener((ActionEvent e) -> {
            new AddPracownik(this, true, centralPanel);
        });
        button2.addActionListener((e) -> {
            new AddDzial(this, true, centralPanel);
        });
        button3.addActionListener((e) -> {
            new AddZlecenie(this, true, centralPanel);
        });

        topSidePane.add(button1);
        topSidePane.add(button2);
        topSidePane.add(button3);
        topSidePane.add(initialButton);

        this.setJMenuBar(new MyMenuBar());
        this.add(leftSidePane, BorderLayout.WEST);
        this.add(topSidePane, BorderLayout.NORTH);
        this.add(centralPanel, BorderLayout.CENTER);


        this.setVisible(true);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                PrinterWriter.writeZlecenie(centralPanel.getListOfZlecenie());
                PrinterWriter.writePraca(centralPanel.getListOfPraca());
                PrinterWriter.writePracownik(centralPanel.getListOfPracownik());
                PrinterWriter.writeDzial(centralPanel.getListaDzialow());
                PrinterWriter.writeBrygada(centralPanel.getListaBrygad());

                System.exit(0);

            }
        });
    }

    public Brygadista getLoginedBrygadista() {
        return loginedBrygadista;
    }

    public void addPracownik() {

    }
}
