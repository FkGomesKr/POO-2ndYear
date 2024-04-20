import java.io.EOFException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AdminDatabases {

    @SuppressWarnings("unchecked")
    public AdminDatabases(User user) throws IOException, ClassNotFoundException {
        //IR BUSCAR AS FUNÇÕES
        HelpProgram help = new HelpProgram();
        Scanner scanner = new Scanner(System.in);
        Utils utils = new Utils();
        Serialization serializeusers = new Serialization();
        Serialization serializeexercises = new Serialization();
        serializeusers.setFile("users.ser");
        serializeexercises.setFile("exercises.ser");

        help.clean();

        utils.print_app_AdminExercises();

        //Scan app manipulation functional purpose/choice
        int option = 0;
        while (option != 1 && option != 2 && option != 3 && option != 4 && option != 5) {
            option = scanner.nextInt();
            scanner.nextLine();
            if (option != 1 && option != 2 && option != 3 && option != 4 && option != 5) utils.choose_valid();
        }

        help.clean();
        if (option == 1) {
            Exercise newexercise = new Exercise();
            System.out.println("Add Exercise");
            System.out.println("------------");
            System.out.print("Step 1 - Type: ");
            newexercise.setType(scanner.nextLine());
            help.clean();
            System.out.print("Step 2 - Id: ");
            String bug = scanner.nextLine();
            int id = Integer.parseInt(bug);
            newexercise.setId(id);
            help.clean();
            System.out.print("Step 3 - Muscle: ");
            newexercise.setMuscle(scanner.nextLine());
            help.clean();
            System.out.print("Step 4 - Name: ");
            newexercise.setName(scanner.nextLine());
            help.clean();
            System.out.print("Step 5 - Material needed: ");
            newexercise.setMaterial(scanner.nextLine());
            help.clean();
            System.out.print("Step 6 - Description: ");
            newexercise.setDescription(scanner.nextLine());
            help.clean();
            System.out.println("Step 6 - Is it hard? y/n");
            String answer = "";
            while (!(answer.equals("y") || answer.equals("Y") || answer.equals("n") || answer.equals("N"))) {
                answer = scanner.nextLine();
                if (answer.equals("y") || answer.equals("Y")) {
                    newexercise.setHard(true);
                } else if (answer.equals("n") || answer.equals("N")) {
                    newexercise.setHard(false);
                } else utils.choose_valid();
                help.clean();
            }


            try {
                Object obj = serializeexercises.getObject();
                if (obj instanceof Map) {
                    Map<Integer, Exercise> exercisesMap = (Map<Integer, Exercise>) obj;
                    exercisesMap.put(newexercise.getId(), newexercise);
                    serializeexercises.writeObject(exercisesMap);
                }
            } catch (EOFException e) {
                // Handle empty file
                Map<Integer, Exercise> exercisesMap = new HashMap<>();
                exercisesMap.put(newexercise.getId(), newexercise);
                try {
                    serializeexercises.writeObject(exercisesMap);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (option == 2){
            try {
                Object obj = serializeexercises.getObject();
                if (obj instanceof Map) {
                    Map<Integer, Exercise> exercisesMap = (Map<Integer, Exercise>) obj;
                    System.out.print("Insert the \"id\" of the exercise you want to delete: ");
                    exercisesMap.remove(scanner.nextInt());
                    serializeexercises.writeObject(exercisesMap);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (option == 3) {
            try {
                Object obj = serializeusers.getObject();
                if (obj instanceof Map) {
                    Map<String, User> usersMap = (Map<String, User>) obj;
                    System.out.print("Insert the \"Username\" of the user you want to delete: ");
                    usersMap.remove(scanner.nextLine());
                    serializeusers.writeObject(usersMap);
                    System.out.println("Username deleted.");
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else if (option == 4) {
            try {
                Object obj = serializeexercises.getObject();
                if (obj instanceof Map) {
                    Map<Integer, Exercise> exercisesMap = (Map<Integer, Exercise>) obj;
                    utils.print_list_of_exercises(exercisesMap);
                    System.out.println("1 - Go Back");
                    int choice = 0;
                    while (choice != 1) {
                        choice = scanner.nextInt();
                        if (choice != 1) utils.choose_valid();
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        new AppManipulation(user);
    }
}
