import javax.swing.*;
import java.awt.*;

public class AddDzial extends JDialog {

    private CentralPane tmp;

    private JPanel panel;
    private JLabel nameLabel;
    private JTextField name;
    private JButton button;
    private JButton cancel;

    public AddDzial(Frame frame, boolean modal, CentralPane tmp) {
        super(frame, modal);

        this.tmp = tmp;

        this.setSize(new Dimension(400, 200));

        panel = new JPanel(new GridLayout(4, 2));
        nameLabel = new JLabel("Name");
        name = new JTextField();

        button = new JButton("OK");
        button.addActionListener((e) -> {
            if(name.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Nie można zapisać pustych dannych", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                DziałPracowników dzial = new DziałPracowników(name.getText());
                tmp.addDzialRow(dzial);
                this.dispose();
            }
        });

        cancel = new JButton("Cancel");
        cancel.addActionListener((e) -> {
            this.dispose();
        });

        panel.add(nameLabel);
        panel.add(name);
        panel.add(button);
        panel.add(cancel);
        this.add(panel);

        this.setVisible(true);
    }

}