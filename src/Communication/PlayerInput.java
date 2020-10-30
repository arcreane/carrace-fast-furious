package Communication;
import org.fusesource.jansi.Ansi;
import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Date;
import java.io.BufferedReader;


public class PlayerInput {
    static int StaticThread =0;
    static private boolean PrevThread;

    private InputUser inputUser;
    private Thread ThreadTyping;
    private Ansi.Color Color;
    private Date start;
    private boolean EventFinished;
    private boolean TimeLeft;
    private int Time;
    private boolean EventEnd;

    public PlayerInput(InputUser inputUser, Ansi.Color Color, int Time){
        StaticThread++;
        PrevThread=false;
        inputUser =inputUser;
        Color=Color;
        start=new Date();
        TimeLeft=true;
        EventEnd=false;
        Time=Time;
    }

    public void manageInput() {

        ThreadTyping = new Thread(() -> CallingRoutine());
        ThreadTyping.start();

        while ((new Date().getTime() - start.getTime()) < Time && !EventFinished) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // e.printStackTrace();
            }
        }
        TimeLeft = false;
        manageEndLife();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void CallingRoutine() {
        int threadId = StaticThread;

        ConsoleModifier.UserInput = true;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(reader);
        try {
            //While there is no input to be processed
            while (!reader.ready() && !PrevThread) {
                //This lets throw an InterruptedException
                Thread.sleep(100);
                if (threadId != StaticThread && !PrevThread) {
                    Thread.currentThread().interrupt();
                }
            }
            if (threadId == StaticThread)
                ConsoleModifier.WriteLine(inputUser.getAndAddLineNumber(), Color, "Type your sequence");
            if (!PrevThread) {
                String ret = reader.readLine();
                ConsoleModifier.WriteLine(inputUser.getLineNumber(), Color, ConsoleModifier.getSpace(ret.length()));
            } else
                PrevThread = false;

            ConsoleModifier.WriteLine(inputUser.getLineNumber() - 1, Color, "");
            String userInput = sc.nextLine();
            if (threadId == StaticThread)
                ConsoleModifier.UserInput = false;

            if (threadId != StaticThread) {
                PrevThread = true;
            } else {
                inputUser.ConsumeInput(userInput, TimeLeft);
            }
        } catch (IOException | InterruptedException /*|AWTException */ e) {
//            e.printStackTrace();
            ConsoleModifier.WriteLine(20, Color, "Interruption");
        }
    }
    public void manageEndLife() {
        if (ThreadTyping.isAlive()) {
            ThreadTyping.interrupt();
        }
    }


}
