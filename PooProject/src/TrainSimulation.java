import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class TrainSimulation {

    @SuppressWarnings("unchecked")
    public TrainSimulation(User user) throws IOException, ClassNotFoundException {

        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        Historic userHistory = new Historic();
        Serialization serialize = new Serialization();
        serialize.setFile("exercises.ser");
        Exercise chosenex = new Exercise();
        Utils utils = new Utils();

        System.out.println("--------------------------------");
        System.out.println("Choose what you are going to do:");
        System.out.println("1 - Training Plan");
        System.out.println("2 - Singular Training");
        System.out.println("3 - Go back");
        System.out.println("--------------------------------");
        while (choice != 1 && choice != 2 && choice != 3) {
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice != 1 && choice != 2 && choice != 3) System.out.println("Please choose a valid option.");
        }

        if (choice == 1) {

            if(user.getTrainingPlan() != null) {
                System.out.print("Days to advance: ");
                int time_advance = 0;
                while (time_advance < 1) {
                    time_advance = scanner.nextInt();
                    scanner.nextLine();
                }

                int calories;
                Plan training_plan = user.getTrainingPlan();
                // boolean cardio = training_plan.getHas_cardio();
                //if (cardio) calories = calories_burnt(user, time_advance, 1);
                //else calories = calories_burnt(user, time_advance, 0);

                int not_training = rand.nextInt(100);
                if (not_training < 10) {
                    System.out.println("----------------------------------------------------------------------");
                    System.out.println("It seems you didn´t train in the last " + time_advance + " days you only burnt " + (time_advance * 50) + " calories");
                    System.out.println("----------------------------------------------------------------------");
                } else {
                    System.out.println("------------------------------------------------------------------------------");
                    System.out.println("Congratulations " + user.getUserId() + " for following the training plan");
                    //  System.out.println("You burnt " + calories + " calories in " + time_advance + " day(s). Very nice!");
                    System.out.println("------------------------------------------------------------------------------");
                }

                user.setDate(user.getDate().plusDays(time_advance));
                if ((LocalDate.now().until(user.getDate(), ChronoUnit.DAYS) > 180))if (user.getGym_level().equals("Amateur")) user.setGym_level("Ocasional Exercise");
                else if ((LocalDate.now().until(user.getDate(), ChronoUnit.DAYS) > 500))user.setGym_level("Professional");

                System.out.println("1 - Go back");
                int go = 0;
                while (go != 1) {
                    go = scanner.nextInt();
                    scanner.nextLine();
                    if (go != 1) System.out.println("Please choose a valid option.");
                }
                new AppInterface(user);
            }
            else {
                System.out.println("You don't have a training plan yet.");
                System.out.println("1 - Go back");
                int go = 0;
                while (go != 1) {
                    go = scanner.nextInt();
                    scanner.nextLine();
                    if (go != 1) System.out.println("Please choose a valid option.");
                    new AppInterface(user);
                }
            }
        }
        else if (choice == 2) {
            int time = 0;
            int reps = 0;
            int option = 0;

            System.out.println("-------------------------------------");
            System.out.println("How many exercises will you be doing:");
            System.out.println("-------------------------------------");
            int exercises = scanner.nextInt();
            scanner.nextLine();
            Exercise[] theExercises = new Exercise[exercises];
            for (int i = 0; i < exercises; i++){
                option = 100;
                System.out.println("-------------------------------------");
                System.out.println("Exercise " + (i+1) + " ");
                System.out.println("-------------------------------------");
                Map<Integer, Exercise> exercisesMap = (Map<Integer, Exercise>) serialize.getObject();
                while(exercisesMap.get(option) == null) {
                    option = scanner.nextInt();
                    scanner.nextLine();
                    if (exercisesMap.get(option) == null) utils.choose_valid();
                }
                chosenex = exercisesMap.get(option);
                theExercises[i] = chosenex;
                option = 0;
                System.out.println("-------------------------------------");
                System.out.println("For how long will you be doing it:");
                System.out.println("-------------------------------------");
                while (option < 1 || option > 60) {
                    option = scanner.nextInt();
                    scanner.nextLine();
                }
                time += option;
                option = 0;
                System.out.println("-------------------------------------");
                System.out.println("How many reps:");
                System.out.println("-------------------------------------");
                while (option < 1 || option > 36) {
                    option = scanner.nextInt();
                    scanner.nextLine();
                }
                reps += option;
            }

            userHistory.setExercises(theExercises);
            userHistory.setDate(user.getDate());
            userHistory.setTime(time);
            userHistory.setTotal_reps(reps);
            user.setDate(user.getDate().plusDays(1));
            user.setHistoricsLine(userHistory);

            System.out.println("------------------------------------------------------------------------------");
            System.out.println("Congratulations " + user.getUserId() + " you burned some calories :D");
            System.out.println("------------------------------------------------------------------------------");

            choice = 0;
            System.out.println("1 - Continue");
            while(choice != 1) {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice != 1) utils.choose_valid();
            }
            new AppInterface(user);

        }
        else{
            new AppInterface(user);
        }
    }


    int calories_burnt(User user, int time_advance, int cardio) {

        int calories = 0;
        String level1 = "Amateur";
        String level2 = "Ocasional Exercise";
        String level3 = "Professional";
        String gym_level = user.getGym_level();

        if (gym_level.equals(level1)) {
            calories = (200 + cardio * 100) * time_advance;
        } else if (gym_level.equals(level2)) {
            calories = (250 + cardio * 100) * time_advance;
        } else {
            calories = (300 + cardio * 100) * time_advance;
        }
        return calories;
    }

}
