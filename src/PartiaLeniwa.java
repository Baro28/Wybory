import java.util.ArrayList;

public class PartiaLeniwa extends Partia
{
    public PartiaLeniwa(String nazwa, int budżet)
    {
        super(nazwa, budżet);
    }

    @Override
    public void przeprowadźKampanię(ArrayList<OkrągWyborczy> okręgi, ArrayList<Działanie> działania)
    {
        while(true)
        {
            int cena = 0;
            boolean brakPieniędzy = true;

            for(OkrągWyborczy okrąg : okręgi)
            {
                if(okrąg != null)
                {
                    for (Działanie działanie : działania)
                    {
                        cena = działanie.getSumaCech() * okrąg.getLiczbaWyborców();
                        if (cena <= budżet)
                        {
                            for (Wyborca wyborca : okrąg.getWyborcy())
                            {
                                wyborca.zmieńCechy(działanie.getZmianyCech());
                            }
                            budżet -= cena;
                            brakPieniędzy = false;
                        }
                    }
                }
            }
            if(brakPieniędzy)
                break;
        }
    }
}
