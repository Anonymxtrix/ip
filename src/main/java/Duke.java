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

    public static String TASK_COLLECTION_STORAGE_PATH = "./data/duke.txt";
    private static final TaskCollection tasks = new TaskCollection(TASK_COLLECTION_STORAGE_PATH);
    private static final Queue<Action> actions = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        Duke.actions.add(new WelcomeUser());

        while (true) {
            try {
                if (Duke.actions.isEmpty()) {
                    String input = Duke.getUserInput();
                    Request request = Request.create(Duke.tasks, input);
                    Duke.actions.add(request.action());
                }

                Action action = Duke.actions.remove();
                Response response = action.execute();
                Duke.tasks.saveTasks();
                Duke.printResponse(response);

                if (action instanceof GoodbyeUser) {
                    break;
                }
            } catch (UserException exception) {
                Duke.printResponse(exception.toResponse());
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
