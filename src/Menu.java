import org.fusesource.jansi.Ansi;
import java.util.*;
import static java.lang.System.out;

public class Menu {

    Scanner scan = new Scanner(System.in);
    int choiceCar;

    /**
     * Constructor
     */
    public Menu() {

    }

    /**
     * Choose the mode, between slow car or fast car
     */
    public void ModeChoice() {

        do {
            var userSelect = scan.nextLine();
            choiceCar = Integer.parseInt(userSelect);
            try {
                if (choiceCar != 1 && choiceCar != 2) {
                    System.out.println("Please entry a valid number");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        } while (choiceCar != 1 && choiceCar != 2);
        if (choiceCar==1){
            choice();
        }else{
            choice();
        }
    }

    /**
     * Return the choice mode
     */
    public int choice() {
        return choiceCar;
    }

    /**
     * Choose the color
     */
    public String Color() {
        boolean found = false;
        Ansi.Color color = null;
        do {
            var colorName = scan.nextLine();
            try {
                color = Ansi.Color.valueOf(colorName);
            } catch (IllegalArgumentException e) {
                System.out.println("This color doesn't exist, type another");
                colorName = "";
            }
            if (color != null)
                found = true;
        } while (!found);
        StringBuilder buffer = new StringBuilder();
        System.out.println(buffer.append("You have selected this color :").append(color));

        return "";
    }
}

