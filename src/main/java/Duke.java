import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;

public class Duke {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String LOGO = " ____        _        \n"
                              + "|  _ \\ _   _| | _____ \n"
                              + "| | | | | | | |/ / _ \\\n"
                              + "| |_| | |_| |   <  __/\n"
                              + "|____/ \\__,_|_|\\_\\___|\n";

    private static final TaskCollection tasks = new TaskCollection();
    private static final Queue<Action> actions = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        Duke.actions.add(new WelcomeUser());

        while (true) {
            if (Duke.actions.isEmpty()) {
                String input = Duke.getUserInput();
                String[] inputSubsections = input.split("\\s", 2);
                String command = inputSubsections[0];

                Action action;
                switch (command) {
                    case "bye": {
                        action = new GoodbyeUser();
                        break;
                    }
                    case "list": {
                        action = new ListTasks(Duke.tasks);
                        break;
                    }
                    case "done": {
                        int itemNumber = Integer.parseInt(inputSubsections[1]);
                        Task task = Duke.tasks.get(itemNumber);
                        action = new CompleteTask(task);
                        break;
                    }
                    case "deadline": {
                        String BY_DELIMITER = " /by ";
                        String[] deadlineInputSubsections = inputSubsections[1].split(BY_DELIMITER);
                        String description = deadlineInputSubsections[0];
                        String by = deadlineInputSubsections[1];
                        Task deadline = new Deadline(description, by);
                        action = new AddTask(deadline, Duke.tasks);
                        break;
                    }
                    case "event": {
                        String AT_DELIMITER = " /at ";
                        String[] eventInputSubsections = inputSubsections[1].split(AT_DELIMITER);
                        String description = eventInputSubsections[0];
                        String at = eventInputSubsections[1];
                        Task event = new Event(description, at);
                        action = new AddTask(event, Duke.tasks);
                        break;
                    }
                    case "todo": {
                        String description = inputSubsections[1];
                        Task toDo = new ToDo(description);
                        action = new AddTask(toDo, Duke.tasks);
                        break;
                    }
                    default: {
                        Task newTask = new Task(inputSubsections[1]);
                        action = new AddTask(newTask, Duke.tasks);
                        break;
                    }
                }
                Duke.actions.add(action);
            }

            Action action = Duke.actions.remove();
            Response response = action.execute();
            Duke.printResponse(response);

            if (action instanceof GoodbyeUser) {
                break;
            }
        }
    }

    /**
     * Prints the Response to the console.
     * @param response The Response to be printed.
     */
    public static void printResponse(Response response) {
        System.out.println(response.toString());
    }

    /**
     * Prompts the user for an input.
     * @return The input string
     * @throws IOException If an I/O error occurs
     */
    public static String getUserInput() throws IOException {
        System.out.println();
        return reader.readLine();
    }
}
