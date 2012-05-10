package connect4.model;

import java.awt.Color;

public class Coin {
	
	Color color;

	
	public Coin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Coin(Color color) {
		this();
		if (color == null) throw new IllegalArgumentException();
		this.color = color;
	}

}
