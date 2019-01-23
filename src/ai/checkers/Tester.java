package ai.checkers;

import java.util.LinkedList;

/**
 *
 *
 * @since 2018-12-14, 16:23:09
 * @author Aleksander Truszczyński
 */
public class Tester 
{
    public static boolean NullMove_Test()
    {
        GameState gs = new GameState();
        
//        gs.board[0][1] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
//        gs.board[1][0] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
//        gs.board[1][2] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
//        gs.board[2][1] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
//        gs.board[3][0] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
//        gs.board[3][2] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
//        gs.board[4][1] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
        gs.board[5][0] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
        gs.board[5][2] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
        gs.board[6][1] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
        gs.board[7][0] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
        gs.board[7][2] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
        
//        gs.board[0][5] = new Piece(Piece.Colour.White, Piece.Type.Pawn);
//        gs.board[0][7] = new Piece(Piece.Colour.White, Piece.Type.Pawn);
//        gs.board[1][6] = new Piece(Piece.Colour.White, Piece.Type.Pawn);
//        gs.board[2][5] = new Piece(Piece.Colour.White, Piece.Type.Pawn);
//        gs.board[2][7] = new Piece(Piece.Colour.White, Piece.Type.Pawn);
//        gs.board[3][6] = new Piece(Piece.Colour.White, Piece.Type.Pawn);
        gs.board[4][7] = new Piece(Piece.Colour.White, Piece.Type.Pawn);
        gs.board[5][4] = new Piece(Piece.Colour.White, Piece.Type.Pawn);
        gs.board[5][6] = new Piece(Piece.Colour.White, Piece.Type.Pawn);
        gs.board[6][5] = new Piece(Piece.Colour.White, Piece.Type.Pawn);
        gs.board[6][7] = new Piece(Piece.Colour.White, Piece.Type.Pawn);
        gs.board[7][6] = new Piece(Piece.Colour.White, Piece.Type.Pawn);
        
        Move m = Simulator.GetOptimalMove(3, Piece.Colour.Black, gs);
        String ms = m.ToString();
        
        return false;
    }
    public static boolean OptimalMove_Test1()
    {
        GameState gs = new GameState();
        gs.board[1][0] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
        gs.board[1][2] = new Piece(Piece.Colour.White, Piece.Type.Pawn);
        gs.board[4][3] = new Piece(Piece.Colour.White, Piece.Type.Pawn);
        gs.board[5][4] = new Piece(Piece.Colour.White, Piece.Type.Pawn);
        gs.board[7][2] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
        
        Move m = Simulator.GetOptimalMove(6, Piece.Colour.White, gs);
        String ms = m.ToString();
        
        return false;
    }
    public static void WhiteMoves_Test()
    {
        GameState gs = new GameState();
        gs.board[0][0] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
        gs.board[1][3] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
        gs.board[2][0] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
        //gs.board[3][4] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
        gs.board[3][6] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
        gs.board[6][4] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
        
        gs.board[0][4] = new Piece(Piece.Colour.White, Piece.Type.Pawn);
        gs.board[1][1] = new Piece(Piece.Colour.White, Piece.Type.Pawn);
        gs.board[1][6] = new Piece(Piece.Colour.White, Piece.Type.Pawn);
        gs.board[2][7] = new Piece(Piece.Colour.White, Piece.Type.Pawn);
        gs.board[5][1] = new Piece(Piece.Colour.White, Piece.Type.Pawn);
        gs.board[5][5] = new Piece(Piece.Colour.White, Piece.Type.Pawn);
        
        
        LinkedList<Move> moves = gs.GetColourMoves(Piece.Colour.White);
        //////////////////////////////////////////////////////////////////////////////////
        LinkedList<String> movesss = new LinkedList<>();
        for(Move m: moves)
            movesss.add(m.ToString()); 
        /////////////////////////////////////////////////////////////////////////////////
        return;
    }
    public static boolean DameAllMoves_Test()
    {
        GameState gs = new GameState();
        PiecePosition curr = new PiecePosition(4, 7);
        gs.board[curr.x][curr.y] = new Piece(Piece.Colour.White, Piece.Type.Dame);
        
        gs.board[2][5] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
        gs.board[3][2] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
        
        LinkedList<Move> norm = Simulator.CheckNormalMoves(curr, gs);
        LinkedList<Move> caps = Simulator.CheckCaptureMoves(curr, gs);
        LinkedList<Move> res = Simulator.GetMoves(curr, gs);
        
        LinkedList<String> normss = new LinkedList<>();
        for(Move m: norm)
            normss.add(m.ToString());        
        LinkedList<String> capsss = new LinkedList<>();
        for(Move m: caps)
            capsss.add(m.ToString());        
        LinkedList<String> ress = new LinkedList<>();
        for(Move m: res)
            ress.add(m.ToString());
        
        String brd = gs.ToString();
        return false;
    }
    public static boolean PawnAllMoves_Test()
    {
        GameState gs = new GameState();
        PiecePosition curr = new PiecePosition(4, 3);
        gs.board[curr.x][curr.y] = new Piece(Piece.Colour.White, Piece.Type.Pawn);
        
        gs.board[2][1] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
        gs.board[6][5] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
        
        LinkedList<Move> res = Simulator.GetMoves(curr, gs);
        
        LinkedList<String> ress = new LinkedList<>();
        for(Move m: res)
            ress.add(m.ToString());
        
        return false;
    }
    public static boolean PawnCapture_Test()
    {
        GameState gs = new GameState();
        PiecePosition curr = new PiecePosition(5, 4);
        gs.board[curr.x][curr.y] = new Piece(Piece.Colour.White, Piece.Type.Pawn);
        gs.board[7][2] = new Piece(Piece.Colour.White, Piece.Type.Pawn);
        
        gs.board[0][3] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
        gs.board[2][3] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
        gs.board[2][5] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
        gs.board[4][1] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
        gs.board[4][3] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
        gs.board[4][5] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
        gs.board[6][1] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
        gs.board[6][3] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
        gs.board[6][5] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
        gs.board[6][7] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
        
        LinkedList<Move> res = Simulator.CheckCaptureMoves(curr, gs);
        
        LinkedList<String> ress = new LinkedList<>();
        for(Move m: res)
            ress.add(m.ToString());
        
        
        return false;
    }
    public static boolean DameCapture_Test1()
    {
        GameState gs = new GameState();
        PiecePosition curr = new PiecePosition(0, 7);
        gs.board[curr.x][curr.y] = new Piece(Piece.Colour.White, Piece.Type.Dame);
        
        gs.board[0][1] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
        gs.board[2][5] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
        gs.board[4][1] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
        gs.board[4][5] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
        gs.board[6][5] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
        
        LinkedList<Move> res = Simulator.CheckCaptureMoves(curr, gs);
        
        LinkedList<String> ress = new LinkedList<>();
        for(Move m: res)
            ress.add(m.ToString());
        
        return false;
    }
    public static boolean DameCapture_Test2()
    {
        GameState gs = new GameState();
        PiecePosition curr = new PiecePosition(4, 3);
        gs.board[curr.x][curr.y] = new Piece(Piece.Colour.White, Piece.Type.Dame);
        
        gs.board[2][1] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
        gs.board[6][5] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);
        
