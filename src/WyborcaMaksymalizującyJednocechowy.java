import java.util.ArrayList;
import java.util.Random;

public class WyborcaMaksymalizującyJednocechowy extends Wyborca
{
    protected int wybranaCecha;

    public WyborcaMaksymalizującyJednocechowy(String imię, String nazwisko, int numerOkręgu, int wybranaCecha)
    {
        this.imię = imię;
        this.nazwisko = nazwisko;
        this.numerOkręgu = numerOkręgu;
        this.wybranaCecha = wybranaCecha;
    }

    @Override
    public void głosuj(ArrayList<OkrągWyborczy> okręgi, Random random)
    {
        OkrągWyborczy okrąg = okręgi.get(numerOkręgu - 1);
        if(okrąg == null)
            okrąg = okręgi.get(numerOkręgu - 2);
        ArrayList<Kandydat> kandydaci = okrąg.getKandydaci();

        ArrayList<Kandydat> wybraniKandydaci = new ArrayList<>();
        int największaCecha = -100;

        for(Kandydat kandydat : kandydaci)
        {
            int cecha = kandydat.getCecha(wybranaCecha - 1);


            if(cecha == największaCecha)
            {
                wybraniKandydaci.add(kandydat);
            }
            else if(cecha > największaCecha)
            {
                wybraniKandydaci.clear();
                wybraniKandydaci.add(kandydat);
                największaCecha = cecha;
            }
        }

        Kandydat wylosowanyKandydat = wybraniKandydaci.get(random.nextInt(wybraniKandydaci.size()));

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
