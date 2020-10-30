package Car;

import org.fusesource.jansi.Ansi;

import java.util.Random;

import static org.fusesource.jansi.Ansi.ansi;

public abstract class Cars {
    Random random;
    boolean stop;
    public int eventProba;
    Ansi.Color color;
    protected boolean eventFinished;
    protected float speed;

    /**
     * Constructor
     */
    public Cars(Ansi.Color p_color) {
        color = p_color;
        eventFinished = true;
        stop = false;
        random = new Random();
    }

    /**
     * get the car's speed
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * race finished
     */
    public boolean isEventFinished() {
        return eventFinished;
    }

    /**
     *
     */
    public void stopAll() {
        stop = true;
    }

    /**
     * Event
     */
    public boolean Happening() {
        var luck = random.nextInt(100);
        if (luck<= eventProba){
            EventSpecial();
            return true;
        }
        return false;
    }

    /**
     * Si evènement fini ça l'écrit en maj
     */
    public void EventSpecial(){
        eventFinished=false;
        System.out.println(ansi().fg(color).a(Ansi.Attribute.INTENSITY_BOLD));
    }
}