package am.aua.hw04.core;

public class Meeting implements Schedulable {
    private String title;
    private double latitude;
    private double longitude;

    // Constructor that takes title, latitude, and longitude
    public Meeting(String title, double latitude, double longitude) {
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Overloaded constructor just for title (in case it's needed)
    public Meeting(String title) {
        this.title = title;
    }

    public static Meeting fromString(String s) throws MalformedStringParameterException {
        String[] tokens = s.split("%%");

        if (tokens.length != 4) {
            throw new MalformedStringParameterException("Invalid number of tokens for Meeting");
        }

        if (tokens[1].contains("%%") || tokens[2].contains("%%") || tokens[3].contains("%%")) {
            throw new MalformedStringParameterException("Data contains delimiter sequence");
        }

        String title = tokens[1];
        double latitude = Double.parseDouble(tokens[2]);
        double longitude = Double.parseDouble(tokens[3]);

        return new Meeting(title, latitude, longitude);
    }

    public String getTitle() {
        return this.title;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public String getFullDetails() {
        return "Title: " + this.title + ", Latitude: " + this.latitude + ", Longitude: " + this.longitude;
    }

    public String toSaveFileString() {
        return "MEETING%%" + this.title + "%%" + this.latitude + "%%" + this.longitude;
    }
}
