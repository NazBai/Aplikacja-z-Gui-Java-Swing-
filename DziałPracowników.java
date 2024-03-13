import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DziałPracowników implements Serializable {

    public int nrDzial;
    public static int genericNumber;
    private String name;
    private static List<String> names = new ArrayList<>();
    private List<Pracownik> listaPracownikow = new ArrayList<>();

    private DziałPracowników(){

    }

    //Private przy construktoże uniemożliwia kożystanie z konstruktora poza klasą
    public DziałPracowników(String name) {
        try {
            if (isUnique(name)) {
                names.add(name);
                this.name = name;

            }
        } catch (NotUniqueNameExeption ex) {
            System.err.println(ex.getMessage());
        }

        nrDzial = genericNumber;
        genericNumber++;

    }

    //Dzięki tym metodą klasa "wie" jakich pracowników posiada
    public void addPracownik(Pracownik pracownik) {
        listaPracownikow.add(pracownik);
    }

    public List<Pracownik> getListaPracownikow() {
        return listaPracownikow;
    }

    public void setName(String name) {
        try {
            if (isUnique(name)) {
                this.name = name;
            }
        } catch (NotUniqueNameExeption ex) {
            System.err.println(ex.getMessage());
        }

    }

    //Medoda zabezpiecza przed utwożeniem objektu o takim samym imieniu
    private boolean isUnique(String name) throws NotUniqueNameExeption {

        for (String tmp : names) {
            if (tmp.equals(name)) {
                throw new NotUniqueNameExeption("Not unique name");
            }
        }
        return true;
    }

    public String getName(){
        return name;
    }

    //Metoda umożliwia utwożenie objektu
    public static DziałPracowników createDzial(String name) {
        return new DziałPracowników(name);
    }

    @Override
    public String toString() {
        return "Dział - " + this.name + ";\n";
    }

    public int getNrDzial() {
        return nrDzial;
    }
}
