import java.io.Serializable;

public class Uzytkownik extends Pracownik implements Serializable {

    private String inicial;
    private String login;
    private String haslo;

    public Uzytkownik(){

    }

    public Uzytkownik(String imie, String nazwisko, String dataUrodzenia, DziałPracowników dzial, String login, String haslo){
        super(imie, nazwisko, dataUrodzenia, dzial);
        this.inicial = imie.charAt(0) + "." + nazwisko.charAt(0);
        this.login = login;
        this.haslo = haslo;
    }

    @Override
    public void setImie(String imie){
        this.inicial.replace(this.inicial.charAt(0), imie.charAt(0));
        super.setImie(imie);
    }

    @Override
    public void setNazwisko(String nazwisko){
        this.inicial.replace(this.inicial.charAt(2), nazwisko.charAt(0));
        super.setNazwisko(nazwisko);
    }

    public String getInicial(){
        return inicial;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public String toString(){
        return super.toString();
    }


}
