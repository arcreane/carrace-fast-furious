package Communication;

public interface InputUser {

    /**
     * Saisi de l'utilisateur
     */

    void ConsumeInput(String input, boolean p_bTimeLeft);

    int getAndAddLineNumber();
    int getLineNumber();
}
