import java.util.ArrayList;
import java.util.Random;

public class WyborcaWszechstronnyPartyjny extends WyborcaWszechstronny
{
    private String wybranaPartia;

    public WyborcaWszechstronnyPartyjny(String imię, String nazwisko, int numerOkręgu, int[] cechy, String wybranaPartia)
    {
        super(imię, nazwisko, numerOkręgu, cechy);
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
        int największaSuma = 0;

        for(Kandydat kandydat : kandydaci)
        {
            if(wybranaPartia.equals(kandydat.getPartia()))
            {
                int suma = 0;

                for (int i = 0; i < wagiCech.length; i++)
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
        }

        Kandydat wylosowanyKandydat = wybraniKandydaci.get(random.nextInt(wybraniKandydaci.size()));

        wylosowanyKandydat.dodajGłos();
        imięKandydata = wylosowanyKandydat.getImię();
        nazwiskoKandydata = wylosowanyKandydat.getNazwisko();
    }
}
