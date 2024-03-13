import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AddZlecenie extends JDialog {

    private CentralPane tmp;

    private Zlecenie zlecenie;

    private JPanel panel;

    private JLabel czyPlaniowaneLabel;
    private JComboBox czyZrealizowaneComboBox;

    private JLabel brygadaLabel;
    private JComboBox brygadaComboBox;
    private JButton button1;


    private JButton button;
    private JButton cancel;

    public AddZlecenie(Frame frame, boolean modal, CentralPane tmp) {
        super(frame, modal);
        zlecenie = new Zlecenie();

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
            else {
                zlecenie.setStan(czyZrealizowaneComboBox.getSelectedItem().equals("Tak") ? StanZlecenia.PLANOWANE : StanZlecenia.NIEPLANOWANE);
                zlecenie.setBrygada(getBrygada(brygadaComboBox.getSelectedItem().toString()));
                tmp.addZlecenieRow(zlecenie);

                this.dispose();
            }

        });

        button1 = new JButton("Dodaj prace do zlecenia");
        button1.addActionListener((e) -> {
            new AddPraca(frame, true, tmp, zlecenie);
        });

        cancel = new JButton("Cancel");
        cancel.addActionListener((e) -> {
            this.dispose();
        });

        panel.add(czyPlaniowaneLabel);
        panel.add(czyZrealizowaneComboBox);
        panel.add(brygadaLabel);
        panel.add(brygadaComboBox);
        panel.add(button1);
        panel.add(button);
        panel.add(cancel);


        this.add(panel);

        this.setVisible(true);
    }

    public String[] createCzyZrealizowaneData(){
        String[] data = {"Tak", "Nie"};
        return data;
    }



    public Brygada getBrygada(String nameOfBrygada){
        List<Brygada> list = tmp.getListaBrygad();
        for(Brygada b : list){
            if(b.getNazwa().equals(nameOfBrygada)){
                return b;
            }
        }
        return null;

    }


    public String[] createBrygadaData(){
        List<Brygada> tmpList = tmp.getListaBrygad();

        String[] data = new String[tmpList.size()];
        int i = 0;

        for (Brygada b : tmpList){
            data[i] = b.getNazwa();
        }
        return data;
    }

}