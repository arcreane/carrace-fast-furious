
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

    public int ModeChoice() {
        try {
            int option = scanner.nextInt();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * @return
     */
    public String Color() {
        try {
            String option = scanner.nextLine();
            return option;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}

