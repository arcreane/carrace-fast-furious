
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
    private double score;
    private Cars player;

    private float traveledDistance;
    private int nbLaps = 0;

    StringBuilder buffer = new StringBuilder();

    public RaceTrack(Cars p_player) {
        player = p_player;
    }

    public boolean isFinished() {
        return finishedRace;
    }

    /**
     * Start game timer
     */
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

    /**
     * Game with : race, time elapsed, distance travelled, laps number
     */
    public void Race(){
        traveledDistance+= 60 * player.getSpeed();
        int lineNumber =1;
        if (player.isEventFinished()){
            consoleModifier.clearConsole();
        }
        buffer.setLength(0);
        buffer.append("Vroom time elapsed : ");
        buffer.append((new Date().getTime() - start.getTime()) / 1000);
        buffer.append(" secs");
        buffer.append(new String(new char[50]).replace('\0',' '));

        consoleModifier.WriteLine(lineNumber++, Ansi.Color.WHITE, buffer.toString(),false);
        if (traveledDistance>10_000){
            nbLaps++;
            if (player.isEventFinished()){
                if (nbLaps<nbLapsMax){
                    buffer.setLength(0);
                    buffer.append("You have finished one lap : ").append(nbLapsMax - nbLaps).append("laps to end");
                    consoleModifier.WriteLine(lineNumber++,Ansi.Color.WHITE,buffer.toString(),false);
                }
            }
            traveledDistance-= 10_000;
        }
        buffer.setLength(0);
        buffer.append("Distance Travelled : ");
        buffer.append(traveledDistance+10_000*nbLaps);
        buffer.append(" (Laps number : ").append(nbLaps).append(")");
        buffer.append(new String(new char[50]).replace('\0',' '));
        consoleModifier.WriteLine(lineNumber++,Ansi.Color.WHITE, buffer.toString(),false);
        buffer.setLength(0);
        buffer.append("Speed : ").append(player.getSpeed()).append(new String(new char[50]).replace('\0',' '));

        consoleModifier.WriteLine(lineNumber++, Ansi.Color.WHITE, buffer.toString(),false);
        if (lineNumber == 4)
            consoleModifier.WriteLine(lineNumber++, Ansi.Color.WHITE, new String(new char[100]).replace('\0', ' '),false);
        consoleModifier.WriteLine(5, Ansi.Color.WHITE, "----------------------------------------------------------- ",false);
        if (player.isEventFinished())
            player.Happening();

        if (nbLaps == nbLapsMax) {
            gameTimer.cancel();
            gameTimer.purge();
            finishedRace = true;
            player.stopAll();
            score = new Date().getTime() - start.getTime();
        }
    }

    /**
     * Return score
     */
    public double getScore() {
        return score;
    }

}