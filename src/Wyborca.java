import java.util.ArrayList;
import java.util.Random;

public abstract class Wyborca
{
    protected String imię;
    protected String nazwisko;
    protected int numerOkręgu;
    protected String imięKandydata;
    protected String nazwiskoKandydata;

    public abstract void głosuj(ArrayList<OkrągWyborczy> okręgi, Random random);

    public abstract void zmieńCechy(ArrayList<Integer> zmiana);

    public abstract int sumaWażonaKandydata(Kandydat kandydat);

    public abstract int sumaWażonaKandydataPoDziałaniu(Kandydat kandydat, Działanie działanie);

    public String getImię()
    {
        return imię;
    }

    public String getNazwisko()
    {
        return nazwisko;
    }

    public String getImięKandydata()
    {
        return imięKandydata;
    }

    public String getNazwiskoKandydata()
    {
        return nazwiskoKandydata;
    }
}
