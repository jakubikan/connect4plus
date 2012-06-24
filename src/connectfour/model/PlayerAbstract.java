package connectfour.model;

import connectfour.model.gameField.GameField;

public abstract class PlayerAbstract implements Player {
	String name;
	private GameField gameField;

	@Override
	public GameField getGameField() {
		return this.gameField;
	}

	@Override
	public final void setGameField(final GameField gameField) {
		try {
			this.gameField = gameField.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return name;

	}

	@Override
	public void setName(final String string) {
		name = string;
	}
}
