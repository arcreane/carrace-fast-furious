package Car;

import org.fusesource.jansi.Ansi;

import java.util.Random;

import static org.fusesource.jansi.Ansi.ansi;


/**
 * 
 */
public abstract class Cars {
    Random random;
    boolean Stop;
    public int eventProba;
    Ansi.Color Color;
    protected boolean EventFinished;
    protected float Speed;

    public Cars(Ansi.Color color) {
        Color = color;
        EventFinished = true;
        Stop = false;
        random = new Random();
    }
    public float getSpeed() {
        return Speed;
    }

    public boolean isEventFinished() {
        return EventFinished;
    }
    public void stopAll() {
        Stop = true;
    }

    public boolean Happening() {
        var luck = random.nextInt(100);
        if (luck<= eventProba){
            EventSpecial();
            return true;
        }
        return false;
    }
    public void EventSpecial(){
        EventFinished=false;
        System.out.println(ansi().fg(Color).a(Ansi.Attribute.INTENSITY_BOLD));
    }




}