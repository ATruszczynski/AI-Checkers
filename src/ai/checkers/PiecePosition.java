package ai.checkers;

/**
 * 
 *
 * @since 2018-12-14, 08:20:53
 * @author Aleksander Truszczyński
 */
public class PiecePosition implements ICopyAndStringable
{
    int x, y;
    public PiecePosition(int xx, int yy)
    {
        x = xx;
        y = yy;
    }

    @Override
    public ICopyAndStringable DeepCopy() 
    {
        return new PiecePosition(x,y);
    }
    @Override
    public String ToString() 
    {
        return "(" + x + "," + y + ")";
    }
    
}