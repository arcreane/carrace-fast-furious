
import Car.Cars;
import Communication.ConsoleModifier;
import org.fusesource.jansi.Ansi;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class RaceTrack extends Menu {
    public static final int CYCLE_LENGTH = 1000;

    private static Date start;
    private static int nbLapsMax = 5;

    ConsoleModifier consoleModifier = new ConsoleModifier();
    public static java.util.Timer  gameTimer = new java.util.Timer();
    int choice = super.choice();

    private boolean finishedRace = false;
    private double Score;
    private Cars Player;

    private float traveledDistance;
    private int nbLaps = 0;

    StringBuilder buffer = new StringBuilder();

    public RaceTrack(Cars Player) {

    }
    public boolean isFinished() {
        return finishedRace;
    }

    void Timer() {
        gameTimer=new Timer();
        gameTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Race();
            }
        }, 0, CYCLE_LENGTH);
        start= new Date();
    }



    public void Race(){
        traveledDistance+= 60 * Player.getSpeed();
        int lineNumber =1;
        if (Player.isEventFinished()){
            consoleModifier.clearConsole();
        }
        buffer.setLength(0);
        buffer.append("Vroom time elapsed : ");
        buffer.append((new Date().getTime() - start.getTime()) / 1000);
        buffer.append(" secs");
        buffer.append(new String(new char[50]).replace('\0',' '));

        consoleModifier.WriteLine(lineNumber++, Ansi.Color.WHITE, buffer.toString());
        if (traveledDistance>10_000){
            nbLaps++;
            if (Player.isEventFinished()){
                if (nbLaps<nbLapsMax){
                    buffer.setLength(0);
                    buffer.append("You have finished one lap : ").append(nbLapsMax - nbLaps).append("laps to end");
                    consoleModifier.WriteLine(lineNumber++,Ansi.Color.WHITE,buffer.toString());
                }
            }
            traveledDistance-= 10_000;
        }
        buffer.setLength(0);
        buffer.append("Distance Travelled : ");
        buffer.append(traveledDistance+10_000*nbLaps);
        buffer.append(" (Laps number : ").append(nbLaps).append(")");
        buffer.append(new String(new char[50]).replace('\0',' '));
        consoleModifier.WriteLine(lineNumber++,Ansi.Color.WHITE, buffer.toString());
        buffer.setLength(0);
        buffer.append("Speed : ").append(Player.getSpeed()).append(new String(new char[50]).replace('\0',' '));

        consoleModifier.WriteLine(lineNumber++, Ansi.Color.WHITE, buffer.toString());
        if (lineNumber == 4)
            consoleModifier.WriteLine(lineNumber++, Ansi.Color.WHITE, new String(new char[100]).replace('\0', ' '));
        consoleModifier.WriteLine(5, Ansi.Color.WHITE, "----------------------------------------------------------- ");
        if (Player.isEventFinished())
            Player.Happening();

        if (nbLaps == nbLapsMax) {
            gameTimer.cancel();
            gameTimer.purge();
            finishedRace = true;
            Player.stopAll();
            Score = new Date().getTime() - start.getTime();
        }
    }
    public double getScore() {
        return Score;
    }

}