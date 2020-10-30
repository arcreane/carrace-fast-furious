
import Car.Cars;
import Car.SlowCar;
import Communication.ConsoleModifier;
import Communication.PlayerInput;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.io.IOException;
import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;

/**
 * 
 */
public class Game {

    public static void main(String[] args) throws IOException, InterruptedException {
        Display displayMenu = new Display();
        Menu RaceMenu = new Menu();
        ConsoleModifier ClearConsole = new ConsoleModifier();
        StringBuilder buffer = new StringBuilder();
        Scanner scan = new Scanner(System.in);
        char escCode = 0x1B;
        int row = 1; int column = 0;
        System.out.print(String.format("%c[%d;%df",escCode,row,column));

        AnsiConsole.systemInstall();

        Cars racingCar = null;
        int choiceCar;

        displayMenu.displayMenu();
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

        ClearConsole.clearConsole();
        displayMenu.displayColor();
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
        System.out.println(buffer.append("You have selected this color :").append(color));

        ClearConsole.clearConsole();

        if (choiceCar == 1)
            System.out.println("course rapide");
        else
            racingCar = new SlowCar(color);

        System.out.println("Let the race Begin (hit Enter to start)");
        ClearConsole.clearConsole();
        RaceTrack race = new RaceTrack(racingCar);
        race.Timer();
        while (!race.isFinished()){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ConsoleModifier.clearConsole();
        buffer.setLength(0);
        ConsoleModifier.WriteLine(0, Ansi.Color.WHITE,buffer.append("You have finished the race in : ").append(race.getScore() /1000).append("seconds").toString());

    }
}