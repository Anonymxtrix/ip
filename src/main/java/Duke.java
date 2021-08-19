import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String LOGO = " ____        _        \n"
                              + "|  _ \\ _   _| | _____ \n"
                              + "| | | | | | | |/ / _ \\\n"
                              + "| |_| | |_| |   <  __/\n"
                              + "|____/ \\__,_|_|\\_\\___|\n";

    public static Response welcomeResponse = new Response(new String[]{ "Hello! I'm Duke", "What can I do for you?" });
    public static Response goodbyeResponse = new Response(new String[]{ "Bye. Hope to see you again soon!" });

    private static final TaskCollection tasks = new TaskCollection();

    public static void main(String[] args) throws IOException {
        Duke.printResponse(Duke.welcomeResponse);

        while (true) {
            String input = Duke.getUserInput();
            String[] inputSubsections = input.split("\\s", 2);
            String command = inputSubsections[0];
            
            if (command.equals("bye")) {
                Duke.printResponse(Duke.goodbyeResponse);
                break;
            }

            if (command.equals("list")) {
                Action listTasks = new ListTasks(Duke.tasks);
                Response listResponse = listTasks.execute();
                Duke.printResponse(listResponse);
                continue;
            }

            if (command.equals("done")) {
                int itemNumber = Integer.parseInt(inputSubsections[1]);
                Task task = Duke.tasks.get(itemNumber);
                Action completeTask = new CompleteTask(task);
                Response doneResponse = completeTask.execute();
                Duke.printResponse(doneResponse);
                continue;
            }

            if (command.equals("deadline")) {
                String BY_DELIMITER = " /by ";
                String[] deadlineInputSubsections = inputSubsections[1].split(BY_DELIMITER);
                String description = deadlineInputSubsections[0];
                String by = deadlineInputSubsections[1];
                Task deadline = new Deadline(description, by);
                Action addTask = new AddTask(deadline, Duke.tasks);
                Response deadlineResponse = addTask.execute();
                Duke.printResponse(deadlineResponse);
                continue;
            }

            if (command.equals("event")) {
                String AT_DELIMITER = " /at ";
                String[] eventInputSubsections = inputSubsections[1].split(AT_DELIMITER);
                String description = eventInputSubsections[0];
                String at = eventInputSubsections[1];
                Task event = new Event(description, at);
                Action addTask = new AddTask(event, Duke.tasks);
                Response eventResponse = addTask.execute();
                Duke.printResponse(eventResponse);
                continue;
            }

            if (command.equals("todo")) {
                String description = inputSubsections[1];
                Task toDo = new ToDo(description);
                Action addTask = new AddTask(toDo, Duke.tasks);
                Response toDoResponse = addTask.execute();
                Duke.printResponse(toDoResponse);
                continue;
            }

            Task newTask = new Task(input);
            Action addTask = new AddTask(newTask, Duke.tasks);
            Response defaultResponse = addTask.execute();
            Duke.printResponse(defaultResponse);
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
