package connect4.ui.gui.swing.widgets;

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
    
    // x coordinates
    private final int arrowWidth = 10;
    private final int xMiddleLeft = 34;
    private final int xLeft = xMiddleLeft - arrowWidth;
    private final int xMiddleRight = xMiddleLeft + arrowWidth;
    private final int xRight = xMiddleRight + arrowWidth;
    private final int xArrowPoint = xMiddleLeft + ((xMiddleRight - xMiddleLeft) / 2);
    
    // y coordinates
    private final int arrowLength = 40;
    private final int yTop = 20;
    private final int yMiddle = yTop + arrowLength / 2;
    private final int yArrowPoint = yTop + arrowLength;
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
        arrow.addPoint(this.xMiddleLeft, this.yMiddle);
        arrow.addPoint(this.xLeft, this.yMiddle);
        arrow.addPoint(this.xArrowPoint, this.yArrowPoint);
        arrow.addPoint(this.xRight, this.yMiddle);
        arrow.addPoint(this.xMiddleRight, this.yMiddle);
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