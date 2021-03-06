package connectfour.ui.gui.swing.widgets;

import javax.swing.*;
import java.awt.*;

/**
 * @author: Stefano Di Martino
 * @created: May 27, 2012
 */

@SuppressWarnings("serial")
public class ArrowCell extends JPanel {
    public static final int ARROW_WIDTH = 10;
    public static final int X_MIDDLE_LEFT = 34;
    public static final int ARROW_LENGTH = 40;
    public static final int Y_TOP = 20;


    private final int column;
    private boolean drawArrow;
    
    // x coordinates
    private final int arrowWidth = ARROW_WIDTH;
    private final int xMiddleLeft = X_MIDDLE_LEFT;
    private final int xMiddleRight = xMiddleLeft + arrowWidth;

    // y coordinates
    private final int arrowLength = ARROW_LENGTH;
    private final int yTop = Y_TOP;
    private Polygon arrow = new Polygon();
    
    public ArrowCell(final int column) {
        this.setArrowBackAndForegroundColor();
        this.drawArrow = false;
        this.column = column;
        this.createArrow();
    }
    
    private void setArrowBackAndForegroundColor() {
        this.setForeground(Color.RED);
        this.setBackground(Color.WHITE);
    }
    
    private void createArrow() {
        arrow.addPoint(this.xMiddleLeft, this.yTop);
        int yMiddle = yTop + arrowLength / 2;
        arrow.addPoint(this.xMiddleLeft, yMiddle);
        int xLeft = xMiddleLeft - arrowWidth;
        arrow.addPoint(xLeft, yMiddle);
        int yArrowPoint = yTop + arrowLength;
        int xArrowPoint = xMiddleLeft + ((xMiddleRight - xMiddleLeft) / 2);
        arrow.addPoint(xArrowPoint, yArrowPoint);
        int xRight = xMiddleRight + arrowWidth;
        arrow.addPoint(xRight, yMiddle);
        arrow.addPoint(this.xMiddleRight, yMiddle);
        arrow.addPoint(this.xMiddleRight, this.yTop);
    }
    
    public void showArrow(boolean show) {
        this.drawArrow = show;
    }
    
    public int getColumn() {
        return this.column;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // paints background
        
        if (this.drawArrow) {
            Graphics2D g2 = (Graphics2D) g;
            
            // Activate Anti-Aliasing!
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            g2.fillPolygon(arrow);
        }
    }
}