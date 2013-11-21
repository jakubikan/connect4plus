package connectfour.ui.gui.swing;

import connectfour.controller.IController;

import javax.swing.*;
import java.awt.*;

/**
 * @author: Stefano Di Martino
 * @created: May 27, 2012
 */

@SuppressWarnings("serial")
final class StatusDisplay extends JPanel {
    private JLabel status = new JLabel();
    private IController controller;
    
    public StatusDisplay(IController controller) {
        this.add(status);
        this.setBackground(Color.WHITE);
        this.controller = controller;
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
        if (controller.getPlayerOnTurn().equals(controller.getPlayers()[0])) { // Player 1
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