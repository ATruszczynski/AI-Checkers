package ai.checkers;

import java.util.LinkedList;

/**
 * 
 *
 * @since 2018-12-14, 07:48:48
 * @author Aleksander Truszczyński
 */
public class Move implements ICopyAndStringable
{
    LinkedList<PiecePosition> steps = new LinkedList<>();
    int score = 0;
    int capturedCount = 0;
    public Move()
    {
        
    }
    public Move(int s)
    {
        score = s;
    }
    public Move(PiecePosition start)
    {
        steps.add((PiecePosition)start.DeepCopy());
    }
    public Move(PiecePosition start, PiecePosition end)
    {
        steps.add((PiecePosition)start.DeepCopy());
        steps.add((PiecePosition)end.DeepCopy());
    }
    
    public void AddStep(PiecePosition next)
    {
        steps.add((PiecePosition)next.DeepCopy());
    }
    public Move GetConcatenatedMove(Move m2)
    {
        Move ret = (Move)this.DeepCopy();
        for(int i = 1; i < m2.Length(); i++)
            ret.steps.add((PiecePosition)(m2.steps.get(i).DeepCopy()));
        ret.score += m2.score;
        ret.capturedCount += m2.capturedCount;
        return ret;
    }
    public int Length()
    {
        return steps.size();
    }

    @Override
    public ICopyAndStringable DeepCopy() 
    {
        Move ret = new Move();
        for(int i = 0; i < steps.size();  i++)
        {
            ret.AddStep((PiecePosition)steps.get(i).DeepCopy());
        }
        ret.score = score;
        ret.capturedCount = capturedCount;
        return ret;
    }
    
    @Override
    public String ToString() 
    {
        String ret = "[m::";
        for(int i = 0; i < steps.size(); i++)
        {
            ret += steps.get(i).ToString();
            
            if(i != steps.size() - 1)
                ret += ">";
        }
        return ret + ",cc:" + capturedCount + ",s:" + score + "]";
    }
}