package Communication;

public interface InputUser {

    void ConsumeInput(String input, boolean p_bTimeLeft);

    int getAndAddLineNumber();
    int getLineNumber();
}
