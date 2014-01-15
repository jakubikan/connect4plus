package connectfour.controller;

/**
 * Created by jakub on 1/15/14.
 */
public interface IHighScoreController {
    void sendHighScore(String gameName, String playerName, int highScore);
}
