import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;

public class    Zlecenie implements Serializable {

    private static int genericNumber = 1;
    private int nrZlecenia;
    private List<Praca> listOfPraca = new ArrayList();
    private Brygada brygada;
    private LocalDateTime dataUtworzenia;
    private LocalDateTime dataRealizacji;
    private LocalDateTime dataZakonczenia;
    private static Map<Integer, Zlecenie> zlecenieMap = new HashMap<>();
    private boolean czyZrealizowane;

    private StanZlecenia stan;

    public Zlecenie (){

        this.dataUtworzenia = now();
        nrZlecenia = genericNumber;
        genericNumber++;
    }

    //4 Konstruktory jak w zadaniu
    public Zlecenie(boolean czyPlanowane){

        this(czyPlanowane, null, null);
        nrZlecenia = genericNumber;
        genericNumber++;

        if(czyPlanowane){
            this.stan = StanZlecenia.PLANOWANE;
        }
        else{
            this.stan = StanZlecenia.NIEPLANOWANE;
        }

        zlecenieMap.put(nrZlecenia, this);
    }

    public Zlecenie(boolean czyPlanowane, Brygada brygada){
        nrZlecenia = genericNumber;
        genericNumber++;

        if(czyPlanowane){
            this.stan = StanZlecenia.PLANOWANE;
        }
        else{
            this.stan = StanZlecenia.NIEPLANOWANE;
        }
        this.stan.stanZlecenia = true;

        dataUtworzenia = now();

        brygada.getBrygadista().addingToZlecenie(this);
        this.brygada = brygada;


        zlecenieMap.put(nrZlecenia, this);
    }

    public Zlecenie(boolean czyPlanowane, List<Praca> listOfPraca) {
        nrZlecenia = genericNumber;
        genericNumber++;

        if (czyPlanowane) {
            this.stan = StanZlecenia.PLANOWANE;
        } else {
            this.stan = StanZlecenia.NIEPLANOWANE;
        }
        this.stan.stanZlecenia = true;

        dataUtworzenia = now();

        this.listOfPraca = listOfPraca;

        zlecenieMap.put(nrZlecenia, this);

    }

    public void setCzyZrealizowane(boolean czyZrealizowane){
        this.czyZrealizowane = czyZrealizowane;

    }

    public Zlecenie(boolean czyPlanowane,Brygada brygada, List<Praca> listOfPraca){
        nrZlecenia = genericNumber;
        genericNumber++;

        if(czyPlanowane){
            this.stan = StanZlecenia.PLANOWANE;
        }
        else{
            this.stan = StanZlecenia.NIEPLANOWANE;
        }
        this.stan.stanZlecenia = true;

        dataUtworzenia = now();

        this.listOfPraca = listOfPraca;

        this.brygada = brygada;

        zlecenieMap.put(nrZlecenia, this);

    }

    public static Zlecenie getZlecenie(int key){
        return zlecenieMap.get(key);
    }

    public void addPraca(Praca praca){
        this.listOfPraca.add(praca);
    }

    public void addPraca(List<Praca> praca){
        this.listOfPraca.addAll(praca);
    }

    public boolean isBrygadaAdded(){
        return this.brygada != null;
    }

    public List<Praca> getListOfPraca() {
        return listOfPraca;
    }

    public void setDataRealizacji() {
        this.dataRealizacji = now();
    }

    public void setDataZakonczenia() {
        this.dataZakonczenia = now();
    }

    //Uniemożliwia dodanie 2giej brygady
    public void addBrygada(Brygada brygada){

        if(isBrygadaAdded()){
            System.out.println("Brygada już została dodana, brak możliwości zmiany");
        }
        else{
            this.brygada = brygada;
        }

    }

    public int getNrZlecenia(){
        return nrZlecenia;
    }

    public LocalDateTime getDataUtworzenia() {
        return dataUtworzenia;
    }

    public LocalDateTime getDataRealizacji() {
        return dataRealizacji;
    }

    public LocalDateTime getDataZakonczenia(){
        return dataZakonczenia;
    }

    public StanZlecenia getStan(){
        return stan;
    }

    private void setZlecenieMap(int nrZlecenia){
        zlecenieMap.put(nrZlecenia, this);
    }

    public Brygada getBrygada(){
        return brygada;
    }

    public boolean getCzyZrealizowane(){
        return czyZrealizowane;
    }

    public void setBrygada(Brygada brygada) {
        this.brygada = brygada;
    }

    public void setStan(StanZlecenia stan) {
        this.stan = stan;
    }


    @Override
    public String toString(){
        return "Nr zlecenia - " + getNrZlecenia() + ";\n"
                + brygada.toString() + ";\n" +
                "Stan zlecenia - " + stan.toString() + ";\n" +
                "Data utwożenia - " + dataUtworzenia.toString() + ";\n" ;
    }

}
