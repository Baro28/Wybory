import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

public abstract class MetodyPrzeliczaniaMandatów
{
    private static void policzGłosyPartii(ArrayList<OkrągWyborczy> okręgi, ArrayList<Partia> partie)
    {
        for(OkrągWyborczy okrąg : okręgi)
        {
            if(okrąg != null)
            {
                for (Kandydat kandydat : okrąg.getKandydaci())
                {
                    for (Partia partia : partie)
                    {
                        if (partia.getNazwa().equals(kandydat.getPartia()))
                        {
                            partia.dodajGłosy(kandydat.getLiczbaGłosów());
                        }
                    }
                }
            }
        }
    }

    public static void MetodaDHondta(ArrayList<OkrągWyborczy> okręgi, ArrayList<Partia> partie)
    {
        policzGłosyPartii(okręgi, partie);

        for(Partia partia : partie)
            partia.wyzerujMandaty();

        TreeMap<Double, Integer> ilorazy = new TreeMap<>();

        System.out.println("Metoda D'Hondta");

        for(OkrągWyborczy okrąg : okręgi)
        {
            if(okrąg == null)
                continue;

            int[] głosy = new int[partie.size()];
            int[] mandaty = new int[partie.size()];

            System.out.println("Okrąg nr: " + okrąg.getNumerOkręgu());

            for(Wyborca wyborca : okrąg.getWyborcy())
            {
                System.out.print(wyborca.getImię() + " " );
                System.out.print(wyborca.getNazwisko() + ": " );
                System.out.print(wyborca.getImięKandydata() + " " );
                System.out.print(wyborca.getNazwiskoKandydata() + "\n" );
            }

            for(Kandydat kandydat : okrąg.getKandydaci())
            {
                System.out.print(kandydat.getImię() + " " );
                System.out.print(kandydat.getNazwisko() + " - " );
                System.out.print(kandydat.getPartia() + " nr " );
                System.out.print(kandydat.getNumerKandydata() + ": " );
                System.out.print(kandydat.getLiczbaGłosów() + "\n");
            }

            for(Kandydat kandydat : okrąg.getKandydaci())
            {
                for(int i = 0; i < partie.size(); i++)
                {
                    if(partie.get(i).getNazwa().equals(kandydat.getPartia()))
                    {
                        głosy[i] += kandydat.getLiczbaGłosów();
                    }
                }
            }

            for(int i = 0; i < partie.size(); i++)
            {
                for(int j = 1; j < okrąg.getLiczbaWyborców() / 10; j++)
                {
                    ilorazy.put((double) głosy[i] / j, i);
                }
            }



            for(int i = 0; i < okrąg.getLiczbaWyborców() / 10; i++)
            {
                double największyIloraz = ilorazy.lastKey();
                mandaty[ilorazy.get(największyIloraz)]++;
                ilorazy.remove(największyIloraz);
            }

            for(int i = 0; i < partie.size(); i++)
            {
                System.out.println(partie.get(i).getNazwa() + ": " + mandaty[i]);
                partie.get(i).dodajMandaty(mandaty[i]);
            }
        }

        System.out.println("Wszystkie okręgi:");

        for(int i = 0; i < partie.size(); i++)
        {
            System.out.println(partie.get(i).getNazwa() + ": " + partie.get(i).getLiczbaMandatów());
        }
    }