        LinkedList<Move> res = Simulator.CheckCaptureMoves(curr, gs);
        
        LinkedList<String> ress = new LinkedList<>();
        for(Move m: res)
            ress.add(m.ToString());
        
        return false;
    }
    
    private static Move GenerateMove(int... movements) 
    {
        Move ret = new Move();

        for (int i = 0; i < movements.length; i += 2)
            ret.AddStep(new PiecePosition(movements[i], movements[i + 1]));

        return ret;
    }
    private static GameState GenerateGameState(Object... arguments) 
    {
        GameState ret = new GameState();

        for (int i = 0; i < arguments.length; i += 3) {
            int x = (int) arguments[i];
            int y = (int) arguments[i + 1];
            String ct = (String) arguments[i + 2];
            String c = ct.substring(0, 1);
            String t = ct.substring(1, 2);
            ret.board[x][y] = new Piece(("b".equals(c)) ? Piece.Colour.Black : Piece.Colour.White, ("p".equals(t)) ? Piece.Type.Pawn : Piece.Type.Dame);
        }

        return ret;
    }
    
    public static boolean GenerateMove_Test1() 
    {
        PiecePosition start = new PiecePosition(2, 7);
        PiecePosition end = new PiecePosition(1, 6);

        Move m = new Move(start, end);
        Move test = GenerateMove(2, 7, 1, 6);

        String ms = m.ToString();
        String tests = test.ToString();

        return ms.equals(tests);
    }
    public static boolean GenerateMove_Test2() 
    {
        PiecePosition start = new PiecePosition(1, 0);
        PiecePosition end = new PiecePosition(7, 6);

        Move m = new Move(start, end);
        Move test = GenerateMove(1, 0, 7, 6);

        String ms = m.ToString();
        String tests = test.ToString();

        return ms.equals(tests);
    }
    public static boolean GenerateMove_Test3() 
    {
        PiecePosition start = new PiecePosition(2, 7);
        Move m = new Move(start);

        m.AddStep(new PiecePosition(0, 5));
        m.AddStep(new PiecePosition(5, 0));
        m.AddStep(new PiecePosition(7, 2));

        Move test = GenerateMove(2, 7, 0, 5, 5, 0, 7, 2);

        String ms = m.ToString();
        String tests = test.ToString();

        return ms.equals(tests);
    }
    public static boolean GenerateGameState_Test1() 
    {
        GameState gs = new GameState();
        gs.board[2][7] = new Piece(Piece.Colour.White, Piece.Type.Dame);
        gs.board[1][5] = new Piece(Piece.Colour.White, Piece.Type.Pawn);
        gs.board[3][3] = new Piece(Piece.Colour.Black, Piece.Type.Dame);
        gs.board[5][3] = new Piece(Piece.Colour.Black, Piece.Type.Pawn);

        GameState test = GenerateGameState(2, 7, "wd", 1, 5, "wp", 3, 3, "bd",5,3,"bp");

        String ans = gs.ToString();
        String tests = test.ToString();

        return ans.equals(tests);
    }
    public static boolean DameNormalMovesTest()
    {
        PiecePosition pp = new PiecePosition(4, 3);
        GameState gs = new GameState();
        gs.board[pp.x][pp.y] = new Piece(Piece.Colour.Black,Piece.Type.Dame);
        gs.board[3][2] = new Piece(Piece.Colour.Black,Piece.Type.Dame);
        gs.board[6][5] = new Piece(Piece.Colour.Black,Piece.Type.Dame);
        
        LinkedList<Move> res = Simulator.GetMoves(pp, gs);
        
        LinkedList<String> ress = new LinkedList<>();
        for(Move m: res)
            ress.add(m.ToString());
        
        return false;
    }
    public static boolean PawnNormalMovesTest()
    {
        PiecePosition pp = new PiecePosition(4, 3);
        GameState gs = new GameState();
        gs.board[pp.x][pp.y] = new Piece(Piece.Colour.White,Piece.Type.Pawn);
        gs.board[3][2] = new Piece(Piece.Colour.Black,Piece.Type.Dame);
        gs.board[6][5] = new Piece(Piece.Colour.Black,Piece.Type.Dame);
        
        LinkedList<Move> res = Simulator.GetMoves(pp, gs);
        
        LinkedList<String> ress = new LinkedList<>();
        for(Move m: res)
            ress.add(m.ToString());
        
        return false;
    }
    public static boolean EvaluateMoveTest()
    {
        GameState gs = new GameState();
        
        gs.board[5][4] = new Piece(Piece.Colour.White, Piece.Type.Pawn);
        gs.board[4][5] = new Piece(Piece.Colour.Black, Piece.Type.Dame);
        
        Move m = new Move(new PiecePosition(5, 4), new PiecePosition(3, 6));
        
        Simulator.EvaluateMove(m, gs);
        
        String ms = m.ToString();
        
        return false;
    }
    public static LinkedList<String> MovesToStrings (LinkedList<Move> moves)
    {
        LinkedList<String> movesss = new LinkedList<>();
        for(Move m: moves)
            movesss.add(m.ToString()); 
        return movesss;
    }
}
