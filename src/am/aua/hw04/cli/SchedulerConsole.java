package am.aua.hw04.cli;

import am.aua.hw04.core.*;
import am.aua.hw04.utils.FileUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class SchedulerConsole {
    private static final Scanner sc = new Scanner(System.in);

    public static void start() {
        Workweek w = new Workweek();
        printWeek(w);

        while (true) {
            String action = promptForAction();

            switch (action.toUpperCase()) {
                case "Q":
                    System.exit(0);
                    break;
                case "P":
                    printWeek(w);
                    break;
                case "C":
                    w = new Workweek();
                    break;
                case "A":
                    handleAddition(w);
                    break;
                case "L":
                    handleLoad(w);
                    break;
                case "S":
                    handleSave(w);
                    break;
            }
        }
    }

    private static String promptForAction() {
        System.out.println("\nChoose an action:");
        System.out.println("A. Add a new item to the schedule");
        System.out.println("L. Load schedule from file");
        System.out.println("S. Save current schedule to file");
        System.out.println("P. Print schedule");
        System.out.println("C. Clear schedule");
        System.out.println("Q. Quit");

        return sc.nextLine();
    }

    private static void handleAddition(Workweek w) {
        System.out.println("Choose one of the following to add to the schedule: ");
        System.out.println("C. Video Call");
        System.out.println("M. Meeting");
        System.out.println("X. Cancel");

        String choice = sc.nextLine();
        switch (choice.toUpperCase()) {
            case "M":
                addMeeting(w);
                break;
            case "C":
                addVideoCall(w);
                break;
            case "X":
                break;
        }
    }

    private static void addMeeting(Workweek w) {
        // Meeting addition logic
    }

    private static void addVideoCall(Workweek w) {
        // Video Call addition logic
    }

    private static void handleLoad(Workweek w) {
        String path = promptForPath("Enter the file path to load the schedule (press enter to use the default path):", "default_load_path.txt");
    }

    private static void handleSave(Workweek w) {
        String path = promptForPath("Enter the file path to save the schedule (press enter to use the default path):", "default_save_path.txt");
    }

    private static String promptForPath(String prompt, String defaultPath) {
        System.out.println(prompt);
        String path = sc.nextLine();
        if (path.isEmpty()) {
            path = defaultPath;
        }
        return path;
    }

    private static void printWeek(Workweek w) {
        System.out.println("\t\t\t\tMON\t\t\tTUE\t\t\tWED\t\t\tTHU\t\t\tFRI");
        System.out.print("MORNING\t\t\t");
        for (int i = 0; i < Days.values().length; i++) {
            System.out.print(beautifyTitle(w.getTitleAt(Days.values()[i], Times.MORNING)));
        }
        System.out.println();
        System.out.print("AFTERNOON");
        for (int i = 0; i < Days.values().length; i++) {
            System.out.print(beautifyTitle(w.getTitleAt(Days.values()[i], Times.AFTERNOON)));
        }
    }

    private static String beautifyTitle(String t) {
        if (t == null || t.isEmpty()) {
            return "\t\t\t";
        }
        return t.substring(0, Math.min(8, t.length()));
    }
}
