import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CentralPane extends JLayeredPane {
    MyFrame frame;

    private List<Pracownik> listOfPracownik = new ArrayList<Pracownik>();
    private List<Zlecenie> listOfZlecenie = new ArrayList<Zlecenie>();
    private List<Praca> listOfPraca = new ArrayList<Praca>();
    private List<DziałPracowników> listaDzialow = new ArrayList<DziałPracowników>();
    private List<Brygada> listaBrygad = new ArrayList<Brygada>();
    private List<Brygadista> listaBrygadistow = new ArrayList<Brygadista>();
    private List selectedData = new ArrayList();

    private String[] pracownikColumnNames = {"First Name",
            "Last Name",
            "Birth Date",
            "Department"};

    private String[] zlecenieColumnNames = {"Nr",
            "Stan",
            "Czy Zrealizowane",
            "Data utworzenia",
            "Data realizacji",
            "Data Zakonczenia"};

    private String[] pracaColumnNames = {"Nr",
            "Rodzaj pracy",
            "Czy zrealizowane",
            "Opis"};

    private String[] dzialColumnNames = {"Nr",
            "Nazwa"};


    JScrollPane jps;
    JScrollPane infoJps;
    JTable curentTab;
    JTable infoTab;
    DefaultTableModel infoMod;
    DefaultTableModel curentMod;
    JLabel infoLabel;
    JLabel botomButtonHolder;
    JPanel buttonHolder;
    JButton button1;
    JButton button2;
    JButton button3;

    JButton button4;
    JButton button5;
    JButton button6;

    int[] selectedRawsIndex;


    public CentralPane(MyFrame frame) {
        this.frame = frame;

        loadData();

        selectedData = listOfPraca;

        this.setPreferredSize(new Dimension(100, 100));
        this.setBackground(Color.red);
        this.setLayout(new GridLayout(4, 1));

        infoMod = new DefaultTableModel();
        infoTab = new JTable(infoMod);

        curentMod = new DefaultTableModel(createPracownikData(), pracownikColumnNames);
        curentTab = new JTable(curentMod);
        ListSelectionModel model = curentTab.getSelectionModel();
        model.addListSelectionListener((e) -> {

            if (curentTab.getSelectedRow() != -1) {
                infoLabel.setText(selectedData.get(curentTab.getSelectedRow()).toString());
            }

            selectedRawsIndex = curentTab.getSelectedRows();
            System.out.println(selectedRawsIndex.length);
        });

        jps = new JScrollPane(curentTab);
        infoJps = new JScrollPane(infoTab);


        button3 = new JButton("Show");
        button3.addActionListener((e) -> {
            createInfoTabData();
        });

        infoLabel = new JLabel();
        infoLabel.setSize(300, 400);
        infoLabel.setSize(300, 400);

        buttonHolder = new JPanel();


        button1 = new JButton("Set");
        button1.addActionListener((e) -> {
            String className = selectedData.get(0).getClass().getSimpleName();
            switch (className) {
                case "Praca": {
                    new SetPraca(frame, true, listOfPraca.get(selectedRawsIndex[0]), this);
                    break;
                }
                case "Zlecenie": {
                    new SetZlecenie(frame, true, listOfZlecenie.get(selectedRawsIndex[0]), this);
                    break;
                }
                case "Pracownik": {
                    new SetPracownik(frame, true, listOfPracownik.get(selectedRawsIndex[0]), this);
                    break;
                }
                case "DziałPracowników": {
                    new SetDzial(frame, true, listaDzialow.get(selectedRawsIndex[0]), this);
                    break;
                }
            }
        });

        button2 = new JButton("Delete");
        button2.addActionListener((e) -> {

            String className = selectedData.get(0).getClass().getSimpleName();

            if (selectedRawsIndex.length > 1) {
                for (int i = 0; i < selectedRawsIndex.length; i++) {
                    selectedData.remove(selectedRawsIndex[i]);
                }
            } else {
                selectedData.remove(selectedRawsIndex[0]);
            }

            switch (className) {
                case "Praca": {
                    refreshPraca();
                    break;
                }
                case "Zlecenie": {
                    refreshZlecenia();
                    break;
                }
                case "Pracownik": {
                    refreshPracownicy();
                    break;
                }
                case "DziałPracowników": {
                    refreshDzialy();
                    break;
                }
            }
        });

        button4 = new JButton("Rozpoczni zlecenie");
        button4.addActionListener((e) -> {
            startZlecenie();
        });

        button5 = new JButton("Zakończ zlecenie");
        button5.addActionListener((e) -> {
            finishZlecenie();
        });

        button6 = new JButton("Dodaj prace do zlecenia");
        button6.addActionListener((e) -> {

        });

        botomButtonHolder = new JLabel();
        botomButtonHolder.setLayout(new GridLayout(5,1));
        botomButtonHolder.add(button4);
        botomButtonHolder.add(button5);

        buttonHolder.setLayout(new GridLayout(5, 1));
        buttonHolder.setSize(100, 200);
        buttonHolder.add(button1);
        buttonHolder.add(button2);
        buttonHolder.add(button3);

        this.add(jps);
        this.add(buttonHolder);
        this.add(infoJps);
        this.add(botomButtonHolder);
    }

    private void startZlecenie(){

        String selectedClass = selectedData.get(0).getClass().getSimpleName();

        if(selectedClass.equals("Zlecenie")){
            listOfZlecenie.get(selectedRawsIndex[0]).setStan(StanZlecenia.REALIZOWANE);
            listOfZlecenie.get(selectedRawsIndex[0]).setDataRealizacji();
        }
    }

    private void finishZlecenie(){
        String selectedClass = selectedData.get(0).getClass().getSimpleName();

        if(selectedClass.equals("Zlecenie")){
            listOfZlecenie.get(selectedRawsIndex[0]).setStan(StanZlecenia.ZAKONCZONE);
            listOfZlecenie.get(selectedRawsIndex[0]).setCzyZrealizowane(true);
            listOfZlecenie.get(selectedRawsIndex[0]).setDataZakonczenia();
        }
    }


    private void createInfoTabData() {
        String selectedClass = selectedData.get(0).getClass().getSimpleName();
        switch (selectedClass) {
            case "Zlecenie": {
                infoMod.setDataVector(createPracaData(listOfZlecenie.get(selectedRawsIndex[0]).getListOfPraca()), pracaColumnNames);
                break;
            }
            case "DziałPracowników": {
                infoMod.setDataVector(createPracownikData(listaDzialow.get(selectedRawsIndex[0]).getListaPracownikow()), pracaColumnNames);
                break;
            }
        }
    }

    private Object[][] createPracownikData() {

        Object[][] tab = new Object[listOfPracownik.size()][4];

        int i = 0;
        int j = 0;

        for (Pracownik p : listOfPracownik) {
            System.out.println(p+ "======================================");
            tab[i][j] = p.getImie();
            j++;
            tab[i][j] = p.getNazwisko();
            j++;
            tab[i][j] = p.getDataUrodzenia();
            j++;
            tab[i][j] = p.getDzial().getName();
            i++;
            j = 0;
        }

        return tab;
    }

    private Object[][] createPracownikData(List<Pracownik> tmpList) {

        Object[][] tab = new Object[tmpList.size()][4];

        int i = 0;
        int j = 0;

        for (Pracownik p : tmpList) {
            tab[i][j] = p.getImie();
            j++;
            tab[i][j] = p.getNazwisko();
            j++;
            tab[i][j] = p.getDataUrodzenia();
            j++;
            tab[i][j] = p.getDzial().getName();
            i++;
            j = 0;
        }

        return tab;
    }

    private Object[][] createZlecenieData() {


        Object[][] tab = new Object[listOfZlecenie.size()][6];

        int i = 0;
        int j = 0;

        for (Zlecenie z : listOfZlecenie) {
            System.out.println(z.getBrygada().getBrygadista() == frame.getLoginedBrygadista());
            if (z.getBrygada().getBrygadista().getLogin().equals(frame.getLoginedBrygadista().getLogin())) {
                System.out.println(z);
                tab[i][j] = z.getNrZlecenia();
                j++;
                tab[i][j] = z.getStan().toString();
                j++;
                tab[i][j] = z.getCzyZrealizowane();
                j++;
                tab[i][j] = z.getDataUtworzenia().toString();
                j++;
                if (z.getDataRealizacji() == null) {
                    tab[i][j] = "-";
                } else {
                    tab[i][j] = z.getDataRealizacji().toString();
                }
                j++;
                if (z.getDataZakonczenia() == null) {
                    tab[i][j] = "-";
                } else {
                    tab[i][j] = z.getDataZakonczenia().toString();
                }
                i++;
                j = 0;
            }
        }

        return tab;
    }

    private Object[][] createDzialData() {


        Object[][] tab = new Object[listaDzialow.size()][2];


        int i = 0;
        int j = 0;

        for (DziałPracowników d : listaDzialow) {
            tab[i][j] = d.getNrDzial();
            j++;
            tab[i][j] = d.getName();
            i++;
            j = 0;
        }

        return tab;

    }


    private Object[][] createPracaData() {

        Object[][] tab = new Object[listOfPraca.size()][4];

        int i = 0;
        int j = 0;

        for (Praca p : listOfPraca) {
            System.out.println(p);
            tab[i][j] = p.getNumerPracy();
            j++;
            tab[i][j] = p.getRodzajPracy().toString();
            j++;
            tab[i][j] = p.getCzyZrealizowane();
            j++;
            tab[i][j] = p.getOpis();
            i++;
            j = 0;
        }

        return tab;

    }

    private Object[][] createPracaData(List<Praca> tabList) {

        Object[][] tab = new Object[tabList.size()][4];

        int i = 0;
        int j = 0;

        for (Praca p : tabList) {
            System.out.println(p);
            tab[i][j] = p.getNumerPracy();
            j++;
            tab[i][j] = p.getRodzajPracy().toString();
            j++;
            tab[i][j] = p.getCzyZrealizowane();
            j++;
            tab[i][j] = p.getOpis();
            i++;
            j = 0;
        }
        return tab;
    }


    public void addPracownicyRow(Pracownik pracownik) {
        listOfPracownik.add(pracownik);
    }

    public void loadData() {
        listaDzialow = PrinterWriter.readDzial();
        listaBrygad = PrinterWriter.readBrygada();
        listaBrygadistow = PrinterWriter.readBrygadista();
        listOfZlecenie = PrinterWriter.readZlecenie();
        listOfPracownik = PrinterWriter.readPracowncy();
        listOfPraca = PrinterWriter.readPraca();
    }

    public void addDzialRow(DziałPracowników dzial) {
        listaDzialow.add(dzial);
    }

    public void addZlecenieRow(Zlecenie zlecenie) {
        listOfZlecenie.add(zlecenie);
    }

    public void showPracownicy() {
        curentMod.setDataVector(createPracownikData(listOfPracownik), pracownikColumnNames);
        selectedData = listOfPracownik;
    }

    public void refreshPracownicy() {
        curentMod.setDataVector(createPracownikData(), pracownikColumnNames);
    }

    public void showZlecenia() {
        curentMod.setDataVector(createZlecenieData(), zlecenieColumnNames);
        selectedData = listOfZlecenie;
    }

    public void refreshZlecenia() {
        curentMod.setDataVector(createZlecenieData(), zlecenieColumnNames);
    }

    public void showPracy() {
        curentMod.setDataVector(createPracaData(), pracaColumnNames);
        selectedData = listOfPraca;
    }

    public void refreshPraca() {
        curentMod.setDataVector(createPracaData(), pracaColumnNames);
    }

    public void showDzialy() {
        curentMod.setDataVector(createDzialData(), dzialColumnNames);
        selectedData = listaDzialow;
        refreshDzialy();
    }

    public void refreshDzialy() {
        curentMod.setDataVector(createDzialData(), dzialColumnNames);
    }


    public List<Praca> getListOfPraca() {
        return listOfPraca;
    }

    public List<Pracownik> getListOfPracownik() {
        return listOfPracownik;
    }


    public List<DziałPracowników> getListaDzialow() {
        return listaDzialow;
    }

    public List<Zlecenie> getListOfZlecenie() {
        return listOfZlecenie;
    }

    public List<Brygada> getListaBrygad() {
        return listaBrygad;
    }
}
