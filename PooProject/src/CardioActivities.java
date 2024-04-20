import java.io.IOException;
import java.util.Scanner;

public class CardioActivities {

    CardioActivities(User user) throws IOException, ClassNotFoundException {

        //IR BUSCAR AS FUNÇÕES
        HelpProgram help = new HelpProgram();
        Scanner scanner = new Scanner(System.in);
        Utils utils = new Utils();

        help.clean();

        utils.type_cardio();

        //SCANNEAR SE É LOGIN OU REGISTO
        int option = 0;
        while (option < 1 || option > 3) {
            option = scanner.nextInt();
            scanner.nextLine();
            if (option < 1 || option > 3) utils.choose_valid();
        }

        if (option == 1)
            utils.distance_altimetry();
        else if (option == 2)
            utils.distance();
        else
            utils.other_cardio_act();

        //GO BACK///////////////////////////////////////////////////////////////////////////////////////
        utils.go_back();
        int goback = 0;
        while(goback != 1) {
            goback = scanner.nextInt();
            scanner.nextLine();
            if (goback != 1) utils.choose_valid();
        }
        new ExercisesActivities(user);
        ///////////////////////////////////////////////////////////////////////////////////////////////

        scanner.close();

    }
}
