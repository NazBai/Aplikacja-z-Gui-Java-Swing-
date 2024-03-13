import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Brygadista extends Uzytkownik implements Serializable {
    private List<Brygada> lsitOfBrygada = new ArrayList();
    private List<Zlecenie> listOfZlecenia = new ArrayList();


    public Brygadista(String imie, String nazwisko, String dataUrodzenia, DziałPracowników dzial, String login, String haslo){
        super(imie, nazwisko, dataUrodzenia, dzial, login, haslo);
    }

    //Dzięki tym metodom klasa "wie" w których brygadach/zleceniach bierze udział brygadysta
    public List<Brygada> getListOfBrygada(){
        return lsitOfBrygada;
    }

    public List<Zlecenie> getListOfZlecenia(){
        return listOfZlecenia;
    }

    public void addToBrygada(Brygada brygada){
        lsitOfBrygada.add(brygada);
    }

    public void addingToZlecenie(Zlecenie zlecenie){
        listOfZlecenia.add(zlecenie);
    }

    @Override
    public String toString(){
        return "Brygadista : " + super.toString();
    }


}
