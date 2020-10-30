package Car;
import Communication.ConsoleModifier;
import Communication.InputUser;
import Communication.PlayerInput;
import org.fusesource.jansi.Ansi;

import java.util.*;
import java.util.stream.Collectors;
import Car.Cars;

import java.util.Random;
import java.util.Scanner;

public class SlowCar extends Cars implements InputUser{
    public static final String WORD_SPEED ="VITESSE";
    public static final int TIME = 10_000;

    private float boostSpeed = 3.0f;
    private int boost;

    private String shuffleSpeed;
    private int lineNumber;
    private List<Character> characterSpeedList;

    Random random = new Random();

    /**
     * Constructor
     */
    public SlowCar(Ansi.Color color) {
        super(color);
        speed=10f;
        eventProba=25;
        boost=0;
        characterSpeedList = new ArrayList<Character>();

        for (char c : WORD_SPEED.toCharArray()){
            characterSpeedList.add(c);
        }
    }

    /**
     * Method Boost
     */
    public void setBoostLapRemaining(int BoostCycleRemaining) {
        boost = BoostCycleRemaining;
        if (BoostCycleRemaining == 0)
            speed /= boostSpeed;
        ConsoleModifier.WriteLine(4, Ansi.Color.WHITE, "Boost lap remaining :  " + BoostCycleRemaining, false);
    }

    /**
     * Savoir si un e évènement à eu lieu ou pas
     */
    public boolean CheckEventHappening() {
        if (boost > 0)
            setBoostLapRemaining(boost - 1);
        eventProba++;
        return super.Happening();
    }

    /**
     * Méthode qui est appellée dans Cars et qui sert à l'évènement
     */
    public void EventSpecial() {
        super.EventSpecial();
        Collections.shuffle(characterSpeedList);
        shuffleSpeed = characterSpeedList.stream().map(String::valueOf).collect(Collectors.joining());
        lineNumber = 6;

        ConsoleModifier.WriteLine(lineNumber++, color, "Type " + shuffleSpeed + " in less than "
                + (TIME / 1000) + " second to get a speed boost", false);
        ConsoleModifier.WriteLine(lineNumber++, color, "Hit enter to start typing", false);

        PlayerInput playerInput = new PlayerInput(this, color, TIME);

        Thread playerInputManagement = new Thread( () -> playerInput.manageInput());
        playerInputManagement.start();

    }

    /**
     * Traitement de la réponse de l'utilisateur par rapport à l'évènement
     */
    public void ConsumeInput(String input, boolean p_bTimeLeft) {
        if (shuffleSpeed.equals(input)) {
            if( p_bTimeLeft) {
                speed *= boostSpeed;
                setBoostLapRemaining(boost + 4);
                eventProba = 0;
                ConsoleModifier.WriteLine(lineNumber++, color, "Well Done, your speed is now : " + speed, false);
            }
            else{
                System.out.println("Sorry to slow");
            }
        } else if (!stop) {
            ConsoleModifier.WriteLine(lineNumber++, color, "Sorry, wrong typing! " + input + " You'll do better next time", false);
        }
        eventFinished = true;
    }

    public int getAndAddLineNumber() {
        return lineNumber++;
    }


    public int getLineNumber() {
        return lineNumber;
    }

}