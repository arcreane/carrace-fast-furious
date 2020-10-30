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
    public static final String Word_Speed ="VITESSE";
    public static final int Time = 10_000;

    private float boostSpeed = 3.0f;
    private int Boost;

    private String ShuffleSpeed;
    private int LineNumber;
    private List<Character> CharacterSpeedList;

    Random random = new Random();

    /**
     * Constructor
     */
    public SlowCar(Ansi.Color color) {
        super(color);
        Speed=10f;
        eventProba=25;
        Boost=0;
        CharacterSpeedList = new ArrayList<Character>();

        for (char c : Word_Speed.toCharArray()){
            CharacterSpeedList.add(c);
        }
    }

    /**
     * Method Boost
     */
    public void setBoostLapRemaining(int BoostCycleRemaining) {
        Boost = BoostCycleRemaining;
        if (BoostCycleRemaining == 0)
            Speed /= boostSpeed;
        ConsoleModifier.WriteLine(4, Ansi.Color.WHITE, "Boost lap remaining :  " + BoostCycleRemaining,false);
    }

    /**
     * Savoir si un e évènement à eu lieu ou pas
     */
    public boolean CheckEventHappening() {
        if (Boost > 0)
            setBoostLapRemaining(Boost - 1);
        eventProba++;
        return super.Happening();
    }

    /**
     * Méthode qui est appellée dans Cars et qui sert à l'évènement
     */
    public void EventSpecial() {
        super.EventSpecial();
        Collections.shuffle(CharacterSpeedList);
        ShuffleSpeed = CharacterSpeedList.stream().map(String::valueOf).collect(Collectors.joining());
        LineNumber = 6;

        ConsoleModifier.WriteLine(LineNumber++, Color, "Type " + ShuffleSpeed + " in less than "
                + (Time / 1000) + " second to get a speed boost",false);
        ConsoleModifier.WriteLine(LineNumber++, Color, "Hit enter to start typing",false);

        PlayerInput playerInput = new PlayerInput(this, Color, Time);

        Thread playerInputManagement = new Thread( () -> playerInput.manageInput());
        playerInputManagement.start();

    }

    /**
     * Traitement de la réponse de l'utilisateur par rapport à l'évènement
     */
    public void ConsumeInput(String input, boolean p_bTimeLeft) {
        if (ShuffleSpeed.equals(input)) {
            if( p_bTimeLeft) {
                Speed *= boostSpeed;
                setBoostLapRemaining(Boost + 4);
                eventProba = 0;
                ConsoleModifier.WriteLine(LineNumber++, Color, "Well Done, your speed is now : " + Speed,false);
            }
            else{
                System.out.println("Sorry to slow");
            }
        } else if (!Stop) {
            ConsoleModifier.WriteLine(LineNumber++, Color, "Sorry, wrong typing! " + input + " You'll do better next time",false);
        }
        EventFinished = true;
    }

    public int getAndAddLineNumber() {
        return LineNumber++;
    }


    public int getLineNumber() {
        return LineNumber;
    }

}