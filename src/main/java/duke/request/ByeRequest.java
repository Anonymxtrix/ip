package duke.request;

import duke.action.Action;
import duke.action.GoodbyeUser;

/**
 * duke.request.ByeRequest represents a request from the user to stop the application.
 */
public class ByeRequest extends Request {
    /**
     * Gets the Action the duke.request.ByeRequest requests to execute.
     * @return The Action to be executed.
     */
    @Override
    public Action action() {
        return new GoodbyeUser();
    }
}
