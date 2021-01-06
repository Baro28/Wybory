import java.util.ArrayList;
import java.util.Random;

public class WyborcaŻelaznyKandydata extends WyborcaŻelaznyPartyjny
{
    private int wybranyKandydat;

    public WyborcaŻelaznyKandydata(String imię, String nazwisko, int numerOkręgu, String wybranaPartia, int wybranyKandydat)
    {
        super(imię, nazwisko, numerOkręgu, wybranaPartia);
        this.wybranyKandydat = wybranyKandydat;
    }

    @Override
    public void głosuj(ArrayList<OkrągWyborczy> okręgi, Random random)
    {
        OkrągWyborczy okrąg = okręgi.get(numerOkręgu - 1);
        if(okrąg == null)
        {
            okrąg = okręgi.get(numerOkręgu - 2);
            wybranyKandydat += okrąg.getOryginalnaLiczbaKandydatów();
        }
        ArrayList<Kandydat> kandydaci = okrąg.getKandydaci();

        for(Kandydat kandydat : kandydaci)
        {
            if(wybranaPartia.equals(kandydat.getPartia()) && kandydat.getNumerKandydata() == wybranyKandydat)
            {
                kandydat.dodajGłos();
                imięKandydata = kandydat.getImię();
                nazwiskoKandydata = kandydat.getNazwisko();
            }
        }
    }
}
