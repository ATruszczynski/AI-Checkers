package ai.checkers;

import java.util.LinkedList;
import javafx.util.Pair;

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
    LinkedList<Move> validMoves = new LinkedList<>();
    LinkedList<PiecePosition> adviceMoves = new LinkedList<>();
    final int movePatienceDef = 2;
    int movePatienceCurr = movePatienceDef;
    final int stalematePatienceDef = 3;
    int stalematePatienceCurr = stalematePatienceDef;
    final long minimalDelay = 500000000;
    Piece.Colour turn = Piece.Colour.White;
    Piece.Colour player = Piece.Colour.White;
    int difficultyLevel = 3;
    
    public Game (CheckersGUI w)
    {
        window = w;
        gs = new GameState();
        gs.Initiate();
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
        Pair<Integer, Integer> tmp = window.getFieldWH();
        int fieldW = tmp.getKey();
        int fieldH = tmp.getValue();
        LinkedList<PiecePosition> pieces = gs.GetColourPiecesList(player);
        
        for(PiecePosition piece: pieces)
        {
            int x = piece.x * fieldW;
            int y = piece.y * fieldH;
            if(Distance(x,y,evt.getX(),evt.getY()) < CheckersGUI.BoardPanel.moveAvailableDiam/2)
            {
                validMoves = gs.GetPieceMoves(player, piece);
            }
        }
        
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
    private GameResult AnalyseGameState()
    {
        return GameResult.InProgress;
    }
    private int Distance(int x1, int y1, int x2, int y2)
    {
        int xx = (int)Math.pow(x2 - x1, 2);
        int yy = (int)Math.pow(y2 - y1, 2);
        
        return (int) Math.sqrt(xx + yy);
        
    }
}