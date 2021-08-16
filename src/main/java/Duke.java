import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String outputIndent = "    ";
    public static String textIndent = " ";
    public static String line = "____________________________________________________________";
    public static String logo = " ____        _        \n"
                              + "|  _ \\ _   _| | _____ \n"
                              + "| | | | | | | |/ / _ \\\n"
                              + "| |_| | |_| |   <  __/\n"
                              + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) throws IOException {
        System.out.println(outputIndent + line);
        System.out.println(outputIndent + textIndent + "Hello! I'm Duke");
        System.out.println(outputIndent + textIndent + "What can I do for you?");
        System.out.println(outputIndent + line);
        System.out.println();
        while (true) {
            String input = reader.readLine();
            if (input.equals("bye")) {
                System.out.println(outputIndent + line);
                System.out.println(outputIndent + textIndent + "Bye. Hope to see you again soon!");
                System.out.println(outputIndent + line);
                break;
            }

            System.out.println(outputIndent + line);
            System.out.println(outputIndent + textIndent + input);
            System.out.println(outputIndent + line);
            System.out.println();
        }
    }
}
