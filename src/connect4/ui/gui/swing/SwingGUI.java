package connect4.ui.gui.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import connect4.controller.GameController;
import connect4.model.Player;
import connect4.model.gameField.GameField;
import connect4.ui.UI;
import connect4.ui.gui.swing.events.ArrowManager;
import connect4.ui.gui.swing.events.NewGameEvent;
import connect4.ui.gui.swing.events.RedoEvent;
import connect4.ui.gui.swing.events.UndoEvent;
import connect4.ui.gui.swing.widgets.ArrowCell;
import connect4.ui.gui.swing.widgets.GUICoin;
import connect4.util.observer.IObserver;

/**
 * @author: Stefano Di Martino
 * @created: May 27, 2012
 */

@SuppressWarnings("serial")
public class SwingGUI extends JFrame implements UI, IObserver {
    // UI Stuff
    private final JPanel cellWrapper = new JPanel();
    private final GUICoin coinCells[][] = new GUICoin[GameField.DEFAULT_ROWS][GameField.DEFAULT_COLUMNS];
    private final ArrowCell arrowCells[] = new ArrowCell[GameField.DEFAULT_COLUMNS];
    private final StatusDisplay statusDisplay = new StatusDisplay();
    private final JToolBar toolBar = new JToolBar("Toolbar");
    
    static final private String UNDO = "previous";
    static final private String REDO = "next";
    private static final String NEW_GAME = "new game";
    
    // Game Stuff
    private final GameController controller = GameController.getInstance();;
    private Player[] players = controller.getPlayers();
    
    private void init() {
        for (int row = 0; row < GameField.DEFAULT_ROWS; row++) {
            for (int col = 0; col < GameField.DEFAULT_COLUMNS; col++) {
                coinCells[row][col] = new GUICoin(col);
            }
        }
        
        for (int col = 0; col < GameField.DEFAULT_COLUMNS; col++) {
            arrowCells[col] = new ArrowCell(col);
        }
        
        ArrowManager.getInstance().setArrowCells(this.arrowCells);
    }
    
    private void addCells() {
        for (int col = 0; col < GameField.DEFAULT_COLUMNS; col++) {
            cellWrapper.add(arrowCells[col]);
        }
        
        for (int row = GameField.DEFAULT_ROWS - 1; row >= 0; row--) {
            for (int col = 0; col < GameField.DEFAULT_COLUMNS; col++) {
                cellWrapper.add(coinCells[row][col]);
            }
        }
    }
    
    public SwingGUI() {
        this.init();
        
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
        
        lineAxisPanel.setPreferredSize(new Dimension(800, 600));
        
        cellWrapper.setPreferredSize(new Dimension(400, 300));
        statusDisplay.setPreferredSize(new Dimension(100, 100));
        
        addButtons();
        
        lineAxisPanel.add(cellWrapper);
        lineAxisPanel.add(statusDisplay);
        pageAxisPanel.add(toolBar);
        pageAxisPanel.add(lineAxisPanel);
        
        contentPane.add(pageAxisPanel);
        
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 0));
        
        this.setContentPane(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 700);
        this.setVisible(true);
        
        this.createBufferStrategy(2);
    }
    
    private void addButtons() {
        JButton button = null;
        
        button = makeNavigationButton("New Game", NEW_GAME, "New Game", "New Game");
        button.addMouseListener(new NewGameEvent(this));
        toolBar.add(button);
        
        button = makeNavigationButton("Undo", UNDO, "Undo", "Undo");
        button.addMouseListener(new UndoEvent(this));
        toolBar.add(button);
        
        button = makeNavigationButton("Redo", REDO, "Redo", "Redo");
        button.addMouseListener(new RedoEvent(this));
        toolBar.add(button);
    }
    
    private JButton makeNavigationButton(final String imageName, final String actionCommand,
                                            final String toolTipText, final String altText) {
        // Look for the image.
        String imgLocation = "images/" + imageName + ".gif";
        URL imageURL = SwingGUI.class.getResource(imgLocation);
        
        // Create and initialize the button.
        JButton button = new JButton();
        button.setActionCommand(actionCommand);
        button.setToolTipText(toolTipText);
        
        if (imageURL != null) { // image found
            button.setIcon(new ImageIcon(imageURL, altText));
        } else { // no image found
            button.setText(altText);
            System.err.println("Resource not found: " + imgLocation);
        }
        
        return button;
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
                } else if (player == players[0]) {
                    coinCells[currentRow][currentColumn].setColor(Color.RED);
                } else if (player == players[1]) {
                    coinCells[currentRow][currentColumn].setColor(Color.YELLOW);
                } else {
                    JOptionPane.showMessageDialog(this, "Fehler beim Einfügen der Münze in Zeile "
                                                            + currentRow + " und Spalte "
                                                            + currentColumn + "!");
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