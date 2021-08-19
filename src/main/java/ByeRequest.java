/**
 * ByeRequest represents a request from the user to stop the application.
 */
public class ByeRequest extends Request {
    /**
     * Creates a ByeRequest.
     * @param taskCollection The target TaskCollection.
     */
    protected ByeRequest(TaskCollection taskCollection) {
        super(taskCollection);
    }

    /**
     * Gets the Action the ByeRequest requests to execute.
     * @return The Action to be executed.
     */
    @Override
    public Action action() {
        return new GoodbyeUser();
    }
}
