import java.util.ArrayList;

public class OkrągWyborczy
{
    private int numerOkręgu;
    private int liczbaWyborców;
    private ArrayList<Kandydat> kandydaci;
    private ArrayList<Wyborca> wyborcy;
    private int oryginalnaLiczbaKandydatów;

    public OkrągWyborczy(int numerOkręgu, int liczbaWyborców)
    {
        this.numerOkręgu = numerOkręgu;
        this.liczbaWyborców = liczbaWyborców;
        this.kandydaci = new ArrayList<>();
        this.wyborcy = new ArrayList<>();
        this.oryginalnaLiczbaKandydatów = 0;
    }

    public void dodajKandydata(Kandydat kandydat)
    {
        kandydaci.add(kandydat);
    }

    public void dodajWyborce(Wyborca wyborca)
    {
        wyborcy.add(wyborca);
    }

    public int getLiczbaWyborców()
    {
        return liczbaWyborców;
    }

    public ArrayList<Kandydat> getKandydaci()
    {
        return kandydaci;
    }

    public ArrayList<Wyborca> getWyborcy()
    {
        return wyborcy;
    }

    public int getNumerOkręgu()
    {
        return numerOkręgu;
    }

    public int getOryginalnaLiczbaKandydatów()
    {
        return oryginalnaLiczbaKandydatów;
    }

    public void scal(OkrągWyborczy okrąg)
    {
        this.oryginalnaLiczbaKandydatów = kandydaci.size();

        for(Kandydat kandydat : okrąg.getKandydaci())
        {
            kandydat.przesuńNumerKandydata(kandydaci.size());
        }

        this.liczbaWyborców += okrąg.getLiczbaWyborców();
        this.wyborcy.addAll(okrąg.getWyborcy());
        this.kandydaci.addAll(okrąg.getKandydaci());

        for(Kandydat kandydat : kandydaci)
        {
            System.out.println("numer kandydata: " + kandydat.getNumerKandydata() + " " + kandydat.getPartia());
        }
    }
}
