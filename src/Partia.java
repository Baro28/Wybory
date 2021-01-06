import java.util.ArrayList;

public abstract class Partia
{
    protected String nazwa;
    protected int budżet;
    protected int liczbaMandatów = 0;
    protected int liczbaGłosów = 0;

    public Partia(String nazwa, int budżet)
    {
        this.nazwa = nazwa;
        this.budżet = budżet;
    }

    public abstract void przeprowadźKampanię(ArrayList<OkrągWyborczy> okręgi, ArrayList<Działanie> działania);

    public int getBudżet()
    {
        return budżet;
    }

    public String getNazwa()
    {
        return nazwa;
    }

    public void dodajGłosy(int głosy)
    {
        liczbaGłosów += głosy;
    }

    public void dodajMandaty(int mandaty)
    {
        liczbaMandatów += mandaty;
    }

    public int getLiczbaMandatów()
    {
        return liczbaMandatów;
    }

    public void wyzerujMandaty()
    {
        liczbaMandatów = 0;
    }
}
