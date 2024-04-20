import java.io.IOException;
import java.util.Scanner;

public class ExercisesActivities {

    public ExercisesActivities(User user) throws IOException, ClassNotFoundException {

        //IR BUSCAR AS FUNÇÕES
        HelpProgram help = new HelpProgram();
        Scanner scanner = new Scanner(System.in);
        Utils utils = new Utils();
        StrengthExercises se = new StrengthExercises();

        help.clean();

        utils.choose_strenght_activities();

        //SCANNEAR SE É LOGIN OU REGISTO
        int option = 0;
        while(option != 1 && option != 2 && option != 3) {
            option = scanner.nextInt();
            scanner.nextLine();
            if (option != 1 && option !=2 && option != 3) utils.choose_valid();
        }

        if (option == 1) {
            Exercise ex = se.strengthExercises(user);
        }
        else if (option == 2) new CardioActivities(user);
        else new AppInterface(user);

        scanner.close();
    }

}
