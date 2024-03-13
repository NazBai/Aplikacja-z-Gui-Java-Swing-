import javax.swing.*;
import java.awt.*;

public class SetPraca extends JDialog {

    private CentralPane tmp;

    private JPanel panel;
    private JLabel rodzajLabel;
    private JComboBox rodzajPraceBox;
    private JLabel opisPracyLabel;
    private JTextField opisPracyFild;
    private JLabel czasPracyLabel;
    private JTextField czasPracyFild;
    private JButton button;
    private JButton cancel;

    public SetPraca(Frame frame, boolean mode, Praca praca, CentralPane centralPane){
        super(frame, mode);

        this.tmp = centralPane;
        this.setSize(new Dimension(400, 200));

        panel = new JPanel(new GridLayout(4, 2));

        rodzajLabel = new JLabel("Rodzaj Pracy");
        rodzajPraceBox = new JComboBox(getRodzajPracyData());

        opisPracyLabel = new JLabel("Opis");
        opisPracyFild = new JTextField();
        opisPracyFild.setSize(100, 40);

        czasPracyLabel = new JLabel("Czas pracy");
        czasPracyFild = new JTextField();
        czasPracyFild.setSize(100, 40);


        button = new JButton("OK");
        button.addActionListener((e) -> {
            if(rodzajPraceBox.getSelectedItem().toString().isEmpty() ||
                    opisPracyFild.getText().isEmpty() ||
                    czasPracyFild.getText().isEmpty()){

                JOptionPane.showMessageDialog(null, "Nie wszystkie posa zostały wypewnione", "Information", JOptionPane.INFORMATION_MESSAGE);

            }
            else{
                praca.setCzasPracy(Integer.parseInt(czasPracyFild.getText()));
                praca.setRodzajPracy(RodzajPracy.valueOf((String) rodzajPraceBox.getSelectedItem()));
                praca.setOpisPracy(opisPracyFild.getText());
                tmp.refreshPraca();
                this.dispose();
            }

        });

        cancel = new JButton("Cancel");
        cancel.addActionListener((e) -> {
            this.dispose();
        });

        panel.add(rodzajLabel);
        panel.add(rodzajPraceBox);
        panel.add(opisPracyLabel);
        panel.add(opisPracyFild);
        panel.add(czasPracyLabel);
        panel.add(czasPracyFild);
        panel.add(button);
        panel.add(cancel);


        this.add(panel);

        this.setVisible(true);
    }

    public String[] getRodzajPracyData(){
        String[] result = new String[4];

        int i = 0;

        result[i++] = RodzajPracy.OGOlNA.toString();
        result[i++] = RodzajPracy.MONTARZ.toString();
        result[i++] = RodzajPracy.DEMONTARZ.toString();
        result[i] = RodzajPracy.WYMIANA.toString();

        return result;
    }
}
