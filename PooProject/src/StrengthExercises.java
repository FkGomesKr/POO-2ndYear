import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class StrengthExercises {

    @SuppressWarnings("unchecked")
    public Exercise strengthExercises(User user) throws IOException, ClassNotFoundException {

        //IR BUSCAR AS FUNÇÕES
        HelpProgram help = new HelpProgram();
        Scanner scanner = new Scanner(System.in);
        Utils utils = new Utils();
        Serialization serialize = new Serialization();
        serialize.setFile("exercises.ser");
        Exercise chosenex = new Exercise();
        utils.strenght_exercises();

        int option = 0;
        while(option<1 || option>8) {
            option = scanner.nextInt();
            scanner.nextLine();
            if (option < 1 || option>8) utils.choose_valid();
        }

        //help.clean();
        Map<Integer, Exercise> exercisesMap = (Map<Integer, Exercise>) serialize.getObject();

        if(option == 1){//CHEST/////////////////////////////////////////////////////////////////////////
            utils.chest_exercises(exercisesMap);
            int optionChest = 0;
            while(exercisesMap.get(optionChest) == null) {
                optionChest = scanner.nextInt();
                scanner.nextLine();
                if (exercisesMap.get(optionChest) == null) utils.choose_valid();
            }

            chosenex = exercisesMap.get(optionChest);

            utils.strenght_extras(chosenex);

        }
        else if(option == 2){//SHOULDERS////////////////////////////////////////////////////////////////
            utils.shoulders_exercises(exercisesMap);
            int optioshoulder = 0;
            while(exercisesMap.get(optioshoulder) == null) {
                optioshoulder = scanner.nextInt();
                scanner.nextLine();
                if (exercisesMap.get(optioshoulder) == null) utils.choose_valid();
            }

            chosenex = exercisesMap.get(optioshoulder);

            utils.strenght_extras(chosenex);
        }
        else if(option == 3){//BICEP////////////////////////////////////////////////////////////////////
            utils.bicep_exercises(exercisesMap);
            int optionbicep = 0;
            while(exercisesMap.get(optionbicep) == null) {
                optionbicep = scanner.nextInt();
                scanner.nextLine();
                if (exercisesMap.get(optionbicep) == null) utils.choose_valid();
            }

            chosenex = exercisesMap.get(optionbicep);

            utils.strenght_extras(chosenex);
        }
        else if(option == 4){//TRICEP////////////////////////////////////////////////////////////////////
            utils.tricep_exercises(exercisesMap);
            int optiontricep = 0;
            while(exercisesMap.get(optiontricep) == null) {
                optiontricep = scanner.nextInt();
                scanner.nextLine();
                if (exercisesMap.get(optiontricep) == null) utils.choose_valid();
            }

            chosenex = exercisesMap.get(optiontricep);

            utils.strenght_extras(chosenex);
        }
        else if(option == 5){//LEGS/////////////////////////////////////////////////////////////////////
            utils.legs_exercises(exercisesMap);
            int optionleg = 0;
            while(exercisesMap.get(optionleg) == null) {
                optionleg = scanner.nextInt();
                scanner.nextLine();
                if (exercisesMap.get(optionleg) == null) utils.choose_valid();
            }

            chosenex = exercisesMap.get(optionleg);

            utils.strenght_extras(chosenex);
        }
        else if(option == 6){//BACK/////////////////////////////////////////////////////////////////////
            utils.back_exercises(exercisesMap);
            int optionback = 0;
            while(exercisesMap.get(optionback) == null) {
                optionback = scanner.nextInt();
                scanner.nextLine();
                if (exercisesMap.get(optionback) == null) utils.choose_valid();
            }

            chosenex = exercisesMap.get(optionback);

            utils.strenght_extras(chosenex);
        }
        else{//ABS//////////////////////////////////////////////////////////////////////////////////////
            utils.abs_exercises(exercisesMap);
            int optionabs = 0;
            while(exercisesMap.get(optionabs) == null) {
                optionabs = scanner.nextInt();
                scanner.nextLine();
                if (exercisesMap.get(optionabs) == null) utils.choose_valid();
            }

            chosenex = exercisesMap.get(optionabs);

            utils.strenght_extras(chosenex);
        }

        //GO BACK///////////////////////////////////////////////////////////////////////////////////////
        System.out.println("1 - Continue");
        int goback = 0;
        while(goback != 1) {
            goback = scanner.nextInt();
            if (goback != 1) utils.choose_valid();
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////
        return chosenex;
    }
}
