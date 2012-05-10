package connect4.model;


public class Human extends PlayerAbstract {
	

	public Human(Coin playerCoin) {
		if (playerCoin == null) throw new IllegalArgumentException();
		this.playerCoin = playerCoin;
	}

	@Override
	public int dropCoin(int column) {
		getGameField();
		return gameField.dropCoin(column, this);
		
		
	}

	@Override
	public void surrender() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getName(String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setName(String string) {
		// TODO Auto-generated method stub
		
	}

}
