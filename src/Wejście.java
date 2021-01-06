import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Wejście
{
    public static void parseInput(String nazwaPliku) throws FileNotFoundException
    {
        File plik = new File(nazwaPliku);
        Scanner scanner = new Scanner(plik);
        ArrayList<Partia> partie = new ArrayList<>();
        ArrayList<OkrągWyborczy> okręgi = new ArrayList<>();
        ArrayList<Działanie> działania = new ArrayList<>();
        int liczbaOkręgów, liczbaPartii, liczbaDziałań, liczbaCech;

        liczbaOkręgów = scanner.nextInt();
        liczbaPartii = scanner.nextInt();
        liczbaDziałań = scanner.nextInt();
        liczbaCech = scanner.nextInt();

        int liczbaScalonychOkręgów = scanner.nextInt();
        String pary = scanner.nextLine();
        int index = 2;
        int pozycjaPrzecinka = 2;
        String nazwyPartii[] = new String[liczbaPartii];
        int budżetyPartii[] = new int[liczbaPartii];
        int scaloneOkręgi[] = new int[liczbaScalonychOkręgów];
        System.out.println(pary);

        //Wczytywanie par do scalenia
        for(int i = 0; i < liczbaScalonychOkręgów; i++)
        {
            while(pary.charAt(pozycjaPrzecinka) != ',')
                pozycjaPrzecinka++;
            scaloneOkręgi[i] = Integer.parseInt(pary.substring(index, pozycjaPrzecinka));
            System.out.println(scaloneOkręgi[i]);
            index += (6 + pozycjaPrzecinka - index - 1); // Przesuwamy indeks o 6 pozycji + szerokość liczby dla liczb wielucyfrowych
            pozycjaPrzecinka++;
        }

        //Wczytywanie nazw partii
        for(int i = 0; i < liczbaPartii; i++)
        {
            nazwyPartii[i] = scanner.next();
        }

        //Wczytywanie budżetów partii
        for(int i = 0; i < liczbaPartii; i++)
        {
            budżetyPartii[i] = scanner.nextInt();
        }

        //Wczytywanie strategii partii
        for(int i = 0; i < liczbaPartii; i++)
        {
            Partia partia;
            switch(scanner.next())
            {
                case "R":
                    partia = new PartiaRozmachowa(nazwyPartii[i], budżetyPartii[i]);
                    partie.add(partia);
                    break;
                case "S":
                    partia = new PartiaSkromna(nazwyPartii[i], budżetyPartii[i]);
                    partie.add(partia);
                    break;
                case "Z":
                    partia = new PartiaZachłanna(nazwyPartii[i], budżetyPartii[i]);
                    partie.add(partia);
                    break;
                case "W":
                    partia = new PartiaLeniwa(nazwyPartii[i], budżetyPartii[i]);
                    partie.add(partia);
                    break;
            }
        }

        //Wczytywanie liczb wyborców partii
        for(int i = 0; i < liczbaOkręgów; i++)
        {
            OkrągWyborczy okrąg = new OkrągWyborczy(i + 1, scanner.nextInt());
            okręgi.add(okrąg);
        }

        //Wczytywanie kandydatów
        for(int i = 0; i < liczbaOkręgów; i++)
        {
            OkrągWyborczy okrąg = okręgi.get(i);
            int liczbaKandydatów = okrąg.getLiczbaWyborców() / 10;
            for(int j = 0; j < liczbaPartii; j++)
            {
                for(int k = 0; k < liczbaKandydatów; k++)
                {
                    String imię = scanner.next();
                    String nazwisko = scanner.next();
                    int numerOkręgu = scanner.nextInt();
                    String nazwaPartii = scanner.next();
                    int numerKandydata = scanner.nextInt();
                    int[] cechy = new int[liczbaCech];
                    for(int l = 0; l < liczbaCech; l++)
                    {
                        cechy[l] = scanner.nextInt();
                    }
                    Kandydat kandydat = new Kandydat(imię, nazwisko, nazwaPartii, numerOkręgu, numerKandydata, cechy);
                    okrąg.dodajKandydata(kandydat);
                }
            }
        }

        //Wczytywanie wyborców
        for(int i = 0; i < liczbaOkręgów; i++)
        {
            OkrągWyborczy okrąg = okręgi.get(i);
            int liczbaWyborców = okrąg.getLiczbaWyborców();

            for(int j = 0; j < liczbaWyborców; j++)
            {
                String imię = scanner.next();
                String nazwisko = scanner.next();
                int numerOkręgu = scanner.nextInt();
                int typWyborcy = scanner.nextInt();
                String nazwaPartii;
                int numerKandydata;
                Wyborca wyborca;
                int numerCechy;
                int[] wagiCech = new int[liczbaCech];

                switch(typWyborcy)
                {
                    case 1:
                        nazwaPartii = scanner.next();
                        wyborca = new WyborcaŻelaznyPartyjny(imię, nazwisko, numerOkręgu, nazwaPartii);
                        okrąg.dodajWyborce(wyborca);
                        break;
                    case 2:
                        nazwaPartii = scanner.next();
                        numerKandydata = scanner.nextInt();
                        wyborca = new WyborcaŻelaznyKandydata(imię, nazwisko, numerOkręgu, nazwaPartii, numerKandydata);
                        okrąg.dodajWyborce(wyborca);
                        break;
                    case 3:
                        numerCechy = scanner.nextInt();
                        wyborca = new WyborcaMinimalizującyJednocechowy(imię, nazwisko, numerOkręgu, numerCechy);
                        okrąg.dodajWyborce(wyborca);
                        break;
                    case 4:
                        numerCechy = scanner.nextInt();
                        wyborca = new WyborcaMaksymalizującyJednocechowy(imię, nazwisko, numerOkręgu, numerCechy);
                        okrąg.dodajWyborce(wyborca);
                        break;
                    case 5:
                        for(int k = 0; k < liczbaCech; k++)
                        {
                            wagiCech[k] = scanner.nextInt();
                        }
                        wyborca = new WyborcaWszechstronny(imię, nazwisko, numerOkręgu, wagiCech);
                        okrąg.dodajWyborce(wyborca);
                        break;
                    case 6:
                        numerCechy = scanner.nextInt();
                        nazwaPartii = scanner.next();
                        wyborca = new WyborcaMinimalizującyJednocechowyPartyjny(imię, nazwisko, numerOkręgu, numerCechy, nazwaPartii);
                        okrąg.dodajWyborce(wyborca);
                        break;
                    case 7:
                        numerCechy = scanner.nextInt();
                        nazwaPartii = scanner.next();
                        wyborca = new WyborcaMaksymalizującyJednocechowyPartyjny(imię, nazwisko, numerOkręgu, numerCechy, nazwaPartii);
                        okrąg.dodajWyborce(wyborca);
                        break;
                    case 8:
                        for(int k = 0; k < liczbaCech; k++)
                        {
                            wagiCech[k] = scanner.nextInt();
                        }
                        nazwaPartii = scanner.next();
                        wyborca = new WyborcaWszechstronnyPartyjny(imię, nazwisko, numerOkręgu, wagiCech, nazwaPartii);
                        okrąg.dodajWyborce(wyborca);
                        break;
                }
            }
        }

        //Wczytywanie działań
        for(int i = 0; i < liczbaDziałań; i++)
        {
            ArrayList<Integer> zmianyCech = new ArrayList<>();
            for(int j = 0; j < liczbaCech; j++)
            {
                zmianyCech.add(scanner.nextInt());
            }
            Działanie działanie = new Działanie(zmianyCech);
            działania.add(działanie);
        }

        //Scalenie okręgów i ustawianie tych o większym numerze na null
        for(int i = 0; i < liczbaScalonychOkręgów; i++)
        {
            OkrągWyborczy okrąg1 = okręgi.get(scaloneOkręgi[i] - 1);
            okrąg1.scal(okręgi.get(scaloneOkręgi[i]));
            okręgi.set(scaloneOkręgi[i], null);
        }

        Wybory wybory = new Wybory(partie, okręgi, działania);
        wybory.wykonaj();
    }
}
