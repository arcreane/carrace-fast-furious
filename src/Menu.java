
import java.util.*;

import static java.lang.System.out;

/**
 * 
 */
public class Menu {

    Scanner scanner = new Scanner(System.in);

    /**
     * Default constructor
     */
    public Menu() {
    }

    /**
     *
     * @return
     */
    public String ModeChoice() {
        try {
            out.println("Please select the King of car you want :");
            out.println("- Type 1 for a speeding car but having 30% chance of failing components");
            out.println("- Type 2 for a slow car but having 50% chance of having a speeding boost");
            String option = scanner.nextLine();
            return option;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @return
     */
    public String Color() {
        try {
            out.println("RED");
            out.println("BLUE");
            out.println("GREEN");
            out.println("YELLOW");
            out.println("MAGENTA");
            out.println("CYAN");
            out.println("WHITE");
            String option = scanner.nextLine();
            return option;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}

