import java.io.IOException;
import java.util.Scanner;

public class Profile {

    public Profile(User user) throws IOException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        Utils utils = new Utils();

        utils.profile(user);

        int choice = 0;
        while(choice != 1 && choice != 2) {
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice != 1 && choice !=2) utils.choose_valid();
        }
        if(choice == 1) new AppInterface(user);
        else utils.delete_account();
    }
}
