import java.util.ArrayList;

public class Działanie
{
    ArrayList<Integer> zmianyCech;
    private int sumaCech = 0;

    public Działanie(ArrayList<Integer> zmianyCech)
    {
        this.zmianyCech = zmianyCech;
        for(int cecha : zmianyCech)
        {
            if(cecha < 0)
                cecha *= (-1);
            sumaCech += cecha;
        }
    }

    public int getSumaCech()
    {
        return sumaCech;
    }

    public ArrayList<Integer> getZmianyCech()
    {
        return zmianyCech;
    }
}
