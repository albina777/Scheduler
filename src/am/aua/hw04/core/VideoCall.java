package am.aua.hw04.core;

public class VideoCall implements Schedulable {
    private String title;
    private String email;

    public VideoCall(String title, String email) {
        this.title = title;
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public String getTitle() {
        return this.title;
    }
    public String getFullDetails() {
        return "Title: " + this.title;
    }

    public static VideoCall fromString(String s) throws MalformedStringParameterException {
        String[] tokens = s.split("%%");

        if (tokens.length != 3) {
            throw new MalformedStringParameterException("Invalid number of tokens for VideoCall");
        }

        if (tokens[1].contains("%%") || tokens[2].contains("%%")) {
            throw new MalformedStringParameterException("Data contains delimiter sequence");
        }

        String title = tokens[1];
        String email = tokens[2];

        return new VideoCall(title, email);
    }





    public String toSaveFileString() {
        return "VIDEOCALL%%" + this.title;
    }
}
