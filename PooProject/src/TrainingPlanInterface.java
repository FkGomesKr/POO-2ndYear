import java.io.IOException;
import java.util.Scanner;

public class TrainingPlanInterface {

    public TrainingPlanInterface(User user) throws IOException, ClassNotFoundException {

        //IR BUSCAR AS FUNÇÕES
        HelpProgram help = new HelpProgram();
        Scanner scanner = new Scanner(System.in);
        CreateTrainingPlan create = new CreateTrainingPlan();

        help.clean();

        System.out.println("--------------------------------------------------------------");
        System.out.println("List of Exercises and Activities.Please choose one option:");
        System.out.println("--------------------------------------------------------------");
        System.out.println("1 - Get New Training Plan"); //Receber um plano de treino randomizado
        System.out.println("2 - Create New Training Plan"); //Criar um plano de treino
        System.out.println("3 - My Training Plan"); //Vai receber um plano de treino
        System.out.println("4 - Go Back");
        System.out.println("--------------------------------------------------------------");

        int option = 0;
        while(option < 1 || option > 4) {
            option = scanner.nextInt();
            scanner.nextLine();
            if (option < 1 || option > 4) System.out.println("Please choose a valid option.");
        }

        if (option == 1) create.getPlan(user);
        else if (option == 2) create.createPlan(user);
        else if (option == 3) new MyTrainingPlan(user);
        else new AppInterface(user);

        scanner.close();
    }

}
