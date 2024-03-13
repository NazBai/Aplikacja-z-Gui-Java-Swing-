import javax.swing.*;
import java.awt.*;

public class SetPracownik extends JDialog {

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


    public SetPracownik(Frame frame, boolean modal, Pracownik pracownik, CentralPane tmp) {
        super(frame, modal);

        this.tmp = tmp;

        this.setSize(new Dimension(400, 200));

        panel = new JPanel(new GridLayout(4, 2));

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
            else {
                pracownik.setImie(name.getText());
                pracownik.setNazwisko(surname.getText());
                pracownik.setDataUrodzenia(dateToString());
                pracownik.setDzial(new DziałPracowników("new"));
                tmp.refreshPracownicy();
                this.dispose();
            }

        });

        cancel = new JButton("Cancel");
        cancel.addActionListener((e) -> {
            this.dispose();
        });

        panel.add(nameLabel);
        panel.add(name);
        panel.add(nazwiskoLabel);
        panel.add(surname);
        panel.add(dataLabel);
        panel.add(dataHolder);
        panel.add(button);
        panel.add(cancel);


        this.add(panel);

        this.setVisible(true);
    }


    public void createYearsData(){
        this.yearsData = new String [75];

        for (int i = 2004; i < 2004 + 74; i++) {
            yearsData[i - 2004] = "" + i;
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
}
