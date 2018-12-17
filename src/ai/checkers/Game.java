package ai.checkers;

import java.util.LinkedList;

/**
 * 
 *
 * @since 2018-12-17, 13:54:17
 * @author Aleksander Truszczyński
 */
public class Game 
{
    public static volatile boolean terminateAsynchronousTasks = false;
    public boolean inProgress = true;
    GameState gs;
    CheckersGUI window;
    LinkedList<Move> validMoves;
    LinkedList<PiecePosition> adviceMoves;
    final int movePatienceDef = 2;
    int movePatienceCurr = movePatienceDef;
    final long minimalDelay = 500000000;
    Piece.Colour turn = Piece.Colour.White;
    Piece.Colour player = Piece.Colour.White;
    int difficultyLevel = 3;
    
    public Game (CheckersGUI w)
    {
        window = w;
    }
    
    public void StartGame()
    {
        
    }
    public void RestartGame()
    {
         
    }
    public void ChangeSettings(int diff)
    {
        
    }
    public void GameTick(java.awt.event.MouseEvent evt)
    {
        
    }
    private boolean HighlightValidMoves(java.awt.event.MouseEvent evt)
    {
        return false;
    }
    private void HighlightPiecesWithValidMoves()
    {
        
    }
    private void ZeroValidMoves()
    {
        
    }
    private void ZeroPiecesWithValidMoves()
    {
        
    }
    private void ZeroAdvices()
    {
        ZeroValidMoves();
        ZeroPiecesWithValidMoves();
    }
    private boolean PerformValidMove(java.awt.event.MouseEvent evt)
    {
        return false;
    }
}