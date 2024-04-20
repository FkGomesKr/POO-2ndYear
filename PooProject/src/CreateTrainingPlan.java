import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.util.Random;

public class CreateTrainingPlan {

    HelpProgram help = new HelpProgram();
    Random random = new Random();
    Scanner scanner = new Scanner(System.in);
    Utils utils = new Utils();

    @SuppressWarnings("unchecked")
    void createPlan(User user) throws IOException, ClassNotFoundException {

        //IR BUSCAR AS FUNÇÕES
        help.clean();
        Plan userplan = new Plan();
        StrengthExercises se = new StrengthExercises();
        Serialization ser = new Serialization();
        ser.setFile("users.ser");
        Map<String, User> usersMap = (Map<String, User>) ser.getObject();

        System.out.println("--------------------------------------------------------------");
        System.out.println("Welcome.How many days a week would you like to train?");
        System.out.println("--------------------------------------------------------------");
        User test = usersMap.get(user.getUserId());
        int number_days = 0;
        while (number_days < 1 || number_days > 7) {
            number_days = scanner.nextInt();
            scanner.nextLine();
            if (number_days < 1 || number_days > 7) System.out.println("Please choose a valid option.");
        }

        int[] days = new int[number_days];

        //help.clean();

        System.out.println("Please choose the days.");
        System.out.println("--------------------------------------------------------------");
        System.out.println("1 - Monday  2 - Tuesday   3 - Wednesday   4 - Thursday");
        System.out.println("5 - Friday  6 - Saturday  7 - Sunday ");
        System.out.println("--------------------------------------------------------------");

        int c = 0;
        int option;
        while (c < number_days) {
            option = 0;
            while (option < 1 || option > 7 || help.isNumberInArray(days, option)) {
                option = scanner.nextInt();
                scanner.nextLine();
                if (option < 1 || option > 7 || help.isNumberInArray(days, option))
                    System.out.println("Please choose a valid option.");
            }
            days[c] = option;
            c++;
        }

        for (int i = 0; i < number_days; i++) {
            String day;
            PlanofDay planofDay = new PlanofDay();
            if (days[i] == 1) day = "Monday";
            else if(days[i]==2) day = "Tuesday";
            else if (days[i]==3) day = "Wednesday";
            else if (days[i]==4) day = "Thursday";
            else if (days[i]==5) day = "Friday";
            else if (days[i]==6) day = "Saturday";
            else day = "Sunday";
            System.out.println("--------------------------------------------------------------");
            System.out.println("Welcome. How many exercises would you like to have on " + day + "?");
            System.out.println("Please choose at least 1 exercises per day");
            System.out.println("--------------------------------------------------------------");
            int number = 0;
            while (number != 1 && number != 2 && number != 3 && number != 4 && number != 5 && number != 6) {
                number = scanner.nextInt();
                if (number != 1 && number != 2 && number != 3 && number != 4 && number != 5 && number != 6) utils.choose_valid();
            }

            for (int j = 0; j < number; j++) {
                utils.choose_strenght_activities();
                int option1 = 0;
                while(option1 != 1 && option1 != 2) {
                    option1 = scanner.nextInt();
                    if (option1 != 1 && option1 !=2) utils.choose_valid();
                }

                if (option1 == 1) {
                    Exercise chosenex = se.strengthExercises(user);
                    planofDay.setExerciseofPlanofDay(chosenex);
                }
                else new CardioActivities(user);
            }
            userplan.setPlanofDay(planofDay, days[i]);

        }

        user.setTrainingPlan(userplan);
        usersMap.remove(user.getUserId());
        usersMap.put(user.getUserId(), user);
        //debugSystem.out.println(user.getTrainingPlan().getPlanofDay(5).getExerciseofPlanofDay(0).getName());
        //debugSystem.out.println(user.getName() + "  oi  " + user.getTrainingPlan().getPlanofDay(4).getExerciseofPlanofDay(0).getName());
        ser.writeObject(usersMap);

        System.out.println("---------------------------------------------------------------");
        System.out.println("Training plan created successfully.You can check it in My Plan");
        System.out.println("---------------------------------------------------------------");

        //GO BACK///////////////////////////////////////////////////////////////////////////////////////
        System.out.println("1- Return");
        int goback = 0;
        while (goback != 1) {
            goback = scanner.nextInt();
            scanner.nextLine();
            if (goback != 1) System.out.println("Please choose a valid option.");
        }
        new TrainingPlanInterface(user);
    }

