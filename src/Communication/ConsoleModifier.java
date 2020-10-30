package Communication;

import org.fusesource.jansi.Ansi;

import java.io.IOException;

public class ConsoleModifier {
    private static ProcessBuilder builder = new ProcessBuilder("/bin/sh","-c","clear").inheritIO();
    static public boolean userInput = false;
    static public boolean blockAccess;
    static char escCode = 0x1B;

    /**
     * Clear console
     */
    public static void clearConsole(){
        try {
            builder.start().waitFor();
        } catch (InterruptedException | IOException ex){}
    }

    static public String getSpace(int p_iStringLength) {
        return new String(new char[p_iStringLength]).replace('\0', ' ');
    }

    public static void WriteLine(int p_iLineIndex, Ansi.Color p_Color, String p_sMessage, boolean p_bForce) {
        if ((!blockAccess && !userInput) || p_bForce) {
            blockAccess = true;
            System.out.print(String.format("%c[%d;%df", escCode, p_iLineIndex, 0));
            System.out.print(new Ansi().fg(p_Color).a(Ansi.Attribute.INTENSITY_BOLD));
            System.out.println(p_sMessage);
            blockAccess = false;
        }
    }

    public static void getWriteLine(int Index, Ansi.Color Color, String Mess, boolean Force){
        WriteLine(Index, Color, Mess, Force);
    }

}
