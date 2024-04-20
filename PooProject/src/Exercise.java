import java.io.Serializable;

public class Exercise implements Serializable {

    private String type;
    private int id;
    private String muscle;
    private String name;
    private String material;
    private String description;
    private boolean hard;

    public Exercise() {
    }

    public Exercise(String type, int id, String muscle, String name, String material, String description, boolean hard) {
        this.type = type;
        this.id = id;
        this.muscle = muscle;
        this.name = name;
        this.material = material;
        this.description = description;
        this.hard = false;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMuscle() {
        return muscle;
    }

    public void setMuscle(String muscle) {
        this.muscle = muscle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHard() {
        return hard;
    }

    public void setHard(boolean hard) {
        this.hard = hard;
    }
}
