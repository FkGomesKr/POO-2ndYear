import java.io.IOException;
import java.util.Scanner;

public class StartMenu {
    HelpProgram help = new HelpProgram();

    public StartMenu() throws IOException, ClassNotFoundException {

        //IR BUSCAR AS FUNÇÕES
        Scanner scanner = new Scanner(System.in);
        LoginRegister funcs = new LoginRegister();
        Utils utils = new Utils();

        utils.print_login_signup();

        //SCANNEAR SE É LOGIN OU REGISTO
        int option = 0;
        while(option != 1 && option != 2 && option != 3) {
            option = scanner.nextInt();
            scanner.nextLine();
            if (option != 1 && option !=2 && option != 3) utils.choose_valid();
        }
        help.clean();
        if (option == 1) funcs.login();
        else if (option == 2) funcs.register();
        else System.exit(0);

        scanner.close();
    }
}
