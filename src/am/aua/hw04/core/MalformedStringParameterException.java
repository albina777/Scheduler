package am.aua.hw04.core;

public class MalformedStringParameterException extends Exception {

    public MalformedStringParameterException()
    {
        super("Incopetabile string provided");
    }

    public MalformedStringParameterException(String message) {
        super(message);
    }
}
