# Scheduler

Design Choices 

Schedulable Interface and Implementing Classes: 
The Schedulable interface allows us to treat different types of activities (in this case,
“Meeting” and `VideoCall`) in the same way. This design choice adheres to the principle
of polymorphism in object-oriented programming.

Workweek Class: 
The `Workweek` class was designed to handle the schedule for a week. In new version, a two-dimensional array was introduced to represent the schedule. The `addToSchedule` and `getTitleAt` methods were also implemented to add items to the schedule and retrieve titles of events, respectively.

FileUtil Class: 
In order to save or load schedules to and from files. To
address this, a new `FileUtil` class was introduced. This class provides two static
methods, `saveStringsToFile` and `loadStringsFromFile`, which handle file I/O
operations.


SchedulerConsole Class: 
The `SchedulerConsole` class was designed to handle user interactions. 
The class was expanded to handle  operations such as loading and saving schedules, adding
new items to the schedule, and handling invalid user inputs.


** For GUI used Spring framework
