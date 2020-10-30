package Car;

import org.fusesource.jansi.Ansi;

import java.util.Random;

import static org.fusesource.jansi.Ansi.ansi;

public abstract class Cars {
    Random random;
    boolean Stop;
    public int eventProba;
    Ansi.Color Color;
    protected boolean EventFinished;
    protected float Speed;

    /**
     * Constructor
     */
    public Cars(Ansi.Color color) {
        Color = color;
        EventFinished = true;
        Stop = false;
        random = new Random();
    }

    /**
     * get the car's speed
     */
    public float getSpeed() {
        return Speed;
    }

    /**
     * race finished
     */
    public boolean isEventFinished() {
        return EventFinished;
    }

    /**
     *
     */
    public void stopAll() {
        Stop = true;
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
        EventFinished=false;
        System.out.println(ansi().fg(Color).a(Ansi.Attribute.INTENSITY_BOLD));
    }
}