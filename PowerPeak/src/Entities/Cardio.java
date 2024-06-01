package Entities;

public class Cardio extends Exercise{

    public Cardio() {
    }

    public Cardio(int id, String name, String material, String description, boolean hard) {
        super(id, name, material, description, hard);
    }

    @Override
    public int calories_spent(User user, int durationMinutes, int heart_rate) {
        double baseCaloriesPerMinute = 20.0;
        double levelMultiplier = 1.0 + (user.getLevel_of_exercise() - 1) * 0.2;
        double heartRateMultiplier = (heart_rate - 70) / 100.0 + 1;
        double weight_multiplier = 1 + (double) (Integer.parseInt(user.getWeight()) - 70) /100;

        double caloriesBurnedPerMinute = baseCaloriesPerMinute * levelMultiplier * heartRateMultiplier * weight_multiplier;

        double totalCaloriesBurned = caloriesBurnedPerMinute * durationMinutes;

        return (int) Math.round(totalCaloriesBurned);
    }

    public String getType() {
        return "Cardio";
    }
}
