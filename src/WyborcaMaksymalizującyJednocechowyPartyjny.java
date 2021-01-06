import java.util.ArrayList;
import java.util.Random;

public class WyborcaMaksymalizującyJednocechowyPartyjny extends WyborcaMaksymalizującyJednocechowy
{
    private String wybranaPartia;

    public WyborcaMaksymalizującyJednocechowyPartyjny(String imię, String nazwisko, int numerOkręgu, int wybranaCecha, String wybranaPartia)
    {
        super(imię, nazwisko, numerOkręgu, wybranaCecha);
        this.wybranaPartia = wybranaPartia;
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
            if(kandydat.getPartia().equals(wybranaPartia))
            {
                int cecha = kandydat.getCecha(wybranaCecha - 1);
                if (cecha == największaCecha)
                {
                    wybraniKandydaci.add(kandydat);
                }
                else if (cecha > największaCecha)
                {
                    wybraniKandydaci.clear();
                    wybraniKandydaci.add(kandydat);
                    największaCecha = cecha;
                }
            }
        }

        Kandydat wylosowanyKandydat = wybraniKandydaci.get(random.nextInt(wybraniKandydaci.size()));

        wylosowanyKandydat.dodajGłos();
        imięKandydata = wylosowanyKandydat.getImię();
        nazwiskoKandydata = wylosowanyKandydat.getNazwisko();
    }
}
