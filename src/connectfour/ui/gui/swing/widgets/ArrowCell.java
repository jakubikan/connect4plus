package connectfour.ui.gui.swing.widgets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;

import javax.swing.JPanel;

/**
 * @author: Stefano Di Martino
 * @created: May 27, 2012
 */

@SuppressWarnings("serial")
public class ArrowCell extends JPanel {
    private final int column;
    private boolean drawArrow;

    private final Polygon arrow = new Polygon();
    
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
        int yTop = 20;
        int xMiddleLeft = 34;
        arrow.addPoint(xMiddleLeft, yTop);
        int arrowLength = 40;
        int yMiddle = yTop + arrowLength / 2;
        arrow.addPoint(xMiddleLeft, yMiddle);
        int arrowWidth = 10;
        int xLeft = xMiddleLeft - arrowWidth;
        arrow.addPoint(xLeft, yMiddle);
        int xMiddleRight = xMiddleLeft + arrowWidth;
        int xArrowPoint = xMiddleLeft + ((xMiddleRight - xMiddleLeft) / 2);
        int yArrowPoint = yTop + arrowLength;
        arrow.addPoint(xArrowPoint, yArrowPoint);
        int xRight = xMiddleRight + arrowWidth;
        arrow.addPoint(xRight, yMiddle);
        arrow.addPoint(xMiddleRight, yMiddle);
        arrow.addPoint(xMiddleRight, yTop);
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