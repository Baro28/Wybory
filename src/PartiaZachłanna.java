import java.lang.reflect.Array;
import java.util.ArrayList;

public class PartiaZachłanna extends Partia
{
    public PartiaZachłanna(String nazwa, int budżet)
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
            int największaZmiana = 0;
            int zmiana = 0;

            for(OkrągWyborczy okrąg : okręgi)
            {
                if(okrąg != null)
                {
                    for (Działanie działanie : działania)
                    {
                        cena = działanie.getSumaCech() * okrąg.getLiczbaWyborców();
                        if (cena <= budżet)
                        {
                            ArrayList<Kandydat> kandydaci = okrąg.getKandydaci();
                            ArrayList<Wyborca> wyborcy = okrąg.getWyborcy();

                            for (Kandydat kandydat : kandydaci)
                            {
                                for (Wyborca wyborca : wyborcy)
                                {
                                    zmiana += Math.abs(wyborca.sumaWażonaKandydataPoDziałaniu(kandydat, działanie) - wyborca.sumaWażonaKandydata(kandydat));
                                }
                            }

                            if (zmiana > największaZmiana)
                            {
                                wybranaCena = cena;
                                wybraneDziałanie = działanie;
                                wybranyOkrąg = okrąg;
                                brakPieniędzy = false;
                                największaZmiana = zmiana;
                            }
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
