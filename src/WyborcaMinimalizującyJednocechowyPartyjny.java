import java.util.ArrayList;
import java.util.Random;

public class WyborcaMinimalizującyJednocechowyPartyjny extends WyborcaMaksymalizującyJednocechowy
{
    private String wybranaPartia;

    public WyborcaMinimalizującyJednocechowyPartyjny(String imię, String nazwisko, int numerOkręgu, int wybranaCecha, String wybranaPartia)
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
        int najmniejszaCecha = 100;

        for(Kandydat kandydat : kandydaci)
        {
            if(kandydat.getPartia().equals(wybranaPartia))
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
        }

        Kandydat wylosowanyKandydat = wybraniKandydaci.get(random.nextInt(wybraniKandydaci.size()));

        wylosowanyKandydat.dodajGłos();
        imięKandydata = wylosowanyKandydat.getImię();
        nazwiskoKandydata = wylosowanyKandydat.getNazwisko();
    }
}
