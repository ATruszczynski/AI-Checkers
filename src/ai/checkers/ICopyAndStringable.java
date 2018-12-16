package ai.checkers;

/**
 * 
 *
 * @since 2018-12-14, 07:53:09
 * @author Aleksander Truszczyński
 */
public interface ICopyAndStringable 
{
    public String ToString();
    public ICopyAndStringable DeepCopy();
}