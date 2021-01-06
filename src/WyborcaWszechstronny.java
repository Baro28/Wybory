import java.util.ArrayList;
import java.util.Random;

public class WyborcaWszechstronny extends Wyborca
{
    protected int[] wagiCech;

    public WyborcaWszechstronny(String imię, String nazwisko, int numerOkręgu, int[] wagiCech)
    {
        this.imię = imię;
        this.nazwisko = nazwisko;
        this.numerOkręgu = numerOkręgu;
        this.wagiCech = wagiCech;
    }

    @Override
    public void głosuj(ArrayList<OkrągWyborczy> okręgi, Random random)
    {
        OkrągWyborczy okrąg = okręgi.get(numerOkręgu - 1);
        if(okrąg == null)
            okrąg = okręgi.get(numerOkręgu - 2);
        ArrayList<Kandydat> kandydaci = okrąg.getKandydaci();

        ArrayList<Kandydat> wybraniKandydaci = new ArrayList<>();
        int największaSuma = 0;

        for(Kandydat kandydat : kandydaci)
        {
            int suma = 0;

            for(int i = 0; i < wagiCech.length; i++)
            {
                suma += wagiCech[i] * kandydat.getCecha(i);
            }

            if (suma == największaSuma)
            {
                wybraniKandydaci.add(kandydat);
            }
            else if (suma > największaSuma)
            {
                wybraniKandydaci.clear();
                wybraniKandydaci.add(kandydat);
                największaSuma = suma;
            }
        }

        Kandydat wylosowanyKandydat = wybraniKandydaci.get(random.nextInt(wybraniKandydaci.size()));

        wylosowanyKandydat.dodajGłos();
        imięKandydata = wylosowanyKandydat.getImię();
        nazwiskoKandydata = wylosowanyKandydat.getNazwisko();
    }

    public void zmieńCechy(ArrayList<Integer> zmiana)
    {
        for (int i = 0; i < wagiCech.length; i++)
        {
            wagiCech[i] += zmiana.get(i);
            if(wagiCech[i] > 100)
                wagiCech[i] = 100;
            if(wagiCech[i] < - 100)
                wagiCech[i] = -100;
        }
    }

    @Override
    public int sumaWażonaKandydata(Kandydat kandydat)
    {
        int suma = 0;
        for(int i = 0; i < wagiCech.length; i++)
        {
            suma += wagiCech[i] * kandydat.getCecha(i);
        }
        return suma;
    }

    @Override
    public int sumaWażonaKandydataPoDziałaniu(Kandydat kandydat, Działanie działanie)
    {
        int suma = 0;
        int[] wagiCechPoDziałaniu = wagiCech.clone();
        for (int i = 0; i < wagiCechPoDziałaniu.length; i++)
        {
            wagiCechPoDziałaniu[i] += działanie.getZmianyCech().get(i);
            if(wagiCechPoDziałaniu[i] > 100)
                wagiCechPoDziałaniu[i] = 100;
            if(wagiCechPoDziałaniu[i] < - 100)
                wagiCechPoDziałaniu[i] = -100;
        }

        for(int i = 0; i < wagiCech.length; i++)
        {
            suma += wagiCechPoDziałaniu[i] * kandydat.getCecha(i);
        }
        return suma;
    }
}
