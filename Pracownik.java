import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pracownik implements Comparable<Pracownik> , Serializable {

    private static List<DziałPracowników> listOfWorkers = new ArrayList();
    private String imie;
    private String nazwisko;
    private String dataUrodzenia;
    private DziałPracowników dzial;
    private static List<Pracownik> listOfPracownik = new ArrayList<Pracownik>();

    public Pracownik(){

    }

    public Pracownik(String imie, String nazwisko, String dataUrodzenia, DziałPracowników dzial){
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataUrodzenia = dataUrodzenia;
        listOfWorkers.add(dzial);
        dzial.addPracownik(this);
        this.dzial = dzial;
        listOfPracownik.add(this);
    }

    public String getImie(){
        return imie;
    }

    public String getNazwisko(){
        return nazwisko;
    }

    public void setImie(String imie){
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko){
        this.nazwisko = nazwisko;
    }

    public String getDataUrodzenia(){
        return dataUrodzenia;
    }

    public DziałPracowników getDzial() {
        return dzial;
    }

    @Override
    public int compareTo(Pracownik pracownik){
        String element1 = this.imie + this.nazwisko + this.dataUrodzenia;
        String element2 = pracownik.getImie() + pracownik.getNazwisko() + pracownik.getDataUrodzenia();
        return element1.compareTo(element2);

    }

    public void setDataUrodzenia(String dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }

    public void setDzial(DziałPracowników dzial) {
        this.dzial = dzial;
    }

    public static List<Pracownik> getListOfPracownik(){
        return listOfPracownik;
    }

    @Override
    public String toString(){
        return "Imie - " + this.imie + ";\n" +
                "Nazwisko - " + this.nazwisko + ";\n" +
                "Data urodzenia - " + this.dataUrodzenia + ";\n"
                + this.dzial.toString();
    }

    public static void addingNewPracownik(Pracownik pracownik){
        listOfPracownik.add(pracownik);
    }


}
