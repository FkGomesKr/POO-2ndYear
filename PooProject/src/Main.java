import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        HelpProgram help = new HelpProgram();

        help.clean();

        new StartMenu();

    }
}