import java.io.IOException;
import java.util.Scanner;

public class AppInterface {

    public AppInterface(User user) throws IOException, ClassNotFoundException {

        //IR BUSCAR AS FUNÇÕES
        HelpProgram help = new HelpProgram();
        Scanner scanner = new Scanner(System.in);
        Utils utils = new Utils();

        help.clean();

        utils.head_bar(user);
        utils.print_main_menu(user);

        //Scan app functional purpose/choice
        int option = 0;
        if (help.isAdmin(user)) {
            while (option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option != 6 & option != 7) {
                option = scanner.nextInt();
                scanner.nextLine();
                if (option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option != 6 && option != 7) utils.choose_valid();
            }
        } else {
            while (option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option != 6) {
                option = scanner.nextInt();
                scanner.nextLine();
                if (option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option != 6) utils.choose_valid();
            }
        }

        help.clean();

        if (option == 1) new Profile(user);
        else if (option == 2) new TrainingPlanInterface(user);
        else if (option == 3) new ExercisesActivities(user);
        else if (option == 4) new TrainSimulation(user);
        else if (help.isAdmin(user) && option == 5) new AppManipulation(user);
        else if (!help.isAdmin(user) && option == 5) new HistoricInterface(user);
        else if (help.isAdmin(user) && option == 6) new HistoricInterface(user);
        else new StartMenu();

        scanner.close();
    }
}
