package Communication;

import org.fusesource.jansi.Ansi;

import java.io.IOException;

public class ConsoleModifier {
    private static ProcessBuilder builder = new ProcessBuilder("/bin/sh","-c","clear").inheritIO();
    static public boolean userInput = false;

    /**
     * Clear console
     */
    public static void clearConsole(){
        try {
            builder.start().waitFor();
        } catch (InterruptedException | IOException ex){}
    }
    public static void WriteLine(int Index, Ansi.Color Color, String Mess){
        WriteLine(Index, Color, Mess);
    }
    static public String getSpace(int p_iStringLength) {
        return new String(new char[p_iStringLength]).replace('\0', ' ');
    }


}
