package ai.checkers;

/**
 * 
 *
 * @since 2018-12-14, 15:19:25
 * @author Aleksander Truszczyński
 */
public class Scoring 
{
     public static final int Distance = 1;
     public static final int CapturingPawn = 10;
     public static final int CapturingDame = 25;
     public static final int CreatingDame = 25;
     public static final int WhiteVictory = Integer.MAX_VALUE;
     public static final int BlackVictory = Integer.MIN_VALUE;
}