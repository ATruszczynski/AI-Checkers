package ai.checkers;

/**
 * 
 *
 * @since 2018-12-14, 07:48:33
 * @author Aleksander Truszczyński
 */
public class Piece implements ICopyAndStringable
{
    public enum Colour 
    {
        Black, White;

        public String ToString() 
        {
            if (this == White)
                return "w";
            else
                return "b";
        }
        public Colour Negate()
        {
            if(this == White)
                return Black;
            else
                return White;
        }
    };

    public enum Type 
    {
        Pawn, Dame;

        public String ToString() 
        {
            if (this == Pawn)
                return "p";
            else
                return "d";
        }
    };
    Colour colour;
    Type type;
    public Piece(Colour c, Type t)
    {
        colour = c;
        type = t;
    }
    @Override
    public ICopyAndStringable DeepCopy()
    {
        return new Piece(colour, type);
    }
    @Override
    public String ToString()
    {
        return "(" + colour.ToString() + "," + type.ToString() + ")";
    }
}