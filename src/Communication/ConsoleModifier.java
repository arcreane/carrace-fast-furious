package Communication;

import org.fusesource.jansi.Ansi;

import java.io.IOException;

public class ConsoleModifier {
    public static boolean UserInput;
    private static ProcessBuilder builder = new ProcessBuilder("/bin/sh","-c","clear").inheritIO();
    static public boolean blockAccess;
    static public boolean userInput = false;
    static char escCode = 0x1B;


    public static void clearConsole(){
        try {
            builder.start().waitFor();
        } catch (InterruptedException | IOException ex){}
    }
    public static void WriteLine(int Index, Ansi.Color Color, String Mess, boolean Force){
        jWriteLine(Index, Color, Mess,Force);
    }
    static public String getSpace(int p_iStringLength) {
        return new String(new char[p_iStringLength]).replace('\0', ' ');
    }

    public static void jWriteLine(int Index, Ansi.Color Color, String Mess, boolean Force) {
        if ((!blockAccess && !userInput) || Force) {
            blockAccess = true;
            System.out.print(String.format("%c[%d;%df", escCode, Index, 0));
            System.out.print(new Ansi().fg(Color).a(Ansi.Attribute.INTENSITY_BOLD));
            System.out.println(Mess);
            blockAccess = false;
        }
    }

}
