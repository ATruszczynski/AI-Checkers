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
    public Boolean inProgress = true;
    GameState gs;
    CheckersGUI window;
    LinkedList<Move> validMoves = new LinkedList<>();
    LinkedList<PiecePosition> adviceMoves = new LinkedList<>();
    final int advicePatienceDef = 2;
    int advicePatienceCurr = advicePatienceDef;
    final int stalematePatienceDef = 5;
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
    public void Player_Turn(java.awt.event.MouseEvent evt)
    {
        if(inProgress && turn == player)
        {
            if(HighlightValidMoves(evt))
            {
                ZeroAdviceMoves();
            }
            else
            {
                if(PerformValidMove(evt))
                {
                    ZeroAdvices();
                    turn = turn.Negate();
                }
                else
                {
                    ZeroValidMoves();
                    if(--advicePatienceCurr <= 0)
                    {
                        HighlightPiecesWithValidMoves();
                    }
                }
            }
            AnalyseGameState();
        }
    }
    public void AI_Turn()
    {
        if(inProgress && turn == player.Negate())
        {
            Move m = Simulator.GetOptimalMove(difficultyLevel, turn, gs);
            if(m != null)
            {
                StalemateCheck(m);
                gs.ApplyMove(m);
                turn = turn.Negate();
            }
            AnalyseGameState();
        }
    }
    private boolean HighlightValidMoves(java.awt.event.MouseEvent evt)
    {
        int w = window.GetBoardPanelWidth();
        int h = window.GetBoardPanelHeight();
        int fieldW = w/8;
        int fieldH = h/8;
        LinkedList<PiecePosition> pieces = gs.GetColourPiecesList(player);
        
        int xm = evt.getX();
        int ym = evt.getY();
        
        for(PiecePosition piece: pieces)
        {
            int x = piece.x * fieldW + fieldW / 2;
            int y = piece.y * fieldH + fieldH / 2;

            if (Distance(x, y, xm, ym) < CheckersGUI.BoardPanel.pieceDiameter / 2) {
                validMoves = gs.GetPieceMoves(player, piece);
                if(!validMoves.isEmpty())
                    return true;
                else
                    return false;
            }
        }
        
        return false;
    }
    private void HighlightPiecesWithValidMoves()
    {
        LinkedList<Move> moves = gs.GetColourMoves(player);
        adviceMoves = new LinkedList<>();
        for(Move m: moves)
        {
            adviceMoves.add(m.steps.getFirst());
        }
    }
    private void ZeroValidMoves()
    {
        validMoves = new LinkedList<>();
    }
    private void ZeroAdviceMoves()
    {
        advicePatienceCurr = advicePatienceDef;
        adviceMoves = new LinkedList<>();
    }
    private void ZeroAdvices()
    {
        ZeroValidMoves();
        ZeroAdviceMoves();
    }
    private boolean PerformValidMove(java.awt.event.MouseEvent evt)
    {
        int w = window.GetBoardPanelWidth();
        int h = window.GetBoardPanelHeight();
        int fieldW = w/8;
        int fieldH = h/8;
        
        int xm = evt.getX();
        int ym = evt.getY();

        for (Move m : validMoves) {
            PiecePosition p = m.steps.getLast();
            int x = p.x * fieldW + fieldW / 2;
            int y = p.y * fieldH + fieldH / 2;

            if (Distance(x, y, xm, ym) < CheckersGUI.BoardPanel.pieceDiameter / 2) 
            {
                StalemateCheck(m);
                gs.ApplyMove(m);
                validMoves = new LinkedList<>();
                return true;
            }
        }
        return false;
    }
    private void AnalyseGameState()
    {
        synchronized (inProgress)
        {
            LinkedList<PiecePosition> whites = gs.GetColourPiecesList(Piece.Colour.White);
            LinkedList<PiecePosition> blacks = gs.GetColourPiecesList(Piece.Colour.Black);

            if(whites.isEmpty())
            {
                BlackVictory();
                return;
            }
            if(blacks.isEmpty())
            {
                WhiteVictory();
                return;
            }
            LinkedList<Move> whiteMoves = gs.GetColourMoves(Piece.Colour.White);
            LinkedList<Move> blackMoves = gs.GetColourMoves(Piece.Colour.Black);

            if (turn == Piece.Colour.White)
                if (blackMoves.isEmpty()) 
                {
                    Draw();
                    return;
                }
            if (turn == Piece.Colour.Black)
                if (whiteMoves.isEmpty()) 
                {
                    Draw();
                    return;
                }
            if(stalematePatienceCurr <= 0)
            {
                //do przemyślenia, przypadki brzegowe warcab
            }
        }
    }
    private void StalemateCheck(Move m)
    {
        Move mcurr = (Move)m.DeepCopy();
        Simulator.EvaluateMove(mcurr, gs);
        if(mcurr.capturedCount == 0)
        {
            stalematePatienceCurr--;
        }
        else
        {
            stalematePatienceCurr = stalematePatienceDef;
        }
    }
    
    private void BlackVictory()
    {
        inProgress = false;
        window.SetEndGameLabelText("Blacks Won!");
    }
    private void WhiteVictory()
    {
        inProgress = false;
        window.SetEndGameLabelText("Whites Won!");
    }
    private void Draw()
    {
        inProgress = false;
        window.SetEndGameLabelText("<html>" + "It's a draw. That's some sort of an achievement in checkers." + "</html>");
    }
    private int Distance(int x1, int y1, int x2, int y2)
    {
        int xx = (int)Math.pow(x2 - x1, 2);
        int yy = (int)Math.pow(y2 - y1, 2);
        
        return (int) Math.sqrt(xx + yy);
        
    }
}