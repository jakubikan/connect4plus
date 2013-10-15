package connectfour.ui.gui.swing;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import connectfour.controller.GameController;

/**
 * @author: Stefano Di Martino
 * @created: May 27, 2012
 */

@SuppressWarnings("serial")
final class StatusDisplay extends JPanel {
    private JLabel status = new JLabel();
    private GameController controller;
    
    public StatusDisplay() {
        this.add(status);
        this.setBackground(Color.WHITE);
        this.controller = GameController.getInstance();
        this.showPlayerOnTurn();
    }
    
    public void update() {
        if (controller.userHasWon()) {
            showWinner();
        } else {
            showPlayerOnTurn();
        }
    }
    
    private void setPlayersColor() {
        if (controller.getPlayerOnTurn() == controller.getPlayers()[0]) { // Player 1
            status.setForeground(Color.RED);
        } else {
            status.setForeground(Color.BLUE);
        }
    }
    
    private void showWinner() {
        String winner = String.format("%s hat gewonnen!", controller.getWinner());
        status.setText(winner);
    }
    
    private void showPlayerOnTurn() {
        setPlayersColor();
        String playerOnTurn = String.format("Spieler %s ist dran", controller.getPlayerNameOnTurn());
        status.setText(playerOnTurn);
    }
}