import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PrinterWriter {

    public static int pracownikIndex = 0;
    private static int pracownikRawIndex;
    public static int zlecenieIndex = 0;
    public static int zlecenieRawIndex;
    public static int pracaIndex = 0;
    public static int pracaRawIndex;
    public static int dzialIndex = 0;
    public static int dzialRawIndex;
    public static int brygadaIndex = 0;
    public static int brygadaRawIndex;
    public static int brygadistaIndex = 0;
    public static int brygadistaRawIndex;


    public static void writePracownik(List<Pracownik> pracowniki) {

        try {
            FileOutputStream fos = new FileOutputStream(new File("src/file"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeInt(pracowniki.size());

            for (Pracownik pracownik : pracowniki) {
                oos.writeObject(pracownik);
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static List<Pracownik> readPracowncy() {

        List<Pracownik> result = new ArrayList<Pracownik>();
        List<Pracownik> sortedResult = new ArrayList<Pracownik>();

        try {

            FileInputStream fis = new FileInputStream(new File("src/file"));
            ObjectInputStream ois = new ObjectInputStream(fis);

            pracownikIndex = ois.readInt();
            pracownikRawIndex = pracownikIndex;

            while (pracownikIndex > 0) {
                pracownikIndex--;
                result.add((Pracownik) ois.readObject());
            }

        } catch (FileNotFoundException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            ex.getMessage();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static int getPracownikiRawIndex() {
        return pracownikRawIndex;
    }

    public static void writeZlecenie(List<Zlecenie> zlecenia) {

        try {
            FileOutputStream fos = new FileOutputStream(new File("src/zlecenieData"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeInt(zlecenia.size());

            for (Zlecenie zlecenie : zlecenia) {
                oos.writeObject(zlecenie);
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static List<Zlecenie> readZlecenie() {

        List<Zlecenie> result = new ArrayList<Zlecenie>();

        try {

            FileInputStream fis = new FileInputStream(new File("src/zlecenieData"));
            ObjectInputStream ois = new ObjectInputStream(fis);

            zlecenieIndex = ois.readInt();
            zlecenieRawIndex = zlecenieIndex;

            while (zlecenieIndex > 0) {
                zlecenieIndex--;
                result.add((Zlecenie) ois.readObject());
            }

        } catch (FileNotFoundException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            ex.getMessage();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        List<Zlecenie>sortedResult = result.stream()
                .sorted(Comparator.comparing(Zlecenie::getNrZlecenia))
                .collect(Collectors.toList());
        return sortedResult;
    }

    public static int getZlecenieRawIndex() {
        return zlecenieRawIndex;
    }

    public static void writePraca(List<Praca> praca) {

        try {
            FileOutputStream fos = new FileOutputStream(new File("src/pracaData"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeInt(praca.size());

            for (Praca curentPraca : praca) {
                oos.writeObject(curentPraca);
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static List<Praca> readPraca() {

        List<Praca> result = new ArrayList<Praca>();

        try {

            FileInputStream fis = new FileInputStream(new File("src/pracaData"));
            ObjectInputStream ois = new ObjectInputStream(fis);

            pracaIndex = ois.readInt();
            pracaRawIndex = pracaIndex;

            while (pracaIndex > 0) {
                pracaIndex--;
                result.add((Praca) ois.readObject());
            }

        } catch (FileNotFoundException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            ex.getMessage();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        List<Praca>sortedResult = result.stream()
                .sorted(Comparator.comparing(Praca::getNumerPracy))
                .collect(Collectors.toList());

        return sortedResult;
    }

    public static int getPracaRawIndex() {
        return pracaRawIndex;
    }

    public static void writeDzial(List<DziałPracowników> dzial) {

        try {
            FileOutputStream fos = new FileOutputStream(new File("src/dzialData"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeInt(dzial.size());

            for (DziałPracowników d : dzial) {
                oos.writeObject(d);
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static List<DziałPracowników> readDzial() {

        List<DziałPracowników> result = new ArrayList<DziałPracowników>();

        try {

            FileInputStream fis = new FileInputStream(new File("src/dzialData"));
            ObjectInputStream ois = new ObjectInputStream(fis);

            dzialIndex = ois.readInt();
            dzialRawIndex = dzialIndex;

            while (dzialIndex > 0) {
                dzialIndex--;
                result.add((DziałPracowników) ois.readObject());
            }

        } catch (FileNotFoundException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            ex.getMessage();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        List<DziałPracowników>sortedResult = result.stream()
                .sorted(Comparator.comparing(DziałPracowników::getNrDzial))
                .collect(Collectors.toList());

        return sortedResult;
    }

    public static int getDzialRawIndex() {
        return dzialRawIndex;
    }

    public static List<Brygada> readBrygada() {

        List<Brygada> result = new ArrayList<Brygada>();

        try {

            FileInputStream fis = new FileInputStream(new File("src/brygadaData"));
            ObjectInputStream ois = new ObjectInputStream(fis);

            brygadaIndex = ois.readInt();
            brygadaRawIndex = brygadaIndex;

            while (brygadaIndex > 0) {
                brygadaIndex--;
                result.add((Brygada) ois.readObject());
            }

        } catch (FileNotFoundException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            ex.getMessage();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        List<Brygada>sortedResult = result.stream()
                .sorted(Comparator.comparing(Brygada::getNazwa))
                .collect(Collectors.toList());

        return result;
    }

    public static void writeBrygada(List<Brygada> brygada) {

        try {
            FileOutputStream fos = new FileOutputStream(new File("src/brygadaData"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeInt(brygada.size());

            for (Brygada b : brygada) {
                System.out.println("bbb" + b.toString());
                oos.writeObject(b);
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static int getBrygadaRawIndex() {
        return brygadaRawIndex;
    }

    public static List<Brygadista> readBrygadista() {

        List<Brygadista> result = new ArrayList<Brygadista>();

        try {

            FileInputStream fis = new FileInputStream(new File("src/brygadistaData"));
            ObjectInputStream ois = new ObjectInputStream(fis);

            brygadistaIndex = ois.readInt();
            brygadistaRawIndex = brygadistaIndex;

            while (brygadistaIndex > 0) {
                brygadistaIndex--;
                Brygadista b;
                b = (Brygadista) ois.readObject();
                System.out.println(b);
                result.add(b);
            }

        } catch (FileNotFoundException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            ex.getMessage();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void writeBrygadista(List<Brygadista> brygadista) {

        try {
            FileOutputStream fos = new FileOutputStream(new File("src/brygadistaData"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeInt(brygadista.size());

            for (Brygadista b : brygadista) {
                System.out.println(b);
                oos.writeObject(b);
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public static int getBrygadistaRawIndex() {
        return brygadistaRawIndex;
    }
}
