package am.aua.hw04;

import am.aua.hw04.cli.SchedulerConsole;
import am.aua.hw04.ui.SchedulerGUI;
import am.aua.hw04.core.Workweek;
import am.aua.hw04.utils.FileUtil;

import java.io.IOException;

public class Main {
    public static final String VERSION = "1.0";

    public static void main(String[] args) {
        if (args.length == 0) {
            SchedulerConsole.start();
            return;
        }

        String command = args[0];
        switch (command) {
            case "-v":
            case "--version":
                System.out.println(VERSION);
                break;
            case "-h":
            case "--help":
                printHelp();
                break;
            case "-path":
                if (args.length < 2) {
                    System.out.println("Please specify a path to a schedule.");
                    return;
                }
                String path = args[1];
                loadSchedule(path);
                break;
            case "-ui":
                // Launching in GUI mode.
                javax.swing.SwingUtilities.invokeLater(() -> {
                    SchedulerGUI gui = new SchedulerGUI();
                    gui.show();
                });
                break;
            default:
                System.out.println("Unknown command: " + command);
                printHelp();
        }
    }

    private static void printHelp() {
        System.out.println("Usage: java -jar hwn4.jar [OPTIONS]");
        System.out.println("Options:");
        System.out.println("  -v, --version       Print the version of the application.");
        System.out.println("  -h, --help          Print this help message.");
        System.out.println("  -path <file_path>   Load the given schedule file.");
        System.out.println("  -ui                 Launch the application in GUI mode.");
    }

    private static void loadSchedule(String path) {
        try {
            Workweek ww = FileUtil.load(path);
            // starts the console after loading the workweek:
            SchedulerConsole.start();
        } catch (IOException e) {
            System.out.println("Error loading the schedule file: " + e.getMessage());
        }
    }
}
