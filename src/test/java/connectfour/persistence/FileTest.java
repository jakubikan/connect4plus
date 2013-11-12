package connectfour.persistence;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * User: Stefano Di Martino
 * Date: 12.11.13
 * Time: 20:26
 */
public class FileTest {


    @Test
    public void testPath() throws Exception {
        File f = new File("hibernate.cfg.xml");
        assertEquals("/home/stefano/Dokumente/Programming/Java/Connect4/hibernate.cfg.xml", f.getAbsoluteFile());
    }
}
