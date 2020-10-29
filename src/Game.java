
import org.fusesource.jansi.Ansi;
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
        Menu RaceMenu = new Menu();
        ConsoleModifier ClearConsole = new ConsoleModifier();

        AnsiConsole.systemInstall();
        char escCode = 0x1B;
        int row = 1; int column = 0;

        System.out.print(String.format("%c[%d;%df",escCode,row,column));

        displayMenu.displayMenu();
        RaceMenu.ModeChoice();
        ClearConsole.clearConsole();
        displayMenu.displayColor();
        RaceMenu.Color();
        ClearConsole.clearConsole();
        System.out.println("Let the race Begin (hit Enter to start)");
        ClearConsole.clearConsole();
        RaceTrack race = new RaceTrack();
        race.Timer();


    }
    /**
     * Default constructor
     */
    public Game() {
    }







}