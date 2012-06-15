package connect4.model;

import connect4.model.gameField.GameField;


public interface Player {

	void surrender();
	void setName(String string);
	String getName();
	Coin getCoin();
	void setCoin(Coin c);
	/**
	 * 
	 * This function should delegate to the method 
	 * GameField.dropCoin(column, player).
	 * 
	 * @param column the Column where to drop the Coin
	 * @param g is the Instance of the Gamefield
	 * @return 
	 */
	int dropCoin(int column, GameField g);

}