    void getPlan(User user) throws IOException, ClassNotFoundException {
/*
        help.clean();

        System.out.println("--------------------------------------------------------------");
        System.out.println("Welcome.How many days a week would you like to train?");
        System.out.println("--------------------------------------------------------------");

        int number_days = 0;
        while(number_days < 1 || number_days > 7) {
            number_days = scanner.nextInt();
            scanner.nextLine();
            if (number_days < 1 || number_days > 7) System.out.println("Please choose a valid option.");
        }

        int[] days = new int[number_days];

        //help.clean();

        System.out.println("Please choose the days.");
        System.out.println("--------------------------------------------------------------");
        System.out.println("1 - Monday  2 - Tuesday   3 - Wednesday   4 - Thursday");
        System.out.println("5 - Friday  6 - Saturday  7 - Sunday ");
        System.out.println("--------------------------------------------------------------");

        int c = 0;
        int option;
        while (c < number_days){
            option = 0;
            while(option < 1 || option > 7 || help.isNumberInArray(days,option)) {
                option = scanner.nextInt();
                scanner.nextLine();
                if (option < 1 || option > 7 || help.isNumberInArray(days, option))
                    System.out.println("Please choose a valid option.");
            }
            days[c] = option;
            c++;
        }

        System.out.println("------------------------------------------------------------------");
        System.out.println("Welcome.What kind of plan do you prefer?");
        System.out.println("------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------");
        System.out.println("1 - Strength Gain 2 - Better Health");
        System.out.println("------------------------------------------------------------------");

        int optionY = 0;
        while(optionY != 1 && optionY !=2) {
            optionY = scanner.nextInt();
            scanner.nextLine();
            if (optionY != 1 && optionY != 2) System.out.println("Please choose a valid number.");
        }

        int number_exercises;
        if (number_days == 1) number_exercises = 7;
        else if(number_days == 2) number_exercises = 10;
        else if(number_days == 3) number_exercises = 12;
        else if(number_days == 4) number_exercises = 15;
        else number_exercises = 17;

        int[] exercises = new int[number_exercises];

        if (optionY == 1){
            if (number_days == 1){
                exercises[0] = random.nextInt(9)+1;//PEITO
                exercises[1] = random.nextInt(9)+1;//PEITO
                exercises[2] = random.nextInt(7)+32;//COSTAS
                exercises[3] = random.nextInt(8)+24;//PERNAS
                exercises[4] = random.nextInt(5)+15;//BICEP
                exercises[5] = random.nextInt(4)+20;//TRICEP
                exercises[6] = random.nextInt(5)+10;//OMBRO
            }
            else if (number_days == 2){
                System.out.println("Coming soon");
            }
            else if (number_days == 3){
                System.out.println("Coming soon");
            }
            else if (number_days == 4){
                System.out.println("Coming soon");
            }
            else{
                System.out.println("Coming soon");
            }
        }
        else{
            System.out.println("Coming soon");
        }


        Plan plan = new Plan(exercises,days,number_days,false);
        user.setTrainingPlan(plan);

        System.out.println("---------------------------------------------------------------");
        System.out.println("Training plan created successfully.You can check it in My Plan");
        System.out.println("---------------------------------------------------------------");

        //GO BACK///////////////////////////////////////////////////////////////////////////////////////
        System.out.println("1- Return");
        int goback = 0;
        while (goback != 1) {
            goback = scanner.nextInt();
            scanner.nextLine();
            if (goback != 1) System.out.println("Please choose a valid option.");
        }
        new TrainingPlanInterface(user);
    }*/
    }
}
