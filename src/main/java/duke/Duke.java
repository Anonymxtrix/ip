package duke;

import java.util.LinkedList;
import java.util.Queue;

import duke.action.Action;
import duke.action.GoodbyeUser;
import duke.action.WelcomeUser;
import duke.exception.UserException;
import duke.request.Request;
import duke.task.TaskCollection;

/**
 * Duke is the class that represents the entire command line application.
 */
public class Duke {
    private static final String TASK_COLLECTION_STORAGE_PATH = "./data/duke.txt";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private final Ui userInterface = new Ui();
    private final TaskCollection tasks = new TaskCollection(TASK_COLLECTION_STORAGE_PATH);
    private final Queue<Action> actions = new LinkedList<>();

    /**
     * Launches the Duke application.
     */
    public void launch() {
        actions.add(new WelcomeUser());

        while (true) {
            try {
                if (actions.isEmpty()) {
                    String input = userInterface.getUserInput();
                    Request request = Request.create(tasks, input);
                    actions.add(request.action());
                }

                Action action = actions.remove();
                Response response = action.execute();
                tasks.saveTasks();
                userInterface.printResponse(response);

                if (action instanceof GoodbyeUser) {
                    break;
                }
            } catch (UserException exception) {
                userInterface.printResponse(exception.toResponse());
            }
        }
    }

    /**
     * Starts the Duke application.
     * @param args Arguments passed when running the function using java.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.launch();
    }
}
