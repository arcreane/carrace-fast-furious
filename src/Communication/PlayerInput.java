package Communication;
import org.fusesource.jansi.Ansi;
import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Date;
import java.io.BufferedReader;


public class PlayerInput {
    static int staticThread = 0;
    static private boolean prevThread;

    private InputUser inputUser;
    private Thread threadTyping;
    private Ansi.Color color;
    private Date start;
    private boolean eventFinished;
    private boolean timeLeft;
    private int time;
    private boolean eventEnd;

    /**
     * Constructor
     */
    public PlayerInput(InputUser p_inputUser, Ansi.Color p_color, int Time){
        staticThread++;
        prevThread = false;
        inputUser = p_inputUser;
        color = p_color;
        start = new Date();
        timeLeft = true;
        eventEnd = false;
        time = Time;
    }

    /**
     * sert pour l'évènement en appellant la méthode callingRoutine
     */
    public void manageInput() {

        threadTyping = new Thread(() -> callingRoutine());
        threadTyping.start();

        while ((new Date().getTime() - start.getTime()) < time && !eventFinished) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // e.printStackTrace();
            }
        }
        timeLeft = false;
        manageEndLife();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * sert pour l'évènement
     */
    public void callingRoutine() {
        int threadId = staticThread;

        ConsoleModifier.userInput = true;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(reader);
        try {
            //While there is no input to be processed
            while (!reader.ready() && !prevThread) {
                //This lets throw an InterruptedException
                Thread.sleep(100);
                if (threadId != staticThread && !prevThread) {
                    Thread.currentThread().interrupt();
                }
            }
            if (threadId == staticThread)
                ConsoleModifier.WriteLine(inputUser.getAndAddLineNumber(), color, "Type your sequence");
            if (!prevThread) {
                String ret = reader.readLine();
                ConsoleModifier.WriteLine(inputUser.getLineNumber(), color, ConsoleModifier.getSpace(ret.length()));
            } else
                prevThread = false;

            ConsoleModifier.WriteLine(inputUser.getLineNumber() - 1, color, "");
            String userInput = sc.nextLine();
            if (threadId == staticThread)
                ConsoleModifier.userInput = false;

            if (threadId != staticThread) {
                prevThread = true;
            } else {
                inputUser.ConsumeInput(userInput, timeLeft);
            }
        } catch (IOException | InterruptedException /*|AWTException */ e) {
//            e.printStackTrace();
            ConsoleModifier.WriteLine(20, color, "Interruption");
        }
    }

    /**
     *
     */
    public void manageEndLife() {
        if (threadTyping.isAlive()) {
            threadTyping.interrupt();
        }
    }


}
