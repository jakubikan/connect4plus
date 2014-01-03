package connectfour.ui.gui.swing.widgets;

import connectfour.controller.IController;
import connectfour.ui.gui.swing.events.GUICoinMouseListener;
import connectfour.ui.gui.swing.events.MouseColumnObserver;
import connectfour.util.observer.IObserver;
import connectfour.util.observer.IObserverWithArguments;

import javax.swing.*;
import java.awt.*;

/**
 * @author: Stefano Di Martino
 * @created: May 27, 2012
 */

@SuppressWarnings("serial")
public class GUICoin extends JPanel implements IObserver {
    public static final int RADIUS = 40;
    public static final int X_POSITION = 18;
    public static final int Y_POSITION = 20;
    private final int column;
    private Color color;
    private final IController controller;
    
	public GUICoin(final IController controller, final int column, IObserverWithArguments observer) {
        this.setCoinBackAndForegroundColor();
        this.color = Color.WHITE;
        this.column = column;
        this.addMouseListener(new GUICoinMouseListener(this));
        this.addMouseListener(new MouseColumnObserver(observer, column));
        this.controller = controller;
    }
    
    private void setCoinBackAndForegroundColor() {
        this.setBackground(Color.BLUE);
        this.setForeground(this.color);
    }
    
    public void setColor(Color color) {
        this.color = color;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // paints background
        this.setCoinBackAndForegroundColor();
        
        Graphics2D g2 = (Graphics2D) g;
        
        // Activate Anti-Aliasing!
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int radius = RADIUS;
        g2.fillOval(X_POSITION, Y_POSITION, radius, radius);
    }
    
    public int getColumn() {
        return this.column;
    }
    
    @Override
    public void update() {
    	this.controller.dropCoinWithSuccessFeedback(this.column);
    }
}