public class Kandydat
{
    private String imię;
    private String nazwisko;
    private String partia;
    private int numerOkręgu;
    private int numerKandydata;
    private int[] cechy;
    private int liczbaGłosów;

    public Kandydat(String imię, String nazwisko, String partia, int numerOkręgu, int numerKandydata, int[] cechy)
    {
        this.imię = imię;
        this.nazwisko = nazwisko;
        this.partia = partia;
        this.numerOkręgu = numerOkręgu;
        this.numerKandydata = numerKandydata;
        this.cechy = cechy;
    }

    public String getImię()
    {
        return imię;
    }

    public String getNazwisko()
    {
        return nazwisko;
    }

    public String getPartia()
    {
        return partia;
    }

    public int getCecha(int numerCechy)
    {
        return cechy[numerCechy];
    }

    public void dodajGłos()
    {
        liczbaGłosów++;
    }

    public int getNumerKandydata()
    {
        return numerKandydata;
    }

    public int getLiczbaGłosów()
    {
        return liczbaGłosów;
    }

    public void przesuńNumerKandydata(int ile)
    {
        this.numerKandydata += ile;
    }
}
