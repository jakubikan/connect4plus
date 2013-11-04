package connect4.persistence.db4o;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Ignore;

import connectfour.controller.GameField;
import connectfour.model.Player;
import connectfour.persistence.ISaveGameDAO;

@Ignore
public class SaveGameDb4oDAOTest {
	private GameField gameField;
	private Player player;
	private Player opponend;
    private ISaveGameDAO sgdb4o;
	private List<Integer> rows;


	@After
	public void tearDown() throws Exception {
		sgdb4o.closeDB();
	}
}