package ai.checkers;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javax.swing.Timer;

/**
 *
 *
 * @since 2018-12-16, 12:01:06
 * @author Aleksander Truszczyński
 */
public class Game {
    GameState gs;
    Piece.Colour player = Piece.Colour.Black;
    Piece.Colour turn = Piece.Colour.White;
    boolean inProgress = false;
    CheckersGUI window;
    LinkedList<Move> validMoves = new LinkedList<>();
    LinkedList<Move> adviceMoves = new LinkedList<>();
    final int patience = 2;
    int difficultyLevel = 3;
    int toAdvice = patience;

    public Game(CheckersGUI w) {
        gs = new GameState();
        gs.Initiate();
        window = w;
    }

    public boolean HighlightMoves(int xp, int yp) {
        if (turn != player)
            return false;
        int w = window.GetBoardPanelWidth();
        int h = window.GetBoardPanelHeight();
        int fieldW = w / 8;
        int fieldH = h / 8;
        LinkedList<PiecePosition> pieces = gs.GetColourPiecesList(turn);
        validMoves = new LinkedList<>();

        for (PiecePosition p : pieces) {
            int x = p.x * fieldW + fieldW / 2;
            int y = p.y * fieldH + fieldH / 2;

            if (Distance(x, y, xp, yp) < CheckersGUI.BoardPanel.pieceDiameter / 2) {
                validMoves = gs.GetPieceMoves(turn, p);
                if(!validMoves.isEmpty())
                    return true;
                else
                    return false;
            }
        }
        //window.repaint();
        return false;
    }

    public boolean PerformValidMove(int xp, int yp) {
        if (turn != player)
            return false;
        int w = window.GetBoardPanelWidth();
        int h = window.GetBoardPanelHeight();
        int fieldW = w / 8;
        int fieldH = h / 8;

        for (Move m : validMoves) {
            PiecePosition p = m.steps.getLast();
            int x = p.x * fieldW + fieldW / 2;
            int y = p.y * fieldH + fieldH / 2;

            if (Distance(x, y, xp, yp) < CheckersGUI.BoardPanel.pieceDiameter / 2) {
                gs.ApplyMove(m);
                validMoves = new LinkedList<>();
                //window.repaint();
                return true;
            }
        }
        //window.repaint();
        return false;
    }
    
    public void AI_Move()
    {
        if(turn != player)
        {
            Move m = Simulator.GetOptimalMove(difficultyLevel, turn, gs);
            if(m == null)
            {
                
            }
            gs.ApplyMove(m);
            turn = turn.Negate();
            window.repaint();
        }
    }
    
    public void UserInteraction(java.awt.event.MouseEvent evt)
    {
        //window.repaint();
        int x = evt.getX();
        int y = evt.getY();
        if(turn == player)
        {
            if(PerformValidMove(x,y))
            {
                toAdvice = patience;
                adviceMoves = new LinkedList<>();
                
                turn = turn.Negate();
                //return true;
            }
            else
            {
                if (!HighlightMoves(x, y))
                    toAdvice--;
                else
                {
                    toAdvice = patience;
                    adviceMoves = new LinkedList<>();
                }
                if (toAdvice == 0) 
                {
                    adviceMoves = gs.GetColourMoves(turn);
                    toAdvice = patience;
                }
            }
        }
    }

    private void GameStateAnalysis()
    {
        
    }
    
    private int Distance(int x1, int y1, int x2, int y2) {
        int sq = (int) (Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        int sqrt = (int) Math.sqrt(sq);

        return sqrt;
    }
}
