import java.time.LocalDate;

public class Historic {

    private LocalDate date;
    private Exercise[] exercises;
    private PlanofDay planofDay;
    private int total_reps;
    private int time;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Exercise[] getExercises() {
        return exercises;
    }

    public void setExercises(Exercise[] exercises) {
        this.exercises = exercises;
    }

    public PlanofDay getPlanofDay() {
        return planofDay;
    }

    public void setPlanofDay(PlanofDay planofDay) {
        this.planofDay = planofDay;
    }

    public int getTotal_reps() {
        return total_reps;
    }

    public void setTotal_reps(int total_reps) {
        this.total_reps = total_reps;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
