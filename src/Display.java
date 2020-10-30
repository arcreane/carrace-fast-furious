import org.fusesource.jansi.Ansi;

import java.io.IOException;
import java.sql.SQLOutput;

import static java.lang.System.out;
import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.ansi;


public class Display {

    /**
     * Display the menu
     */
    public void displayMenu(){
        out.println("---------------------------------------------------------");
        out.println("|                                                       |");
        out.println("|            Welcome to Race against the clock          |");
        out.println("|                                                       |");
        out.println("---------------------------------------------------------");
        out.println("\nPlease select the kind of car you want");
        out.println("\t-Type 1 for a speeding car but having 30% chance of failing components");
        out.println("\t-Type 2 for a slow car but having 50% chance of having a speeding boost");
    }

    /**
     * Display the race
     */
    public void displayRace(){
        out.println("---------------------------------------------------------");

    }

    /**
     * Display the score
     */
    public void displayScore(){

    }

    /**
     * Display the color
     */
    public void displayColor(){
        out.println(ansi().reset().a("Select the name of the color you have selected : "));
        out.println( ansi().reset().fg(RED).a("RED").reset() );
        out.println( ansi().reset().fg(GREEN).a("GREEN").reset() );
        out.println( ansi().reset().fg(YELLOW).a("YELLOW").reset() );
        out.println( ansi().reset().fg(CYAN).a("CYAN").reset() );
        out.println( ansi().reset().fg(BLUE).a("BLUE").reset() );
        out.println( ansi().reset().fg(WHITE).a("WHITE").reset() );
    }

}
