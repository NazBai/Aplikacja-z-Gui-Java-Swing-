import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Praca implements Serializable {

    private static  int genericNumber = 1;
    private int numerPracy;
    private int czasPracy;
    private RodzajPracy rodzajPracy;
    private boolean czyZrealizowane;
    private String opisPracy;
    private static Map<Integer, Praca> pracaMap = new HashMap<>();

    public Praca(RodzajPracy rodzajPracy, String opisPracy, int czasPracy){
        this.rodzajPracy = rodzajPracy;
        this.czasPracy = czasPracy;
        this.opisPracy = opisPracy;
        numerPracy = genericNumber;
        genericNumber++;
        pracaMap.put(numerPracy, this);
    }

    public void setCzasPracy(int czasPracy) {
        this.czasPracy = czasPracy;
    }

    public void setCzyZrealizowane(boolean czyZrealizowane) {
        this.czyZrealizowane = czyZrealizowane;
    }

    public void setOpisPracy(String opisPracy) {
        this.opisPracy = opisPracy;
    }

    public void setRodzajPracy(RodzajPracy rodzajPracy) {
        this.rodzajPracy = rodzajPracy;
    }


    public boolean isCzyZrealizowane(){
        return czyZrealizowane;
    }


    public static Praca getPraca(int key){
        return pracaMap.get(key);
    }

    public int getNumerPracy() {
        return numerPracy;
    }

    public RodzajPracy getRodzajPracy() {
        return rodzajPracy;
    }

    public boolean getCzyZrealizowane(){
        return czyZrealizowane;
    }

    public String getOpis(){
        return opisPracy;
    }

    @Override
    public String toString(){
        return "Rodzaj pracy - " + rodzajPracy.toString() + ";\n" +
                "Nr pracy - " + numerPracy + ";\n" +
                "Opis pracy - " + opisPracy + ";\n" +
                "Czas pracy - " + czasPracy + "min;\n";
    }
}
