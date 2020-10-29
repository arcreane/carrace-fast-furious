
import Car.Cars;
import Car.SlowCar;

import java.util.*;

import static java.lang.System.out;

/**
 * 
 */
public class RaceTrack extends Menu {

    public RaceTrack() {

    }
    public static final int CYCLE_LENGTH = 1000;
    public static java.util.Timer  gameTimer = new java.util.Timer();
    int choice = super.choice();

    private float traveledDistance;
    private boolean finishedRace = false;
    ConsoleModifier consoleModifier = new ConsoleModifier();
    StringBuilder buffer = new StringBuilder();

    private static int nbLapsMax = 5;
    private int nbLaps = 0;

    void Timer() {
        gameTimer = new java.util.Timer();
        gameTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Race();
            }
        }, 0, CYCLE_LENGTH);
    }

    public boolean isFinished() {
        return finishedRace;
    }

    public void Race(){
        if (choice == 1 ){
            //Fast
        }else{
            SlowCar slow = new SlowCar();
            traveledDistance += 60 * slow.slowSpeed;
            if (isFinished()) {
                consoleModifier.clearConsole();
            }

            buffer.append("Vroom,Time elapsed : "); // il faut afficher le temps écoulé

            buffer.append("Distance traveled : ").append(traveledDistance + 10_000 * nbLaps)
                    .append(" (Laps number : ").append(nbLaps).append(")");

            buffer.append("----------------------------------------------------------- ").append(choice.m_iProbabilityEvent));

            if (traveledDistance > 10_000) {
                nbLaps++;

                if (isFinished()) {
                    if (nbLaps < nbLapsMax) {
                        buffer.setLength(0);
                        buffer.append("You have finished a lap. You have still  : ").append(nbLapsMax - nbLaps).append(" laps to go");
                        consoleModifier.clearConsole();
                    }

                }
                traveledDistance -= 10_000;
            }
        }
    }

}