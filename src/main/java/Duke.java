import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String OUTPUT_INDENT = "    ";
    public static String TEXT_INDENT = " ";
    public static String LINE = "____________________________________________________________";
    public static String LOGO = " ____        _        \n"
                              + "|  _ \\ _   _| | _____ \n"
                              + "| | | | | | | |/ / _ \\\n"
                              + "| |_| | |_| |   <  __/\n"
                              + "|____/ \\__,_|_|\\_\\___|\n";

    public static String[] welcomeMessages = {"Hello! I'm Duke", "What can I do for you?"};
    public static String[] goodbyeMessages = {"Bye. Hope to see you again soon!"};

    private static final List<Task> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Duke.printMessages(welcomeMessages);

        while (true) {
            String input = Duke.getUserInput();
            String[] inputSubsections = input.split("\\s", 2);
            String command = inputSubsections[0];
            
            if (command.equals("bye")) {
                Duke.printMessages(goodbyeMessages);
                break;
            }

            if (command.equals("list")) {
                int numberOfItems = Duke.list.size();
                String[] listMessages = new String[numberOfItems + 1];
                listMessages[0] = "Here are the tasks in your list:";
                for (int index = 0; index < numberOfItems; index++) {
                    Task task = Duke.list.get(index);
                    listMessages[index + 1] = (index + 1) + ".[" + task.getStatusIcon() + "] " + task.description;
                }
                Duke.printMessages(listMessages);
                continue;
            }

            if (command.equals("done")) {
                int itemNumber = Integer.parseInt(inputSubsections[1]);
                Task task = Duke.list.get(itemNumber - 1);
                task.isDone = true;
                String[] doneMessages = new String[]{
                        "Nice! I've marked this task as done: ",
                        "  [" + task.getStatusIcon() + "] " + task.description
                };
                Duke.printMessages(doneMessages);
                continue;
            }

            Duke.list.add(new Task(input));
            String[] defaultMessages = { "added: " + input };
            Duke.printMessages(defaultMessages);
        }
    }

    /**
     * Prints an array of messages to the console.
     * @param messages The array of messages to be printed.
     */
    public static void printMessages(String[] messages) {
        System.out.println(Duke.OUTPUT_INDENT + Duke.LINE);
        for (String message : messages) {
            System.out.println(Duke.OUTPUT_INDENT + Duke.TEXT_INDENT + message);
        }
        System.out.println(Duke.OUTPUT_INDENT + Duke.LINE);
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
