import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SetZlecenie extends JDialog {

    private CentralPane tmp;

    private JPanel panel;

    private JLabel czyPlaniowaneLabel;
    private JComboBox czyZrealizowaneComboBox;

    private JLabel brygadaLabel;
    private JComboBox brygadaComboBox;


    private JButton button;
    private JButton cancel;

    public SetZlecenie(Frame frame, boolean modal, Zlecenie zlecenie, CentralPane tmp) {
        super(frame, modal);

        this.tmp = tmp;

        this.setSize(new Dimension(400, 200));

        panel = new JPanel(new GridLayout(4, 2));

        czyPlaniowaneLabel = new JLabel("Czy planowane");
        czyZrealizowaneComboBox = new JComboBox(createCzyZrealizowaneData());

        brygadaLabel = new JLabel("Brygada");
        brygadaComboBox = new JComboBox(createBrygadaData());


        button = new JButton("OK");

        button.addActionListener((e) -> {
            if(czyZrealizowaneComboBox.getSelectedItem().toString().isEmpty() || brygadaComboBox.getSelectedItem().toString().isEmpty()){
                JOptionPane.showMessageDialog(null, "Nie można zapisać pustych dannych", "Information", JOptionPane.INFORMATION_MESSAGE);

            }
            else{
                zlecenie.setBrygada(getBrygada((String) brygadaComboBox.getSelectedItem()));

                tmp.refreshZlecenia();
                this.dispose();
            }

        });

        cancel = new JButton("Cancel");
        cancel.addActionListener((e) -> {
            this.dispose();
        });

        panel.add(czyPlaniowaneLabel);
        panel.add(czyZrealizowaneComboBox);
        panel.add(brygadaLabel);
        panel.add(brygadaComboBox);
        panel.add(button);
        panel.add(cancel);


        this.add(panel);

        this.setVisible(true);
    }

    public String[] createCzyZrealizowaneData(){
        String[] data = {"Tak", "Nie"};
        return data;
    }

    public String[] createBrygadaData(){
        List<Brygada> tmpList = new ArrayList<>();
        tmpList.addAll(tmp.getListaBrygad());

        String[] data = new String[tmpList.size()];

        int i = 0;

        for (Brygada b : tmpList){
            data[i] = b.getNazwa();
        }

        return data;
    }

    public Brygada getBrygada(String nameOfBrygada){

        List<Brygada> list = new ArrayList<Brygada>();
        Brygada result;

        for(Brygada b : list){
            if(b.getNazwa().equals(nameOfBrygada)){
                return (result = b);
            }
        }

        return null;

    }

}