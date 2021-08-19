/**
 * Request represents a request from the user to perform an action on a collection of tasks.
 */
public abstract class Request {
    private static final String COMMAND_DELIMITER = "\\s";

    protected TaskCollection taskCollection;

    /**
     * Creates a Request to perform actions on a TaskCollection.
     * @param taskCollection The target TaskCollection.
     */
    protected Request(TaskCollection taskCollection) {
        this.taskCollection = taskCollection;
    }

    /**
     * Gets the Action the Request requests to execute.
     * @return The Action to be executed.
     */
    public abstract Action action();

    /**
     * Creates the Request related to the input requestString.
     * @param taskCollection The TaskCollection to perform the Request on.
     * @param requestString The request String.
     * @return The Request related to the request String and TaskCollection.
     */
    public static Request create(TaskCollection taskCollection, String requestString) {
        String[] substrings = requestString.split(COMMAND_DELIMITER, 2);
        String commandString = substrings[0];
        String commandInput = substrings.length > 1 ? substrings[1] : "";
        Command command = Command.parseFrom(commandString);

        switch (command) {
            case BYE:
                return new ByeRequest(taskCollection);
            default:
                return new ByeRequest(taskCollection);

            case DONE:
                return new DoneRequest(taskCollection, commandInput);
            case LIST:
                return new ListRequest(taskCollection);

            case DEADLINE:
                return new DeadlineRequest(taskCollection, commandInput);
            case EVENT:
                return new EventRequest(taskCollection, commandInput);
            case TODO:
                return new ToDoRequest(taskCollection, commandInput);
        }
    }
}
