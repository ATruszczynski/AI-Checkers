package ai.checkers;

import static java.lang.Thread.sleep;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import javax.swing.Timer;

/**
 * 
 *
 * @since 2018-12-14, 07:48:08
 * @author Aleksander Truszczyński
 */
public class GameState implements ICopyAndStringable
{
    Piece[][] board;
    int size;
    public GameState()
    {
        size = 8;
        board = new Piece[size][size];
    }
    public void GetBeginningState()
    {
        board = new Piece[size][size];
        for (int y = 0; y < 3; y++)
        {
            for (int x = 0; x < size; x++)
            {
                if(x % 2 != y % 2)
                {
                    board[x][y] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
                    board[size - x - 1][size - y - 1] = new Piece(Piece.Colour.White, Piece.Type.Pawn);
                }
            }
        }
//        board[0][7] = new Piece(Piece.Colour.White, Piece.Type.Dame);
//        board[2][7] = new Piece(Piece.Colour.Black, Piece.Type.Dame);
        
//        board[0][7] = new Piece(Piece.Colour.White, Piece.Type.Pawn);
//        board[0][5] = new Piece(Piece.Colour.White, Piece.Type.Pawn);
//        board[0][3] = new Piece(Piece.Colour.White, Piece.Type.Pawn);
//        board[1][6] = new Piece(Piece.Colour.White, Piece.Type.Pawn);
//        board[2][7] = new Piece(Piece.Colour.White, Piece.Type.Pawn);
//        board[3][6] = new Piece(Piece.Colour.White, Piece.Type.Pawn);
//        board[4][7] = new Piece(Piece.Colour.White, Piece.Type.Pawn);
//        board[5][6] = new Piece(Piece.Colour.White, Piece.Type.Pawn);
//        board[7][6] = new Piece(Piece.Colour.White, Piece.Type.Pawn);
//        
//        board[0][1] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
//        board[1][0] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
//        board[1][2] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
//        board[2][1] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
//        board[3][0] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
//        board[4][1] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
//        board[5][0] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
//        board[5][4] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
//        board[7][0] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
    }
    
    public LinkedList<PiecePosition> GetPiecesList()
    {
        LinkedList<PiecePosition> ret = new LinkedList<>();
        for(int x = 0; x < size; x++)
            for(int y = 0; y < size; y++)
            {
                if(board[x][y] != null)
                {
                    ret.add(new PiecePosition(x, y));
                }
            }
        
        return ret;
    }
    public LinkedList<PiecePosition> GetColourPiecesList(Piece.Colour c)
    {
        LinkedList<PiecePosition> ret = new LinkedList<>();
        
        for(int x = 0; x < size; x++)
            for(int y = 0; y < size; y++)
            {
                if(board[x][y] != null)
                {
                    if(board[x][y].colour == c)
                        ret.add(new PiecePosition(x, y));
                }
            }
        
        return ret;
    }
    public LinkedList<Move> GetColourMoves(Piece.Colour c)
    {
        LinkedList<Move> ret = new LinkedList<>();
        
            
        if(c == Piece.Colour.Black)
        {
            LinkedList<PiecePosition> blacks = GetColourPiecesList(c);
            for(PiecePosition curr: blacks)
            {
                ret.addAll(Simulator.GetMoves(curr, (GameState)this.DeepCopy()));
            }
        }
        else if(c == Piece.Colour.White)
        {
            LinkedList<PiecePosition> whites = GetColourPiecesList(c);
            for(PiecePosition curr: whites)
            {
                ret.addAll(Simulator.GetMoves(curr, (GameState)this.DeepCopy()));
            }
        }
        
        return Simulator.GetValidMoves(ret, this);
    }
    public LinkedList<Move> GetPieceMoves(Piece.Colour col, PiecePosition pp)
    {
        LinkedList<Move> ret = new LinkedList<>();
        LinkedList<Move> moves = GetColourMoves(col);
        for(Move m: moves)
        {
            PiecePosition p = m.steps.getFirst();
            if(p.x == pp.x && p.y == pp.y)
                ret.add((Move)m.DeepCopy());
        }
        return ret;
    }
    
    public void ApplyMove(Move move)
    {
        int x = move.steps.get(0).x;
        int y = move.steps.get(0).y;
        Piece p = board[x][y];
        for(int i = 1; i < move.steps.size(); i++)
        {
            PiecePosition pos = move.steps.get(i);
            int dx = (pos.x - x)/Math.abs(pos.x - x);
            int dy = (pos.y - y)/Math.abs(pos.y - y);
            while(x != pos.x && y != pos.y)
            {
                board[x][y] = null;
                x += dx;
                y += dy;
            }
            if(p.colour == Piece.Colour.Black && y == 7 && p.type == Piece.Type.Pawn)
            {
                p.type = Piece.Type.Dame;
            }
            if(p.colour == Piece.Colour.White && y == 0 && p.type == Piece.Type.Pawn)
            {
                p.type = Piece.Type.Dame;
            }
        }
        board[x][y] = p;
    }
    public GameState GetNewGameState(Move move)
    {
        GameState ret = (GameState)this.DeepCopy();
        ret.ApplyMove(move);
        return ret;
    }

    @Override
    public ICopyAndStringable DeepCopy() 
    {
        GameState ret = new GameState();
        
        for(int x = 0; x < size; x++)
            for(int y = 0; y < size; y++)
            {
                if(board[x][y] != null)
                {
                    ret.board[x][y] = (Piece)board[x][y].DeepCopy();
                }
                else
                {
                    ret.board[x][y] = null;
                }
            }
        return ret;
    }
    @Override
    public String ToString() 
    {
        String ret = "[b::";
        
        LinkedList<PiecePosition> pieces = GetPiecesList();
        int pSize = pieces.size();
        for(int i = 0; i < pSize; i++)
        {
            PiecePosition pp = pieces.get(i);
            Piece p = board[pp.x][pp.y];
            String pps = pp.ToString();
            String ps = p.ToString();
            
            ret += pps.substring(0, pps.length() - 1);
            ret += ",";
            ret += ps.substring(1);
        }
        
        ret += "]";
        return ret;
    }
    
}