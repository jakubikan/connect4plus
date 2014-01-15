package connectfour.controller;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

/**
 * Created by jakub on 1/2/14.
 */
public class HighScoreController implements IHighScoreController {
    private static final String HIGHSCORE_URL = "http://localhost:9000/addHighscoreJson";

    private DefaultHttpClient client;

    private static final Logger log = Logger.getLogger(HighScoreController.class.getName());

    public HighScoreController() {
    }

    @Override
    public void sendHighScore(String gameName, String playerName, int highScore) {
        client =  new DefaultHttpClient();
        HttpPost post = new HttpPost(HIGHSCORE_URL);
        StringEntity input = null;
        try {
            String s = String.format("{\"game\":\"%s\",\"player\":\"%s\",\"score\":\"%d\"}",gameName, playerName, highScore);
            System.out.println(s);
            input = new StringEntity(s);

            input.setContentType("application/json");
            post.setEntity(input);

            HttpResponse response = client.execute(post);


            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                log.info(output);
            }

        } catch (UnsupportedEncodingException e) {
            log.info(e.getMessage());
        } catch( ClientProtocolException e) {
            log.info(e.getMessage());
        }

        catch (IOException e) {
            log.info(e.getMessage());
        }
        finally {
            client.getConnectionManager().shutdown();
        }


    }
}
