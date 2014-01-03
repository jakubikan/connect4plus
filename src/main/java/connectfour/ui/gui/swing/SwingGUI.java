package connectfour.ui.gui.swing;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import connectfour.controller.IController;
import connectfour.model.GameField;
import connectfour.model.Player;
import connectfour.ui.UI;
import connectfour.ui.gui.swing.controller.ArrowManager;
import connectfour.ui.gui.swing.widgets.ArrowCell;
import connectfour.ui.gui.swing.widgets.GUICoin;
import connectfour.util.observer.IObserver;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Stefano Di Martino
 * @created: May 27, 2012
 */

@SuppressWarnings("serial")
@Singleton
public class SwingGUI extends JFrame implements UI, IObserver {
    public static final Dimension DIMENSION_PANEL = new Dimension(800, 600);
    public static final Dimension DIMENSION_CELL_WRAPPER = new Dimension(400, 300);
    public static final Dimension DIMENSION_STATUS_DISPLAY = new Dimension(100, 100);
    public static final Border CONTENT_PANE_BOARDER = BorderFactory.createEmptyBorder(20, 20, 10, 0);
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 710;
    // UI Stuff
    private final JPanel cellWrapper = new JPanel();
    private final GUICoin coinCells[][] = new GUICoin[GameField.DEFAULT_ROWS][GameField.DEFAULT_COLUMNS];
    private final List<ArrowCell> listArrowCells = new ArrayList<ArrowCell>(
                                            GameField.DEFAULT_COLUMNS);
    private final StatusDisplay statusDisplay;
    
    // Game Stuff
    private final IController controller;
    private Player[] players;
    
    private void initGameField() {
        for (int row = 0; row < GameField.DEFAULT_ROWS; row++) {
            for (int col = 0; col < GameField.DEFAULT_COLUMNS; col++) {
                coinCells[row][col] = new GUICoin(controller, col, ArrowManager.getInstance());
            }
        }
        
        for (int col = 0; col < GameField.DEFAULT_COLUMNS; col++) {
            listArrowCells.add(new ArrowCell(col));
        }
        ArrowManager.getInstance().setArrowCells(listArrowCells);
    }
    
    private void addCells() {
        for (int col = 0; col < GameField.DEFAULT_COLUMNS; col++) {
            cellWrapper.add(listArrowCells.get(col));
        }
        
        for (int row = GameField.DEFAULT_ROWS - 1; row >= 0; row--) {
            for (int col = 0; col < GameField.DEFAULT_COLUMNS; col++) {
                cellWrapper.add(coinCells[row][col]);
            }
        }
    }
    
    @Inject
    public SwingGUI(IController controller) {
    	this.controller = controller;
    	statusDisplay = new StatusDisplay(controller);
    	players = controller.getPlayers();
    	
        this.initGameField();
        
        final JPanel contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        
        final JPanel lineAxisPanel = new JPanel();
        final JPanel pageAxisPanel = new JPanel();
        
        final BoxLayout lineAxisLayout = new BoxLayout(lineAxisPanel, BoxLayout.LINE_AXIS);
        final BoxLayout pageAxisLayout = new BoxLayout(pageAxisPanel, BoxLayout.PAGE_AXIS);
        
        lineAxisPanel.setLayout(lineAxisLayout);
        pageAxisPanel.setLayout(pageAxisLayout);
        
        // Layout setzen
        cellWrapper.setLayout(new GridLayout(7, 7));
        
        this.addCells();
        
        lineAxisPanel.setPreferredSize(DIMENSION_PANEL);
        
        cellWrapper.setPreferredSize(DIMENSION_CELL_WRAPPER);
        statusDisplay.setPreferredSize(DIMENSION_STATUS_DISPLAY);
        
        lineAxisPanel.add(cellWrapper);
        lineAxisPanel.add(statusDisplay);
        pageAxisPanel.add(new ToolBar(controller, this, this));
        pageAxisPanel.add(lineAxisPanel);
        
        contentPane.add(pageAxisPanel);
        
        contentPane.setBorder(CONTENT_PANE_BOARDER);
        
        this.setContentPane(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        
        this.createBufferStrategy(2);
    }
    
    @Override
    public void drawGameField() {
        players = controller.getPlayers();
        statusDisplay.update();
        for (int currentRow = 0; currentRow < GameField.DEFAULT_ROWS; currentRow++) {
            for (int currentColumn = 0; currentColumn < GameField.DEFAULT_COLUMNS; currentColumn++) {
                Player player = this.controller.getPlayerAt(currentRow, currentColumn);
                
                if (player == null) {
                    coinCells[currentRow][currentColumn].setColor(Color.WHITE);
                } else if (player.equals(players[0])) {
                    coinCells[currentRow][currentColumn].setColor(Color.RED);
                } else if (player.equals(players[1])) {
                    coinCells[currentRow][currentColumn].setColor(Color.YELLOW);
                } else {
                    JOptionPane.showMessageDialog(this,
                                                            "Fehler beim Einfuegen der Muenze in Zeile "
                                                                                                    + currentRow
                                                                                                    + " und Spalte "
                                                                                                    + currentColumn
                                                                                                    + "!");
                }
            }
        }
        this.repaint();
    }
    
    @Override
    public void update() {
        this.drawGameField();
    }
}