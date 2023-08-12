package am.aua.hw04.ui;

import am.aua.hw04.core.Days;
import am.aua.hw04.core.Times;
import am.aua.hw04.core.Workweek;
import am.aua.hw04.utils.FileUtil;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SchedulerGUI {

    private JFrame frame;
    private JTable scheduleTable;
    private Workweek currentWorkweek = new Workweek();


    public SchedulerGUI() {
        initFrame();
        initMenuBar();
        initTable();
    }

    private void initFrame() {
        frame = new JFrame("Scheduler");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
    }

    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createEditMenu());
        menuBar.add(createFileMenu());
        frame.setJMenuBar(menuBar);
    }

    private JMenu createEditMenu() {
        JMenu editMenu = new JMenu("Edit");
        editMenu.add(new JMenuItem("Add Event")).addActionListener(e -> new EventAdditionDialog(frame, this).setVisible(true));
        return editMenu;
    }

    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(createMenuItem("Open", this::openFile));
        fileMenu.add(createMenuItem("Save", this::saveFile));
        fileMenu.add(new JMenuItem("New"));  // Placeholder for now
        fileMenu.add(createMenuItem("Exit", () -> frame.dispose()));
        return fileMenu;
    }

    private JMenuItem createMenuItem(String title, Runnable action) {
        JMenuItem menuItem = new JMenuItem(title);
        menuItem.addActionListener(e -> action.run());
        return menuItem;
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                String[] lines = FileUtil.loadStringsFromFile(selectedFile.getPath());
                currentWorkweek = Workweek.fromStringArray(lines);
                refreshTableData();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error loading the schedule file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                FileUtil.saveStringsToFile(currentWorkweek.toStringArray(), selectedFile.getPath());
                JOptionPane.showMessageDialog(frame, "Schedule saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error saving the schedule file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void initTable() {
        String[] columns = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        Object[][] data = new Object[2][5];
        scheduleTable = new JTable(data, columns);
        frame.add(new JScrollPane(scheduleTable), BorderLayout.CENTER);
    }

    public void refreshTableData() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                scheduleTable.setValueAt(currentWorkweek.getTitleAt(Days.values()[j], Times.values()[i]), i, j);
            }
        }
    }

    public Workweek getCurrentWorkweek() {
        return currentWorkweek;
    }

    public void show() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SchedulerGUI().show());
    }
}
