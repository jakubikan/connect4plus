package test;

/**
 * @author: stefano
 * @created: Apr 11, 2012
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.fusesource.jansi.AnsiConsole;

public class JANSITest {
    
    public static void main(String[] args) throws IOException {
        
        // String file = "/home/stefano/Downloads/ROY-ED1.ANS";
        // if (args.length > 0)
        // file = args[0];
        //
        // // Allows us to disable ANSI processing.
        // if ("true".equals(System.getProperty("jansi", "true"))) {
        // AnsiConsole.systemInstall();
        // }
        //
        // System.out.print(ansi().reset().eraseScreen().cursor(1, 1));
        // System.out.print("=======================================================================");
        // FileInputStream f = new FileInputStream(file);
        // int c;
        // while ((c = f.read()) >= 0) {
        // System.out.write(c);
        // }
        // f.close();
        // System.out.println("=======================================================================");
        
        JANSITest test = new JANSITest();
        test.readANSI();
        
    }
    
    private void readANSI() throws IOException {
        AnsiConsole.systemInstall();
        
        AnsiConsole.out.println("\033[46;1;31m TESTAUSGABE \033[43;4;34m KUNTER \033[45;1;32m BUNT \033[44;1;33m ANGESTRICHEN \033[0m");
        
        String fileName = "Connect_4_LabelV2.ans";
        
        InputStream is = this.getClass().getResourceAsStream(fileName);
        // BufferedInputStream in = new BufferedInputStream(is);
        InputStreamReader ir = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(ir);
        String tmp;
        while ((tmp = br.readLine()) != null) {
            System.out.print(tmp);
        }
        
        br.close();
        System.out.println();
        
        AnsiConsole.systemUninstall();
    }
}
