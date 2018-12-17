package ai.checkers;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;

/**
 *
 *
 * @since 2018-12-16, 12:01:06
 * @author Aleksander Truszczyński
 */
public class Game {
    GameState gs;
    Piece.Colour player = Piece.Colour.White;
    Piece.Colour turn = Piece.Colour.White;
    boolean inProgress = false;
    CheckersGUI window;
    LinkedList<Move> validMoves = new LinkedList<>();
    final int patience = 3;
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
                validMoves = gs.GetPieceMoves(Piece.Colour.White, p);
                return true;
            }
        }
        window.repaint();
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
                window.repaint();
                return true;
            }
        }
        window.repaint();
        return false;
    }
    
    public void UserInteraction(java.awt.event.MouseEvent evt)
    {
        int x = evt.getX();
        int y = evt.getY();
        if(turn == player)
        {
            if(validMoves.isEmpty())
            {
                if(!HighlightMoves(x, y))
                    toAdvice--;
                if(toAdvice == 0)
                {
                    validMoves = gs.GetColourMoves(turn);
                    toAdvice = patience;
                }
            }
            else
            {
                PerformValidMove(x,y);
            }
        }
    }

    private int Distance(int x1, int y1, int x2, int y2) {
        int sq = (int) (Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        int sqrt = (int) Math.sqrt(sq);

        return sqrt;
    }
}
