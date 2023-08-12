package am.aua.hw04.ui;

import am.aua.hw04.core.*;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class EventAdditionDialog extends JDialog {

    private JTextField titleField;
    private JComboBox<String> typeDropdown;
    private JComboBox<Days> dayDropdown;
    private JComboBox<Times> timeDropdown;
    private final SchedulerGUI parentGUI;
    private JTextField emailField;
    private JTextField latitudeField;
    private JTextField longitudeField;

    public EventAdditionDialog(JFrame frame, SchedulerGUI parentGUI) {
        super(frame, "Add Event", true);
        this.parentGUI = parentGUI;
        initUI();
    }

    private void initUI() {
        setLayout(new GridLayout(7, 2));
        setSize(500, 400);
        setLayout(new GridLayout(5, 2));
        emailField = new JTextField();



        // Initializes UI components
        titleField = new JTextField();
        typeDropdown = new JComboBox<>(new String[]{"Video Call", "Meeting"});
        dayDropdown = new JComboBox<>(Days.values());
        timeDropdown = new JComboBox<>(Times.values());
        latitudeField = new JTextField();
        longitudeField = new JTextField();

        // Creates and configures buttons
        JButton addButton = createAddButton();
        JButton cancelButton = createCancelButton();

        populateDialog(addButton, cancelButton);
    }

    private JButton createAddButton() {
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            addEventToSchedule();
            parentGUI.refreshTableData();
            dispose();
        });
        return addButton;
    }

    private JButton createCancelButton() {
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        return cancelButton;
    }

    private void addEventToSchedule() {
        String title = titleField.getText();
        Days selectedDay = Objects.requireNonNullElse(dayDropdown.getItemAt(dayDropdown.getSelectedIndex()), Days.values()[0]);
        Times selectedTime = Objects.requireNonNullElse(timeDropdown.getItemAt(timeDropdown.getSelectedIndex()), Times.MORNING);

        if ("Video Call".equals(typeDropdown.getSelectedItem())) {
            String email = emailField.getText();
            parentGUI.getCurrentWorkweek().addToSchedule(new VideoCall(title, email), selectedDay, selectedTime);
        } else if ("Meeting".equals(typeDropdown.getSelectedItem())) {
            double latitude = Double.parseDouble(latitudeField.getText());
            double longitude = Double.parseDouble(longitudeField.getText());
            parentGUI.getCurrentWorkweek().addToSchedule(new Meeting(title, latitude, longitude), selectedDay, selectedTime);
        }
    }

    private void populateDialog(JButton addButton, JButton cancelButton) {
        addComponents(
                new JLabel("Title:"), titleField,
                new JLabel("Type:"), typeDropdown,
                new JLabel("Day:"), dayDropdown,
                new JLabel("Time:"), timeDropdown,
                new JLabel("Email (for Video Call):"), emailField,
                new JLabel("Latitude (for Meeting):"), latitudeField,
                new JLabel("Longitude (for Meeting):"), longitudeField,
                addButton, cancelButton
        );
    }


    private void addComponents(Component... components) {
        for (Component component : components) {
            add(component);
        }
    }
}
