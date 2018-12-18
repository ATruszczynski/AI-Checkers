package ai.checkers;

import java.awt.AWTEvent;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import javax.swing.*;

/**
 * 
 *
 * @since 2018-12-12, 14:37:57
 * @author Aleksander Truszczyński
 */
public class CheckersGUI extends javax.swing.JFrame {
    static int controlOffset = 15;
    static Dimension buttonDim = new Dimension(80,20);
    Game game;
    
    Timer paintTimer;
    int paintTimerInterval = 20;
    
    
    /** Creates new form CheckersGUI */
    public CheckersGUI() {
        initComponents();
        getThisFukenWindowToLookAsIWant();
        
        game = new Game(this);
        paintTimer = new Timer(paintTimerInterval, (e) -> repaint());
        paintTimer.start();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        boardPanel = new BoardPanel();
        menuPanel = new javax.swing.JPanel();
        startButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        difficultySlider = new javax.swing.JSlider();
        settingsButton = new javax.swing.JButton();
        endGameLabel = new javax.swing.JLabel();
        whiteRadioButton = new javax.swing.JRadioButton();
        blackRadioButton = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1200, 800));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        boardPanel.setBackground(new java.awt.Color(255, 255, 153));
        boardPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        boardPanel.setMinimumSize(new java.awt.Dimension(800, 800));
        boardPanel.setPreferredSize(new java.awt.Dimension(800, 800));
        boardPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                boardPanelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout boardPanelLayout = new javax.swing.GroupLayout(boardPanel);
        boardPanel.setLayout(boardPanelLayout);
        boardPanelLayout.setHorizontalGroup(
            boardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 796, Short.MAX_VALUE)
        );
        boardPanelLayout.setVerticalGroup(
            boardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 796, Short.MAX_VALUE)
        );

        getContentPane().add(boardPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        menuPanel.setBackground(new java.awt.Color(0, 255, 255));

        startButton.setText("Start");
        startButton.setMaximumSize(new java.awt.Dimension(60, 25));
        startButton.setMinimumSize(new java.awt.Dimension(60, 25));
        startButton.setPreferredSize(new java.awt.Dimension(60, 25));
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        resetButton.setText("Reset");
        resetButton.setMaximumSize(new java.awt.Dimension(60, 25));
        resetButton.setMinimumSize(new java.awt.Dimension(60, 25));
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        difficultySlider.setMajorTickSpacing(1);
        difficultySlider.setPaintTicks(true);
        difficultySlider.setMaximum(7);
        difficultySlider.setMinimum(1);
        difficultySlider.setPaintLabels(true);
        difficultySlider.setPaintTicks(true);
        difficultySlider.setValue(1);

        settingsButton.setText("Apply and Reset");
        settingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsButtonActionPerformed(evt);
            }
        });

        endGameLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        whiteRadioButton.setSelected(true);
        whiteRadioButton.setText("White");
        whiteRadioButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                whiteRadioButtonMousePressed(evt);
            }
        });

        blackRadioButton.setText("Black");
        blackRadioButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                blackRadioButtonMousePressed(evt);
            }
        });

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(menuPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(menuPanelLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(menuPanelLayout.createSequentialGroup()
                                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(menuPanelLayout.createSequentialGroup()
                                        .addComponent(whiteRadioButton)
                                        .addGap(56, 56, 56)
                                        .addComponent(blackRadioButton))
                                    .addComponent(settingsButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(endGameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(difficultySlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(148, 148, 148)
                .addComponent(difficultySlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(endGameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(menuPanelLayout.createSequentialGroup()
                        .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(whiteRadioButton)
                            .addComponent(blackRadioButton))
                        .addGap(28, 28, 28)
                        .addComponent(settingsButton)))
                .addContainerGap(455, Short.MAX_VALUE))
        );

        getContentPane().add(menuPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 0, 400, 800));

        setSize(new java.awt.Dimension(1219, 842));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void getThisFukenWindowToLookAsIWant()
    {
        HideEndGameLabel();
        this.setResizable(false);
        this.setSize(new Dimension(1200, 830));
        Dimension d = new Dimension(800,800);
        boardPanel.setSize(d);
        
//        difficultySlider.setPaintTicks(rootPaneCheckingEnabled);
//        difficultySlider.setMajorTickSpacing(1);
        
//        startButton.setLocation(new Point(30,80000));
//        startButton.setSize(buttonDim);
        
        int i = 0;
        repaint();
    }
    
    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        
    }//GEN-LAST:event_formMousePressed

    private void boardPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boardPanelMousePressed
        game.Player_Turn(evt);
        game.AI_Turn();
    }//GEN-LAST:event_boardPanelMousePressed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        game.StartGame();
    }//GEN-LAST:event_startButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        game.RestartGame();
    }//GEN-LAST:event_resetButtonActionPerformed

    private void settingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsButtonActionPerformed
        game.ChangeSettings(difficultySlider.getValue(), GetSelectedColour());
        game.RestartGame();
    }//GEN-LAST:event_settingsButtonActionPerformed

    private void whiteRadioButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_whiteRadioButtonMousePressed
        blackRadioButton.setSelected(false);
    }//GEN-LAST:event_whiteRadioButtonMousePressed

    private void blackRadioButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_blackRadioButtonMousePressed
        
        whiteRadioButton.setSelected(false);
    }//GEN-LAST:event_blackRadioButtonMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CheckersGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CheckersGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CheckersGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CheckersGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CheckersGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton blackRadioButton;
    private javax.swing.JPanel boardPanel;
    private javax.swing.JSlider difficultySlider;
    private javax.swing.JLabel endGameLabel;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton settingsButton;
    private javax.swing.JButton startButton;
    private javax.swing.JRadioButton whiteRadioButton;
    // End of variables declaration//GEN-END:variables

    public int GetBoardPanelWidth()
    {
        return this.boardPanel.getWidth();
    }
    
    public int GetBoardPanelHeight()
    {
        return this.boardPanel.getHeight();
    }
    public void HideEndGameLabel()
    {
        endGameLabel.setVisible(false);
    }
    private Piece.Colour GetSelectedColour()
    {
        if(whiteRadioButton.isSelected())
            return Piece.Colour.White;
        else
            return Piece.Colour.Black;
    }
    class BoardPanel extends JPanel
    {
        Color blacks = new Color(100,60,24);
        Color whites = new Color(220,200,140);
        Color chosen = new Color(77, 219, 255);
        Color line = Color.RED;
        float lineThiccness = 2;
        int linePointRadius = 10;
        public final static int pieceDiameter = 80;
        public final static int moveAvailableDiam = 90;
        
        public BoardPanel()
        {
            super();
        }
        
        @Override
        public void paintComponent(Graphics g)
        {
            //setSize(new Dimension(800,800));
            DrawBoard(g);
            DrawMoveFields(game.validMoves, g);
            DrawMoveAvailablePieces(game.adviceMoves,g);
            DrawPieces(g);
            DrawMoveLines(game.validMoves, g);
        }
        
        private void DrawBoard(Graphics g)
        {
            int w = getWidth();
            int h = getHeight();
            int fieldW = w/8;
            int fieldH = h/8;
            
            for(int x = 0; x < 8; x++)
            {
                for(int y = 0; y < 8; y++)
                {
                    boolean xx = x%2 == 0;
                    boolean yy = y%2 == 0;
                    
                    if(xx == yy)
                    {
                        DrawField(x,y,Color.WHITE,g);
                    }
                    else
                    {
                        DrawField(x,y,Color.BLACK,g);
                    }
                    g.fillRect(x*fieldW, y*fieldH, fieldW, fieldH);
                }
            }
        }
        private void DrawPieces(Graphics g)
        {
            LinkedList<PiecePosition> pointPieces = game.gs.GetPiecesList();
            int w = getWidth();
            int h = getHeight();
            int fieldW = w/8;
            int fieldH = h/8;
            for(PiecePosition point: pointPieces)
            {
                Piece piece = game.gs.board[point.x][point.y];
                if(piece.colour == Piece.Colour.White)
                    g.setColor(whites);
                else
                    g.setColor(blacks);
                int x = point.x * fieldW + (fieldW-pieceDiameter)/2;
                int y = point.y * fieldH + (fieldH-pieceDiameter)/2;
                g.fillOval(x, y, pieceDiameter, pieceDiameter);
                if(piece.type == Piece.Type.Dame)
                {
                    int dameDiameter = (int) (pieceDiameter*0.75);
                    x = point.x * fieldW + (fieldW-dameDiameter)/2;
                    y = point.y * fieldH + (fieldH-dameDiameter)/2;
                    g.setColor(Color.BLACK);
                    g.drawOval(x, y, dameDiameter, dameDiameter);
                }
            }
        }
        private void DrawField(int x, int y, Color c, Graphics g)
        {
            int w = getWidth();
            int h = getHeight();
            int fieldW = w / 8;
            int fieldH = h / 8;
            
            g.setColor(c);
            g.fillRect(x * fieldW, y * fieldH, fieldW, fieldH);
                
        }
        private void DrawLine(int x1, int y1, int x2, int y2, Color c, Graphics g)
        {
            int w = getWidth();
            int h = getHeight();
            int fieldW = w / 8;
            int fieldH = h / 8;
            
            g.setColor(c);
            ((Graphics2D)g).setStroke(new BasicStroke(lineThiccness));
            g.drawLine(x1*fieldW + fieldW/2, y1*fieldH + fieldH/2, x2*fieldW  + fieldW/2, y2*fieldH + fieldH/2);
            ((Graphics2D)g).setStroke(new BasicStroke(1));
        }
        private void DrawMoveFields(LinkedList<Move> avMoves, Graphics g)
        {
            for(Move m: avMoves)
            {
                int xb = m.steps.get(0).x;
                int yb = m.steps.get(0).y;
                int xe = m.steps.getLast().x;
                int ye = m.steps.getLast().y;
                
                DrawField(xb,yb,chosen,g);
                DrawField(xe,ye,chosen,g);
                
                
            }
        }
        private void DrawDot(int x, int y, Color c, Graphics g)
        {
            int w = getWidth();
            int h = getHeight();
            int fieldW = w / 8;
            int fieldH = h / 8;
            
            int xp = x * fieldW + (fieldW-linePointRadius)/2;
            int yp = y * fieldH + (fieldH-linePointRadius)/2;
            
            g.setColor(c);
            g.fillOval(xp, yp, linePointRadius, linePointRadius);
        }
        private void DrawMoveLines(LinkedList<Move> avMoves, Graphics g)
        {
            for(Move m: avMoves)
            {
                for (int i = 0; i < m.steps.size() - 1; i++) 
                {
                    PiecePosition p1 = m.steps.get(i);
                    PiecePosition p2 = m.steps.get(i + 1);
                    
                    DrawDot(p1.x, p1.y, line, g);
                    DrawLine(p1.x, p1.y, p2.x, p2.y, line, g);
                    DrawDot(p2.x, p2.y, line, g);
                }
            }
        }
        private void DrawMoveAvailablePieces(LinkedList<PiecePosition> piecesWithMove, Graphics g)
        {
            int w = getWidth();
            int h = getHeight();
            int fieldW = w/8;
            int fieldH = h/8;
            g.setColor(chosen);
            for(PiecePosition p: piecesWithMove)
            {
                int x = p.x * fieldW + (fieldW-moveAvailableDiam)/2;
                int y = p.y * fieldH + (fieldH-moveAvailableDiam)/2;
                g.fillOval(x, y, moveAvailableDiam, moveAvailableDiam);
            }
        }
    }
    
    public void SetEndGameLabelText(String txt)
    {
        endGameLabel.setText(txt);
        endGameLabel.setVisible(true);
    }
}
