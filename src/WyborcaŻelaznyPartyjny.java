import java.util.ArrayList;
import java.util.Random;

public class WyborcaŻelaznyPartyjny extends Wyborca
{
    protected String wybranaPartia;

    public WyborcaŻelaznyPartyjny(String imię, String nazwisko, int numerOkręgu, String wybranaPartia)
    {
        this.imię = imię;
        this.nazwisko = nazwisko;
        this.numerOkręgu = numerOkręgu;
        this.wybranaPartia = wybranaPartia;
    }

    @Override
    public void głosuj(ArrayList<OkrągWyborczy> okręgi, Random random)
    {
        OkrągWyborczy okrąg = okręgi.get(numerOkręgu - 1);
        if(okrąg == null)
            okrąg = okręgi.get(numerOkręgu - 2);
        ArrayList<Kandydat> kandydaci = okrąg.getKandydaci();
        ArrayList<Kandydat> kandydaciWybranejPartii = new ArrayList<>();

        for(Kandydat kandydat : kandydaci)
        {
            if(wybranaPartia.equals(kandydat.getPartia()))
                kandydaciWybranejPartii.add(kandydat);
        }

        Kandydat wylosowanyKandydat = kandydaciWybranejPartii.get(random.nextInt(kandydaciWybranejPartii.size()));

        wylosowanyKandydat.dodajGłos();
        imięKandydata = wylosowanyKandydat.getImię();
        nazwiskoKandydata = wylosowanyKandydat.getNazwisko();
    }

    @Override
    public void zmieńCechy(ArrayList<Integer> zmiana)
    {

    }

    @Override
    public int sumaWażonaKandydata(Kandydat kandydat)
    {
        return 0;
    }

    @Override
    public int sumaWażonaKandydataPoDziałaniu(Kandydat kandydat, Działanie działanie)
    {
        return 0;
    }
}
