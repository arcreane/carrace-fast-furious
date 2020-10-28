
import org.fusesource.jansi.AnsiConsole;

import java.util.*;

import static org.fusesource.jansi.Ansi.Color.GREEN;
import static org.fusesource.jansi.Ansi.Color.RED;
import static org.fusesource.jansi.Ansi.ansi;

/**
 * 
 */
public class Game {

    public static void main(String[] args) {
        Display displayMenu = new Display();
        AnsiConsole.systemInstall();
        char escCode = 0x1B;
        int row = 20; int column = 0;

        System.out.print(String.format("%c[%d;%df",escCode,row,column));
        displayMenu.displayColor();

    }
    /**
     * Default constructor
     */
    public Game() {
    }







}