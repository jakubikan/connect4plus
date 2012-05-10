package connect4.model;


public interface Player {
	
	int dropCoin(int column);
	void surrender();
	void setName(String string);
	void getName(String string);
	GameField getGameField();
	

}
