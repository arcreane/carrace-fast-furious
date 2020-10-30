package Car.Part;

public abstract class CarParts {
    protected int m_iLineNumber;

    public int getAndAddLineNumber() {
        return m_iLineNumber++;
    }

    public abstract void Fails();
}
