package ai.checkers;

import java.util.LinkedList;
import javafx.util.Pair;

/**
 * 
 *
 * @since 2018-12-14, 08:08:48
 * @author Aleksander Truszczyński
 */
public class Simulator 
{
    public static LinkedList<Move> GetMoves(PiecePosition curr, GameState gs)
    {
        LinkedList<Move> ret = new LinkedList<>();
        
        LinkedList<Move> captures = CheckCaptureMoves(curr, gs);
        ret.addAll(captures);
        if(ret.isEmpty())
        {
            LinkedList<Move> moves = CheckNormalMoves(curr, gs);
            ret.addAll(moves);
        }
        
        return GetValidMoves(ret, gs);
    }
    public static LinkedList<Move> CheckNormalMoves(PiecePosition curr, GameState gs)
    {
        LinkedList<Move> ret = new LinkedList<>();
        Piece piece = gs.board[curr.x][curr.y];
        LinkedList<PiecePosition> pl = new LinkedList<>();
        
        if(piece.type == Piece.Type.Pawn)
        {
            if(piece.colour == Piece.Colour.Black)
            {
                int nextY = curr.y + 1;
                if(curr.x > 0 && nextY < 8)//NE
                {
                    int nextX = curr.x - 1;
                    if(gs.board[nextX][nextY] == null)
                        pl.add(new PiecePosition(nextX, nextY));
                }
                if(curr.x < 7 && nextY < 8)//NW
                {
                    int nextX = curr.x + 1;
                    if(gs.board[nextX][nextY] == null)
                        pl.add(new PiecePosition(nextX, nextY));
                }
            }
            else if(piece.colour == Piece.Colour.White)
            {
                int nextY = curr.y - 1;
                if(curr.x > 0 && nextY >= 0)//SE
                {
                    int nextX = curr.x - 1;
                    if(gs.board[nextX][nextY] == null)
                        pl.add(new PiecePosition(nextX, nextY));
                }
                if(curr.x < 7 && nextY >= 0)//SW
                {
                    int nextX = curr.x + 1;
                    if(gs.board[nextX][nextY] == null)
                        pl.add(new PiecePosition(nextX, nextY));
                }
            }
        }
        else if(piece.type == Piece.Type.Dame)
        {
            pl.addAll(GetDiagonalNormalMovesPoses(curr, gs, Direction.NE));
            pl.addAll(GetDiagonalNormalMovesPoses(curr, gs, Direction.NW));
            pl.addAll(GetDiagonalNormalMovesPoses(curr, gs, Direction.SW));
            pl.addAll(GetDiagonalNormalMovesPoses(curr, gs, Direction.SE));
        }
        
        for (PiecePosition pp : pl)
            ret.add(new Move(curr, pp));
        return ret;
    }
    public static LinkedList<Move> CheckCaptureMoves(PiecePosition curr, GameState gs)
    {
        LinkedList<Move> ret = new LinkedList<>();
        
        ret.addAll(CheckCaptureMovesRec(curr, gs, Direction.NE));
        ret.addAll(CheckCaptureMovesRec(curr, gs, Direction.NW));
        ret.addAll(CheckCaptureMovesRec(curr, gs, Direction.SW));
        ret.addAll(CheckCaptureMovesRec(curr, gs, Direction.SE));
        
//        LinkedList<String> ress = new LinkedList<>();
//        for(Move m: ret)
//            ress.add(m.ToString());
        
        return ret;
    }
    private static LinkedList<Move> CheckCaptureMovesRec(PiecePosition curr, GameState gs, Direction d)
    {
        LinkedList<Move> ret = new LinkedList<>();
        
        LinkedList<Move> currentLevelMoves = new LinkedList<Move>();
        
        Piece p = gs.board[curr.x][curr.y];
        
        LinkedList<PiecePosition> capturingSteps = GetDiagonalCaptureSteps(curr, gs, d);
        
        for (PiecePosition pos : capturingSteps)
            currentLevelMoves.add(new Move(curr, pos));
        
        for(Move m: currentLevelMoves)
        {
            ret.add(m);
            PiecePosition nextPos = m.steps.getLast();
            GameState nextGS = gs.GetNewGameState(m);
            LinkedList<Move> nextLevelMoves = new LinkedList<>();
            nextLevelMoves.addAll(CheckCaptureMovesRec(nextPos, nextGS, Direction.NE));
            nextLevelMoves.addAll(CheckCaptureMovesRec(nextPos, nextGS, Direction.NW));
            nextLevelMoves.addAll(CheckCaptureMovesRec(nextPos, nextGS, Direction.SW));
            nextLevelMoves.addAll(CheckCaptureMovesRec(nextPos, nextGS, Direction.SE));
            
            for(Move next: nextLevelMoves)
            {
                ret.add(m.GetConcatenatedMove(next));
            }
            
        }
        
        return ret;
    }
    public static void EvaluateMove(Move move, GameState gs)
    {
        Piece[][] board = ((GameState)gs.DeepCopy()).board;
        int whiteScore = 0;
        int capturedCount = 0;
        int x = move.steps.get(0).x;
        int y = move.steps.get(0).y;
        Piece p = (Piece)board[x][y].DeepCopy();
        board[x][y] = null;
        for(int i = 1; i < move.steps.size(); i++)
        {
            PiecePosition pos = move.steps.get(i);
            int dx = (pos.x - x)/Math.abs(pos.x - x);
            int dy = (pos.y - y)/Math.abs(pos.y - y);
            while(x != pos.x && y != pos.y)
            {
                x += dx;
                y += dy;
                
                //if there is anthing on the move's path, it's a captured piece
                if(board[x][y] != null) 
                {
                    capturedCount++;
                    Piece captured = board[x][y];
                    board[x][y] = null;
                    int valueForWhite = 0;
                    int capturedValue = 0;
                    if (p.colour == Piece.Colour.Black) 
                    {
                        valueForWhite = -1;
                    } 
                    else 
                    {
                        valueForWhite = 1;
                    }
                    
                    if(captured.type == Piece.Type.Pawn)
                    {
                        capturedValue = Scoring.CapturingPawn;
                    }
                    else
                    {
                        capturedValue = Scoring.CapturingDame;
                    }
                    
                    whiteScore += (valueForWhite * capturedValue);
                }
            }
            //black dame was created
            if(p.colour == Piece.Colour.Black && y == 7 && p.type == Piece.Type.Pawn)
            {
                whiteScore -= Scoring.CreatingDame;
            }
            //white dame was created
            if(p.colour == Piece.Colour.White && y == 0 && p.type == Piece.Type.Pawn)
            {
                whiteScore += Scoring.CreatingDame;
            }
        }
        int dy = move.steps.get(move.steps.size() - 1).y - move.steps.get(0).y;
        
        if(p.type == Piece.Type.Pawn)
            whiteScore -= dy*Scoring.Distance;
        
        move.capturedCount = capturedCount;
        move.score = whiteScore;
    }
    public static LinkedList<PiecePosition> GetDiagonalNormalMovesPoses(PiecePosition curr, GameState gs, Direction dir) 
    {
        int dx = dir.dx;
        int dy = dir.dy;
        LinkedList<PiecePosition> ret = new LinkedList<>();
        
        int x = curr.x;
        int y = curr.y;
        
        x += dx;
        y += dy;
        while(x >= 0 && x < 8 && y >= 0 && y < 8)
        {
            if(gs.board[x][y] == null)
                ret.add(new PiecePosition(x, y));
            else
                break;
            x += dx;
            y += dy;
        }
        return ret;
    }
    public static LinkedList<PiecePosition> GetDiagonalCaptureSteps(PiecePosition curr, GameState gs, Direction dir)
    {
        LinkedList<PiecePosition> ret = new LinkedList<>();
        
        int x = curr.x;
        int y = curr.y;
        int dx = dir.dx;
        int dy = dir.dy;
        Piece p = gs.board[x][y];
        
        if(p.type == Piece.Type.Pawn)
        {
            int capX = x + dx;
            int capY = y + dy;
            int endX = capX + dx;
            int endY = capY + dy;
            if ((capX >= 0 && capX < 8 && capY >= 0 && capY < 8) && gs.board[capX][capY] != null && gs.board[capX][capY].colour != p.colour && (endX >= 0 && endX < 8 && endY >= 0 && endY < 8) && gs.board[endX][endY] == null) 
            {
                ret.add(new PiecePosition(endX, endY));
            }
        }
        else if(p.type == Piece.Type.Dame)
        {
            int capX = x + dx;
            int capY = y + dy;
            while((capX >= 0 && capX < 8 && capY >= 0 && capY < 8) && gs.board[capX][capY] == null)
            {
                capX += dx;
                capY += dy;
            }
            if((capX >= 0 && capX < 8 && capY >= 0 && capY < 8) && gs.board[capX][capY].colour != p.colour)
            {
                int endX = capX + dx;
                int endY = capY + dy;
                while((endX >= 0 && endX < 8 && endY >= 0 && endY < 8) && gs.board[endX][endY] == null)
                {
                    ret.add(new PiecePosition(endX, endY));
                    endX += dx;
                    endY += dy;
                }
            }
        }
        
        return ret;
    }
    public static LinkedList<Move> GetValidMoves (LinkedList<Move> moves, GameState gs)
    {
        LinkedList<Move> candidateMoves = new LinkedList<>();
        LinkedList<Move> ret = new LinkedList<>();
        
        int maxCaptures = 0;
        for(Move m: moves)
        {
            if(m.Length() == 5)
            {
                int i = 0;
            }
            EvaluateMove(m, gs);
            if(m.capturedCount >= maxCaptures)
            {
                candidateMoves.add(m);
                if(m.capturedCount > maxCaptures)
                    maxCaptures = m.capturedCount;
            }
        }
        
        for(Move m: candidateMoves)
        {
            if(m.capturedCount == maxCaptures)
            {
                ret.add(m);
            }
        }
        
        return ret;
    }
    
    
    public static Move GetOptimalMove(int depth, Piece.Colour turn, GameState gs)
    {
        LinkedList<Move> moves = gs.GetColourMoves(turn);
        if(moves.isEmpty())
            return null;
        
        Move minMaxMove = null;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        
        
        //////////////////////////////////////////////////////////////////////////////////
        LinkedList<String> movesss = Tester.MovesToStrings(moves);
        /////////////////////////////////////////////////////////////////////////////////
        
        if(turn == Piece.Colour.Black)
        {
            int min = Integer.MAX_VALUE;
            for(Move m: moves)
            {
                //////////////////////////////////////////////////////////////////////////////////
                String ms = m.ToString();
                int i = 0;
                if(m.steps.getFirst().x == 7)
                    i = 0;
                int newMin = AlphaBeta(depth - 1, m, turn.Negate(), gs.GetNewGameState(m), alpha, beta);
                if (newMin <= min) {
                    min = newMin;
                    minMaxMove = m;
                }
            }
        }
        else if(turn == Piece.Colour.White)
        {
            for(Move m: moves)
            {
                //////////////////////////////////////////////////////////////////////////////////
                String ms = m.ToString();
                /////////////////////////////////////////////////////////////////////////////////
                int newAlpha = AlphaBeta(depth - 1, m, turn.Negate(), gs.GetNewGameState(m), alpha, beta);
                if(newAlpha >= alpha)
                {
                    alpha = newAlpha;
                    minMaxMove = m;
                }
            }
        }
        
        return minMaxMove;
    }
    public static int AlphaBeta(int depth, Move cumuMove, Piece.Colour turn, GameState gs, int alpha, int beta)
    {
        if(depth == 0)
        {
            return cumuMove.score;
        }
        
        LinkedList<Move> moves = gs.GetColourMoves(turn);
        
        if(moves.isEmpty())
        {
            if(turn == Piece.Colour.Black)
            {
                return Scoring.WhiteVictory;
            }
            else if(turn == Piece.Colour.White)
            {
                return Scoring.BlackVictory;
            }
        }
                
        
        //////////////////////////////////////////////////////////////////////////////////
        LinkedList<String> movesss = Tester.MovesToStrings(moves);
        /////////////////////////////////////////////////////////////////////////////////
        
        if(turn == Piece.Colour.Black)
        {
            for(Move m: moves)
            {
                //////////////////////////////////////////////////////////////////////////////////
                String ms = m.ToString();
                /////////////////////////////////////////////////////////////////////////////////
                int newBeta = AlphaBeta(depth - 1, cumuMove.GetConcatenatedMove(m), turn.Negate(), gs.GetNewGameState(m), alpha, beta);
                if(newBeta < beta)
                {
                    beta = newBeta;
                }
                if(alpha >= beta)
                {
                    break;
                }
            }
            return beta;
        }
        else if(turn == Piece.Colour.White)
        {
            for(Move m: moves)
            {
                //////////////////////////////////////////////////////////////////////////////////
                String ms = m.ToString();
                /////////////////////////////////////////////////////////////////////////////////
                int newAlpha = AlphaBeta(depth - 1, cumuMove.GetConcatenatedMove(m), turn.Negate(), gs.GetNewGameState(m), alpha, beta);
                if(newAlpha > alpha)
                {
                    alpha = newAlpha;
                }
                if(alpha >= beta)
                {
                    break;
                }
            }
            return alpha;
        }
        
        return -666666;
    }
}