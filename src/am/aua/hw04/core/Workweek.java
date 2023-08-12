package am.aua.hw04.core;

import am.aua.hw04.core.*;
public class Workweek implements Cloneable {
    private final Schedulable[][] schedule;

    public Workweek() {
        this.schedule = new Schedulable[Days.values().length][Times.values().length];
    }


    public void addToSchedule(Schedulable s, Days d, Times t) {
        schedule[d.ordinal()][t.ordinal()] = s;
    }

    public void removeFromSchedule(Days d, Times t) {
        schedule[d.ordinal()][t.ordinal()] = null;
    }

    public String getTitleAt(Days d, Times t) {
        Schedulable schedulable = schedule[d.ordinal()][t.ordinal()];
        return schedulable != null ? schedulable.getTitle() : null;
    }

    public String getFullDetailsAt(Days d, Times t) {
        Schedulable schedulable = schedule[d.ordinal()][t.ordinal()];
        return schedulable != null ? schedulable.getFullDetails() : null;
    }

    public Schedulable getScheduleAt(Days d, Times t) {
        return schedule[d.ordinal()][t.ordinal()];
    }

    public Schedulable getEvent(Days d, Times t) {
        return schedule[d.ordinal()][t.ordinal()];
    }

    // Additional method to print the full details of the schedule
    public void printFullSchedule() {
        for (Days day : Days.values()) {
            for (Times time : Times.values()) {
                Schedulable schedulable = schedule[day.ordinal()][time.ordinal()];
                if (schedulable != null) {
                    System.out.println(day + " " + time + ": " + schedulable.getFullDetails());
                } else {
                    System.out.println(day + " " + time + ": No scheduled activity");
                }
            }
        }
    }

    public String[] toStringArray() {
        String[] result = new String[10];
        int index = 0;
        for (Days day : Days.values()) {
            for (Times time : Times.values()) {
                String title = schedule[day.ordinal()][time.ordinal()] != null ?
                        schedule[day.ordinal()][time.ordinal()].getTitle().replace(",", "") : "";

                // Determine the type based on the instance
                String type = "";
                if (schedule[day.ordinal()][time.ordinal()] instanceof VideoCall) {
                    type = "Video Call";
                } else if (schedule[day.ordinal()][time.ordinal()] instanceof Meeting) {
                    type = "Meeting";
                }

                result[index++] = day + "," + time + "," + title + "," + type;
            }
        }
        return result;
    }



    public static Workweek fromStringArray(String[] lines) throws MalformedStringParameterException {
        Workweek workweek = new Workweek();
        for (String line : lines) {
            String[] tokens = line.split("%%");
            Days day = Days.valueOf(tokens[1]);
            Times time = Times.valueOf(tokens[2]);

            if (tokens[0].equals("VIDEOCALL")) {
                workweek.schedule[day.ordinal()][time.ordinal()] = VideoCall.fromString(line);
            } else if (tokens[0].equals("MEETING")) {
                workweek.schedule[day.ordinal()][time.ordinal()] = Meeting.fromString(line);
            } else {
                throw new MalformedStringParameterException("Unknown event type");
            }
        }
        return workweek;
    }

    public void addEvent(Days day, Times time, String s) throws MalformedStringParameterException {
        if (s.startsWith("VIDEOCALL")) {
            this.schedule[day.ordinal()][time.ordinal()] = VideoCall.fromString(s);
        } else if (s.startsWith("MEETING")) {
            this.schedule[day.ordinal()][time.ordinal()] = Meeting.fromString(s);
        } else {
            throw new MalformedStringParameterException("Unknown event type");
        }
    }
}