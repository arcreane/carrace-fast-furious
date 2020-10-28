import org.fusesource.jansi.Ansi;

import static java.lang.System.out;
import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.ansi;


public class Display {


    public void displayMenu(){
        out.println("Please select the King of car you want :");
        out.println("- Type 1 for a speeding car but having 30% chance of failing components");
        out.println("- Type 2 for a slow car but having 50% chance of having a speeding boost");
    }
    public void displayRace(){

    }

    public void displayScore(){

    }
    public void displayColor(){
        out.println( ansi().eraseScreen().fg(RED).a("RED").reset() );
        out.println( ansi().fg(GREEN).a("GREEN").reset() );
        out.println( ansi().fg(YELLOW).a("YELLOW").reset() );
        out.println( ansi().fg(CYAN).a("BLUE").reset() );
        out.println( ansi().fg(BLUE).a("PURPLE").reset() );
        out.println( ansi().fg(WHITE).a("GREY").reset() );


    }



}
