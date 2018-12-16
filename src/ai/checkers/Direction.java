package ai.checkers;

/**
 * 
 *
 * @since 2018-12-14, 08:11:39
 * @author Aleksander Truszczyński
 */
public enum Direction implements ICopyAndStringable
{
    NE(-1,1), NW(1,1), SW(1,-1), SE(-1,-1);
    
    int dx;
    int dy;
    
    Direction(int ddx, int ddy)
    {
        dx = ddx;
        dy = ddy;
    }

    @Override
    public ICopyAndStringable DeepCopy() 
    {
        return this;
    }

    @Override
    public String ToString() 
    {
        if (this == NW)
            return "NW";
        else if (this == NE)
            return "NE";
        else if (this == SW)
            return "SW";
        else
            return "SE";
    }
    
}