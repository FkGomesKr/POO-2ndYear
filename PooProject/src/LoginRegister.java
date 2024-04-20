import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoginRegister {

    HelpProgram help = new HelpProgram();

    @SuppressWarnings("unchecked")
    public void login() throws IOException, ClassNotFoundException {

        User useratlogin = null; //potential user logging in
        Scanner scanner = new Scanner(System.in);
        int whatsHappening = 5;
        String[] values = new String[7];
        Utils utils = new Utils();

        while(whatsHappening != 1 && whatsHappening != 4){

            //VARIAVEIS////////////////////////////////////////
            int choiceAgain = 0;
            String user_code;

            int found = 0;
            ////////////////////////////////////////////////////

            //PEDE O USERNAME AO USER///////////////////////////
            utils.ask_username();
            user_code = scanner.nextLine();
            ////////////////////////////////////////////////////
            Serialization serialize = new Serialization(); //Initialize serialization class
            serialize.setFile("users.ser"); //set file name to (de)serialize
            try {
                Object obj = serialize.getObject(); // Read the deserialized object
                // Check if the deserialized object is indeed a Map<String, User>
                if (obj instanceof Map) {

                    Map<String, User> usersMap = (Map<String, User>) obj;
                    useratlogin = usersMap.get(user_code); //apply the user_code, the key, we received as input to search the user in the hashmap

                    if (useratlogin != null) {
                        found = 1; // user exists
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                // IOException occurred
                e.printStackTrace();
            }
            if (found == 0) {
                whatsHappening = 2;
            } else {
                Console cons = System.console();
                if (cons != null) {
                    char[] passwordChars = cons.readPassword("Password:");
                    String password = new String(passwordChars);
                    if (password.equals(useratlogin.getPassword())) {
                        whatsHappening = 1;
                    } else {
                        whatsHappening = 3;
                    }
                    java.util.Arrays.fill(password.toCharArray(), ' ');
                }
            }

            /////////////////////////////////////////////////////

            //CASO TENHA FALHADO O LOGIN/////////////////////////
            if (whatsHappening == 2 || whatsHappening == 3){
                help.clean();
                if(whatsHappening == 2) utils.user_notfound();
                else utils.password_wrong();
               utils.try_again();
                while(choiceAgain != 1 && choiceAgain != 2 && choiceAgain != 3) {
                    choiceAgain = scanner.nextInt();
                    scanner.nextLine();
                    help.clean();
                    if (choiceAgain != 1 && choiceAgain !=2 && choiceAgain != 3) utils.choose_valid();
                }
                if(choiceAgain == 2) whatsHappening = 4;  // VAI PARA O SIGN UP
                else if(choiceAgain == 3) System.exit(0); // FECHA O PROGRAMA
            }
            //////////////////////////////////////////////////////
        }

        //PARA FINALIZAR O PROCESSO//////////////////////////////
        help.clean();
        if(whatsHappening == 1) new AppInterface(useratlogin);
        else register();
        /////////////////////////////////////////////////////////
        scanner.close();
    }

    @SuppressWarnings("unchecked")
    public void register() throws IOException, ClassNotFoundException {

        int restart = 1;

        Scanner scanner = new Scanner(System.in);
        String[] values = new String[8];
        Utils utils = new Utils();

        int found = 1;
        User user;

        while (restart ==1){

            String username = "";
            values[4] = "okolokdas";
            values[5] = "dasd";

            utils.welcome_username();
            while (found != 0){

                username = scanner.nextLine();
                help.clean();

                Serialization serialize = new Serialization(); //Initialize serialization class
                serialize.setFile("users.ser"); //set file name to (de)serialize

                try {
                    Object obj = serialize.getObject(); // Read the deserialized object
                    // Check if the deserialized object is indeed a Map<String, User>
                    if (obj instanceof Map) {

                        Map<String, User> usersMap = (Map<String, User>) obj;
                        user = usersMap.get(username); //apply the user_code, the key, we received as input to search the user in the hashmap

                        if (user == null) found = 0;
                        else {
                            System.out.println("Username already exists.");
                            System.out.print("Step 1 - Username:");
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    // IOException occurred
                    e.printStackTrace();
                }
            }
            values[0] = username;

            utils.get_password();
            values[1] = scanner.nextLine();
            help.clean();
            utils.get_fullname();
            values[2] = scanner.nextLine();
            help.clean();
            utils.get_adress();
            values[3] = scanner.nextLine();
            help.clean();
            while(!(help.validateEmail(values[4]))){
                utils.get_email();
                values[4] = scanner.nextLine();
                help.clean();
            }
            while(!(help.isValidNumber(values[5]))){
                utils.get_Heartrate();
                values[5] = scanner.nextLine();
                help.clean();
            }
            utils.get_weight();
            values[6] = scanner.nextLine();
            help.clean();
            utils.exercice_level();
            int choice = 0;
            while (choice != 1 && choice != 2 && choice != 3) {
                choice = scanner.nextInt();
                scanner.nextLine();
                help.clean();
            }
            if (choice == 1) {
                values[7] = "Ocasional Exercise";
            } else if (choice == 2) {
                values[7] = "Amateur";
            } else {
                values[7] = "Professional";
            }
            help.clean();

            //CONFIRMAR INFORMAÇÕES////////////////////////////////////
            help.clean();
            utils.data_confirmation(values);
            int ok = 0;
            while (ok != 1 && ok != 2) {
                ok = scanner.nextInt();
                scanner.nextLine();
                if (ok != 1 && ok !=2) utils.choose_valid();
                help.clean();
            }
            if (ok == 1) restart = 0;
            ////////////////////////////////////////////////////////////
        }

        // User that we're gonna add into the HashMap before serializing
        User novoUser = new User(values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7]);
        Serialization serialize = new Serialization();
        serialize.setFile("users.ser");
        try {
            Map<String, User> usersMap;
            Object obj = serialize.getObject();
            if (obj instanceof Map) {
                usersMap = (Map<String, User>) obj;
            } else {
                usersMap = new HashMap<>();
            }

            usersMap.put(novoUser.getUserId(), novoUser);
            serialize.writeObject(usersMap);

        } catch (EOFException e) {
            // Handle empty file
            Map<String, User> usersMap = new HashMap<>();
            usersMap.put(novoUser.getUserId(), novoUser);

            try {
                serialize.writeObject(usersMap);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        ///////////////////////////////////////////////////////////

        //PARA FINALIZAR//////////////////////////////////////////
        utils.singup_login();
        int login = 0;
        while (login != 1 && login != 2) {
            login = scanner.nextInt();
            scanner.nextLine();
            if (login != 1 && login !=2) utils.choose_valid();
            help.clean();
        }
        if (login == 1) login();
        else System.exit(0);
        //////////////////////////////////////////////////////////
        scanner.close();
    }


}
