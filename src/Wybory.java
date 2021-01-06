import java.util.ArrayList;
import java.util.Random;

public class Wybory
{
    ArrayList<Partia> partie;
    ArrayList<OkrągWyborczy> okręgi;
    ArrayList<Działanie> działania;

    public Wybory(ArrayList<Partia> partie, ArrayList<OkrągWyborczy> okręgi, ArrayList<Działanie> działania)
    {
        this.partie = partie;
        this.okręgi = okręgi;
        this.działania = działania;
    }

    public void wykonaj()
    {
        for(Partia partia : partie)
        {
            partia.przeprowadźKampanię(okręgi, działania);
        }
        Random random = new Random();

        for(OkrągWyborczy okrąg : okręgi)
        {
            if(okrąg != null)
            {
                for (Wyborca wyborca : okrąg.getWyborcy())
                {
                    wyborca.głosuj(okręgi, random);
                }
            }
        }

        MetodyPrzeliczaniaMandatów.MetodaDHondta(okręgi, partie);
        MetodyPrzeliczaniaMandatów.MetodaSainteLague(okręgi, partie);
        MetodyPrzeliczaniaMandatów.MetodaHareaNiemeyera(okręgi, partie);
    }
}
