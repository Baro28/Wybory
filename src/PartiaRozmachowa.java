import java.util.ArrayList;

public class PartiaRozmachowa extends Partia
{
    public PartiaRozmachowa(String nazwa, int budżet)
    {
        super(nazwa, budżet);
    }

    @Override
    public void przeprowadźKampanię(ArrayList<OkrągWyborczy> okręgi, ArrayList<Działanie> działania)
    {
        while(true)
        {
            OkrągWyborczy wybranyOkrąg = null;
            Działanie wybraneDziałanie = null;
            int wybranaCena = 0;
            int cena = 0;
            boolean brakPieniędzy = true;

            for(OkrągWyborczy okrąg : okręgi)
            {
                if(okrąg != null)
                {
                    for (Działanie działanie : działania)
                    {
                        cena = działanie.getSumaCech() * okrąg.getLiczbaWyborców();
                        if (cena > wybranaCena && cena <= budżet)
                        {
                            wybranaCena = cena;
                            wybraneDziałanie = działanie;
                            wybranyOkrąg = okrąg;
                            brakPieniędzy = false;
                        }
                    }
                }
            }
            if(brakPieniędzy)
                break;
            budżet -= wybranaCena;
            for(Wyborca wyborca : wybranyOkrąg.getWyborcy())
            {
                wyborca.zmieńCechy(wybraneDziałanie.getZmianyCech());
            }
        }
    }
}
