package connectfour.ui.tui;

import java.util.Scanner;

import com.google.inject.Inject;

import connectfour.controller.IController;
import connectfour.model.Computer;
import connectfour.controller.GameField;
import connectfour.model.Player;
import connectfour.ui.UI;
import connectfour.util.observer.IObserver;

/**
 * @author: Stefano Di Martino
 */

public class TUI implements UI, IObserver {
    private final String newline = System.getProperty("line.separator");
    private final IController controller;
    private Player players[];
    
    @Inject
    public TUI(IController controller) {
        this.controller = controller;
        this.players = controller.getPlayers();
    }
    
    @Override
    public void drawGameField() {
        this.players = controller.getPlayers();
		
        if (controller.userHasWon()) {
            System.out.printf("%s hat gewonnen\n\n", controller.getWinner());
            return;
        } else {
            System.out.printf("%s ist dran\n\n", controller.getPlayerNameOnTurn());
        }
        
        System.out.println(this.renderGameField());
        
        if (controller.getPlayerOnTurn() instanceof Computer) {
            return;
        }
        
        Scanner scanner = new Scanner (System.in);
        System.out.print("Eingabe: ");
        
        String userInput = scanner.next();
        
        System.out.println("\n\n");
        scanner.close();
        String exit = "quit";
        if (userInput.equals(exit)) {
            System.exit(0);
        }
        
        this.parseUserInput(userInput);
    }
    
    private void parseUserInput(final String userInput) {
        if (isInteger(userInput)) {
            int column = Integer.parseInt(userInput) - 1;
            
            if (!controller.dropCoinWithSuccessFeedback(column)) {
                System.out.println("Ungueltige Eingabe!\n");
                this.drawGameField();
            }
        } else {
            System.out.println("Ungueltige Eingabe!\n");
            this.drawGameField();
        }
    }
    
    private boolean isInteger(final String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public String renderGameField() {
        int row = GameField.DEFAULT_ROWS - 1;
        int col = GameField.DEFAULT_COLUMNS;
        
        StringBuilder playingField = new StringBuilder();
        String begin = "|";
        String empty = "_";
        String end = "_|";
        String coin1 = "O";
        String coin2 = "X";
        
        System.out.println("  1   2   3   4   5   6   7");
        
        for (int currentRow = row; currentRow >= 0; currentRow--) {
            playingField.append(begin);
            
            for (int currentColumn = 0; currentColumn < col; currentColumn++) {
                playingField.append(empty);
                
                Player player = this.controller.getPlayerAt(currentRow, currentColumn);
                
                if (player == null) {
                    playingField.append(empty);
                } else if (player == players[0]) {
                    playingField.append(coin1);
                } else if (player == players[1]) {
                    playingField.append(coin2);
                } else {
                    playingField.append("FEHLER!");
                }
                
                playingField.append(end);
            }
            playingField.append(newline);
        }
        return playingField.toString();
    }
    
    @Override
    public void update() {
    	System.out.println(this.renderGameField());
    }
}