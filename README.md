# Scheduler Java Project Overview

The Scheduler Java project is a versatile scheduling application designed to manage weekly schedules effectively. Utilizing the principles of object-oriented programming, this project showcases polymorphism, encapsulation, and file I/O operations to create a user-friendly scheduling tool.

## Key Features and Design Choices:

- **Schedulable Interface and Implementing Classes:** The core of the project is the `Schedulable` interface, which standardizes how different types of activities, such as "Meeting" and "VideoCall," are handled. This approach ensures that the system can easily be extended with new types of schedulable activities without altering the existing codebase.

- **Workweek Class:** At the heart of the scheduling logic is the `Workweek` class, which manages the schedule for a week. The class uses a two-dimensional array to store schedule information, providing methods like `addToSchedule` and `getTitleAt` to manipulate and retrieve the schedule data.

- **FileUtil Class:** To ensure schedules can be persisted and retrieved between sessions, the `FileUtil` class offers utility methods for file input/output operations. Methods such as `saveStringsToFile` and `loadStringsFromFile` make it easy to save schedules to files and load them back into the application.

- **SchedulerConsole Class:** Interacting with users is handled by the `SchedulerConsole` class, which has been designed to facilitate operations like loading and saving schedules, adding new items, and managing invalid inputs. This class ensures a seamless user experience by providing a clear and straightforward command-line interface.

- **Spring Framework for GUI:** To enhance the user interface, the project utilizes the Spring framework for developing a graphical user interface (GUI). This choice allows for a more interactive and accessible way for users to manage their schedules.

## Getting Started

This project is ideal for individuals looking to manage their schedules or developers seeking a foundational project for understanding object-oriented design and file manipulation in Java. Clone the repository, dive into the code, and see how Java's powerful features can be used to build a practical and extendable scheduling tool.
