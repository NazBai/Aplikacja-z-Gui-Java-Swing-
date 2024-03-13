import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Brygada implements Serializable {

    private String nazwa;
    private Brygadista brygadista;
    private List<Pracownik> listaPracownikow = new ArrayList<Pracownik>();


    public Brygada(String nazwa, Brygadista brygadista){
        this.nazwa = nazwa;
        brygadista.addToBrygada(this);
        this.brygadista = brygadista;
    }

    //Przez użycie "instanceof" nie ma możliwości dodania objektu klasy Użytkownik do listy pacowników
    public void addPracownik(Pracownik pracownik){
        if(pracownik instanceof Uzytkownik){
            System.out.println("Brak możliwości dodania objektu klasy Użytkownik do listy pracowników");
        }
        else {
            listaPracownikow.add(pracownik);
        }

    }

    public List<Pracownik> getListaPracownikow() {
        return listaPracownikow;
    }

    public void addPracownik(List<Pracownik> listaPracownikow){
        for(Pracownik pracownik : listaPracownikow){
            addPracownik(pracownik);
        }
    }

    public String getImionaPracownikow(){
        String result = "Pracowniki: ";

        for(Pracownik pracownik : listaPracownikow){
            result += pracownik.getImie() + ", ";
        }
        result = result.substring(0, result.length() - 2);
        return result;
    }

    public Brygadista getBrygadista(){
        return brygadista;
    }

    public String getNazwa(){
        return nazwa;
    }



    @Override
    public String toString(){
        return "Nazwa Brygady - " + this.nazwa + ";\n"
                + getBrygadista().toString() + "" +
                getImionaPracownikow();

    }



}