    public static void MetodaSainteLague(ArrayList<OkrągWyborczy> okręgi, ArrayList<Partia> partie)
    {
        policzGłosyPartii(okręgi, partie);
        for(Partia partia : partie)
            partia.wyzerujMandaty();

        TreeMap<Double, Integer> ilorazy = new TreeMap<>();

        System.out.println("Metoda Sainte-Laguë");

        for(OkrągWyborczy okrąg : okręgi)
        {
            if(okrąg == null)
                continue;

            int[] głosy = new int[partie.size()];
            int[] mandaty = new int[partie.size()];

            System.out.println("Okrąg nr: " + okrąg.getNumerOkręgu());

            for(Wyborca wyborca : okrąg.getWyborcy())
            {
                System.out.print(wyborca.getImię() + " " );
                System.out.print(wyborca.getNazwisko() + ": " );
                System.out.print(wyborca.getImięKandydata() + " " );
                System.out.print(wyborca.getNazwiskoKandydata() + "\n" );
            }

            for(Kandydat kandydat : okrąg.getKandydaci())
            {
                System.out.print(kandydat.getImię() + " " );
                System.out.print(kandydat.getNazwisko() + " - " );
                System.out.print(kandydat.getPartia() + " nr " );
                System.out.print(kandydat.getNumerKandydata() + ": " );
                System.out.print(kandydat.getLiczbaGłosów() + "\n");
            }

            for(Kandydat kandydat : okrąg.getKandydaci())
            {
                for(int i = 0; i < partie.size(); i++)
                {
                    if(partie.get(i).getNazwa().equals(kandydat.getPartia()))
                    {
                        głosy[i] += kandydat.getLiczbaGłosów();
                    }
                }
            }

            for(int i = 0; i < partie.size(); i++)
            {
                for(int j = 1; j < okrąg.getLiczbaWyborców() / 10; j++)
                {
                    ilorazy.put((double) głosy[i] / (2*j - 1), i);
                }
            }

            for(int i = 0; i < okrąg.getLiczbaWyborców() / 10; i++)
            {
                double największyIloraz = ilorazy.lastKey();
                mandaty[ilorazy.get(największyIloraz)]++;
                ilorazy.remove(największyIloraz);
            }

            for(int i = 0; i < partie.size(); i++)
            {
                System.out.println(partie.get(i).getNazwa() + ": " + mandaty[i]);
                partie.get(i).dodajMandaty(mandaty[i]);
            }
        }

        System.out.println("Wszystkie okręgi:");

        for(int i = 0; i < partie.size(); i++)
        {
            System.out.println(partie.get(i).getNazwa() + ": " + partie.get(i).getLiczbaMandatów());
        }
    }

    public static void MetodaHareaNiemeyera(ArrayList<OkrągWyborczy> okręgi, ArrayList<Partia> partie)
    {
        policzGłosyPartii(okręgi, partie);
        for(Partia partia : partie)
            partia.wyzerujMandaty();

        System.out.println("Metoda Hare’a-Niemeyera");

        for(OkrągWyborczy okrąg : okręgi)
        {
            if(okrąg == null)
                continue;

            int[] głosy = new int[partie.size()];
            double[] mandaty = new double[partie.size()];

            System.out.println("Okrąg nr: " + okrąg.getNumerOkręgu());

            for(Wyborca wyborca : okrąg.getWyborcy())
            {
                System.out.print(wyborca.getImię() + " " );
                System.out.print(wyborca.getNazwisko() + ": " );
                System.out.print(wyborca.getImięKandydata() + " " );
                System.out.print(wyborca.getNazwiskoKandydata() + "\n" );
            }

            for(Kandydat kandydat : okrąg.getKandydaci())
            {
                System.out.print(kandydat.getImię() + " " );
                System.out.print(kandydat.getNazwisko() + " - " );
                System.out.print(kandydat.getPartia() + " nr " );
                System.out.print(kandydat.getNumerKandydata() + ": " );
                System.out.print(kandydat.getLiczbaGłosów() + "\n");
            }

            for(Kandydat kandydat : okrąg.getKandydaci())
            {
                for(int i = 0; i < partie.size(); i++)
                {
                    if(partie.get(i).getNazwa().equals(kandydat.getPartia()))
                    {
                        głosy[i] += kandydat.getLiczbaGłosów();
                    }
                }
            }

            int sumaGłosów = 0;
            for(int i = 0; i < głosy.length; i++)
                sumaGłosów += głosy[i];

            for(int i = 0; i < partie.size(); i++)
            {
                mandaty[i] = (double) (głosy[i] * (okrąg.getLiczbaWyborców() / 10)) / sumaGłosów;
            }

            int sumaMandatów = 0;
            TreeMap<Double, Integer> częściUłamkowe = new TreeMap<>();
            for(int i = 0; i < mandaty.length; i++)
            {
                sumaMandatów += (int) mandaty[i];
                częściUłamkowe.put(mandaty[i] - (int) mandaty[i], i);
            }

            int pozostałeMandaty = (okrąg.getLiczbaWyborców() / 10) - sumaMandatów;

            for(int i = 0; i < pozostałeMandaty; i++)
            {
                double największaCzęśćUłamkowa = częściUłamkowe.lastKey();
                mandaty[częściUłamkowe.get(największaCzęśćUłamkowa)]++;
                częściUłamkowe.remove(największaCzęśćUłamkowa);
            }

            for(int i = 0; i < partie.size(); i++)
            {
                System.out.println(partie.get(i).getNazwa() + ": " + (int) mandaty[i]);
                partie.get(i).dodajMandaty((int) mandaty[i]);
            }
        }

        System.out.println("Wszystkie okręgi:");

        for(int i = 0; i < partie.size(); i++)
        {
            System.out.println(partie.get(i).getNazwa() + ": " + partie.get(i).getLiczbaMandatów());
        }
    }

}
