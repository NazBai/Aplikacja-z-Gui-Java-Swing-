import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AddPracownik extends JDialog {

    private CentralPane tmp;

    private JPanel panel;
    private JLabel nameLabel;
    private JLabel nazwiskoLabel;
    private JLabel dataLabel;
    private JLabel dzialLabel;
    private JLabel dataHolder;
    private JTextField name;
    private JTextField surname;
    private JComboBox days;
    private JComboBox month;
    private JComboBox years;
    private JComboBox dzial;
    private JButton button;
    private JButton cancel;

    private String [] daysData;
    private String [] monthData;
    private String [] yearsData;
    private String [] dzialData;


    public AddPracownik(Frame frame, boolean modal, CentralPane tmp) {
        super(frame, modal);

        this.tmp = tmp;

        this.setSize(new Dimension(400, 200));

        panel = new JPanel(new GridLayout(5, 1));

        nameLabel = new JLabel("Name");
        nazwiskoLabel = new JLabel("Nazwisko");
        dataLabel = new JLabel("Data");
        dzialLabel = new JLabel("Dzial");

        name = new JTextField();
        name.setSize(100, 40);

        surname = new JTextField();
        surname.setSize(100, 40);

        createYearsData();
        createMonthData();
        createDaysData();
        createDzialData();


        years = new JComboBox(yearsData);
        years.setEditable(false);
        years.setSize(40, 20);

        days = new JComboBox(daysData);
        days.setEditable(false);

        month = new JComboBox(monthData);
        month.setEditable(false);

        dataHolder = new JLabel();
        dataHolder.add(years);
        dataHolder.add(month);
        dataHolder.add(days);
        dataHolder.setSize(new Dimension(100, 40));
        dataHolder.setLayout(new FlowLayout());

        button = new JButton("OK");
        button.addActionListener((e) -> {
            if(name.getText().isEmpty() || surname.getText().isEmpty() || dzial.getSelectedItem().toString().isEmpty()){
                JOptionPane.showMessageDialog(null, "Nie można zapisać pustych dannych", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                Pracownik pracownik = new Pracownik(name.getText(), surname.getText(), dateToString(), getDzial((String) dzial.getSelectedItem()));
                tmp.addPracownicyRow(pracownik);
                this.dispose();
            }
        });

        cancel = new JButton("Cancel");
        cancel.addActionListener((e) -> {
            this.dispose();
        });

        dzial = new JComboBox<>(dzialData);

        panel.add(nameLabel);
        panel.add(name);
        panel.add(nazwiskoLabel);
        panel.add(surname);
        panel.add(dataLabel);
        panel.add(dataHolder);
        panel.add(dzialLabel);
        panel.add(dzial);
        panel.add(button);
        panel.add(cancel);


        this.add(panel);

        this.setVisible(true);
    }



    public void createDzialData(){
        List<DziałPracowników> tmpList = tmp.getListaDzialow();
        dzialData = new String[tmpList.size()];

        int i = 0;

        for(DziałPracowników działPracowników : tmpList){
            dzialData[i++] = działPracowników.getName();
        }
    }


    public void createYearsData(){
        this.yearsData = new String [59];

        for (int i = 1945; i < 2004; i++) {
            yearsData[i - 1945] = "" + i;
        }
    }

    public void createMonthData(){
        this.monthData = new String[13];

        for (int i = 1; i <= 12; i++) {
            monthData[i] = "" + i;
        }
    }

    public void createDaysData(){
        this.daysData = new String[32];

        for (int i = 1; i <= 31; i++) {
            daysData[i] = "" + i;
        }
    }

    public String dateToString(){
        String result = "";

        result = days.getSelectedItem() + "-" + month.getSelectedItem() + "-" + years.getSelectedItem();

        return result;
    }

    public DziałPracowników getDzial(String name){
        List<DziałPracowników> tmpList = tmp.getListaDzialow();
        DziałPracowników result;

        for (DziałPracowników d : tmpList){
            if(d.getName().equals(name)){
                return (result = d);
            }
        }
        return null;
    }
}
