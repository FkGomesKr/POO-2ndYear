import java.io.Serializable;

public class Plan implements Serializable {

    private PlanofDay[] plan;
    private int[] days;

    public Plan() {
        this.plan = new PlanofDay[8];
    }

    public PlanofDay getPlanofDay(int day) {
        return plan[day];
    }

    public void setPlanofDay(PlanofDay planofDay, int day) {
        this.plan[day] = planofDay;
    }
}
