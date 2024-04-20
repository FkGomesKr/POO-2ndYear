import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class AppStats {

    @SuppressWarnings("unchecked")
    public AppStats(User user) throws IOException, ClassNotFoundException {
        //IR BUSCAR AS FUNÇÕES
        HelpProgram help = new HelpProgram();
        Scanner scanner = new Scanner(System.in);
        Utils utils = new Utils();


        help.clean();
        utils.print_app_stats();

        try {
            Serialization serialize = new Serialization();
            serialize.setFile("users.ser");
            Object obj = serialize.getObject();
            if (obj instanceof Map) {
                Map<String, User> usersMap = (Map<String, User>) obj;
                System.out.println("Já somos " + usersMap.size() + " utilizadores na POWER PEAK!");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("1 - Go Back");

        //Scan app stats functional purpose/choice
        int option = 0;
        while (option != 1) {
            option = scanner.nextInt();
            scanner.nextLine();
            if (option != 1) System.out.println("Please choose a valid option.");
        }
        new AppManipulation(user);
    }
}
