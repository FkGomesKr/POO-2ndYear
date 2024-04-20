import java.io.IOException;
import java.util.Scanner;

public class HistoricInterface {

    public HistoricInterface(User user) throws IOException, ClassNotFoundException {

        Historic line;

        if(user.getHistorics() == null){
            System.out.println("No active historics");
        }
        else {
            int size = user.getHistorics().size();
            for (int i = 0; i < size; i++) {
                line = user.getHistorics().get(i);
                System.out.println("-------------------------------------------------");
                System.out.println("Date:" + line.getDate());
                System.out.println("Time of Training:" + line.getTime() + " minutes");
                System.out.println("Total of Reps:" + line.getTotal_reps());
                for (int z = 0; z < line.getExercises().length; z++) {
                    System.out.println((z+1) + ": " + line.getExercises()[z].getName());
                }
                System.out.println("-------------------------------------------------");
            }
        }

        Utils utils = new Utils();
        Scanner scanner = new Scanner(System.in);

        utils.go_back();

        int choice = 0;
        while(choice != 1) {
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice != 1) utils.choose_valid();
        }
        new AppInterface(user);
    }

}
