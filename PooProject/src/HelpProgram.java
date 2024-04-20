import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelpProgram {

    public void clean(){
        //LIMPAR A CONSOLA
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public boolean validateEmail(String email) {
        String EMAIL_REGEX =
                "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isValidNumber(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isNumberInArray(int[] array, int number) {
        for (int num : array) {
            if (num == number) {
                return true;
            }
        }
        return false;
    }

    public boolean isAdmin(User user) {
        return user.getUserId().equals("GomesAdmin") || user.getUserId().equals("PedroAdmin") || user.getUserId().equals("JoãoNunoAdmin");
    }
}
