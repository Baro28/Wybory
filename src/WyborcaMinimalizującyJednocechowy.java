import java.util.ArrayList;
import java.util.Random;

public class WyborcaMinimalizującyJednocechowy extends Wyborca
{
    private int wybranaCecha;

    public WyborcaMinimalizującyJednocechowy(String imię, String nazwisko, int numerOkręgu, int wybranaCecha)
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
        int najmniejszaCecha = 100;

        for(Kandydat kandydat : kandydaci)
        {
            int cecha = kandydat.getCecha(wybranaCecha - 1);

            if (cecha == najmniejszaCecha)
            {
                wybraniKandydaci.add(kandydat);
            }
            else if (cecha < najmniejszaCecha)
            {
                wybraniKandydaci.clear();
                wybraniKandydaci.add(kandydat);
                najmniejszaCecha = cecha;
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
