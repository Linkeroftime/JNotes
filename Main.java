import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
  // declare tasks as a class variable
 
  private static ArrayList<String> tasks = new ArrayList<String>();

  // had to define the scanner globally to avoid a NoSuchElementException that
  // seemed to have been caused by opening and closing the scanner prematurely
  private static Scanner scanner = new Scanner(System.in);

  // declare writeTask method as static and return an ArrayList value
  public static void writeTasks() {

    System.out.println("Enter a task to get started! Press enter without entering any value to exit.");

    // Initiates a loop so then every input the user enters becomes a task.
    while (true) {

      // assign the input to a variable (task)
      String task = scanner.nextLine();

      // Checks if the task is an empty string, and exits if it is, otherwise appends
      // to tasks.
      if (!Objects.equals(task, "")) {
        // append the task to the ArrayList tasks
        tasks.add(task);
      } else {
        // exits the loop
        break;
      }
    }
  }

  // prints tasks out
  // (Was not certain of how to make this method. Usually methods do not have
  // void, and I spent a lot of time before I figured out that I just needed to
  // print it out to the user.)
  public static void printTasks() {
    // catching a potential error in case the user chooses to view the tasks first.
    if (tasks.isEmpty()) {
      System.out.println("There are no tasks to print!");
    } else {
      // prints out tasks
      System.out.println("\nHere are your tasks: ");
      for (int i = 0; i < tasks.size(); i++) {
        System.out.println((i + 1) + ". " + tasks.get(i));
      }
    }

  }

  public static void deleteTask() {
    // handles if there are no tasks to be deleted
    if (tasks.isEmpty()) {
      System.out.println("There are no tasks to delete!");
      return;
    }

    // prints out tasks to see number of tasks
    printTasks();

    // prompts user for the number to be deleted
    System.out.println("Enter the number of a task to be deleted: ");
    String input = scanner.nextLine();

    // allows user to cancel and return to the original prompt
    if (input.isEmpty()) {
      System.out.println("Deleting task cancelled. Returning...");
      return;
    }

    int taskToDelete;

    // catches invalid inputs, decided to include this after some troubleshooting
    try {
      taskToDelete = Integer.parseInt(input);
    } catch (NumberFormatException e) {
      System.out.println("Invalid input. Please enter a number.");
      return;
    }

    // checks to see if number is within the task list
    if (taskToDelete < 1 || taskToDelete > tasks.size()) {
      if (tasks.size() == 1) {
        System.out.println("Invalid input. There is only 1 task.");
      } else {
      System.out.println("Please enter a valid number between 1 and " + (tasks.size()));
      }
      return;
    }

    // Deletes the task
    // (had to print the message before removing the task, since the index of the
    // list is updated)
    System.out.println(tasks.get(taskToDelete - 1) + " has been deleted!");
    tasks.remove(taskToDelete - 1);
  }

  public static void main(String[] args) {

    // Greeting
    System.out.println(
        "Welcome to JNotes! Here, you can write down tasks to do and cross them off as you go! Let your productivity dreams ring true!");

    // enters a loop that cannot be broken until the user chooses to exit.
    while (true) {
      System.out.println("\nWould you like to: \n1. Add a task\n2. View tasks\n3. Delete a task\n4. Exit");

      int option;

      // used a try - catch statement to catch a NoSuchElementException after
      // troubleshooting for a long time.
      try {
        option = Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a valid number.");
        continue;
      }

      // Spent some time designing this part, as I am not super familiar with switch
      // statements.
      switch (option) {
        case 1:
          writeTasks();
          break;
        case 2:
          printTasks();
          break;
        case 3:
          deleteTask();
          break;
        case 4:
          System.out.println("Thank you for using JNotes. Goodbye!");
          scanner.close();
          return;
        default:
          System.out.println("Please enter a valid number from 1-4");
      }
    }
  }
}
