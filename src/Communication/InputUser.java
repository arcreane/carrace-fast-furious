package Communication;

public interface InputUser {

    /**
     * User's entry
     */

    void ConsumeInput(String input, boolean p_bTimeLeft);

    int getAndAddLineNumber();
    int getLineNumber();
}
